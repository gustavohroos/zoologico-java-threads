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
        while (this.comida < quantidade) {

            if(animal.especie == "Leão"){
                System.out.println("\u001B[31m" + animal.especie + " " + animal.id + " está esperando comida." + "\u001B[0m");
                System.out.println("\u001B[31m" + "Comida no comedouro: " + this.comida + "\u001B[0m");
            } else if(animal.especie == "Suricato"){
                System.out.println("\u001B[33m" + animal.especie + " " + animal.id + " está esperando comida." + "\u001B[0m");
                System.out.println("\u001B[33m" + "Comida no comedouro: " + this.comida + "\u001B[0m");
            } else if(animal.especie == "Avestruz"){
                System.out.println("\u001B[32m" + animal.especie + " " + animal.id + " está esperando comida." + "\u001B[0m");
                System.out.println("\u001B[32m" + "Comida no comedouro: " + this.comida + "\u001B[0m");
            }
            wait();
        }
        if(animal.especie == "Leão"){
            System.out.println("\u001B[31m" + animal.especie + " " + animal.id + " está comendo." + "\u001B[0m");
            System.out.println("\u001B[31m" + "Comida no comedouro: " + this.comida + "\u001B[0m");
            System.out.println("\u001B[31m" + "Comida consumida: " + quantidade + "\u001B[0m");
        } else if(animal.especie == "Suricato"){
            System.out.println("\u001B[33m" + animal.especie + " " + animal.id + " está comendo." + "\u001B[0m");
            System.out.println("\u001B[33m" + "Comida no comedouro: " + this.comida + "\u001B[0m");
            System.out.println("\u001B[33m" + "Comida consumida: " + quantidade + "\u001B[0m");
        } else if(animal.especie == "Avestruz"){
            System.out.println("\u001B[32m" + animal.especie + " " + animal.id + " está comendo." + "\u001B[0m");
            System.out.println("\u001B[32m" + "Comida no comedouro: " + this.comida + "\u001B[0m");
            System.out.println("\u001B[32m" + "Comida consumida: " + quantidade + "\u001B[0m");
        }
        this.comida -= quantidade;
        notifyAll();
    }

    synchronized void encher(Veterinario veterinario) throws InterruptedException {
        while(this.estoque.quantidade == 0){
            wait(500);
        }
        if(this.estoque.quantidade < (this.capacidade - this.comida)){
            this.comida += this.estoque.quantidade;
            this.estoque.quantidade = 0;
        } else {
            this.estoque.quantidade -= (this.capacidade - this.comida);
            this.comida = this.capacidade;
        }
        
        notifyAll();
    }
}
