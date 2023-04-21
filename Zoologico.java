import java.util.ArrayList;

class Zoologico{
    ArrayList<Comedouro> comedouros = new ArrayList<Comedouro>();
    ArrayList<Thread> threads = new ArrayList<Thread>();
    ArrayList<Estoque> estoques = new ArrayList<Estoque>();

    int dias;

    public static void main (String[] args) {
    
        
        Zoologico zoologico = new Zoologico(30);
        zoologico.iniciar();

        // Executar o programa por 30 segundos

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
        }

        for(int i = 0; i < 10; i++) {
            Suricato suricato = new Suricato(comedouro2, i);
            Thread threadSuricato = new Thread(suricato);
            this.threads.add(threadSuricato);
        }

        for(int i = 0; i < 7; i++) {
            Avestruz avestruz = new Avestruz(comedouro3, i);
            Thread threadAvestruz = new Thread(avestruz);
            this.threads.add(threadAvestruz);
        }

        
        Veterinario veterinario1 = new Veterinario(comedouros, "Jorge");
        Thread threadVeterinario1 = new Thread(veterinario1);
        this.threads.add(threadVeterinario1);

        Veterinario veterinario2 = new Veterinario(comedouros, "Alceu");
        Thread threadVeterinario2 = new Thread(veterinario2);
        this.threads.add(threadVeterinario2);
        

        Fornecedor fornecedor = new Fornecedor("Francisco", this.estoques);
        Thread threadFornecedor = new Thread(fornecedor);
        this.threads.add(threadFornecedor);
    }

    void iniciar(){
        for(Thread t : this.threads) {
            t.start();
        }
    }

}

