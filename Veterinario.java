import java.util.ArrayList;

public class Veterinario implements Runnable {
    ArrayList<Comedouro> comedouros;
    String nome;
    

    Veterinario(ArrayList<Comedouro> comedouros, String nome){
        this.comedouros = comedouros;
        this.nome = nome;
    }

    public void run() {
        while(true) {
            try{
                for(Comedouro c : this.comedouros){
                    if(c.comida < c.capacidade){
                        c.encher(this);
                    }
                }
            } catch (Exception e) {
                return;
            }
        }
    }   
}
