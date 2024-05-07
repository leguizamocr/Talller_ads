import java.util.List;


public interface Permisos_estudiante_profesor {
    List<Libro> getLibros_reservados();

    void reservar_libro_id(int id);
    void devolver_libro(int id);
    void consultar_disponibilidad(int id);
}
