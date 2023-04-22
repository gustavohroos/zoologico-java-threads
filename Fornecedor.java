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
                        for(Estoque e2 : this.estoques){
                            System.out.println("O fornecedor " + this.nome + " está enchendo o estoque de " + e.tipo + ".");
                            e2.reabastecer(this);
                        }
                        
                    }
                }
            } catch (Exception e) {
                return;
            }
            
        }
    }


}
