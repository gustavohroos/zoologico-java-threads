class Suricato extends Animal {


    Suricato(Comedouro comedouro, int id){
        super(comedouro, id);
        this.especie = "Suricato";
        this.tempoDeSonoMin = 6;
        this.tempoDeSonoMax = 10;
        this.consomeMin = 1;
        this.consomeMax = 2;
        
    }
}
