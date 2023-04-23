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

    }
    
}