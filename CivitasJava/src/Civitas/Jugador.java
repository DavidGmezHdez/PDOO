package Civitas;

import java.util.ArrayList;
import java.lang.Float;

public class Jugador implements Comparable<Jugador> {
    protected static int CasasMax = 4;
    protected static int HotelesMax = 4;
    protected static int CasasPorHotel = 4;
    protected static float PasoPorSalida = 1000;
    protected static float PrecioLibertad = 200;
    private static float SaldoInicial = 7500;
    
    
    private String nombre;
    private int numCasillaActual;
    private boolean puedeComprar;
    private float saldo; 
    protected boolean encarcelado;
    
    
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
        for(int i=0;i<jug.propiedades.size();i++){
            this.propiedades.add(jug.propiedades.get(i));
        }
        this.saldo = jug.saldo;
    }
    
    
    boolean cancelarHipoteca(int ip){
        boolean result = false;
        if (this.encarcelado)
            return result;
        
        if(this.existeLaPropiedad(ip)){
            TituloPropiedad propiedad = propiedades.get(ip);
            float cantidad = propiedad.getImporteCancelarHipoteca();
            boolean puedoGastar = this.puedoGastar(cantidad);
            if(puedoGastar){
                result = propiedad.cancelarHipoteca(this);
                if(result){
                    Diario.getInstance().ocurreEvento("El jugador " + nombre + " cancela la hipoteca de la propiedad  " + ip);
                }
            }
        }
        return result;
    }
    

    int cantidadCasasHoteles() {
        int total = 0;
        for (TituloPropiedad propiedad : propiedades) {
            total = total + propiedad.getNumCasas() + propiedad.getNumHoteles();
        }
        return total;
    }
    

    @Override
    public int compareTo(Jugador otro){
        return (new Float(this.getSaldo()).compareTo(otro.getSaldo()));
    }
    
    
    boolean comprar(TituloPropiedad titulo){
        boolean result = false;
        if (this.encarcelado)
            return result;
        
        if(this.puedeComprar){
            if(this.puedoGastar(titulo.getPrecioCompra())){
                result = titulo.comprar(this);
                if(result){
                    this.propiedades.add(titulo);
                    Diario.getInstance().ocurreEvento("El jugador " + nombre + " compra la propiedad  " + titulo.toString());
                }
            }
        }
        this.puedeComprar = false;
        return result;
    } 
    
    
    boolean construirCasa(int ip){
        throw new UnsupportedOperationException("No implementado");
    } 
    
    
    boolean construirHotel(int ip){
        boolean result = false;
        if (this.encarcelado)
            return result;
        
        if(this.existeLaPropiedad(ip)){
            TituloPropiedad propiedad = this.propiedades.get(ip);
            if(this.puedoEdificarHotel(propiedad)){
                result = propiedad.construirHotel(this);
                propiedad.derruirCasas(getCasasPorHotel(), this);
                Diario.getInstance().ocurreEvento("El jugador " + nombre + " construye hotel en la propiedad  " + ip);
            }
        }
        return result;
    } 
    
    
    protected boolean debeSerEncarcelado(){
        if (this.encarcelado)
            return false;
        else if(this.tieneSalvoconducto()){
            this.perderSalvoConducto();
            Diario.getInstance().ocurreEvento("Jugador " + this.nombre + 
                    "usa salvoconducto, no entra en la cárcel");
            return false;
        }
        else
            return true;
    } 
    
    
    boolean enBancarrota(){
        return this.saldo <= 0;
    } 
    
    
    boolean encarcelar(int numCasillaCarcel){
        if(this.debeSerEncarcelado()){
            this.moverACasilla(numCasillaCarcel);
            this.encarcelado = true;
            Diario.getInstance().ocurreEvento("Jugador " + this.nombre + 
                    " encarcelado, movido a cárcel");
        }
        return this.encarcelado;
    }
    
    
    private boolean existeLaPropiedad(int ip){
        return propiedades.get(ip) != null;
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
    
    
    private static float getPremioPasoPorSalida() {
        return PasoPorSalida;
    }
    

    private static float getPrecioLibertad() {
        return PrecioLibertad;
    }
    
    
    public ArrayList<TituloPropiedad> getPropiedades() {
        return propiedades;
    }
    
    
    boolean getPuedeComprar() {
        return puedeComprar;
    }
    

    protected float getSaldo() {
        return saldo;
    }
    
    
    boolean hipotecar(int ip){
        boolean result = false;
        if (this.encarcelado)
            return result;
        
        if(this.existeLaPropiedad(ip)){
            TituloPropiedad propiedad = this.propiedades.get(ip);
            result = propiedad.hipotecar(this);
        }
        if (result)
                Diario.getInstance().ocurreEvento("El jugador " + nombre + " hipoteca la propiedad  " + ip);
        return result;
    }
    
    
    public boolean isEncarcelado() {
        return encarcelado;
    }
    
    
    boolean modificarSaldo(float cantidad){
        this.saldo+=cantidad;
        Diario.getInstance().ocurreEvento("Saldo del jugador " + this.nombre + ""
                + "modificado: " + cantidad);
        return true;
    }
    
    
    boolean moverACasilla(int numCasilla){
        if(this.encarcelado)
            return false;
        else{
            this.numCasillaActual = numCasilla;
            this.puedeComprar = false;
            Diario.getInstance().ocurreEvento("Jugador " + this.nombre + 
                    "moviendose a casilla " + numCasilla);
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
    
    
    boolean paga(float cantidad){
        return this.modificarSaldo(cantidad*-1);
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
        this.modificarSaldo(PasoPorSalida);
        Diario.getInstance().ocurreEvento("Jugador " + this.nombre + " pasa por salida");
        return true;
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
        return propiedad.getNumCasas()<4 && this.saldo>propiedad.getPrecioEdificar();
    }
    
    
    private boolean puedoEdificarHotel(TituloPropiedad propiedad){
        return this.puedoGastar(propiedad.getPrecioEdificar()) && propiedad.getNumHoteles() < getHotelesMax() 
                && propiedad.getNumCasas()>=getCasasPorHotel();
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
        boolean salir=false;
        if(this.encarcelado && this.puedeSalirCarcelPagando()){
            this.paga(PrecioLibertad);
            this.encarcelado = false;
            Diario.getInstance().ocurreEvento("Jugador " + this.nombre + " sale de carcel pagando");
            salir=true;
        }
        return salir;
    }
    
    
    boolean salirCarcelTirando(){
        boolean salir=false;
        if(this.encarcelado && Dado.getInstance().salgoDeLaCarcel()){
            this.encarcelado = false;
            Diario.getInstance().ocurreEvento("Jugador " + this.nombre + " sale de carcel tirando");
            salir=true;
        }
        return salir;
    }
    
    
    boolean tieneAlgoQueGestionar(){
        return !this.propiedades.isEmpty();
    }
    
    
    boolean tieneSalvoconducto(){
        return this.salvoconducto != null;
    }
    
    
    boolean vender(int ip){
        if(this.encarcelado){
            return false;
        } else if(this.existeLaPropiedad(ip) && this.propiedades.get(ip).vender(this)){
            Diario.getInstance().ocurreEvento("Propiedad " + this.propiedades.get(ip).getNombre() + " vendida por el jugador " + this.nombre );
            this.propiedades.remove(ip);
            return true;
        } else {
            return false;
        }            
    }
    
    
    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", numCasillaActual=" + 
                numCasillaActual + ", puedeComprar=" + puedeComprar + ", saldo=" + 
                saldo + ", encarcelado=" + encarcelado + ", propiedades=" + 
                propiedades + '}';
    }

}

