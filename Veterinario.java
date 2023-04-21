import java.util.ArrayList;

public class Veterinario implements Runnable {
    ArrayList<Comedouro> comedouros;
    String nome;
    

    Veterinario(ArrayList<Comedouro> comedouros, String nome){
        this.comedouros = comedouros;
        this.nome = nome;
    }

    public void run() {
        while(true){
            for(Comedouro c : this.comedouros){
                try {
                    c.encher(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
