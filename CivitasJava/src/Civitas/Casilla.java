package Civitas;

import java.util.ArrayList;
import Civitas.Diario;

public class Casilla {
    private String nombre;
    private static int carcel;
    private float importe;
    
    private TipoCasilla tipo;
    private TituloPropiedad tituloPropiedad;
    private Sorpresa sorpresa;
    private MazoSorpresas mazo;
    
    Casilla(String nombre) {
        init();
        this.nombre = nombre;
    }
    
    
    Casilla(TituloPropiedad titulo){
        init();
        tituloPropiedad = titulo;
    }
    
    
    Casilla(float cantidad, String nombre){
        init();
        importe = cantidad;
        this.nombre = nombre;
    }
    
    
    Casilla(int numCasillaCarcel, String nombre){
        init();
        this.nombre = nombre;
        carcel = numCasillaCarcel;
    }
    
    
    Casilla(MazoSorpresas mazo, String nombre){
        init();
        this.mazo = mazo;
        this.nombre = nombre;
    }
    
    
    private void informe(int iactual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento("Ha caído en la casilla el jugador " + todos.get(iactual).getNombre());
        toString();
    }

    
    public String getNombre() {
        return nombre;
    }

    
    TituloPropiedad getTituloPropiedad() {
        return tituloPropiedad;
    }
    
    
    private void init(){
        //NO SE A QUE INICIALIZARLOO ??????????
    }
    
    
    public boolean jugadorCorrecto(int iactual, ArrayList<Jugador> todos){
        boolean es_correcto = false;
        if(iactual>=0 && iactual<todos.size()){
            es_correcto=true;
        }
        return es_correcto;
    }
    
    
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
        throw new UnsupportedOperationException("No implementado");
    }
    
    
    private void recibeJugador_calle(int iactual, ArrayList<Jugador> todos){
        throw new UnsupportedOperationException("No implementado");
    }
    
   
    private void recibeJugador_impuesto(int iactual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(iactual,todos)){
            informe(iactual,todos);
            todos.get(iactual).pagaImpuesto(importe);                    
        }            
    }
    
    
    private void recibeJugador_juez(int iactual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(iactual,todos)){
            informe(iactual,todos);
            todos.get(iactual).encarcelar(carcel);
        }
    }
    
    
    private void recibeJugador_sorpresa(int iactual, ArrayList<Jugador> todos){
        throw new UnsupportedOperationException("No implementado");
    }
    
    
    @Override
    public String toString(){
        return "Casilla {" + "Nombre= " + nombre + ", importe=" + importe + '}';
    }
    
}
