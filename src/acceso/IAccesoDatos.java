package acceso;

import domain.Recipiente;
import excepciones.*;
import java.util.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;

public interface IAccesoDatos {
    String MI_CORREO = "Tu Correo";
    String PSSWRD = "Contrasenia de tu cuenta Google";
    
    boolean existe(String nombreArchivo) throws AccesoDatosEx;
    List<Recipiente> lista(String nombreArchivo) throws LecturaDatosEx;
    void escribir (Recipiente recipiente, String nombreArchivo, boolean anexar)throws AccesoDatosEx;
    void crear(String nombreArchivo) throws AccesoDatosEx;
    void borrar(String nombreArchivo) throws AccesoDatosEx;
    
    void enviarEmail(String nombreArchivo) throws MessagingException;
    Message prepararMensaje(Session session, String miCuenta, String recipiente);
           
}
