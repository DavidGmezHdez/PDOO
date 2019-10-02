package Civitas;
import java.util.ArrayList;
import java.util.Collections;
import Civitas.Jugador;

public class Sorpresa {
    private String texto;
    private int valor;
    private Tablero tablero;
    private TipoSorpresa tipo;
    private MazoSorpresas mazo;
    
    private Jugador jugador;
    private Casilla casillaActual;
    
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
    
    private void init(){
        valor = -1;
        mazo = null;
        tablero = null;
    }

    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos) {
        boolean es_correcto = false;
        if (actual >= 0 && actual < todos.size()){
            es_correcto = true;
        }
        return es_correcto;
    }
    
    //NO SE COMO SE HACE LO DE COGER GETINSTANCE DE DIARIO Y AÑADIRLO ???????
    void informe(int actual, ArrayList<Jugador> todos){
        
    }
    
    private void aplicarAJugador_irCarcel(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            todos.get(actual).encarcelar(tablero.getCarcel());
        }
    }
    
    private void aplicarAJugador_irACasilla (int actual, ArrayList<Jugador> todos){
        int casillaActual = todos.get(actual).getNumCasillaActual();
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            int tirada = tablero.calcularTirada(casillaActual, valor);
            int nuevaPosicion = tablero.nuevaPosicion(casillaActual, tirada);
            todos.get(actual).moverACasilla(nuevaPosicion);
            //ME FALTAA LA ÚLTIMA PARTE ????????????????
        }
    } 
    
    
    private void aplicarAJugador_pagarCobrar(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            todos.get(actual).modificarSaldo(valor);        
        }    
    }
    
    private void aplicarAJugador_porCasaHotel(int actual, ArrayList<Jugador> todos){
        int numPropiedades = todos.get(actual).getPropiedades().size();
        int nuevoValor = valor*numPropiedades;
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            todos.get(actual).modificarSaldo(nuevoValor);        
        }     
    }
  
}
