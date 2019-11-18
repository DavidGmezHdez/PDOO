package Civitas;

import java.util.ArrayList;

public class CasillaCarcel extends Casilla{
    private int carcel;
    
    CasillaCarcel(String nombre,int numCasillaCarcel){
        super(nombre);
        this.carcel = numCasillaCarcel;
    }

    @Override    
    void recibeJugador(int iactual, ArrayList<Jugador> todos) {
        if(super.jugadorCorrecto(iactual,todos)){
            super.informe(iactual,todos);
            todos.get(iactual).encarcelar(carcel);
        }
    }
    
}
