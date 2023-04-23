public class Estoque {
    int capacidade;
    int quantidade;
    String tipo;

    Estoque(String tipo){
        this.tipo = tipo;
        this.capacidade = 30;
        this.quantidade = 30;
    }

    synchronized void reabastecer(Fornecedor fornecedor) throws InterruptedException {
        this.quantidade = this.capacidade;
        notifyAll();
    }
}
