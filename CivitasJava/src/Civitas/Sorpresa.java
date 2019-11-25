package Civitas;

import java.util.ArrayList;

public abstract class Sorpresa {
    protected String texto;
    //private int valor;
   // private Tablero tablero;
    //private TipoSorpresa tipo;
    //private MazoSorpresas mazo;
    
    
    Sorpresa(String texto){
        this.texto = texto;
    }
    
    
    void informe(int actual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento("Aplicando sorpresa: \n" + this.toString() + 
                " al jugador " + todos.get(actual).getNombre() + "de tipo: " + this.getClass());
    }
    
    abstract void aplicarAJugador(int actual, ArrayList<Jugador> todos);

    /*
    

    
    
    
    private void aplicarAJugador_salirCarcel (int actual, ArrayList<Jugador> todos){
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
        if(this.tipo==TipoSorpresa.SALIRCARCEL)
            this.mazo.inhabilitarCartaEspecial(this);
    }
    
    
    void usada(){
        if(this.tipo==TipoSorpresa.SALIRCARCEL)
            this.mazo.habilitarCartaEspecial(this);
    }
    
    
    public void setValor(int valor) {
        this.valor = valor;
    }
    */
     public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos) {
        boolean es_correcto = false;
        if (actual >= 0 && actual < todos.size()){
            es_correcto = true;
        }
        return es_correcto;
    }
        
}
