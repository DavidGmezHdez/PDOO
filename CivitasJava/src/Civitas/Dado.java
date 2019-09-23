package Civitas;
import java.util.ArrayList;
import java.util.Random;

public class Dado {
    
    private static final  Dado instance = new Dado();
    private int ultimoResultado;
    private boolean debug;
    private static int SalidaCarcel;
    
    private int valor;
    private boolean salir = false;
    

    static public Dado getInstance() {
        return instance;
    }
    
    private Dado() {
        SalidaCarcel = 5;
        debug = false;
        ultimoResultado = 1;
    }
    
    int tirar() {
        Random rand = new Random();
        if (!debug)
            ultimoResultado = rand.nextInt(6)+1;
        else
            ultimoResultado = 1;
            
        return ultimoResultado;
    }
    
    boolean salgoDeLaCarcel (){
        valor = tirar();
        if (valor >= 5)
            salir = true;
        
        return salir;
    }
    
    int quienEmpieza (int n){
        int primerJugador = 1;
        Random rand = new Random();
        primerJugador = rand.nextInt(n) + 1;
        return primerJugador;
    }
    
    
    void setDebug(boolean d) {
        this.debug = d;
    }

    public int getUltimoResultado() {
        return ultimoResultado;
    }

    public boolean isDebug() {
        return debug;
    }
    
    
    
    
    
}
