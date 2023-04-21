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
        while (quantidade > 0) {
            System.out.println("O fornecedor " + fornecedor.nome + " está esperando o estoque esvaziar.");
            wait();
        }
        System.out.println("O fornecedor " + fornecedor.nome + " está enchendo o estoque.");
        quantidade = capacidade;
        notifyAll();
    }
}
