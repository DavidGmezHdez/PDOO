package Civitas;
import java.util.ArrayList;

public class Jugador implements Comparable<Jugador> {
    static int CasasMax = 4;
    static int HotelesMax = 4;
    static int CasasPorHotel = 4;
    static int PasoPorSalida = 1000;
    static int PrecioLibertad = 200;
    private static int SaldoInicial = 200;
    
    static final private Diario diario = Diario.getInstance();
    static final private Dado dado = Dado.getInstance();


    
    private String nombre;
    private int numCasillaActual;
    private boolean puedeComprar;
    private float saldo; 
    boolean encarcelado;
    private ArrayList<TituloPropiedad> propiedades;
    private Sorpresa salvoconducto;
    

    Jugador(String nom){
        this.nombre = nom;
        this.numCasillaActual = 0;
        this.puedeComprar = true;
        this.saldo = SaldoInicial;
        this.encarcelado = false;
        this.propiedades = new ArrayList<>();
    }
    
    protected Jugador(Jugador jug){
        this.nombre = jug.nombre;
        this.numCasillaActual = jug.numCasillaActual;
        this.puedeComprar = jug.puedeComprar;
        this.encarcelado = jug.encarcelado;
        this.propiedades = jug.propiedades;
        this.saldo = jug.saldo;
    }
    
    
    boolean cancelarHipoteca(int ip){
        
    } 
    
    int cantidadCasasHoteles(){
    
    }
    
    public int compareTo(Jugador otro){
    
    }
    
    boolean comprar(TituloPropiedad titulo){
    
    } 
    
    boolean construirCasa(int ip){
    
    } 
    
    boolean construirHotel(int ip){
    
    } 
    
    protected boolean debeSerEncarcelado(){
        if (this.encarcelado)
            return false;
        else if(this.tieneSalvoconducto()){
            this.perderSalvoConducto();
            diario.ocurreEvento("Jugador usa salvoconducto, no entra en la cárcel");
            return false;   
        }
        else
            return true;
    } 
    
    boolean enBancarrota(){
    
    } 
    
    boolean encarcelar(int numCasillaCarcel){
        if(this.debeSerEncarcelado()){
            this.moverACasilla(numCasillaCarcel);
            this.encarcelado = true;
            diario.ocurreEvento("Jugador encarcelado, movido a cárcel");
        }
        return this.encarcelado;
    }
    
    private boolean existeLaPropiedad(int ip){
    
    }
    

    private static int getCasasMax() {
        return CasasMax;
    }

    static int getCasasPorHotel() {
        return CasasPorHotel;
    }

    private static int getHotelesMax() {
        return HotelesMax;
    }
    
    protected String getNombre() {
        return nombre;
    }

    int getNumCasillaActual() {
        return numCasillaActual;
    }
    
    private static int getPasoPorSalida() {
        return PasoPorSalida;
    }

    private static int getPrecioLibertad() {
        return PrecioLibertad;
    }
    
    protected ArrayList<TituloPropiedad> getPropiedades() {
        return propiedades;
    }
    
    boolean getPuedeComprar() {
        return puedeComprar;
    }

    protected float getSaldo() {
        return saldo;
    }
    
    boolean hipotecar(int ip){
    
    }
    
    public boolean isEncarcelado() {
        return encarcelado;
    }
    
    boolean modificarSaldo(float cantidad){
        this.saldo+=cantidad;
        diario.ocurreEvento("Saldo del jugador modificado: " + cantidad);
        return true;
    }
    
    boolean moverACasilla(int numCasilla){
        if(this.encarcelado)
            return false;
        else{
            this.numCasillaActual = numCasilla;
            this.puedeComprar = false;
            diario.ocurreEvento("Jugador moviendose a casilla " + numCasilla);
            return true;
        }
    }
    
    boolean obtenerSalvoconducto(Sorpresa sorpresa){
        if(this.encarcelado)
            return false;
        else{
            this.salvoconducto = sorpresa;
            return true;
        }
    }
    
    boolean paga(float paga){
        return this.modificarSaldo(paga*-1);
    }
    
    boolean pagaAlquiler(float cantidad){
        if(this.encarcelado)
            return false;
        else
            return this.paga(cantidad);
    }
    
    boolean pagaImpuesto(float cantidad){
        if(this.encarcelado)
            return false;
        else
            return this.paga(cantidad);
    }
    
    boolean pasaPorSalida(){
    
    }
    
    private void perderSalvoConducto(){
        this.salvoconducto.usada();
        this.salvoconducto = null;
    }
    
    boolean puedeComprarCasilla(){
        if(this.encarcelado)
            this.puedeComprar = false;
        else
            this.puedeComprar = true;
        
        return this.puedeComprar;
    }
    
    private boolean puedeSalirCarcelPagando(){
        return this.saldo >= PrecioLibertad;
    }
    
    private boolean puedoEdificarCasa(TituloPropiedad propiedad){
    
    }
    
    private boolean puedoEdificarHotel(TituloPropiedad propiedad){
    
    }
    
    private boolean puedoGastar(float precio){
        if(this.encarcelado)
            return false;
        else
            return this.saldo >= precio;
    }
    
    boolean recibe(float cantidad){
        if(this.encarcelado)
            return false;
        else
            return this.modificarSaldo(cantidad);
    }
    
    boolean salirCarcelPagando(){
        if(this.encarcelado && this.puedeSalirCarcelPagando()){
            this.encarcelado = false;
            diario.ocurreEvento("Jugador " + this.nombre + " sale de carcel pagando");
        }
        
        return this.encarcelado;
    }
    
    boolean salirCarcelTirando(){
        
    
    }
    
    boolean tieneAlgoQueGestionar(){
        return !this.propiedades.isEmpty();
    }
    
    boolean tieneSalvoconducto(){
        return this.salvoconducto != null;
    }
    
    boolean vender(int ip){
        if(this.encarcelado)
            return false;
        else if(this.existeLaPropiedad(ip) && this.propiedades.get(ip).vender(this)){
            diario.ocurreEvento("Propiedad " + this.propiedades.get(ip).getNombre() + " vendida por el jugador " + this.nombre );
            this.propiedades.remove(ip);
            return true;
            
        }
        else 
            return false;
    }
    
    

    
    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", numCasillaActual=" + numCasillaActual + ", puedeComprar=" + puedeComprar + ", saldo=" + saldo + ", encarcelado=" + encarcelado + ", propiedades=" + propiedades + '}';
    }

}
