import java.util.ArrayList;

class Zoologico{
    ArrayList<Comedouro> comedouros = new ArrayList<Comedouro>();
    ArrayList<Thread> threads = new ArrayList<Thread>();
    ArrayList<Estoque> estoques = new ArrayList<Estoque>();
    ArrayList<Animal> animais = new ArrayList<Animal>();
    ArrayList<Veterinario> veterinarios = new ArrayList<Veterinario>();
    Fornecedor fornecedor;

    int dias;

    public static void main (String[] args) throws InterruptedException {
    
        
        Zoologico zoologico = new Zoologico(30);
        zoologico.iniciar();

    
    }


    Zoologico(int dias) {

        this.dias = dias;
        this.estoques.add(new Estoque("Carne"));
        this.estoques.add(new Estoque("Composto"));
        this.estoques.add(new Estoque("Pasto"));

        Comedouro comedouro1 = new Comedouro("Carne");
        Comedouro comedouro2 = new Comedouro("Composto");
        Comedouro comedouro3 = new Comedouro("Pasto");

        comedouros.add(comedouro1);
        comedouros.add(comedouro2);
        comedouros.add(comedouro3);
        
        for(int i = 0; i < 4; i++) {
            Leao leao = new Leao(comedouro1, i);
            Thread threadLeao = new Thread(leao);
            this.threads.add(threadLeao);
            this.animais.add(leao);
        }

        for(int i = 0; i < 10; i++) {
            Suricato suricato = new Suricato(comedouro2, i);
            Thread threadSuricato = new Thread(suricato);
            this.threads.add(threadSuricato);
            this.animais.add(suricato);
        }

        for(int i = 0; i < 7; i++) {
            Avestruz avestruz = new Avestruz(comedouro3, i);
            Thread threadAvestruz = new Thread(avestruz);
            this.threads.add(threadAvestruz);
            this.animais.add(avestruz);
        }

        
        Veterinario veterinario1 = new Veterinario(comedouros, "Jorge");
        Thread threadVeterinario1 = new Thread(veterinario1);
        this.threads.add(threadVeterinario1);
        this.veterinarios.add(veterinario1);

        Veterinario veterinario2 = new Veterinario(comedouros, "Alceu");
        Thread threadVeterinario2 = new Thread(veterinario2);
        this.threads.add(threadVeterinario2);
        this.veterinarios.add(veterinario2);
        

        Fornecedor fornecedor = new Fornecedor("Francisco", this.estoques);
        Thread threadFornecedor = new Thread(fornecedor);
        this.threads.add(threadFornecedor);
        this.fornecedor = fornecedor;
    }

    void iniciar() throws InterruptedException {
        for(Thread t : this.threads) {
            t.start();
        }

        int tempoMaximo = 1000 * 24 * this.dias; // 1000ms * 24h * dias

        // É necessário implementar um mecanismo para que o programa não fique rodando infinitamente.
        


        System.out.println("Dias: " + this.dias);
        
        System.out.println("Estoque de carne: " + this.estoques.get(0).quantidade);
        System.out.println("Estoque de composto: " + this.estoques.get(1).quantidade);
        System.out.println("Estoque de pasto: " + this.estoques.get(2).quantidade);
        
        for (Animal an : this.animais) {
            System.out.println(an.especie + " " + an.id + " comeu " + an.comidaConsumida + " kg de comida.");
        }

        System.out.println("Fim do programa");
    }


}

