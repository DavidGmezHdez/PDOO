package Civitas;

import java.util.ArrayList;

public class SorpresaIrACasilla extends Sorpresa{
    private String texto;
    private int valor;
    private Tablero tablero;
    
    SorpresaIrACasilla(Tablero tablero, int valor, String texto){     
        super(texto);
        this.tablero = tablero;
        this.valor = valor;  
    }
    
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        int casillaActualNum = todos.get(actual).getNumCasillaActual();
        if(super.jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            int tirada = tablero.calcularTirada(casillaActualNum, this.valor);
            int nuevaPosicion = tablero.nuevaPosicion(casillaActualNum, tirada);
            todos.get(actual).moverACasilla(nuevaPosicion);
            tablero.getCasilla(nuevaPosicion).recibeJugador(actual,todos);
        }
    }
    
    @Override
    public String toString(){
        return "Sorpresa: { \n Texto = " + texto + "\n Tipo = IrACasilla" + 
                "\n Valor = " + valor + "}";
    }
}
