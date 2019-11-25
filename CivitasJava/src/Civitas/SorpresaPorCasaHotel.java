package Civitas;

import java.util.ArrayList;

public class SorpresaPorCasaHotel extends Sorpresa{
    private int valor;
    
    SorpresaPorCasaHotel(int valor, String texto){
        super(texto);
        this.valor=valor;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        int numPropiedades = todos.get(actual).cantidadCasasHoteles();
        int nuevoValor = this.valor*numPropiedades;
        if(super.jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            todos.get(actual).modificarSaldo(nuevoValor);        
        }     
    }
    
    @Override
    public String toString(){
        return "Sorpresa: { \n Texto = " + texto + "\n Tipo = PorCasaHotel" + 
                "\n Valor = " + valor + "}";
    }
}
