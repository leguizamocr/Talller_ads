public abstract class Usuario_base implements Factory_Usuarios{
    private String nombre;
    private int edad;
    private long cedula;

    public Usuario_base(String nombre, int edad, long cedula){
        this.cedula = cedula;
        this.edad = edad;
        this.nombre = nombre;
    }

    public Usuario_base(){}

    @Override
    public String getNombre(){
        return nombre;
    }

    @Override 
    public int getEdad(){
        return edad;
    }

    @Override
    public long getCedula(){
        return cedula;
    }
}

