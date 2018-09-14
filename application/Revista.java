package application;

import java.util.Observable;
import java.util.Observer;

public class Revista extends Observable{
    private int edicao;
      
    public void setNovaEdicao(int novaEdicao) {
        this.edicao = novaEdicao;
        setChanged();
        notifyObservers();
    }
     
    public int getEdicao() {
        return this.edicao;
    }
}
