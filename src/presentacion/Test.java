package presentacion;

import java.util.Scanner;
import negocio.*;

public class Test {
    public static void main(String[] args) {
        int opcion = -1;
        var scanner = new Scanner(System.in);
        ICatalogoRecipiente catalogo = new CatalogoRecipiente();

        while (opcion != 0) {
            System.out.println("Elige una opcion: \n"
                    + "1. Iniciar catalago de recipientes\n"
                    + "2. Agregar recipiente\n"
                    + "3. Listar recipientes\n"
                    + "4. Enviar correo\n"
                    + "0. Salir"
            );
            opcion = Integer.parseInt(scanner.nextLine());
            
            switch (opcion) {
                case 1:
                    catalogo.iniciarArchivo();
                    break;
                case 2:
                    System.out.println("Escriba el correo del recipiente:");
                    var correoRecipiente = scanner.nextLine();
                    catalogo.agregarRecipiente(correoRecipiente);
                    
                    break;
                case 3:
                    catalogo.listarRecipientes();
                    break;
                case 4:
                    catalogo.enviarEmailRecipientes();
                    break;
                case 0:
                    System.out.println("Hasta Pronto!");
                    break;
                default:
                    System.out.println("Error, Opcion no reconocida");
                    break;
            }
        }
    }
}
