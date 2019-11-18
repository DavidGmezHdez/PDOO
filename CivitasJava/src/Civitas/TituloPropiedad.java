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
        this.propietario = jugador;
    }
    
    
    boolean cancelarHipoteca(Jugador jugador){
        boolean resultado = false;
        if (this.hipotecado && this.esEsteElPropietario(jugador)){
            this.propietario.paga(this.getImporteCancelarHipoteca());
            this.hipotecado = false;
            resultado = true;
            
        }
        return resultado;
    }
    
    int cantidadCasasHoteles(){
        return this.getNumCasas() + this.getNumHoteles();
    }
    
    boolean comprar(Jugador jugador){
        boolean resultado = false;
        if(!this.tienePropietario()){
            this.propietario = jugador;
            this.propietario.paga(this.precioCompra);
            resultado = true;
        }
        return resultado;
    }
    
    
    
    boolean construirCasa(Jugador jugador){
       boolean resultado = false;
        if(this.esEsteElPropietario(jugador)){
            this.propietario.paga(this.precioEdificar);
            this.numCasas++;
            resultado = true;
        }
        return resultado;
    }
    
    
    boolean construirHotel(Jugador jugador){
        boolean resultado = false;
        if(this.esEsteElPropietario(jugador)){
            this.propietario.paga(this.precioEdificar);
            this.numHoteles++;
            resultado = true;
        }
        return resultado;
    }
    
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
    

    public String getNombre() {
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
            return new Float(this.alquilerBase*(1+(this.numCasas*0.5)+(this.numHoteles*0.5)));
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
        boolean salida = false;
        if(!this.hipotecado && this.esEsteElPropietario(jugador)){
            this.propietario.recibe(this.getImporteHipoteca());
            this.hipotecado = true;
            salida = true;
        }
        return salida;
    }
    
    private boolean propietarioEncarcelado(){
        return propietario.isEncarcelado();
    }
    
    
    boolean tienePropietario(){
        return this.propietario != null;
    }
    
    
    void tramitarAlquiler(Jugador jugador){
        if(this.tienePropietario() && !this.esEsteElPropietario(jugador)){
            float precio = getPrecioAlquiler();
            jugador.pagaAlquiler(precio);
            propietario.recibe(precio);
        }
    }
    
    
    boolean vender(Jugador jugador){
        if(this.esEsteElPropietario(jugador) && !this.getHipotecado()){
            this.propietario.recibe(this.getPrecioVenta());
            this.propietario = null;
            this.numCasas = 0;
            this.numHoteles = 0;
            return true;
        }
        else
            return false;
    }
    
    
    @Override
    public String toString() {
        
        if(this.propietario == null){
         return "TituloPropiedad {" + "\n Nombre = " + nombre 
                + "\n  AlquilerBase = " + alquilerBase + "\n  FactorInteresesHipoteca = " 
                + factorInteresesHipoteca + "\n  FactorRevalorizacion = " + factorRevalorización 
                + "\n  HipotecaBase = " + hipotecaBase + "\n  Hipotecado = " + hipotecado + "\n  PrecioCompra = " 
                + precioCompra + "\n  PrecioEdificar = " + precioEdificar + "\n  NumCasas = " + numCasas 
                + "\n  NumHoteles = " + numHoteles + "\n  Propietario = Sin propietario}";
        
        
        }
        else{
        return "TituloPropiedad {" + " Nombre = " + nombre 
                + ", alquilerBase = " + alquilerBase + ", factorInteresesHipoteca = " 
                + factorInteresesHipoteca + ", factorRevalorizacion = " + factorRevalorización 
                + ", hipotecaBase = " + hipotecaBase + ", hipotecado = " + hipotecado + ", precioCompra = " 
                + precioCompra + ", precioEdificar = " + precioEdificar + ", numCasas = " + numCasas 
                + ", numHoteles = " + numHoteles + ", propietario = " + propietario.getNombre() + "}\n";
        }
        
        
    }
}

