package Civitas;

import Civitas.Dado;
import java.util.ArrayList;

public class TestP1 {

    private static Dado dado = Dado.getInstance();
    private static TipoCasilla mostrarCasilla;
    private static TipoSorpresa mostrarSorpresa;
    private static OperacionesJuego mostrarOperaciones;
    private static EstadosJuego mostrarEstados;
    private static MazoSorpresas mazo;
    private static Sorpresa sorp1;
    private static Sorpresa sorp2;


    
    public static void main(String[] args) {
        //Apartado 1
        
        for(int i=0; i<5; i++){
            System.out.println("Debug: " + dado.isDebug());
        }
        
        
        //Apartado 2
        dado.setDebug(false);
        System.out.println("Debug: " + dado.isDebug());
        
        dado.tirar();
        System.out.println("Ultimo resultado: " + dado.getUltimoResultado());

        //Apartado 3
        System.out.println("Salgo de la carcel: " + dado.salgoDeLaCarcel());
        
        //Apartado 4
        mostrarCasilla = TipoCasilla.CALLE;
        System.out.println("Tipo casilla: " + mostrarCasilla);
        
        mostrarSorpresa = TipoSorpresa.PAGARCOBRAR;
        System.out.println("Tipo sorpresa: " + mostrarSorpresa);
        
        mostrarOperaciones = OperacionesJuego.GESTIONAR;
        System.out.println("Operaciones Juego: " + mostrarOperaciones);
        
        mostrarEstados = EstadosJuego.DESPUES_CARCEL;
        System.out.println("Estados Juego: " + mostrarEstados);
        
        //Apartado 5
        
        mazo.alMazo(s);
       
        


        
        
        
        
        
    
    }
}
