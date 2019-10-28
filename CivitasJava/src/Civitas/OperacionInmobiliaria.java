package Civitas;
public class OperacionInmobiliaria {
    private int numPropiedad;
    private GestionesInmobiliarias gestion;
    
    public OperacionInmobiliaria(GestionesInmobiliarias gest, int ip){
        this.gestion = gest;
        this.numPropiedad = ip;
    }

    public int getNumPropiedad() {
        return numPropiedad;
    }

    public GestionesInmobiliarias getGestion() {
        return gestion;
    }
}
