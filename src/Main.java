import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n===== SISTEMA DE BIBLIOTECA =====");
            System.out.println("1. Agregar libro");
            System.out.println("2. Listar libros");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Agregar usuario");
            System.out.println("6. Listar usuarios");
            System.out.println("7. Guardar y salir");
            System.out.print("Opción: ");
            option = sc.nextInt();
            sc.nextLine();

            switch(option) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    biblioteca.agregarLibro(titulo, autor);
                    break;
                case 2:
                    biblioteca.listarLibros();
                    break;
                case 3:
                    biblioteca.listarLibros();
                    System.out.print("Número de libro a prestar: ");
                    int pIndex = sc.nextInt() - 1;
                    biblioteca.prestarLibro(pIndex);
                    break;
                case 4:
                    biblioteca.listarLibros();
                    System.out.print("Número de libro a devolver: ");
                    int dIndex = sc.nextInt() - 1;
                    biblioteca.devolverLibro(dIndex);
                    break;
                case 5:
                    System.out.print("ID de usuario: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    biblioteca.agregarUsuario(id, nombre);
                    break;
                case 6:
                    biblioteca.listarUsuarios();
                    break;
                case 7:
                    biblioteca.guardarTodo();
                    System.out.println("✅ Datos guardados. Hasta luego!");
                    break;
                default:
                    System.out.println("❌ Opción inválida.");
            }
        } while(option != 7);

        sc.close();
    }
}