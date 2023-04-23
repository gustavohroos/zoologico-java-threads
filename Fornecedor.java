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
                        System.out.println("\u001B[34m" + "Estoque de " + e.tipo + " está vazio. O fornecedor " + this.nome + " está enchendo todos estoques." + "\u001B[0m");
                        this.estoques.get(0).reabastecer(this);
                        this.estoques.get(1).reabastecer(this);
                        this.estoques.get(2).reabastecer(this);
                    }
                }
            } catch (Exception e) {
                return;
            }
        }
    }
}
