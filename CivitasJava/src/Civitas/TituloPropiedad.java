package Civitas;
public class TituloPropiedad {

    private String nombre;
    private float alquilerBase;
    private static float factorInteresesHipoteca = (float) 1.1;
    private float factorRevalorización;
    private float hipotecaBase;
    private boolean hipotecado;
    private float precioCompra;
    private float precioEdificar;
    private int numCasas;
    private int numHoteles;
    
    Jugador propietario;


    public TituloPropiedad(String nombre, float alquilerBase, float factorRevalorización, 
            float hipotecaBase, float precioCompra, float precioEdificar) {
        this.nombre = nombre;
        this.alquilerBase = alquilerBase;
        this.factorRevalorización = factorRevalorización;
        this.hipotecaBase = hipotecaBase;
        this.precioCompra = precioCompra;
        this.precioEdificar = precioEdificar;
        this.propietario = null;
        this.numCasas = 0;
        this.numHoteles = 0;
        this.hipotecado = false;
    }
    
        
    
    void actualizaPropietarioPorConversion(Jugador jugador){
    
    }
    
    boolean cancelarHipoteca(Jugador jugador){
        throw new UnsupportedOperationException("No implementado");
    }
    /*
    boolean cancelarHipoteca(Jugador jugador){
        if(this.hipotecado && this.esEsteElPropietario(jugador)){
            jugador.paga(this.getImporteCancelarHipoteca());
            this.hipotecado = false;
            return true;
        }
        else
            return false;
    }
    */
    
    int cantidadCasasHoteles(){
        return this.getNumCasas() + this.getNumHoteles();
    }
    
    boolean comprar(Jugador jugador){
        throw new UnsupportedOperationException("No implementado");
    }
    
    boolean construirCasa(Jugador jugador){
       throw new UnsupportedOperationException("No implementado");
    }
    
    boolean construirHotel(Jugador jugador){
        throw new UnsupportedOperationException("No implementado");
    }
    /*
    boolean comprar(Jugador jugador){
        if(this.tienePropietario()){
            return false;
        }
        else{
            this.propietario = jugador;
            jugador.paga(this.precioCompra);
            return true;
        }
    }
  
    
    boolean construirCasa(Jugador jugador){
        boolean resultado = false;
        if(this.esEsteElPropietario(jugador)){
            jugador.paga(this.precioEdificar);
            this.numCasas++;
            resultado = true;
        }
        return resultado;
    }
    
    boolean construirHotel(Jugador jugador){
        boolean resultado = false;
        if(this.esEsteElPropietario(jugador)){
            jugador.paga(this.precioEdificar);
            this.numHoteles++;
            resultado = true;
        }
        return resultado;
    }*/
    
    boolean derruirCasas(int n,Jugador jugador){
        if(this.esEsteElPropietario(jugador) && this.getNumCasas() >= n){
            this.numCasas -= n;
            return true;
        }
        else
            return false;
    }
    
    private boolean esEsteElPropietario(Jugador jugador){
        return this.propietario == jugador;
    }
    
    public boolean getHipotecado(){
        return this.hipotecado;
    }
    
    float getImporteCancelarHipoteca(){
        return this.hipotecaBase * this.factorInteresesHipoteca;
    }
    
    float getImporteHipoteca(){
        return this.hipotecaBase;
    }        

    String getNombre() {
        return nombre;
    }


    int getNumCasas() {
        return numCasas;
    }

    int getNumHoteles() {
        return numHoteles;
    }
    
    float getPrecioAlquiler() {
        if(this.hipotecado || this.propietarioEncarcelado())
            return 0;
        else
            return 1234;
    }

    float getPrecioCompra() {
        return precioCompra;
    }

    float getPrecioEdificar() {
        return precioEdificar;
    }
    
    private float getPrecioVenta(){
        return this.getPrecioCompra() + 
                (this.cantidadCasasHoteles() * this.precioEdificar * this.factorRevalorización);
    }


    Jugador getPropietario() {
        return propietario;
    }
    
    boolean hipotecar(Jugador jugador){
        throw new UnsupportedOperationException("No implementado");
    }
    /*
    boolean hipotecar(Jugador jugador){
        if(!this.hipotecado && this.esEsteElPropietario(jugador)){
            jugador.recibe(this.hipotecaBase);
            this.hipotecado = true;
            return true;
        }
        else
            return false;
    }
    */
    
    private boolean propietarioEncarcelado(){
        return propietario.isEncarcelado();
    }
    
    boolean tienePropietario(){
        return this.propietario != null;
    }
    
    void tramitarAlquiler(Jugador jugador){
        if(this.tienePropietario() && !this.esEsteElPropietario(jugador)){
            jugador.pagaAlquiler(alquilerBase);
            propietario.recibe(alquilerBase);
        }
    }
    
    boolean vender(Jugador jugador){
        if(this.esEsteElPropietario(jugador) && !this.hipotecado){
            jugador.recibe(this.getPrecioVenta());
            return true;
        }
        else
            return false;
    }
    
    
    @Override
    public String toString() {
        return "TituloPropiedad{" + "nombre=" + nombre 
                + ", alquilerBase=" + alquilerBase + ", factorInteresesHipoteca=" 
                + factorInteresesHipoteca + ", factorRevalorizaci\u00f3n=" + factorRevalorización 
                + ", hipotecaBase=" + hipotecaBase + ", hipotecado=" + hipotecado + ", precioCompra=" 
                + precioCompra + ", precioEdificar=" + precioEdificar + ", numCasas=" + numCasas + ", numHoteles=" 
                + numHoteles + ", propietario=" + propietario + '}';
    }
}

