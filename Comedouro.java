class Comedouro {
    int capacidade = 10;
    int comida = 10;
    String tipo;

    Comedouro(String tipo){
        this.tipo = tipo;
    }

    synchronized void comer(Animal animal, int quantidade) throws InterruptedException {
        while (comida < animal.consomeMin) {
            System.out.println(animal.especie + " " + animal.id + " está esperando comida.");
            System.out.println("Comida no comedouro: " + this.comida);
            wait();
        }
        System.out.println(animal.especie + " " + animal.id + " está comendo.");
        this.comida -= quantidade;
        notifyAll();
    }

    synchronized void encher(Veterinario veterinario) throws InterruptedException {
        this.comida = capacidade;
        notifyAll();
    }
}
