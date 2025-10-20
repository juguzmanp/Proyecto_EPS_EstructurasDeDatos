import java.util.*;

public class SistemaEPS {

    private Scanner sc;
    private ArrayList<Usuario> usuarios;
    private Queue<Usuario> colaCitas;
    private PriorityQueue<UsuarioUrgencias> colaUrgencias;

    public SistemaEPS() {
        usuarios = new ArrayList<>();
        colaCitas = new LinkedList<>();
        colaUrgencias = new PriorityQueue<>(Comparator.comparingInt(UsuarioUrgencias::getNivelTriage));
        sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Solicitar cita");
            System.out.println("3. Solicitar atencion urgencias");
            System.out.println("4. Ver colas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> solicitarCita();
                case 3 -> solicitarUrgencia();
                case 4 -> mostrarColas();
                default -> System.out.println("Opcion no valida");
            }
        } while (opcion != 5);
    }

    private void registrarUsuario() {
        System.out.println("Tipo de documento: ");
        System.out.println("1. TI\n2. CC\n3. CE");
        int tipo = sc.nextInt();
        sc.nextLine();
        String tipoDoc = switch (tipo) {
            case 1 -> "TI";
            case 2 -> "CC";
            case 3 -> "CE";
            default -> "Desconocido";
        };

        System.out.print("Numero de documento: ");
        String numDoc = sc.nextLine();

        System.out.print("Nombres: ");
        String nombres = sc.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();

        System.out.print("Edad: ");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.println("Sexo:\n1. Masculino\n2. Femenino");
        int sex = sc.nextInt();
        sc.nextLine();
        String sexo = (sex == 1) ? "Masculino" : "Femenino";

        Usuario u = new Usuario(tipoDoc, numDoc, nombres, apellidos, edad, sexo);
        usuarios.add(u);
        System.out.println("Usuario registrado correctamente.");
    }

    private Usuario buscarUsuarioPorDocumento(String doc) {
        for (Usuario u : usuarios) {
            if (u.getNumeroDocumento().equals(doc)) return u;
        }
        return null;
    }

    private void solicitarCita() {
        System.out.print("Ingrese numero de documento: ");
        String doc = sc.nextLine();
        Usuario u = buscarUsuarioPorDocumento(doc);
        if (u != null) {
            colaCitas.add(u);
            System.out.println("Usuario agregado a la cola de citas.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private void solicitarUrgencia() {
        System.out.print("Ingrese numero de documento: ");
        String doc = sc.nextLine();
        Usuario u = buscarUsuarioPorDocumento(doc);
        if (u != null) {
            System.out.print("Ingrese nivel de urgencia (1=Alta prioridad, 5=Baja): ");
            int prioridad = sc.nextInt();
            sc.nextLine();
            colaUrgencias.add(new UsuarioUrgencias(u, prioridad));
            System.out.println("Usuario agregado a la cola de urgencias con prioridad " + prioridad);
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private void mostrarColas() {
        System.out.println("\n--- Cola de citas ---");
        for (Usuario u : colaCitas)
            System.out.println(u);
        System.out.println("\n--- Cola de urgencias ---");
        for (UsuarioUrgencias uu : colaUrgencias)
            System.out.println(uu.getUsuario() + " - Prioridad " + uu.getNivelTriage());
    }

}
