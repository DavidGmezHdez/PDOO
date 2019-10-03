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
    
    }
    
    public boolean construirCasa(int ip){
        return this.jugadores.get(this.indiceJugadorActual).construirCasa(ip);
    }
    
    public boolean construirHotel(int ip){
        return this.jugadores.get(this.indiceJugadorActual).construirHotel(ip);
    }
    
    private void contabilizarPasosPorSalida(Jugador jugadorActual){
        if(tablero.getPorSalida()>0)
            jugadores.get(indiceJugadorActual).pasaPorSalida();
        //Como hacer para que cobre todas las veces que ha pasado por la salida en su turno actual.
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
    
    }
    
    private void inicializarMazoSorpresas(Tablero tablero){
        //Leer las reglas para saber la cantidad de cartas sorpresa
        
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
