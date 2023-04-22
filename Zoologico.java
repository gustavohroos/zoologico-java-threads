import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

class Zoologico{
    ArrayList<Comedouro> comedouros = new ArrayList<Comedouro>();
    ArrayList<Thread> threads = new ArrayList<Thread>();
    ArrayList<Estoque> estoques = new ArrayList<Estoque>();
    ArrayList<Animal> animais = new ArrayList<Animal>();
    ArrayList<Veterinario> veterinarios = new ArrayList<Veterinario>();
    Fornecedor fornecedor;
    int dias;

    public static void main (String[] args) throws InterruptedException {
        Zoologico zoologico = new Zoologico(Integer.parseInt(args[0]));
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

        Veterinario veterinario1 = new Veterinario(comedouros, "Vanderlei");
        Thread threadVeterinario1 = new Thread(veterinario1);
        this.threads.add(threadVeterinario1);
        this.veterinarios.add(veterinario1);

        Veterinario veterinario2 = new Veterinario(comedouros, "Lindomar");
        Thread threadVeterinario2 = new Thread(veterinario2);
        this.threads.add(threadVeterinario2);
        this.veterinarios.add(veterinario2);

        Fornecedor fornecedor = new Fornecedor(this.estoques, "Ivanildo");
        Thread threadFornecedor = new Thread(fornecedor);
        this.threads.add(threadFornecedor);
        this.fornecedor = fornecedor;
        
        for(int i = 0; i < 4; i++) {
            Leao leao = new Leao(comedouro1, i+1);
            Thread threadLeao = new Thread(leao);
            this.threads.add(threadLeao);
            this.animais.add(leao);
        }

        for(int i = 0; i < 10; i++) {
            Suricato suricato = new Suricato(comedouro2, i+1);
            Thread threadSuricato = new Thread(suricato);
            this.threads.add(threadSuricato);
            this.animais.add(suricato);
        }

        for(int i = 0; i < 7; i++) {
            Avestruz avestruz = new Avestruz(comedouro3, i+1);
            Thread threadAvestruz = new Thread(avestruz);
            this.threads.add(threadAvestruz);
            this.animais.add(avestruz);
        }
    }

    void iniciar() throws InterruptedException {
        for(Thread t : this.threads) {
            t.start();
        }
        System.out.println("Iniciando programa");
        System.out.println("Quantidade de threads: " + this.threads.size());

        int tempoMaximo = 1000 * 24 * this.dias; // 1000ms * 24h * dias

        // É necessário implementar um mecanismo para que o programa não fique rodando infinitamente.

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for(Thread t : threads) {
                    t.interrupt();
                }
                imprimir();
                System.exit(0);
            }
        }, tempoMaximo);
    }

    void imprimir(){
        System.out.println("Dias: " + this.dias);

        float totalComidaLeoes = 0;
        float totalComidaSuricatos = 0;
        float totalComidaAvestruzes = 0;

        float totalHorasDormidasLeoes = 0;
        float totalHorasDormidasSuricatos = 0;
        float totalHorasDormidasAvestruzes = 0;
        
        for (Animal an : this.animais) {
            System.out.println(an.especie + " " + an.id + " comeu: " + an.comidaConsumida + " unidades de comida.");
            System.out.println(an.especie + " " + an.id + " dormiu durante " + an.horasDormidas + " horas.");
            if(an.especie == "Leão") {
                totalComidaLeoes += an.comidaConsumida;
                totalHorasDormidasLeoes += an.horasDormidas;
            } else if(an.especie == "Suricato") {
                totalComidaSuricatos += an.comidaConsumida;
                totalHorasDormidasSuricatos += an.horasDormidas;
            } else if(an.especie == "Avestruz") {
                totalComidaAvestruzes += an.comidaConsumida;
                totalHorasDormidasAvestruzes += an.horasDormidas;
            }
        }

        System.out.println("Média de comida consumida pelos leões por refeição: " + totalComidaLeoes / 4 / this.dias / 2);
        System.out.println("Média de comida consumida pelos suricatos por refeição: " + totalComidaSuricatos / 10 / this.dias / 2);
        System.out.println("Média de comida consumida pelos avestruzes por refeição: " + totalComidaAvestruzes / 7 / this.dias / 2);

        System.out.println("Média de horas dormidas pelos leões por dia: " + totalHorasDormidasLeoes / 4 / this.dias);
        System.out.println("Média de horas dormidas pelos suricatos por dia: " + totalHorasDormidasSuricatos / 10 / this.dias);
        System.out.println("Média de horas dormidas pelos avestruzes por dia: " + totalHorasDormidasAvestruzes / 7 / this.dias);

        System.out.println("Fim do programa");
    }

}

