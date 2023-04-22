class Comedouro {
    int capacidade = 10;
    int comida = 10;
    String tipo;
    Estoque estoque;

    Comedouro(String tipo, Estoque estoque){
        this.tipo = tipo;
        this.estoque = estoque;
    }

    synchronized void comer(Animal animal, int quantidade) throws InterruptedException {
        while (comida < animal.consomeMin) {
            System.out.println(animal.especie + " " + animal.id + " está esperando comida.");
            System.out.println("Comida no comedouro: " + this.comida);
            wait();
        }
        System.out.println(animal.especie + " " + animal.id + " está comendo.");
        System.out.println("Comida no comedouro: " + this.comida);
        System.out.println("Comida consumida: " + quantidade);
        this.comida -= quantidade;
        notifyAll();
    }

    synchronized void encher(Veterinario veterinario) throws InterruptedException {
        while(this.estoque.quantidade < this.comida){
            System.out.println("O veterinário " + veterinario.nome + " está esperando o estoque de " + this.tipo + " encher.");
            wait();
        }
        System.out.println("O veterinário " + veterinario.nome + " está enchendo o comedouro de " + this.tipo + ".");
        this.estoque.quantidade -= this.capacidade - this.comida;
        this.comida = this.capacidade;
        notifyAll();
    }
}
