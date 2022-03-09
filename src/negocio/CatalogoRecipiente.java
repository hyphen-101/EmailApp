package negocio;

import acceso.*;
import domain.Recipiente;
import excepciones.AccesoDatosEx;
import excepciones.LecturaDatosEx;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;


public class CatalogoRecipiente implements ICatalogoRecipiente{
    
    private IAccesoDatos datos;
    
    public CatalogoRecipiente() {
        this.datos = new AccesoDatosImple();
    }

    @Override
    public void agregarRecipiente(String nombreRecipiente) {
        var recipiente = new Recipiente(nombreRecipiente);
        boolean anexar = false;
        try {
            anexar = this.datos.existe(NOMBRE_ARCHIVO);
            this.datos.escribir(recipiente, NOMBRE_ARCHIVO, anexar);
            System.out.println("Se ha escrito un nuevo recipiente en lista");
        } catch (AccesoDatosEx ex) {
            ex.printStackTrace(System.out);
            System.out.println("Se produjo un Error al escribir ");
        }
    }

    @Override
    public void listarRecipientes() {
        try {
            var listado = this.datos.lista(NOMBRE_ARCHIVO);
            for(var recipiente : listado){
                System.out.println(recipiente);
            };
        } catch (LecturaDatosEx ex) {
            ex.printStackTrace(System.out);
            System.out.println("Se produjo un Error al listar ");
        }
    }

    @Override
    public void enviarEmailRecipientes() {
        try {
            this.datos.enviarEmail(NOMBRE_ARCHIVO);
        } catch (MessagingException ex) {
            Logger.getLogger(CatalogoRecipiente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void iniciarArchivo() {
        try {
            if (this.datos.existe(NOMBRE_ARCHIVO)) {
                this.datos.borrar(NOMBRE_ARCHIVO);
                this.datos.crear(NOMBRE_ARCHIVO);
            } else {
                this.datos.crear(NOMBRE_ARCHIVO);
            }
        } catch (AccesoDatosEx ex) {
            ex.getMessage();
            System.out.println("Error al crear archivo");  
        }
    }
    
}
