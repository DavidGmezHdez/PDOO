package Civitas;
import java.util.ArrayList;
import java.util.Random;

public class Dado {
    
    static final private Dado instance = new Dado();
    private Random random;
    private int ultimoResultado;
    private boolean debug;
    private static int SalidaCarcel;
    
    private int valor;
    private boolean salir = false;
    private int primerJugador;

    static public Dado getInstance() {
        return instance;
    }
    
    private Dado() {
        SalidaCarcel = 5;
        random = new Random();
        debug = false;
        ultimoResultado = 1;
    }
    
    int tirar() {
        if (!debug)
            ultimoResultado = random.nextInt(6)+1;
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
        primerJugador = random.nextInt(n-1)+1;
        return primerJugador;
    }
    
    
    void setDebug(boolean d) {
        this.debug = d;
    }

    public int getUltimoResultado() {
        return ultimoResultado;
    }
    
    
    
    
    
}
