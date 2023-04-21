public class Leao extends Animal{

    Leao(Comedouro comedouro, int id) {
        super(comedouro, id);
        this.especie = "Leão";
        this.tempoDeSonoMin = 8;
        this.tempoDeSonoMax = 12;
        this.consomeMin = 2;
        this.consomeMax = 5;
    }

}
