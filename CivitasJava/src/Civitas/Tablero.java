package Civitas;
import java.util.ArrayList;

public class Tablero {
    private ArrayList<Casilla> casillas;
    private int numCasillaCarcel;
    private int porSalida;
    private boolean tieneJuez;
    
    Tablero(int numero){
        if(numero>=1)
            numCasillaCarcel = numero;
        else
            numCasillaCarcel = 1;
        
        casillas = new ArrayList<>(); 
        Casilla Salida = new Casilla("Salida");
        casillas.add(Salida);
        porSalida = 0;
        tieneJuez = false;
    }
    
    
    private boolean correcto(){
        return casillas.size() > numCasillaCarcel && tieneJuez;
    }

    private boolean correcto(int numCasilla){
        return correcto() && (numCasilla >=0 && numCasilla<= casillas.size());
    }
    
    int getCarcel(){
        return numCasillaCarcel;
    }
    
    int getPorSalida(){
        return porSalida;
    }
    
    void añadeCasilla(Casilla casilla){
        Casilla carcel = new Casilla("Carcel");
        if(casillas.size() == numCasillaCarcel){           
            casillas.add(carcel);
        }
        
        casillas.add(casilla);
    }

    void añadeJuez(){
        Casilla Juez = new Casilla("Juez");
        if(!tieneJuez)    
            casillas.add(Juez);
        
        tieneJuez = true;
    }
    
    Casilla getCasilla(int numCasilla){
        if(numCasilla >= 0 && numCasilla <= casillas.size())
            return casillas.get(numCasilla);
        else
            return null;
    }
    
    int nuevaPosicion(int actual, int tirada){
        int posicion;
        if(!correcto())
            return posicion = -1;
        else{
            posicion = (actual+tirada)%20; 
        }
        
        if(actual+tirada != posicion)
            porSalida++;
             
        return posicion;
    }
    
    int calcularTirada(int actual, int destino){
        return destino - actual + 20;
    }
    
}
