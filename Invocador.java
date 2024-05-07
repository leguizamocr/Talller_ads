
public class Invocador {
    Usuario_base s;
    private Comando comando;

    public void setComando(Comando comando){
        this.comando = comando;
    }

    public void ejecutar(int id){
        comando.ejecutar(id);
    }

    public void deshacer(int id){
        comando.deshacer(id);
    }
}
