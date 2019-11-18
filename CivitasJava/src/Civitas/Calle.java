package Civitas;
public class Calle extends Casilla {
    private TituloPropiedad titulo;
    
    Calle(TituloPropiedad titulo){
        super(titulo.getNombre());
        this.titulo = titulo;
    }
    
    TituloPropiedad getTituloPropiedad(){
        return this.titulo;
    }
    
    
    
    
    
}
