package poo;

public class Camaleon extends Reptil{
    private int huevo;
    private String color;
    
    public Camaleon(String ColorActualP, int patasReptilP){ 
        super(patasReptilP);
        this.huevo = 0;
        this.color = ColorActualP;
    }
    
    public String getColor(){
        return color;
    }
    
    public void setColor(String c){
        this.color = c;
    }
    
    public int getHuevo(){
        return huevo;
    }   
    
    public void setHuevo(int h){
        this.huevo = h;
    }
    
    public void ponerHuevo(){
        if(huevo>9){
            System.out.println("El camaleon no puede poner mas huevos");
        }else{
            huevo++;
        } 
    }
    
    public void correr(){
        super.correr();
    }
    
    public void calentarse(int h){
        super.calentarse(h);
    }
}


