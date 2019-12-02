package GUI;

import Civitas.CivitasJuego;
import java.util.ArrayList;
import java.util.Scanner;

public class test {
    
    public static void main(String[] args) {
        CivitasView vista = new CivitasView();
        
        Dado.createInstance(vista);
        
        Dado dado = Dado.getInstance();
        
        CapturaNombres capturador = new CapturaNombres(vista,true);
        
        ArrayList<String> nombres = capturador.getNombres();
        
        CivitasJuego juego = new CivitasJuego(nombres);
        
        Controlador controlador = new Controlador(juego,vista);
        
        vista.setCivitas(juego);
        
        vista.actualizarVista();
    }

}
