import java.util.ArrayList;

public class Fornecedor implements Runnable {
    String nome;
    ArrayList<Estoque> estoques;

    Fornecedor(String nome, ArrayList<Estoque> estoques){
        this.nome = nome;
        this.estoques = estoques;
    }
    
    public void run() {
        while(true) {
            for(Estoque e : this.estoques){
                if(e.quantidade == 0){
                    try {
                        for(Estoque e2 : this.estoques){
                            e2.reabastecer(this);
                        }
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }


}
