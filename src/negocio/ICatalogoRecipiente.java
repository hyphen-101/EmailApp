package negocio;

public interface ICatalogoRecipiente {
    //Colocar el la direccion en donde se almacena el archivo de texto en NOMBRE_ARCHIVO
    String NOMBRE_ARCHIVO = "recipientes.txt";
    void agregarRecipiente(String nombreRecipiente);
    void listarRecipientes();
    //void buscarPelicula(String buscar);
    void enviarEmailRecipientes();
    void iniciarArchivo();
}
