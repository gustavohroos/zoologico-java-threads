import java.util.Random;

class Animal implements Runnable {
    String especie;
    int id;
    int tempoDeSonoMin;
    int tempoDeSonoMax;
    int consomeMin;
    int consomeMax;
    int comidaConsumida;
    int horasDormidas;
    Comedouro comedouro;
    Random rand = new Random();

    Animal(Comedouro comedouro, int id) {
        this.comedouro = comedouro;
        this.id = id;
    }

    public void run() {
        while(true){
            try {
                // O animal começa a comer
                System.out.println(this.especie + " " + this.id + " começou a comer.");
                int comidaConsumidaNessaRefeicao = rand.nextInt(this.consomeMax - this.consomeMin + 1) + this.consomeMin;
                System.out.println(this.especie + " " + this.id + " vai comer " + comidaConsumidaNessaRefeicao + " unidades de comida.");
                comedouro.comer(this, comidaConsumidaNessaRefeicao);
                Thread.yield();

                // O animal para de comer e se exibe
                System.out.println(this.especie + " " + this.id + " terminou de comer " + comidaConsumidaNessaRefeicao + " unidades de comida.");
                this.comidaConsumida += comidaConsumidaNessaRefeicao;
                System.out.println(this.especie + " " + this.id + " começou a se exibir.");

                // O animal começa a comer de novo
                System.out.println(this.especie + " " + this.id + " começou a comer.");
                comidaConsumidaNessaRefeicao = rand.nextInt(this.consomeMax - this.consomeMin + 1) + this.consomeMin;
                this.comidaConsumida += comidaConsumidaNessaRefeicao;
                System.out.println(this.especie + " " + this.id + " vai comer " + comidaConsumidaNessaRefeicao + " unidades de comida.");
                comedouro.comer(this, comidaConsumidaNessaRefeicao);
                Thread.yield();
                
                // O animal para de comer e vai dormir
                System.out.println(this.especie + " " + this.id + " terminou de comer " + comidaConsumidaNessaRefeicao + " unidades de comida.");
                int tempoDeSono = rand.nextInt(this.tempoDeSonoMax - this.tempoDeSonoMin + 1) + this.tempoDeSonoMin;
                System.out.println(this.especie + " " + this.id + " vai dormir por " + tempoDeSono + " horas (segundos).");
                Thread.sleep(1000 * tempoDeSono);
                this.horasDormidas += tempoDeSono;

                Thread.sleep((1000 * 24) - (1000 * tempoDeSono)); // Passa um dia

            } catch (InterruptedException e) {
                return;
            }
        }
    }

    
}