class Avestruz extends Animal {
    
    Avestruz(Comedouro comedouro, int id){
        super(comedouro, id);
        this.especie = "Avestruz";
        this.tempoDeSonoMin = 4;
        this.tempoDeSonoMax = 8;
        this.consomeMin = 2;
        this.consomeMax = 4;
    }

    public void run() {
        while(true){
            try {
                // O animal começa a comer
                int comidaConsumidaNessaRefeicao = rand.nextInt(this.consomeMax - this.consomeMin + 1) + this.consomeMin;
                System.out.println("\u001B[32m" + this.especie + " " + this.id + " quer comer " + comidaConsumidaNessaRefeicao + " unidades de comida." + "\u001B[0m");
                comedouro.comer(this, comidaConsumidaNessaRefeicao);
                Thread.yield();

                // O animal para de comer e se exibe
                System.out.println("\u001B[32m" + this.especie + " " + this.id + " terminou de comer " + comidaConsumidaNessaRefeicao + " unidades de comida." + "\u001B[0m");
                this.comidaConsumida += comidaConsumidaNessaRefeicao;
                System.out.println("\u001B[32m" + this.especie + " " + this.id + " começou a se exibir." + "\u001B[0m");

                // O animal começa a comer de novo
                comidaConsumidaNessaRefeicao = rand.nextInt(this.consomeMax - this.consomeMin + 1) + this.consomeMin;
                this.comidaConsumida += comidaConsumidaNessaRefeicao;
                System.out.println("\u001B[32m" + this.especie + " " + this.id + " quer comer " + comidaConsumidaNessaRefeicao + " unidades de comida." + "\u001B[0m");
                comedouro.comer(this, comidaConsumidaNessaRefeicao);
                Thread.yield();
                
                // O animal para de comer e vai dormir
                System.out.println("\u001B[32m" + this.especie + " " + this.id + " terminou de comer " + comidaConsumidaNessaRefeicao + " unidades de comida." + "\u001B[0m");
                int tempoDeSono = rand.nextInt(this.tempoDeSonoMax - this.tempoDeSonoMin + 1) + this.tempoDeSonoMin;
                System.out.println("\u001B[32m" + this.especie + " " + this.id + " vai dormir por " + tempoDeSono + " horas (segundos)." + "\u001B[0m");
                Thread.sleep(1000 * tempoDeSono);
                this.horasDormidas += tempoDeSono;

                Thread.sleep((1000 * 24) - (1000 * tempoDeSono)); // Passa um dia

            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
