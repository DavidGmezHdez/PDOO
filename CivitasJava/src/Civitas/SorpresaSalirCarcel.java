package Civitas;

import java.util.ArrayList;


public class SorpresaSalirCarcel extends Sorpresa{
    private MazoSorpresas mazo;
    
    SorpresaSalirCarcel(MazoSorpresas mazo){
        super("¡Genial! Has conseguido la carta para evitar la cárcel");
        this.mazo = mazo;        
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual, todos)){
            informe(actual,todos);
            int nadieSalvoconducto = 0;
            for(int i=0; i<todos.size(); i++){
                if(todos.get(i).tieneSalvoconducto())
                    nadieSalvoconducto++;
            }
            
            //Si nadie tiene Salvoconducto
            if(nadieSalvoconducto==0){
                todos.get(actual).obtenerSalvoconducto(this);
                salirDelMazo();
            }
        }    
    }
    
    void salirDelMazo(){
        this.mazo.inhabilitarCartaEspecial(this);
    }
    
    
    void usada(){
        this.mazo.habilitarCartaEspecial(this);
    }
    
    @Override
    public String toString(){
        return "Sorpresa: { \n Texto = " + texto + "\n Tipo = SalirCarcel"+ "}";
    }
}
