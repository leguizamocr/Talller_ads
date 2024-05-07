import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) throws IOException {
        int opc_menuInicial = 0;
        List<Usuario_base> usuarios = new ArrayList<>();
        Scanner scn = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Singleton_BaseDatos.getInstance();
        while (opc_menuInicial != 4) {
            System.out.println("***** MENÚ *****");
            System.out.println("1. Crear Usuario");
            System.out.println("2. Admin");
            System.out.println("3. Estudiante o profesor");
            System.out.println("4. Salir");
            System.out.print("Digite su opción: ");
            opc_menuInicial = scn.nextInt();
            switch (opc_menuInicial) {
                case 1:
                    Usuario_base x ;
                    String nombre;
                    int edad, tipo;
                    long cedula;
                    System.out.println("Tenga en cuenta la siguiente información: ");
                    System.out.println("    Tipo 1 -> Admin");
                    System.out.println("    Tipo 2 -> Profesor");
                    System.out.println("    Tipo 3 -> Estudiante");
                    System.out.println();
                    System.out.print("Nombre: ");
                    nombre = reader.readLine();
                    System.out.print("Edad: ");
                    edad = Integer.parseInt(reader.readLine());
                    System.out.print("cedula: ");
                    cedula = Long.parseLong(reader.readLine());
                    System.out.print("Tipo: ");
                    tipo = Integer.parseInt(reader.readLine());
                    if(tipo == 1 || tipo == 2 || tipo == 3){
                        x = Fabrica_usuarios.crear_usuario(tipo, nombre, edad, cedula);
                        usuarios.add(x);
                    }else{
                        System.out.println("Tipo no valido");
                    }
                    break;
                case 2:
                    long cedula_admin;
                    Usuario_base adminBase = null;
                    boolean existe = false;
                    System.out.print("Ingrese su cédula: ");
                    cedula_admin = Long.parseLong(reader.readLine());
                    for (Usuario_base u : usuarios) {
                        if(u.getCedula() == cedula_admin && u instanceof Admin){
                            existe = true;
                            adminBase = u;
                        }
                    }
                    if(existe){
                        int opc_admins = 0;
                        while (opc_admins != 3) {
                            System.out.println("***MENU ADMINS***");
                            System.out.println("1. Añadir libro");
                            System.out.println("2. Eliminar libro");
                            System.out.println("3. Salir");
                            System.out.print("Digite su opción:");
                            opc_admins = Integer.parseInt(reader.readLine());
                            switch (opc_admins) {
                                case 1:
                                    String titulo, autor, genero;
                                    int num_pags;
                                    System.out.print("Autor: ");
                                    autor = reader.readLine();
                                    System.out.print("Titulo: ");
                                    titulo = reader.readLine();
                                    System.out.print("Genero: ");
                                    genero = reader.readLine();
                                    System.out.print("Número de páginas: ");
                                    num_pags = Integer.parseInt(reader.readLine());
                                    ((Admin)adminBase).anadir_libro(titulo, genero, autor, num_pags, true);
                                    break;
                                case 2:
                                    int id_eliminar;
                                    Singleton_BaseDatos.getInstance().mostrar_libros();
                                    System.out.print("Digite el id del libro a eliminar: ");
                                    id_eliminar = Integer.parseInt(reader.readLine());
                                    Singleton_BaseDatos.getInstance().eliminar_libro_id(id_eliminar);
                                    break;
                                case 3:

                                    break;
                                default:
                                    System.out.println("Opción no valida");
                                    break;
                            }
                        }
                        }else{
                            System.out.println("No se encontró la cédula registrada");
                    }
                    break;
                case 3:
                    Usuario_base y = null;
                    long cedula_pe;
                    boolean existe_pe = false;
                    System.out.print("Digite la cédula: ");
                    cedula_pe = Long.parseLong(reader.readLine());
                    for (Usuario_base usuario_base : usuarios) {
                        if (usuario_base.getCedula() == cedula_pe) {
                            y = usuario_base;
                            existe_pe = true;
                        }
                    }
                    if (y instanceof Estudiante){
                        y = new Estudiante();
                    }else{
                        y = new Profesor();
                    }
                    if(existe_pe){
                        CareTaker careTaker = new CareTaker(new Originator());
                        String comando = "iniciar";
                        String comando_anterior = "";
                        int id = -1;
                        Invocador cmd = new Invocador();
                        while (!comando.equals("salir")) {
                            comando_anterior = comando;
                            String aux ="";
                            System.out.print("$ ");
                            comando = reader.readLine();
                            String[] p = comando.split("\\D+");
                            for (String string : p) {
                                aux = aux+  string;
                            }
                            System.out.println(aux);
                            try{
                                id = Integer.parseInt(aux);
                            }catch(Exception e){

                            }
                            if(comando.equals("libro " + id + " reservar")){
                                System.out.println("holaaa");
                                cmd.setComando(new Comando_reservar()); 
                                Comando_reservar.x = y;
                                cmd.ejecutar(id);
                                careTaker.guardarEstado();
                            }else if(comando.equals("libro "+id+" devolver")){
                                cmd.setComando(new Comando_devolver());
                                Comando_devolver.x = y;
                                cmd.ejecutar(id);
                                careTaker.guardarEstado();
                            }else if(comando.equals("libro "+id+" disponibilidad")){
                                cmd.setComando(new Comando_disponibilidad());
                                Comando_disponibilidad.x = y;
                                cmd.ejecutar(id);
                                careTaker.guardarEstado();
                            }else if(comando.equals("rehacer comando anterior")){
                                if(comando_anterior.equals("libro " + id + " reservar")){
                                    careTaker.rehacer();
                                }else if(comando_anterior.equals("libro "+id+" devolver")){
                                    careTaker.rehacer();
                                }else if(comando_anterior.equals("libro "+id+" disponibilidad")){
                                    careTaker.rehacer();
                            } else if(comando.equals("mostrar")){
                                if(y instanceof Estudiante){
                                    for(Libro l: ((Estudiante)y).getLibros_reservados()){
                                        System.out.println(l.getTitulo());
                                    }
                                }else{
                                    for (Libro libro : ((Profesor)y).getLibros_reservados()) {
                                        System.out.println(libro.getTitulo());
                                    }
                                }
                            }
                        }
                    }
                   
                    break;
            }
        }
        
    }
}
}
