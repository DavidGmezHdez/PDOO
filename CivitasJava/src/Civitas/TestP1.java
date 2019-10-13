package Civitas;

import java.util.Scanner;
import java.util.ArrayList;

public class TestP1 {

    private static Dado dado = Dado.getInstance();
    private static Diario diario = Diario.getInstance();
    private static TipoCasilla mostrarCasilla;
    private static TipoSorpresa mostrarSorpresa;
    private static OperacionesJuego mostrarOperaciones;
    private static EstadosJuego mostrarEstados;
    private static MazoSorpresas mazo = new MazoSorpresas();
    private static final Scanner in = new Scanner (System.in);

    
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
        System.out.println ("Introduce el tamaño del tablero");
        int tamTablero = in.nextInt();
        
        System.out.println ("Introduce la posición de la cárcel");
        int posCarcel = in.nextInt();
        if(posCarcel>tamTablero){
            posCarcel = tamTablero - 1;
        }

        Tablero tablero = new Tablero(posCarcel);

        Casilla calle_willyrex = new Casilla ("Calle Willyrex");
        Casilla calle_guerrero = new Casilla ("Calle Guerrero");
        Casilla calle_picaporte = new Casilla ("Calle Picaporte");
        Casilla calle_cabra = new Casilla ("Calle Cabra");
        Casilla calle_giorgio = new Casilla ("Calle Giorgio");
        Casilla calle_potter = new Casilla ("Calle Potter");
        Casilla calle_petunia = new Casilla ("Calle Petunia");
        Casilla calle_motorola = new Casilla ("Calle Motorola");
        Casilla calle_focus = new Casilla ("Calle Focus");
        Casilla calle_rengar = new Casilla ("Calle Rengar");
        Casilla calle_yisus = new Casilla ("Calle Yisus");
        Casilla calle_ruby = new Casilla ("Calle Ruby");
        Casilla calle_java = new Casilla ("Calle Java");
        Casilla calle_diseño = new Casilla ("Calle Diseño");
        Casilla calle_net = new Casilla ("Calle Net");
        Casilla calle_anchoa = new Casilla ("Calle Anchoa");
        Casilla calle_frijoles = new Casilla ("Calle Frijoles");
        Casilla calle_caramelo = new Casilla ("Calle Caramelo");
        Casilla calle_fideo = new Casilla ("Calle Fideo");
        
        tablero.añadeCasilla(calle_willyrex);
        tablero.añadeCasilla(calle_guerrero);
        tablero.añadeCasilla(calle_picaporte);
        tablero.añadeCasilla(calle_cabra);
        tablero.añadeCasilla(calle_giorgio);
        tablero.añadeCasilla(calle_potter);
        tablero.añadeCasilla(calle_petunia);
        tablero.añadeCasilla(calle_motorola);
        tablero.añadeCasilla(calle_focus);
        tablero.añadeCasilla(calle_rengar);
        tablero.añadeCasilla(calle_yisus);
        tablero.añadeCasilla(calle_ruby);
        tablero.añadeCasilla(calle_java);
        tablero.añadeCasilla(calle_diseño);
        tablero.añadeCasilla(calle_net);
        tablero.añadeCasilla(calle_anchoa);
        tablero.añadeCasilla(calle_frijoles);
        tablero.añadeCasilla(calle_caramelo);
        tablero.añadeCasilla(calle_fideo);
        
        for(int i=0; i < tamTablero; i++){
            System.out.println("Casilla num " + i + ": " + tablero.getCasilla(i).getNombre());
        }
        
        System.out.println("Ultimo resultado " + dado.getUltimoResultado());
        
        int despues = dado.tirar();
        System.out.println("Despues: " + despues);

        int tirada = tablero.calcularTirada(dado.getUltimoResultado(), despues);
        
        System.out.println("Pos destino: " + tablero.nuevaPosicion(dado.getUltimoResultado(), tirada));
        
        diario.ocurreEvento("hola");
        diario.ocurreEvento("adios");
        
        //POR QUE SI PONGO EL BUCLE DE 0 A <2NO ME SALE EL ÚLTIMO EVENTO ????????????
        for(int i = 0; i <= diario.getEventos().size();i++){
            System.out.println("Evento " + i + ": " + diario.leerEvento());
        }
        
        System.out.println("Eventos pendientes: " + diario.eventosPendientes());
        
        diario.ocurreEvento("Que tal");
        System.out.println("Eventos pendientes: " + diario.eventosPendientes());

    
    }
}

