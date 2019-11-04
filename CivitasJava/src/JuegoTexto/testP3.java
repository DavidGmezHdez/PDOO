package JuegoTexto;

import Civitas.CivitasJuego;
import Civitas.Dado;
import java.util.ArrayList;
import java.util.Scanner;

public class testP3 {
    private static Dado dado = Dado.getInstance();
    private static final Scanner in = new Scanner (System.in);
    
    public static void main(String[] args) {
        ArrayList<String> nombres = new ArrayList<>();
        nombres.add("Marta");
        nombres.add("Jose");
        nombres.add("Rodrigo");
        
        CivitasJuego civitas = new CivitasJuego(nombres);
        VistaTextual vista = new VistaTextual();
        
        Controlador controlador = new Controlador(civitas,vista);
        controlador.juega();
        
        /*
        dado.setDebug(false);
        System.out.println("Debug: " + dado.isDebug());
        */
        
        
    }

}
