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
            wait(500);
        }
        System.out.println(animal.especie + " " + animal.id + " está comendo.");
        System.out.println("Comida no comedouro: " + this.comida);
        System.out.println("Comida consumida: " + quantidade);
        this.comida -= quantidade;
        notifyAll();
    }

    synchronized void encher(Veterinario veterinario) throws InterruptedException {
        while(this.estoque.quantidade == 0){
            wait(500);
        }
        if(this.estoque.quantidade < (this.capacidade - this.comida)){
            System.out.println("Quantidade no estoque: " + this.estoque.quantidade);
            this.comida += this.estoque.quantidade;
            this.estoque.quantidade = 0;
        } else {
            this.estoque.quantidade -= (this.capacidade - this.comida);
            this.comida = this.capacidade;
        }
        
        notifyAll();
    }
}
