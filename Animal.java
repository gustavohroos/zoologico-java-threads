import java.util.Random;

class Animal implements Runnable {
    String especie;
    int id;
    int tempoDeSonoMin;
    int tempoDeSonoMax;
    int consomeMin;
    int consomeMax;
    int comidaConsumida;
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
                System.out.println(this.especie + this.id + " começou a comer.");
                int comidaConsumidaNessaRefeicao = rand.nextInt(consomeMax - consomeMin + 1) + consomeMin;
                System.out.println(this.especie + this.id + " vai comer " + comidaConsumidaNessaRefeicao + " unidades de comida.");
                
                comedouro.comer(this);

                // O animal para de comer e dorme
                System.out.println(especie + id + " terminou de comer " + comidaConsumidaNessaRefeicao + " unidades de comida.");
                this.comidaConsumida += comidaConsumidaNessaRefeicao;

                int tempoDeSono = rand.nextInt(tempoDeSonoMax - tempoDeSonoMin + 1) + tempoDeSonoMin;
                System.out.println(especie + id + " vai dormir por " + tempoDeSono + " segundos.");
                Thread.sleep(1000 * tempoDeSono);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getComidaConsumida() {
        return comidaConsumida;
    }

    public String getNome() {
        return especie + id;
    }
}