public class Admin extends Usuario_base implements Permisos_admin {
    public static final int tipo_usuario = 1;

    public Admin(String nombre, int edad, long cedula){
        super(nombre, edad, cedula);
    }

    @Override
    public void anadir_libro(String titulo, String genero, String autor, int num_pags, boolean disponible){
        Libro x = new Libro(titulo, genero, autor, num_pags, true);
        Singleton_BaseDatos.getInstance().setLibro(x);
        System.out.println("Libro agregado exitosamente");
    }

    @Override
    public void eliminar_libro_id(int id){
        Singleton_BaseDatos.getInstance().eliminar_libro_id(id);
    }
}
