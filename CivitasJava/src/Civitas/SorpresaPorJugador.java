package Civitas;

import java.util.ArrayList;

public class SorpresaPorJugador extends Sorpresa{
    private int valor;
    
    SorpresaPorJugador(int valor, String texto){
        super(texto);
        this.valor=valor;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
       if(jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            int valorActual = this.valor;
            int valorOtros = this.valor;
            String text = " Pagar al jugador " + todos.get(actual).getNombre();
            valorActual = valorActual * (todos.size()-1);
            valorOtros = valorOtros * -1;
            Sorpresa sorpActual = new SorpresaPagarCobrar(valorActual, text);
            Sorpresa sorpOtros =  new SorpresaPagarCobrar(valorOtros, text);

            for(int i=0; i < todos.size(); i++){
                if(i==actual){
                    sorpActual.aplicarAJugador(actual,todos);
                }else{
                    sorpOtros.aplicarAJugador(i, todos);
                }
            }        
        }     
    }
    
    @Override
    public String toString(){
        return "Sorpresa: { \n Texto = " + texto + "\n Tipo = PorJugador" + 
                "\n Valor = " + valor + "}";
    }
}
