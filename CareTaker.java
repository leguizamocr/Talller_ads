import java.util.ArrayList;
import java.util.List;

public class CareTaker {
    private List<Memento> listas_guardadas = new ArrayList<>();
    private Originator originator;

    public CareTaker(Originator originator){
        this.originator = originator;
    }

    public void guardarEstado(){
        listas_guardadas.add(originator.guardar());
    }

    public void rehacer(){
        if(!listas_guardadas.isEmpty()){
            originator.reponer(listas_guardadas.remove(listas_guardadas.size()-1));
        }
    }
}
