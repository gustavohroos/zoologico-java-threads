public class Estoque {
    final int capacidade;
    int quantidade;
    String tipo;

    Estoque(String tipo){
        this.tipo = tipo;
        this.capacidade = 30;
        this.quantidade = 30;
    }

    synchronized void reabastecer(Fornecedor fornecedor) throws InterruptedException {
        int faltando = this.capacidade - this.quantidade;
        System.out.println("\u001B[34m" + "O fornecedor " + fornecedor.nome + " está enchendo o estoque de " + this.tipo + "." + "\u001B[0m");
        System.out.println("\u001B[34m" + "Estava (" + this.quantidade + "/" + this.capacidade + ").\u001B[0m");
        this.modificarEstoque(faltando);
        if(this.tipo == "Carne"){
            fornecedor.quantidadePreenchidaCarne += faltando;
        } else if(this.tipo == "Composto"){
            fornecedor.quantidadePreenchidaComposto += faltando;
        } else {
            fornecedor.quantidadePreenchidaPasto += faltando;
        }
        notifyAll();
    }

    synchronized void modificarEstoque(int quantidade) throws InterruptedException {
        this.quantidade += quantidade;
        notifyAll();
    }
}
