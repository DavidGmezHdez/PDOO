package Civitas;
import java.util.ArrayList;

public class SorpresaEspeculador extends Sorpresa {
    private int valor;
    
    SorpresaEspeculador(int valor, String texto){
        super(texto);
        this.valor=valor;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual, todos)){
            Especulador espc = new Especulador(todos.get(actual),valor);
            todos.remove(actual);
            todos.add(espc);
        }
    }
    
    @Override
    public String toString(){
        return "Sorpresa: { \n Texto = " + texto + "\n Tipo = Especulador" + 
                "\n Valor = " + valor + "}";
    }
}
