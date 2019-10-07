package Civitas;
import java.util.ArrayList;
public class CivitasJuego {
    private int indiceJugadorActual;
    private ArrayList<Jugador> jugadores;
    private MazoSorpresas mazo;
    private Tablero tablero;
    private GestorEstados gestorEstados;
    private EstadosJuego estado;
    
    private static Dado dado = Dado.getInstance();
    
    CivitasJuego(ArrayList<String> nombres){
        for(int i=0; i<nombres.size(); i++){
            jugadores.add(new Jugador(nombres.get(i)));
        }
        
        
        gestorEstados = new GestorEstados();
        gestorEstados.estadoInicial();
        this.indiceJugadorActual = dado.quienEmpieza(nombres.size());
        mazo = new MazoSorpresas();
        
        this.inicializarTablero(mazo);
        this.inicializarMazoSorpresas(tablero);
        
        
    }
    
    
    private void avanzaJugador(){
        
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
        //return this.jugadores.get(this.indiceJugadorActual).comprar(titulo);
    }
    
    public boolean construirCasa(int ip){
        return this.jugadores.get(this.indiceJugadorActual).construirCasa(ip);
    }
    
    public boolean construirHotel(int ip){
        return this.jugadores.get(this.indiceJugadorActual).construirHotel(ip);
    }
    
    private void contabilizarPasosPorSalida(Jugador jugadorActual){
        if(tablero.getPorSalida()>0)
            for(int i=0;i<tablero.getPorSalida();i++)
                jugadores.get(indiceJugadorActual).pasaPorSalida();
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
    
    private void inicializarMazoSorpresas(Tablero tablero){
        //Leer las reglas para saber la cantidad de cartas sorpresa
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
        
        this.tablero.añadeCasilla(calle_willyrex);
        this.tablero.añadeCasilla(calle_guerrero);
        this.tablero.añadeCasilla(calle_picaporte);
        this.tablero.añadeCasilla(calle_cabra);
        this.tablero.añadeCasilla(calle_giorgio);
        this.tablero.añadeCasilla(calle_potter);
        this.tablero.añadeCasilla(calle_petunia);
        this.tablero.añadeCasilla(calle_motorola);
        this.tablero.añadeCasilla(calle_focus);
        this.tablero.añadeCasilla(calle_rengar);
        this.tablero.añadeCasilla(calle_yisus);
        this.tablero.añadeCasilla(calle_ruby);
        this.tablero.añadeCasilla(calle_java);
        this.tablero.añadeCasilla(calle_diseño);
        this.tablero.añadeCasilla(calle_net);
        this.tablero.añadeCasilla(calle_anchoa);
        this.tablero.añadeCasilla(calle_frijoles);
        this.tablero.añadeCasilla(calle_caramelo);
        this.tablero.añadeCasilla(calle_fideo);
    }
    
    private void pasarTurno(){
        this.indiceJugadorActual = (this.indiceJugadorActual+1)%this.jugadores.size();
    }
    
    private ArrayList<Jugador >ranking(){
    
    }
    
    private boolean salirCarcelPagando(){
        return this.jugadores.get(this.indiceJugadorActual).salirCarcelPagando();
    }
    
    public boolean salirCarcelTirando(){
        return this.jugadores.get(this.indiceJugadorActual).salirCarcelPagando();
    }
    
    public OperacionesJuego siguientePaso(){
    
    }
    
    public void siguientePasoCompletado(OperacionesJuego operacion){
        this.estado = this.gestorEstados.siguienteEstado(jugadores.get(indiceJugadorActual), this.estado, operacion);
    }
    
    boolean vender(int ip){
        return this.jugadores.get(this.indiceJugadorActual).vender(ip);
    }
}
