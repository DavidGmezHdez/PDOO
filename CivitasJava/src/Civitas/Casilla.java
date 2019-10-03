package Civitas;

import java.util.ArrayList;

public class Casilla {
    private String nombre;
    private static int carcel;
    private float importe;
    
    private TipoCasilla tipo;
    private TituloPropiedad tituloPropiedad;
    private Sorpresa sorpresa;
    private MazoSorpresas mazo;
    
    Casilla(String nombre) {
        this.nombre = nombre;
    }
    
    Casilla(TituloPropiedad titulo){
        tituloPropiedad = titulo;
    }
    
    Casilla(float cantidad, String nombre){
        importe = cantidad;
        this.nombre = nombre;
    }
    
    Casilla(int numCasillaCarcel, String nombre){
        this.nombre = nombre;
        carcel = numCasillaCarcel;
    }
    
    Casilla(MazoSorpresas mazo, String nombre){
        this.mazo = mazo;
        this.nombre = nombre;
    }
    
    private void informe(int actual, ArrayList<Jugador> todos){
        toString();
    }

    public String getNombre() {
        return nombre;
    }

    TituloPropiedad getTituloPropiedad() {
        return tituloPropiedad;
    }
    
    
    
    
}