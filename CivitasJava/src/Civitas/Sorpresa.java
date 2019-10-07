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
    //private Casilla casillaActual;
    
    
    
    //Constructor para la sorpresa que envía a la cárcel
    Sorpresa(TipoSorpresa tipo, Tablero tablero){
        init();
        this.tipo = tipo;
        this.tablero = tablero;
    }
    
    //Constructor para la sorpresa que envía al jugador a otra casilla
    Sorpresa(TipoSorpresa tipo, Tablero tablero, int valor, String texto){
        init();      
        this.tipo = tipo;
        this.tablero = tablero;
        this.valor = valor;
        this.texto = texto;    
    }
    
    //Constructor para el resto de sorpresas
    Sorpresa(TipoSorpresa tipo, int valor, String texto){
        init();
        this.tipo=tipo;
        this.valor=valor;
        this.texto=texto;
    }
    
    //Constructor para sorpresa que permite evitar la carcel
    Sorpresa(TipoSorpresa tipo, MazoSorpresas mazo){
        init();
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
    
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        switch(this.tipo){
            case IRCARCEL:
                aplicarAJugador_irCarcel(actual, todos);
            case IRCASILLA:
                aplicarAJugador_irACasilla (actual, todos);
            case PAGARCOBRAR:
                aplicarAJugador_pagarCobrar(actual, todos);
            case PORCASAHOTEL:
                aplicarAJugador_porCasaHotel(actual, todos);
            case PORJUGADOR:
                aplicarAJugador_porJugador(actual, todos);
            case SALIRCARCEL:
                aplicarAJugador_salirCarcel (actual, todos);       
        }
    }

    void informe(int actual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento("Aplicando sorpresa al jugador " + todos.get(actual).getNombre());
    }
    
    private void aplicarAJugador_irCarcel(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            todos.get(actual).encarcelar(tablero.getCarcel());
        }
    }
    
    private void aplicarAJugador_irACasilla (int actual, ArrayList<Jugador> todos){
        int casillaActualNum = todos.get(actual).getNumCasillaActual();
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            int tirada = tablero.calcularTirada(casillaActualNum, valor);
            int nuevaPosicion = tablero.nuevaPosicion(casillaActualNum, tirada);
            todos.get(actual).moverACasilla(nuevaPosicion);
            //ESTO ESTARIA BIEEEEN ????????????????
            casillaActual.recibeJugador(actual,todos);
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
    
    
    //ESTÁ BIENN ????????
    private void aplicarAJugador_porJugador(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            
            for(int i=0; i < todos.size(); i++){
                if(i==actual){
                    tipo = tipo.PAGARCOBRAR;
                    valor *= (todos.size()-1);
                    todos.get(i).recibe(valor);
                }else{
                    tipo = tipo.PAGARCOBRAR;
                    valor *= -1;
                    todos.get(i).recibe(valor);
                }            
            }        
        }    
    }
    
    private void aplicarAJugador_salirCarcel (int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual, todos)){
            informe(actual,todos);
            int nadieSalvoconducto = 0;
            for(int i=0; i<todos.size(); i++){
                if(todos.get(i).tieneSalvoconducto())
                    nadieSalvoconducto++;
            }
            
            //Si nadie tiene Salvoconducto
            if(nadieSalvoconducto==0){
                todos.get(actual).obtenerSalvoconducto(this);
                salirDelMazo();
            }
        }    
    }
    
    void salirDelMazo(){
        if(tipo==tipo.SALIRCARCEL)
            mazo.inhabilitarCartaEspecial(this);
    }
    
    void usada(){
        if(tipo==tipo.SALIRCARCEL)
            mazo.habilitarCartaEspecial(this);
    }
    
    @Override
    public String toString(){
        return "Sorpresa{ " + "nombre= " + texto + "valor= " + valor + '}';
    }
    
}
