class Avestruz extends Animal {
    
    Avestruz(Comedouro comedouro, int id){
        super(comedouro, id);
        this.especie = "Avestruz";
        this.tempoDeSonoMin = 4;
        this.tempoDeSonoMax = 8;
        this.consomeMin = 2;
        this.consomeMax = 4;
        this.cor = "\u001B[32m";
    }
}
