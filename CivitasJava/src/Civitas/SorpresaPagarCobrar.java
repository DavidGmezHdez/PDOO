package Civitas;

import java.util.ArrayList;

public class SorpresaPagarCobrar extends Sorpresa{
    private int valor;
    
    SorpresaPagarCobrar(int valor, String texto){
        super(texto);
        this.valor=valor;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if(super.jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            todos.get(actual).modificarSaldo(this.valor);        
        }    
    }
    
    @Override
    public String toString(){
        return "Sorpresa: { \n Texto = " + texto + "\n Tipo = PagarCobrar" + 
                "\n Valor = " + valor + "}";
    }
}
