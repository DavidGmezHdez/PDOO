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
    private static Dado dado = Dado.getInstance();
    
    
    public CivitasJuego(ArrayList<String> nombres){
        jugadores = new ArrayList<>();
        for(int i=0; i<nombres.size(); i++){
              jugadores.add(new Jugador(nombres.get(i)));
        }
        
        gestorEstados = new GestorEstados();
        estado = gestorEstados.estadoInicial();        
        
        this.indiceJugadorActual = dado.quienEmpieza(jugadores.size());
        mazo = new MazoSorpresas();
     
        this.inicializarTablero(this.mazo);
        this.inicializarMazoSorpresas(this.tablero);
        
    }
    
    
    private void avanzaJugador(){
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
        int posicionActual = jugadorActual.getNumCasillaActual();
        int tirada = dado.tirar();
        int posicionNueva = this.tablero.nuevaPosicion(posicionActual, tirada);
        System.out.println(" Tirada: " + tirada + " Posicion nueva: " + posicionNueva);
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
        
        TituloPropiedad titulo = ((CasillaCalle) casilla).getTituloPropiedad();
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
    
    public void setDebugDado(boolean deb){
        dado.setDebug(deb);
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
        mazo.alMazo(new SorpresaCarcel (tablero));

        mazo.alMazo(new SorpresaIrACasilla (tablero,10,"Pides un Uber que te lleva la casilla mitad del tablero"));

        mazo.alMazo(new SorpresaIrACasilla (tablero,5,"Alquilas una bici amarilla que te lleva a la casilla 5, luego la tiras al río"));

        mazo.alMazo(new SorpresaPorJugador (200,"Pides a la gente que te de dinero para comprar un regalo en común, pero acabas quedándotelo tu para ir a Pedro"));

        mazo.alMazo(new SorpresaPorJugador (-50, "Dijiste que invitarías a chupitos pero no lo hiciste, pagas 50 euros a cada uno"));

        mazo.alMazo(new SorpresaPagarCobrar (500,"Recibes un sobre con la letra B escrita, recibes 500 euros"));

        mazo.alMazo(new SorpresaPagarCobrar (-200,"Te vas a la ruleta, crees ganar pero el ruso de al lado te hace la jugada, pierdes 200 euros"));

        mazo.alMazo(new SorpresaPorCasaHotel (300,"Gracias a la burbuja del alquiler, la gente compra más casas y hay más turistas en hoteles, ganas 300 euros."));

        mazo.alMazo(new SorpresaPorCasaHotel (-500, "Mala suerte, Hacienda te ha pillado saltándote la declaración de bienes, debes 500 euros"));

        mazo.alMazo(new SorpresaSalirCarcel (mazo));

    }
    
    
    private void inicializarTablero(MazoSorpresas mazo){
        this.tablero = new Tablero(15);

        this.tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Willyrex", 625, (float)0.70, 12, 350, 400)));
        this.tablero.añadeCasilla(new CasillaSorpresa("Sorpresa",mazo));
        this.tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Guerrero", 700, (float)0.50, 10, 550, 250)));
        this.tablero.añadeJuez();
        this.tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Picaporte", 740, (float)0.55, 19, 300, 575)));
        this.tablero.añadeCasilla(new Casilla("Parking: Coche Seguro, Precio !Barato"));
        this.tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Petunia", 925, (float)0.40, 17, 875, 600)));
        this.tablero.añadeCasilla(new CasillaSorpresa("Sorpresa",mazo));
        this.tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Ruby", 500, (float)0.95, 14, 175, 275)));
        this.tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Focus", 830, 1, 16, 675, 500)));
        this.tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Motorola", 777, (float)0.70, 15, 750, 470)));
        this.tablero.añadeCasilla(new CasillaImpuesto("Impuesto",500));
        this.tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Rengar", 900, (float)0.80, 12, 200, 450)));
        this.tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Potter", 675, (float)0.2, 20, 475, 750)));
        this.tablero.añadeCasilla(new CasillaSorpresa("Sorpresa",mazo));
        this.tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Jesucristo", 1000, (float)0.9, 11, 250, 325)));
        this.tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Giorgio", 890, (float)0.3, 13, 1000, 300)));
        this.tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Fideo", 550, (float)0.65, 15, 600, 750)));
    }
    
    
    private void pasarTurno(){
        this.indiceJugadorActual = (this.indiceJugadorActual+1)%this.jugadores.size();
    }
    
    
    public ArrayList<Jugador >ranking(){
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
        } else if(operacion == OperacionesJuego.AVANZAR) {
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
