package poo;

public class Especie {
    private int cantPatas;
    
    public Especie(int patasP){
        this.cantPatas = patasP;
    } 
    
    public int getCantPatas(){
        return cantPatas;
    }
    
    public void correr(){
        System.out.println("El animal empezo a correr");
    }

}
