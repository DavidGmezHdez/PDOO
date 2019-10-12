package Civitas;
import java.util.ArrayList;
import java.util.Random;

public class Dado {
    
    private static final Dado instance = new Dado();
    private int ultimoResultado;
    private Random random = new Random();
    private boolean debug;
    private static int SalidaCarcel;
    
    
    static public Dado getInstance() {
        return instance;
    }
    
    
    private Dado() {
        SalidaCarcel = 5;
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
        int valor;
        boolean salir = false;
        valor = tirar();
        if (valor >= 5)
            salir = true;
        
        return salir;
    }
    
    
    int quienEmpieza (int n){
        int primerJugador = 1;
        primerJugador = random.nextInt(n) + 1;
        return primerJugador;
    }
    
    
    public void setDebug(boolean d) {
        this.debug = d;
    }
    

    int getUltimoResultado() {
        return ultimoResultado;
    }

    public boolean isDebug() {
        return debug;
    }
    
}
