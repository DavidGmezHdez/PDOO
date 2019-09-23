package Civitas;

import Civitas.Dado;

import java.util.ArrayList;

public class TestP1 {

    private static Dado dado = Dado.getInstance();
    private static Diario diario = Diario.getInstance();
    private static TipoCasilla mostrarCasilla;
    private static TipoSorpresa mostrarSorpresa;
    private static OperacionesJuego mostrarOperaciones;
    private static EstadosJuego mostrarEstados;
    private static MazoSorpresas mazo = new MazoSorpresas();

    
    public static void main(String[] args) {
        //Apartado 1
        for (int i = 0; i < 5; i++) {
            System.out.println("Empieza el jugador: " + dado.quienEmpieza(4));
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
        
        //Apartado 5 y 6
        Sorpresa sorp1 = new Sorpresa("Sorpresa 1");
        Sorpresa sorp2 = new Sorpresa("Sorpresa 2");
           
        mazo.alMazo (sorp1);
        mazo.alMazo (sorp2);
        
        System.out.println ("Siguiente sorpresa: " + mazo.siguiente().getNombre());
        
        mazo.inhabilitarCartaEspecial(sorp2);
        System.out.println ("Leer evento: " + diario.leerEvento());

        mazo.habilitarCartaEspecial(sorp2);
        System.out.println ("Leer evento: " + diario.leerEvento());

        //Apartado 7

        
        

       
        


        
        
        
        
        
    
    }
}
