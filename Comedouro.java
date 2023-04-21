class Comedouro {
    int capacidade = 10;
    int comida = 10;
    String tipo;

    Comedouro(String tipo){
        this.tipo = tipo;
    }

    synchronized void comer(Animal animal) throws InterruptedException {
        while (comida < animal.consomeMin) {
            System.out.println(animal.especie + animal.id + " está esperando comida.");
            wait();
        }
        System.out.println(animal.especie + animal.id + " está comendo.");
        comida -= animal.comidaConsumida;
        notifyAll();
    }

    synchronized void encher(Veterinario veterinario) throws InterruptedException {
        while (comida > 0) {
            System.out.println("O veterinário " + veterinario.nome + " está esperando o comedouro esvaziar.");
            wait();
        }
        System.out.println("O veterinário " + veterinario.nome + " está enchendo o comedouro.");
        comida = capacidade;
        notifyAll();
    }
}
