package poo;

public class Reptil extends Especie{
    private int temperatura;
    
    public Reptil(int patasP){
        super(patasP);
        this.temperatura = 0;
    }
    
    public int getTemperatura(){
        return temperatura;
    }
    public void calentarse(int horasP){
        this.temperatura += horasP;
    }
}
