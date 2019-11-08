package Civitas;
import java.util.ArrayList;

public class Casilla {
    private String nombre;
    private static int carcel;
    private float importe;
    
    private TipoCasilla tipo;
    private TituloPropiedad tituloPropiedad;
    private Sorpresa sorpresa;
    private MazoSorpresas mazo;
    
    
    private void init(){
        this.nombre = " ";
        this.carcel = 0;
        this.importe = 0;
        this.tituloPropiedad = null;
        this.tipo = null;
        this.sorpresa = null;
        this.mazo = null;
    }
    
    Casilla(String nombre) {
        init();
        this.nombre = nombre;
        this.tipo = TipoCasilla.DESCANSO;
    }
    
    
    Casilla(TituloPropiedad titulo){
        init();
        tipo = TipoCasilla.CALLE;
        tituloPropiedad = titulo;
    }
    
    
    Casilla(float cantidad, String nombre){
        init();
        this.tipo = TipoCasilla.IMPUESTO;
        importe = cantidad;
        this.nombre = nombre;
    }
    
    
    Casilla(int numCasillaCarcel, String nombre){
        init();
        this.tipo = TipoCasilla.CARCEL;
        this.nombre = nombre;
        carcel = numCasillaCarcel;
    }
    
    
    Casilla(MazoSorpresas mazo, String nombre){
        init();
        this.tipo = TipoCasilla.SORPRESA;
        this.mazo = mazo;
        this.nombre = nombre;
    }
    
    
    private void informe(int iactual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento("El jugador " + todos.get(iactual).getNombre() + 
                " ha caído en la casilla: \n" + this.toString());
    }

    
    public String getNombre() {
        return nombre;
    }

    
    TituloPropiedad getTituloPropiedad() {
        return tituloPropiedad;
    }
    
    


    public boolean jugadorCorrecto(int iactual, ArrayList<Jugador> todos) {
        boolean es_correcto = false;
        if (iactual >= 0 && iactual < todos.size()) {
            es_correcto = true;
        }
        return es_correcto;
    }

    void recibeJugador(int iactual, ArrayList<Jugador> todos) {/*
        switch (this.tipo) {
            case CALLE:
                recibeJugador_calle(iactual, todos);
            case IMPUESTO:
                recibeJugador_impuesto(iactual, todos);
            case JUEZ:
                recibeJugador_juez(iactual, todos);
            case SORPRESA:
                recibeJugador_sorpresa(iactual, todos);
            default:
                informe(iactual, todos);
        }*/
        if(tipo==tipo.CALLE)
             recibeJugador_calle(iactual,todos);
         else if(tipo==tipo.IMPUESTO)
             recibeJugador_impuesto(iactual,todos);
         else if(tipo==tipo.JUEZ)
             recibeJugador_juez(iactual,todos);
         else if(tipo==tipo.SORPRESA)
             recibeJugador_sorpresa(iactual,todos);
         else 
             informe(iactual,todos);
    }

    private void recibeJugador_calle(int iactual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(iactual,todos)){
            informe(iactual,todos);
            Jugador jugador = todos.get(iactual);
            
            if(!this.tituloPropiedad.tienePropietario()){
                jugador.puedeComprarCasilla();
            } else{
                this.tituloPropiedad.tramitarAlquiler(jugador);
            }
        }
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
        if(this.jugadorCorrecto(iactual, todos)){
            this.sorpresa = this.mazo.siguiente();
            this.informe(iactual, todos);
            this.sorpresa.aplicarAJugador(iactual, todos);
        }
    }
    
    
    @Override
    public String toString(){
        
        switch(this.tipo){
       
            case CALLE:
                return "Casilla {" + "\n TituloPropiedad: " + tituloPropiedad.toString() + ", \n Tipo: " + tipo + "\n}";
            case IMPUESTO:
                return "Casilla {" + "\n Nombre: " + nombre + " \n Tipo: " + tipo + "\n}" + "Importe: " + importe + "\n";
            default:
                return "Casilla {" + "\n Nombre: " + nombre + " \n Tipo: " + tipo + "\n}";
        }
        /*
        if(tipo==tipo.CALLE)
            return "Casilla {" + "\n TituloPropiedad: " + tituloPropiedad.toString() + ", \n Tipo: " + tipo + "\n}";
         else if(tipo==tipo.IMPUESTO)
            return "Casilla {" + "\n Nombre: " + nombre + " \n Tipo: " + tipo + "\n}" + "Importe: " + importe + "\n";
         else 
            return "Casilla {" + "\n Nombre: " + nombre + " \n Tipo: " + tipo + "\n}";
            */
    }
    
}
