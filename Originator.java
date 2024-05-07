import java.util.ArrayList;
import java.util.List;

public class Originator {
    public static Usuario_base x;
    List<Libro> libros_copia;

    public Originator(){
        this.libros_copia = new ArrayList<>();
    }

    public Memento guardar(){
        if (x instanceof Admin) {
            System.out.println("Los admins no tienen libros reservados");
            return null;
        }else if(x instanceof Profesor){
            libros_copia = ((Profesor)x).getLibros_reservados();
            return new Memento(libros_copia);
        }else if(x instanceof Estudiante){
            libros_copia = ((Estudiante)x).getLibros_reservados();
            return new Memento(libros_copia);
        }
        return null;
    }

    public void reponer(Memento m){
        if (x instanceof Admin) {
            System.out.println("Los admins no tienen libros reservados");
        }else if(x instanceof Profesor){
            ((Profesor)x).setLibros_reservados(m.libros_copia);
        }else if(x instanceof Estudiante){
            ((Estudiante)x).setLibros_reservados(m.libros_copia);
        }
    }
}
