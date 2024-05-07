import java.util.List;

public class Memento{
    public static Usuario_base x;
    List<Libro> libros_copia;

    public Memento(List<Libro> libros){
        if (x instanceof Admin) {
            System.out.println("Los admins no tienen libros reservados");
        }else if(x instanceof Profesor){
            libros_copia = ((Profesor)x).getLibros_reservados();
        }else if(x instanceof Estudiante){
            libros_copia = ((Estudiante)x).getLibros_reservados();
        }
    }

    public List<Libro> getEstadLibros(){
        return libros_copia;
    }


}
