import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Singleton_BaseDatos{
    private static Singleton_BaseDatos instance = null;
    private List<Libro> libros_BD;
    
    
    private Singleton_BaseDatos(){
        libros_BD = new ArrayList<>();
    }
    
    public static Singleton_BaseDatos getInstance(){
        if(instance == null){
            instance = new Singleton_BaseDatos();
        }
        return instance;
    }

    public Libro getLibro(int id){
        for (Libro x : libros_BD) {
            if(x.getId() == id && x.isDisponible()){
                x.setDisponible(false);
                return x;
            }
            if(x.getId() == id && !x.isDisponible()){
                System.out.println("Libro no disponible");
                return null;
            }
        }
        return null;
    }

    public void setLibro(Libro x){
        libros_BD.add(x);
        ordenarLibros();
    }

    public boolean devolver_libro(int id){
        for (Libro libro : libros_BD) {
            if(libro.getId() == id){
                libro.setDisponible(true);
                return true;
            }
        }
        return false;
    }

    public void ordenarLibros(){
        Collections.sort(libros_BD, new Comparator<Libro>() {
            @Override
            public int compare(Libro l1, Libro l2) {
                return l1.getTitulo().compareToIgnoreCase(l2.getTitulo());
            }
            
        });
    }

    public void eliminar_libro_id(int id){
        Libro aux = null;
        for (Libro libro : libros_BD) {
            if(libro.getId() == id && libro.isDisponible()){
                aux = libro;
            }
        }
        if (aux == null) {
            System.out.println("Libro no registrado en la base de datos o no se encuentra disponible");
        }else{
            libros_BD.remove(aux);
            System.out.println("Libro eliminado exitosamente de la base de datos");
        }
    }

    public void mostrar_libros(){
        for (Libro libro : libros_BD) {
            System.out.println();
            System.out.println("Titulo: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor());
            System.out.println("Genero: " + libro.getGenero());
            System.out.println("Número de páginas: " + libro.getNum_pags());
            System.out.println("ID: " + libro.getId());
            System.out.println("Disponible: " + libro.isDisponible());
            System.out.println();
        }
    }
}
