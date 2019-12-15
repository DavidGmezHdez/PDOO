package Civitas;

import java.util.ArrayList;

public class CasillaImpuesto extends Casilla {
    private float importe;
    
    CasillaImpuesto(String nombre, float cantidad){
        super(nombre);
        this.importe = cantidad;
        super.tipo = 2;
    }
    
    @Override
    void recibeJugador(int iactual, ArrayList<Jugador> todos) {
        if(super.jugadorCorrecto(iactual,todos)){
            super.informe(iactual,todos);
            todos.get(iactual).pagaImpuesto(importe);                    
        }        
    }
    
    @Override
    public String toString(){
        return "Casilla {" + "\n Nombre: " + super.getNombre() +" Valor " + this.importe +"}\n";
    }
}
