package Civitas;

public class Sorpresa {
    private String texto;
    private int valor;
    private Tablero tablero;
    private TipoSorpresa tipo;
    private MazoSorpresas mazo;
    
    //Constructor para la sorpresa que envía a la cárcel
    Sorpresa(TipoSorpresa tipo, Tablero tablero){
        this.tipo = tipo;
        this.tablero = tablero;
    }
    
    //Constructor para la sorpresa que envía al jugador a otra casilla
    Sorpresa(TipoSorpresa tipo, Tablero tablero, int valor, String texto){
        this.tipo = tipo;
        this.tablero = tablero;
        this.valor = valor;
        this.texto = texto;    
    }
    
    //Constructor para el resto de sorpresas
    Sorpresa(TipoSorpresa tipo, int valor, String texto){
        this.tipo=tipo;
        this.valor=valor;
        this.texto=texto;
    }
    
    //Constructor para sorpresa que permite evitar la carcel
    Sorpresa(TipoSorpresa tipo, MazoSorpresas mazo){
        this.tipo = tipo;
        this.mazo = mazo;        
    }
    
    
  
}
