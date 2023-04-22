import java.util.ArrayList;

public class Fornecedor implements Runnable {
    String nome;
    ArrayList<Estoque> estoques;

    Fornecedor(ArrayList<Estoque> estoques, String nome){
        this.nome = nome;
        this.estoques = estoques;
    }
    
    public void run() {
        while(true) {
            try{
                for(Estoque e : this.estoques){
                    if(e.quantidade == 0){
                        try {
                            e.reabastecer(this);
                            ArrayList<Estoque> estoques2 = estoques;
                            estoques2.remove(e);
                            for(Estoque e2 : estoques2){
                                e2.reabastecer(this);
                            }
                        } catch (InterruptedException e1) {
                            return;
                        }
                    }
                }
            } catch (Exception e) {
                return;
            }
            
        }
    }


}
