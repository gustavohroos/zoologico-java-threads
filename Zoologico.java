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

        Estoque estoqueCarne = new Estoque("Carne");
        Estoque estoqueComposto = new Estoque("Composto");
        Estoque estoquePasto = new Estoque("Pasto");

        Comedouro comedouroCarne = new Comedouro("Carne", estoqueCarne);
        Comedouro comedouroComposto = new Comedouro("Composto", estoqueComposto);
        Comedouro comedouroPasto = new Comedouro("Pasto", estoquePasto);

        this.estoques.add(estoqueCarne);
        this.estoques.add(estoqueComposto);
        this.estoques.add(estoquePasto);
        
        this.comedouros.add(comedouroCarne);
        this.comedouros.add(comedouroComposto);
        this.comedouros.add(comedouroPasto);

        Veterinario veterinario1 = new Veterinario(this.comedouros, "Vanderlei");
        Thread threadVeterinario1 = new Thread(veterinario1);
        this.threads.add(threadVeterinario1);
        this.veterinarios.add(veterinario1);

        Veterinario veterinario2 = new Veterinario(this.comedouros, "Lindomar");
        Thread threadVeterinario2 = new Thread(veterinario2);
        this.threads.add(threadVeterinario2);
        this.veterinarios.add(veterinario2);

        Fornecedor fornecedor = new Fornecedor(this.estoques, "Ivanildo");
        Thread threadFornecedor = new Thread(fornecedor);
        this.threads.add(threadFornecedor);
        this.fornecedor = fornecedor;
        
        for(int i = 0; i < 10; i++) {
            Leao leao = new Leao(comedouroCarne, i+1);
            Thread threadLeao = new Thread(leao);
            this.threads.add(threadLeao);
            this.animais.add(leao);
        }

        for(int i = 0; i < 10; i++) {
            Suricato suricato = new Suricato(comedouroComposto, i+1);
            Thread threadSuricato = new Thread(suricato);
            this.threads.add(threadSuricato);
            this.animais.add(suricato);
        }

        for(int i = 0; i < 10; i++) {
            Avestruz avestruz = new Avestruz(comedouroPasto, i+1);
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

        System.out.println("Média de comida consumida pelos leões por refeição: " + totalComidaLeoes / 10 / this.dias / 2);
        System.out.println("Média de comida consumida pelos suricatos por refeição: " + totalComidaSuricatos / 10 / this.dias / 2);
        System.out.println("Média de comida consumida pelos avestruzes por refeição: " + totalComidaAvestruzes / 10 / this.dias / 2);

        System.out.println("Média de horas dormidas pelos leões por dia: " + totalHorasDormidasLeoes / 10 / this.dias);
        System.out.println("Média de horas dormidas pelos suricatos por dia: " + totalHorasDormidasSuricatos / 10 / this.dias);
        System.out.println("Média de horas dormidas pelos avestruzes por dia: " + totalHorasDormidasAvestruzes / 10 / this.dias);

        System.out.println("Fim do programa");
    }

}

