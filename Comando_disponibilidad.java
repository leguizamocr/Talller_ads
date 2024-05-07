public class Comando_disponibilidad implements Comando {
    public static Usuario_base x;

    @Override
    public void ejecutar(int id) {
        if(x instanceof Admin){
            System.out.println("Los admins no pueden realizar este comando");
        }else if(x instanceof Estudiante){
            ((Estudiante)x).consultar_disponibilidad(id);
        }else if(x instanceof Profesor){
            ((Profesor)x).consultar_disponibilidad(id);
    }
    }

    @Override
    public void deshacer(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'deshacer'");
    }

}
