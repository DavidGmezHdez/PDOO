package Civitas;

import java.util.ArrayList;

public class SorpresaCarcel extends Sorpresa{
    private Tablero tablero;
    private String texto = "¡Directo a la cárcel!";
    
    SorpresaCarcel (Tablero tablero){
        super("¡Directo a la cárcel!");
        this.tablero = tablero;
    }
        
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        if(super.jugadorCorrecto(actual, todos)){
            informe(actual,todos);
            todos.get(actual).encarcelar(this.tablero.getCarcel());
        }
    }
    
    @Override
    public String toString(){
        return "Sorpresa: { \n Texto = " + texto + "\n Tipo = IrACarcel }";
    }
}
    
    
