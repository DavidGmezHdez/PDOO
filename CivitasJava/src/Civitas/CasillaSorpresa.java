package Civitas;

import java.util.ArrayList;

public class CasillaSorpresa extends Casilla {
    private MazoSorpresas mazo;
    
    CasillaSorpresa(String nombre, MazoSorpresas mazo){
        super(nombre);
        this.mazo = mazo;
    }
    
    @Override
    void recibeJugador(int iactual, ArrayList<Jugador> todos) {
        Sorpresa sorpresa;
        if(super.jugadorCorrecto(iactual, todos)){
            sorpresa = this.mazo.siguiente();
            super.informe(iactual, todos);
            sorpresa.aplicarAJugador(iactual, todos);
        }
    }
}
