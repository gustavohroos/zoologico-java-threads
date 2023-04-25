import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

class Zoologico{
    ArrayList<Comedouro> comedouros = new ArrayList<Comedouro>();
    ArrayList<Thread> threads = new ArrayList<Thread>();
    ArrayList<Thread> animalThreads = new ArrayList<Thread>();
    ArrayList<Estoque> estoques = new ArrayList<Estoque>();
    ArrayList<Leao> leoes = new ArrayList<Leao>();
    ArrayList<Suricato> suricatos = new ArrayList<Suricato>();
    ArrayList<Avestruz> avestruzes = new ArrayList<Avestruz>();
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
            Suricato suricato = new Suricato(comedouroComposto, i+1);
            Thread threadSuricato = new Thread(suricato);
            this.animalThreads.add(threadSuricato);
            this.suricatos.add(suricato);
        }

        for(int i = 0; i < 7; i++) {
            Avestruz avestruz = new Avestruz(comedouroPasto, i+1);
            Thread threadAvestruz = new Thread(avestruz);
            this.animalThreads.add(threadAvestruz);
            this.avestruzes.add(avestruz);
        }
        for(int i = 0; i < 4; i++) {
            Leao leao = new Leao(comedouroCarne, i+1);
            Thread threadLeao = new Thread(leao);
            this.animalThreads.add(threadLeao);
            this.leoes.add(leao);
        }

    }

    void iniciar() throws InterruptedException {

        System.out.println("Iniciando programa");
        System.out.println("Quantidade de threads: " + (this.threads.size() + this.animalThreads.size()));

        for(Thread t : this.threads) {
            t.start();
        }

        for(Thread t : this.animalThreads) {
            t.start();
        }

        int tempoMaximo = 1000 * 24 * this.dias; // 1000ms * 24h * dias

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
        
        float totalComidaLeoes = 0;
        float totalComidaSuricatos = 0;
        float totalComidaAvestruzes = 0;
        
        float totalHorasDormidasLeoes = 0;
        float totalHorasDormidasSuricatos = 0;
        float totalHorasDormidasAvestruzes = 0;
        
        int minComidaLeao = this.leoes.get(0).comidaConsumida;
        int maxComidaLeao = this.leoes.get(0).comidaConsumida;
        int minComidaSuricato = this.suricatos.get(0).comidaConsumida;
        int maxComidaSuricato = this.suricatos.get(0).comidaConsumida;
        int minComidaAvestruz = this.avestruzes.get(0).comidaConsumida;
        int maxComidaAvestruz = this.avestruzes.get(0).comidaConsumida;
        
        int minHorasDormidasLeao = this.leoes.get(0).horasDormidas;
        int maxHorasDormidasLeao = this.leoes.get(0).horasDormidas;
        int minHorasDormidasSuricato = this.suricatos.get(0).horasDormidas;
        int maxHorasDormidasSuricato = this.suricatos.get(0).horasDormidas;
        int minHorasDormidasAvestruz = this.avestruzes.get(0).horasDormidas;
        int maxHorasDormidasAvestruz = this.avestruzes.get(0).horasDormidas;
        
        for(Leao leao : this.leoes) {
            totalComidaLeoes += leao.comidaConsumida;
            totalHorasDormidasLeoes += leao.horasDormidas;
            
            if(leao.comidaConsumida < minComidaLeao) {
                minComidaLeao = leao.comidaConsumida;
            }
            if(leao.comidaConsumida > maxComidaLeao) {
                maxComidaLeao = leao.comidaConsumida;
            }
            if(leao.horasDormidas < minHorasDormidasLeao) {
                minHorasDormidasLeao = leao.horasDormidas;
            }
            if(leao.horasDormidas > maxHorasDormidasLeao) {
                maxHorasDormidasLeao = leao.horasDormidas;
            }
        }
        
        for(Suricato suricato : this.suricatos) {
            totalComidaSuricatos += suricato.comidaConsumida;
            totalHorasDormidasSuricatos += suricato.horasDormidas;
            
            if(suricato.comidaConsumida < minComidaSuricato) {
                minComidaSuricato = suricato.comidaConsumida;
            }
            if(suricato.comidaConsumida > maxComidaSuricato) {
                maxComidaSuricato = suricato.comidaConsumida;
            }
            if(suricato.horasDormidas < minHorasDormidasSuricato) {
                minHorasDormidasSuricato = suricato.horasDormidas;
            }
            if(suricato.horasDormidas > maxHorasDormidasSuricato) {
                maxHorasDormidasSuricato = suricato.horasDormidas;
            }
        }
        
        for(Avestruz avestruz : this.avestruzes) {
            totalComidaAvestruzes += avestruz.comidaConsumida;
            totalHorasDormidasAvestruzes += avestruz.horasDormidas;
            
            if(avestruz.comidaConsumida < minComidaAvestruz) {
                minComidaAvestruz = avestruz.comidaConsumida;
            }
            if(avestruz.comidaConsumida > maxComidaAvestruz) {
                maxComidaAvestruz = avestruz.comidaConsumida;
            }
            if(avestruz.horasDormidas < minHorasDormidasAvestruz) {
                minHorasDormidasAvestruz = avestruz.horasDormidas;
            }
            if(avestruz.horasDormidas > maxHorasDormidasAvestruz) {
                maxHorasDormidasAvestruz = avestruz.horasDormidas;
            }
        }
        
        System.out.println("Relatório final:");
        System.out.println("Dias: " + this.dias + "\nHoras: " + this.dias * 24 + "(segundos)");
        System.out.println("\nComida:");
        System.out.println("Mínimo de comida consumida por um leão: " + minComidaLeao);
        System.out.println("Máximo de comida consumida por um leão: " + maxComidaLeao);
        System.out.println("Média de comida consumida pelos leões por refeição: " + totalComidaLeoes / 4 / this.dias / 2);
        System.out.println("Mínimo de comida consumida por um suricato: " + minComidaSuricato);
        System.out.println("Máximo de comida consumida por um suricato: " + maxComidaSuricato);
        System.out.println("Média de comida consumida pelos suricatos por refeição: " + totalComidaSuricatos / 10 / this.dias / 2);
        System.out.println("Mínimo de comida consumida por um avestruz: " + minComidaAvestruz);
        System.out.println("Máximo de comida consumida por um avestruz: " + maxComidaAvestruz);
        System.out.println("Média de comida consumida pelos avestruzes por refeição: " + totalComidaAvestruzes / 7 / this.dias / 2);
        
        System.out.println("\nHoras dormidas:");
        System.out.println("Mínimo de horas dormidas por um leão: " + minHorasDormidasLeao);
        System.out.println("Máximo de horas dormidas por um leão: " + maxHorasDormidasLeao);
        System.out.println("Média de horas dormidas pelos leões por dia: " + totalHorasDormidasLeoes / 4 / this.dias);
        System.out.println("Mínimo de horas dormidas por um suricato: " + minHorasDormidasSuricato);
        System.out.println("Máximo de horas dormidas por um suricato: " + maxHorasDormidasSuricato);
        System.out.println("Média de horas dormidas pelos suricatos por dia: " + totalHorasDormidasSuricatos / 10 / this.dias);
        System.out.println("Mínimo de horas dormidas por um avestruz: " + minHorasDormidasAvestruz);
        System.out.println("Máximo de horas dormidas por um avestruz: " + maxHorasDormidasAvestruz);
        System.out.println("Média de horas dormidas pelos avestruzes por dia: " + totalHorasDormidasAvestruzes / 7 / this.dias);

        System.out.println("\nQuantidades preenchidas pelo fornecedor:");
        System.out.println("Estoque carne: " + this.fornecedor.quantidadePreenchidaCarne);
        System.out.println("Estoque composto: " + this.fornecedor.quantidadePreenchidaComposto);
        System.out.println("Estoque pasto: " + this.fornecedor.quantidadePreenchidaPasto);


        System.out.println("\nFim do programa");
    }

}

