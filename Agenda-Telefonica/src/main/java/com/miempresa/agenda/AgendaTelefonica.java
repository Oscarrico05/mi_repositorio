import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AgendaTelefonica {

    private static final String FICHERO = "agenda.dat";  // Archivo donde se guarda la agenda

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Contacto> agenda = cargarAgenda();  // Cargar la agenda desde el fichero

        while (true) {
            System.out.println("\n===== AGENDA TELEFÓNICA =====");
            System.out.println("1. Añadir nuevo contacto");
            System.out.println("2. Eliminar contacto");
            System.out.println("3. Mostrar agenda");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    añadirContacto(agenda, scanner);
                    break;
                case 2:
                    eliminarContacto(agenda, scanner);
                    break;
                case 3:
                    mostrarAgenda(agenda);
                    break;
                case 4:
                    guardarAgenda(agenda);
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void añadirContacto(List<Contacto> agenda, Scanner scanner) {
        System.out.print("Ingrese el nombre del contacto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el teléfono del contacto: ");
        String telefono = scanner.nextLine();

        agenda.add(new Contacto(nombre, telefono));
        System.out.println("Contacto añadido.");
    }

    private static void eliminarContacto(List<Contacto> agenda, Scanner scanner) {
        System.out.print("Ingrese el nombre del contacto a eliminar: ");
        String nombre = scanner.nextLine();
        boolean eliminado = false;

        for (int i = 0; i < agenda.size(); i++) {
            if (agenda.get(i).getNombre().equalsIgnoreCase(nombre)) {
                agenda.remove(i);
                eliminado = true;
                break;
            }
        }

        if (eliminado) {
            System.out.println("Contacto eliminado.");
        } else {
            System.out.println("No se encontró el contacto.");
        }
    }

    private static void mostrarAgenda(List<Contacto> agenda) {
        if (agenda.isEmpty()) {
            System.out.println("La agenda está vacía.");
        } else {
            System.out.println("\nAgenda:");
            for (Contacto contacto : agenda) {
                System.out.println(contacto);
            }
        }
    }

    private static void guardarAgenda(List<Contacto> agenda) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO))) {
            oos.writeObject(agenda);
        } catch (IOException e) {
            System.out.println("Error al guardar la agenda: " + e.getMessage());
        }
    }

    private static List<Contacto> cargarAgenda() {
        File fichero = new File(FICHERO);
        if (!fichero.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            return (List<Contacto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar la agenda: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
