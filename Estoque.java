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
        int faltando = this.capacidade - this.quantidade;
        this.quantidade = this.capacidade;
        if(this.tipo == "Carne"){
            fornecedor.quantidadePreenchidaCarne += faltando;
        } else if(this.tipo == "Composto"){
            fornecedor.quantidadePreenchidaComposto += faltando;
        } else {
            fornecedor.quantidadePreenchidaPasto += faltando;
        }
        notifyAll();
    }
}
