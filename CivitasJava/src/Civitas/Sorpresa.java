package Civitas;

import java.util.ArrayList;

public abstract class Sorpresa {
    protected String texto;
    //private int valor;
   // private Tablero tablero;
    //private TipoSorpresa tipo;
    //private MazoSorpresas mazo;
    
    
    Sorpresa(String texto){
        this.texto = texto;
    }
    
    void informe(int actual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento("Aplicando sorpresa: \n" + this.toString() + 
                " al jugador " + todos.get(actual).getNombre() + " de tipo: " + this.getClass());
    }
    
    abstract void aplicarAJugador(int actual, ArrayList<Jugador> todos);

    
    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos) {
        boolean es_correcto = false;
        if (actual >= 0 && actual < todos.size()){
            es_correcto = true;
        }
        return es_correcto;
    }
        
}
