package poo;

public class Tigre extends Mamifero{
    public Tigre(int patasP){
        super(patasP);
    }
    
    public Camaleon alimentar(Camaleon c, int nHuevos){
        //recibe por parametro a un camaleon, se come los huenos necesarios del camaleon
        if(c.getHuevo()-nHuevos>=0){
            c.setHuevo(c.getHuevo()-nHuevos);
        }else{
            c.setHuevo(0);
        }
        return c;
    }
    
    public Camaleon buscar(Camaleon c){
        //recibe por parametro a un camaleon, des-oculta al camaleon y lo hace correr
        //si no está oculto solo lo hace correr
        if(c.getColor()=="oculto"){
            c.setColor("verde");
            c.correr();
        }else{
            c.correr();
        }
        return c;
    }
    
    public void setCrias(int c){
        super.setCrias(c);
    }
    
    public void Grunir(){
        super.Grunir();
    }

}
