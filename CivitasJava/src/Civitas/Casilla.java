package Civitas;
import java.util.ArrayList;

public class Casilla {
    private String nombre;
    
    Casilla(String nombre) {
        this.nombre = nombre;
    }
    
    void informe(int iactual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento("El jugador " + todos.get(iactual).getNombre() + 
                " ha caído en la casilla: \n" + this.toString());
    }
    
    public String getNombre() {
        return nombre;
    }

    public boolean jugadorCorrecto(int iactual, ArrayList<Jugador> todos) {
        boolean es_correcto = false;
        if (iactual >= 0 && iactual < todos.size()) {
            es_correcto = true;
        }
        return es_correcto;
    }

    void recibeJugador(int iactual, ArrayList<Jugador> todos) {
        informe(iactual, todos);
    }
        
    @Override
    public String toString(){
        return "Casilla {" + "\n Nombre: " + nombre + "}\n";
    }
}
