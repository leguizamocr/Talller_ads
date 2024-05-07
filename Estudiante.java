import java.util.ArrayList;
import java.util.List;

public class Estudiante extends Usuario_base implements Permisos_estudiante_profesor {
    public static final int tipo_usuario = 3;
    private List<Libro> libros_reservados;

    public void setLibros_reservados(List<Libro> libros_reservados) {
        this.libros_reservados = libros_reservados;
    }

    public Estudiante(String nombre, int edad, long cedula) {
        super(nombre, edad, cedula);
    }
    
    public Estudiante(){
        libros_reservados = new ArrayList<>();
    }

    @Override
    public List<Libro> getLibros_reservados() {
        return libros_reservados;
    }

    @Override
    public void reservar_libro_id(int id) {
        Libro x;
        x = Singleton_BaseDatos.getInstance().getLibro(id);
        if(x != null){
            System.out.println("Libro reservado exitosamente");
            this.libros_reservados.add(x);
        }else{
            System.out.println("Libro no disponible");
        }
    }

    @Override
    public void devolver_libro(int id) {
        boolean x;
        Libro y;
        y = Singleton_BaseDatos.getInstance().getLibro(id);
        x = Singleton_BaseDatos.getInstance().devolver_libro(id);
        if (x) {
            System.out.println("Libro devuelto exitosamente");
            libros_reservados.remove(y);
        }else{
            System.out.println("Error al devolver el libro");
        }
    }

    @Override
    public void consultar_disponibilidad(int id) {
        Libro x;
        x = Singleton_BaseDatos.getInstance().getLibro(id);
        if(x.isDisponible()){
            System.out.println("El libro con id "+ x.getId()+ " esta disponoble");
        }else{
            System.out.println("El libro " + x.getId() + " no esta disponible");
        }
    }


}
