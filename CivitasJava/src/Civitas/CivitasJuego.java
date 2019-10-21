package Civitas;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class CivitasJuego {
    private int indiceJugadorActual;
    
    private ArrayList<Jugador> jugadores;
    private MazoSorpresas mazo;
    private Tablero tablero;
    private GestorEstados gestorEstados;
    private EstadosJuego estado;
    
    
    CivitasJuego(ArrayList<String> nombres){
      jugadores = new ArrayList<>();
      for(int i=0; i<nombres.size(); i++){
            jugadores.add(new Jugador(nombres.get(i)));
        }
        
        gestorEstados = new GestorEstados();
        gestorEstados.estadoInicial();
        
        this.indiceJugadorActual = Dado.getInstance().quienEmpieza(jugadores.size());
        mazo = new MazoSorpresas();
        
        this.inicializarTablero(mazo);
        this.inicializarMazoSorpresas(tablero);
        
    }
    
    
    private void avanzaJugador(){
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
        int posicionActual = jugadorActual.getNumCasillaActual();
        int tirada = Dado.getInstance().tirar();
        int posicionNueva = this.tablero.nuevaPosicion(posicionActual, tirada);
        Casilla casilla = this.tablero.getCasilla(posicionNueva);
        this.contabilizarPasosPorSalida(jugadorActual);
        jugadorActual.moverACasilla(posicionNueva);
        casilla.recibeJugador(this.indiceJugadorActual, jugadores);
        this.contabilizarPasosPorSalida(jugadorActual);
    }
    
    
    public void actualizarInfo(){
        if(jugadores.get(indiceJugadorActual).enBancarrota())
            this.ranking();
        else
            System.out.println(jugadores.get(indiceJugadorActual).toString());
    }
    
    
    public boolean cancelarHipoteca(int ip){
        return this.jugadores.get(this.indiceJugadorActual).cancelarHipoteca(ip);
    }

    
    public boolean comprar(){
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
        int numCasillaActual = jugadorActual.getNumCasillaActual();
        Casilla casilla = this.tablero.getCasilla(numCasillaActual);
        TituloPropiedad titulo = casilla.getTituloPropiedad();
        boolean res = jugadorActual.comprar(titulo);
        
        return res;
    }
    
    
    public boolean construirCasa(int ip){
        return this.jugadores.get(this.indiceJugadorActual).construirCasa(ip);
    }
    
    
    public boolean construirHotel(int ip){
        return this.jugadores.get(this.indiceJugadorActual).construirHotel(ip);
    }
    
    
    private void contabilizarPasosPorSalida(Jugador jugadorActual){
        while(this.tablero.getPorSalida() > 0)
                jugadorActual.pasaPorSalida();
    }
    
    
    public boolean finalDelJuego(){
        boolean fin = false;
        for(int i=0;i<jugadores.size();i++){
            if(jugadores.get(i).enBancarrota())
                fin = true;
        }
        return fin;
    }
    
    
    public Casilla getCasillaActual(){
        return this.tablero.getCasilla(this.jugadores.get(indiceJugadorActual).getNumCasillaActual());
    }
    
    
    public Jugador getJugadorActual(){
        return this.jugadores.get(indiceJugadorActual);
    }
    
    
    public boolean hipotecar(int ip){
        return this.jugadores.get(this.indiceJugadorActual).hipotecar(ip);
    }
    
    
    public String infoJugadorTexto(){
        return jugadores.get(indiceJugadorActual).toString();
    }
    
    public int getIndiceJugadorActual(){
        return this.indiceJugadorActual;
    }
            
    
    
    private void inicializarMazoSorpresas(Tablero tablero){
        mazo.alMazo(new Sorpresa (TipoSorpresa.IRCARCEL,tablero));

        mazo.alMazo(new Sorpresa (TipoSorpresa.IRCASILLA, tablero,10,"Pides un Uber que te lleva la casilla mitad del tablero"));

        mazo.alMazo(new Sorpresa (TipoSorpresa.IRCASILLA,tablero,5,"Alquilas una bici amarilla que te lleva a la casilla 5, luego la tiras al río"));

        mazo.alMazo(new Sorpresa (TipoSorpresa.PORJUGADOR,200,"Pides a la gente que te de dinero para comprar un regalo en común, pero acabas quedándotelo tu para ir a Pedro"));

        mazo.alMazo(new Sorpresa (TipoSorpresa.PORJUGADOR, -50, "Dijiste que invitarías a chupitos pero no lo hiciste, pagas 50 euros a cada uno"));

        mazo.alMazo(new Sorpresa (TipoSorpresa.PAGARCOBRAR,500,"Recibes un sobre con la letra B escrita, recibes 500 euros"));

        mazo.alMazo(new Sorpresa (TipoSorpresa.PAGARCOBRAR,200,"Te vas a la ruleta, crees ganar pero el ruso de al lado te hace la jugada, pierdes 200 euros"));

        mazo.alMazo(new Sorpresa (TipoSorpresa.PORCASAHOTEL,300,"Gracias a la burbuja del alquiler, la gente compra más casas y hay más turistas en hoteles, ganas 300 euros."));

        mazo.alMazo(new Sorpresa (TipoSorpresa.PORCASAHOTEL, 500, "Mala suerte, Hacienda te ha pillado saltándote la declaración de bienes, debes 500 euros"));

        mazo.alMazo(new Sorpresa (TipoSorpresa.SALIRCARCEL,mazo));

    }
    
    
    private void inicializarTablero(MazoSorpresas mazo){
        this.tablero = new Tablero(14);
        
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Willyrex", 625, 75, 12, 350, 400)));
        this.tablero.añadeCasilla(new Casilla(mazo,"Sorpresa"));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Guerrero", 700, 50, 10, 550, 250)));
        this.tablero.añadeJuez();
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Picaporte", 740, 55, 19, 300, 575)));
        this.tablero.añadeCasilla(new Casilla("Parking: Coche Seguro, Precio !Barato"));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Petunia", 925, 90, 17, 875, 600)));
        this.tablero.añadeCasilla(new Casilla(mazo,"Sorpresa"));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Ruby", 500, 95, 14, 175, 275)));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Focus", 830, 100, 16, 675, 500)));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Motorola", 777, 85, 15, 750, 470)));
        this.tablero.añadeCasilla(new Casilla(500,"Impuesto"));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Rengar", 900, 80, 12, 200, 450)));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Potter", 675, 60, 20, 475, 750)));
        this.tablero.añadeCasilla(new Casilla(mazo,"Sorpresa"));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Jesucristo", 1000, 60, 11, 250, 325)));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Giorgio", 890, 65, 13, 1000, 300)));
        this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Fideo", 550, 80, 15, 600, 750)));
    }
    
    
    private void pasarTurno(){
        this.indiceJugadorActual = (this.indiceJugadorActual+1)%this.jugadores.size();
    }
    
    
    private ArrayList<Jugador >ranking(){
        Collections.sort(jugadores);
        return jugadores;
    }
    
    
    public boolean salirCarcelPagando(){
        return this.jugadores.get(this.indiceJugadorActual).salirCarcelPagando();
    }
    
    
    public boolean salirCarcelTirando(){
        return this.jugadores.get(this.indiceJugadorActual).salirCarcelTirando();
    }
    
    
    public OperacionesJuego siguientePaso(){
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
        OperacionesJuego operacion = this.gestorEstados.operacionesPermitidas(jugadorActual, this.estado);
        
        if(operacion == OperacionesJuego.PASAR_TURNO){
            pasarTurno();
            siguientePasoCompletado(operacion);
        } else {
            avanzaJugador();
            siguientePasoCompletado(operacion);
        }
        return operacion;
    }
    
    
    public void siguientePasoCompletado(OperacionesJuego operacion){
        this.estado = this.gestorEstados.siguienteEstado(jugadores.get(indiceJugadorActual), this.estado, operacion);
    }
    
    
    public boolean vender(int ip){
        return this.jugadores.get(this.indiceJugadorActual).vender(ip);
    }
    
}
