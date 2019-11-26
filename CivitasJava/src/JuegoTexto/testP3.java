package JuegoTexto;

import Civitas.CivitasJuego;
import java.util.ArrayList;
import java.util.Scanner;

public class testP3 {
    private static final Scanner in = new Scanner (System.in);
    
    private static ArrayList<String> getNombreJugadores(){
        ArrayList<String> lista = new ArrayList<>();
        int num_jugadores;

        System.out.println("Introduce el numero de jugadores:");
        num_jugadores = in.nextInt();

        for(int i=0; i<num_jugadores; i++){
            System.out.println("Introduce el nombre del jugador:");
            String s = in.next();
        lista.add(s);
        }

        return lista;
    }
    
    public static void main(String[] args) {
        ArrayList<String> nombres = new ArrayList<>();
        nombres = getNombreJugadores();
        
        CivitasJuego civitas = new CivitasJuego(nombres);
        VistaTextual vista = new VistaTextual();
        civitas.setDebugDado(true);
        
        Controlador controlador = new Controlador(civitas,vista);
        controlador.juega();
        
    }

}
