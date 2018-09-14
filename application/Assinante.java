package application;

import java.util.Observable;
import java.util.Observer;

public class Assinante implements Observer{
    
	Observable revistaInformatica;
    
    int edicaoNovaRevista;
     
    public Assinante(Observable revistaInformatica) {
        this.revistaInformatica = revistaInformatica;
        revistaInformatica.addObserver(this);
    }
     
   	@Override
	public void update(Observable arg0, Object arg1) {  
            edicaoNovaRevista = ((Revista)revistaInformatica).getEdicao();
            System.out.println("Aten��o, j� chegou a mais uma edi��o da Revista Informatica. " +
                    "Esta � a sua edi��o n�mero: " + edicaoNovaRevista);
	}

}
