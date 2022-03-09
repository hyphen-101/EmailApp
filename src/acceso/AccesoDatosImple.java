package acceso;


import domain.Recipiente;
import excepciones.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;


public class AccesoDatosImple implements IAccesoDatos {

    @Override
    public boolean existe(String nombreArchivo) {
        var archivo = new File(nombreArchivo);
        return archivo.exists();
    }

    @Override
    public List<Recipiente> lista(String nombreArchivo) throws LecturaDatosEx {
        var archivo = new File(nombreArchivo);
        List<Recipiente> recipinetesList = new ArrayList<>();
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            while (linea != null) {
                Recipiente recipiente = new Recipiente(linea);
                recipinetesList.add(recipiente);
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Error al leer recipientes de archivo" + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error al leer recipientes de archivo" + ex.getMessage());
        }

        return recipinetesList;
    }

    @Override
    public void escribir(Recipiente recipiente, String nombreArchivo, boolean anexar) throws AccesoDatosEx {
        var archivo = new File(nombreArchivo);
        try {
            var entrada = new PrintWriter(new FileWriter(archivo, anexar));
            entrada.println(recipiente.toString());
            System.out.println("Recipinete incluido con exito");
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Error al escribir recipientes de archivo" + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error al escribir recipientes de archivo I/O" + ex.getMessage());
        }

    }

    @Override
    public void crear(String nombreArchivo) throws AccesoDatosEx {
        var archivo = new File(nombreArchivo);
        try {
            var entrada = new FileWriter(archivo);
            entrada.close();
            System.out.println("Se ha creado un archivo");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error al escribir archivo" + ex.getMessage());
        }
    }

    @Override
    public void borrar(String nombreArchivo) throws AccesoDatosEx {
        var archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            archivo.delete();
            System.out.println("se ha borrado el archivo con exito");
        }
    }

    @Override
    public void enviarEmail(String nombreArchivo) throws MessagingException {
        System.out.println("Preparando para enviar mensaje");
        Properties propiedades = new Properties();

        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.mime.address.strict", "false");

        Session session = Session.getDefaultInstance(propiedades, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MI_CORREO, PSSWRD);
            }
        });
        var archivo = new File(nombreArchivo);

        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            //No estaria de mas agregar contador de mensajes....
            while (linea != null) {
                Message mensaje = prepararMensaje(session, MI_CORREO, linea);
                Transport.send(mensaje);
                System.out.println("Mensaje enviado satisfactoriamenete");
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AccesoDatosImple.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AccesoDatosImple.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Message prepararMensaje(Session session, String miCuenta, String recipiente) {
        
        try {
            //obj MimeMessage
            Message mensaje = new MimeMessage(session);
            // Correo de el que emite el mensaje
            mensaje.setFrom(new InternetAddress(miCuenta));
            //Recipiente (Quien recibe el mensaje)
            InternetAddress address = new InternetAddress(recipiente);
            mensaje.setRecipient(Message.RecipientType.TO, address);
            //tema del mensaje
            mensaje.setSubject("Mensjae desde la App EmailGroupJava");
            //creando el primer MimeBodypart
            //mensaje.setText("Miren mi mensaje \n Esta super cool");
            BodyPart cuerpoDeMensaje1 = new MimeBodyPart();
            cuerpoDeMensaje1.setText("Este es el texto del mensaje\n Mensaje de archivo adjunto");
            
            //Segunda parte del cuerpo del correo (Archivo adjunto)
            BodyPart cuerpoDeMensaje2 = new MimeBodyPart();
            //Colocar la direccion del archivo que se desea adjuntar
            String nombreArchivo = "C:\\";
            DataSource fuente = new FileDataSource(nombreArchivo);
            cuerpoDeMensaje2.setDataHandler(new DataHandler(fuente));
            cuerpoDeMensaje2.setFileName(nombreArchivo);
            
            //Creando el objeto MultiPartes
            Multipart objMulti = new MimeMultipart();
            objMulti.addBodyPart(cuerpoDeMensaje1);
            objMulti.addBodyPart(cuerpoDeMensaje2);
            // agregar el obj Multiparte a la variable mensaje
            mensaje.setContent(objMulti);
            
            return mensaje;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            System.out.println("Excepcion de mensaje" + ex.getMessage());
        }
        return null;
    }

}
