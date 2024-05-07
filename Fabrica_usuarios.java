
public class Fabrica_usuarios {
    public static Usuario_base crear_usuario(int tipo, String nombre, int edad, long cedula){
        switch (tipo) {
            case 1:
                return new Admin(nombre,edad,cedula);
            case 2:
                return new Profesor(nombre, edad, cedula);
            case 3:
                return new Estudiante(nombre, edad, cedula);
            default:
                System.out.println("Tipo de usuario no valido");
                return null;
        }
    }
}
