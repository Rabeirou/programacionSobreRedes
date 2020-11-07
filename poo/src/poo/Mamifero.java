package poo;

public class Mamifero extends Especie{
    private int crias;
    
    public Mamifero(int patasP){
        super(patasP);
        this.crias = 0;
    }
    
    public void Grunir(){
        System.out.println("Grrrrr");
    }
    
    public int getCrias(){
         return crias;
    }
    
    public void setCrias(int c){
        crias = c;
    }

}
