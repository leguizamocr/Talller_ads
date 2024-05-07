public interface Permisos_admin {
    void anadir_libro(String titulo, String genero, String autor, int num_pags,boolean disponible);
    void eliminar_libro_id(int id);
}
