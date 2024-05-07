public class Comando_devolver implements Comando {
    public static Usuario_base x;

    @Override
    public void ejecutar(int id) {
        if(x instanceof Admin){
            System.out.println("Los admins no pueden realizar este comando");
        }else if(x instanceof Estudiante){
            ((Estudiante)x).devolver_libro(id);
        }else if(x instanceof Profesor){
            ((Profesor)x).devolver_libro(id);
    }
    }

    @Override
    public void deshacer(int id) {
        if(x instanceof Admin){
            System.out.println("Los admins no pueden realizar este comando");
        }else if(x instanceof Estudiante){
            ((Estudiante)x).reservar_libro_id(id);
        }else if(x instanceof Profesor){
            ((Profesor)x).reservar_libro_id(id);
    }
    }

}

