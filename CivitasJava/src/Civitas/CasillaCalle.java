package Civitas;

import java.util.ArrayList;

public class CasillaCalle extends Casilla {
    private TituloPropiedad titulo;
    
    CasillaCalle(TituloPropiedad titulo){
        super(titulo.getNombre());
        this.titulo = titulo;
        super.tipo = 1;
    }
    
    TituloPropiedad getTituloPropiedad(){
        return this.titulo;
    }
    
    @Override
    void recibeJugador(int iactual, ArrayList<Jugador> todos) {
            if(super.jugadorCorrecto(iactual,todos)){
            super.informe(iactual,todos);
            Jugador jugador = todos.get(iactual);
            
            if(!this.titulo.tienePropietario()){
                jugador.puedeComprarCasilla();
            } else{
                this.titulo.tramitarAlquiler(jugador);
            }
        }
    }
    
    @Override
    public String toString(){
        return "Casilla {" + "\n Nombre: " + super.getNombre() +"\n" + titulo.toString() + "}\n";
    }
    
    
    
    
}
