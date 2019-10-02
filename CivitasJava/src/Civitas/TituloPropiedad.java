<<<<<<< HEAD

package Civitas;
public class TituloPropiedad {
    private String nombre;
    private float alquilerBase;
    private float factorInteresesHipoteca = (float) 1.1;
    private float factorRevalorización;
    private float hipotecaBase;
    private boolean hipotecado;
    private float precioCompra;
    private float precioEdificar;
    private int numCasas;
    private int numHoteles;
    
    Jugador propietario;



    public TituloPropiedad(String nombre, float alquilerBase, float factorRevalorización, float hipotecaBase, float precioCompra, float precioEdificar) {
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

    public float getPrecioAlquiler() {
        if(hipotecado = true || propietario.isEncarcelado())
            return 0;
        else
            return this.alquilerBase;
    }

    public float getImporteCancelarHipoteca() {
        return this.hipotecaBase * this.factorInteresesHipoteca;
    }
    
    private boolean esEsteElPropietario(Jugador jugador){
        return this.propietario == jugador;
    }
    protected boolean cancelarHipoteca(Jugador jugador){
        if (this.hipotecado && esEsteElPropietario(jugador)){
            //Necesito jugador
        
        }
        
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
=======

package Civitas;
public class TituloPropiedad {

    private String nombre;
    private float alquilerBase;
    private float factorInteresesHipoteca = (float) 1.1;
    private float factorRevalorización;
    private float hipotecaBase;
    private boolean hipotecado;
    private float precioCompra;
    private float precioEdificar;
    private int numCasas;
    private int numHoteles;
    
    Jugador propietario;



    public TituloPropiedad(String nombre, float alquilerBase, float factorRevalorización, float hipotecaBase, float precioCompra, float precioEdificar) {
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

    public float getPrecioAlquiler() {
        if(hipotecado = true || propietario.isEncarcelado())
            return 0;
        else
            return this.alquilerBase;
    }

    public float getImporteCancelarHipoteca() {
        return this.hipotecaBase * this.factorInteresesHipoteca;
    }
    
    private boolean esEsteElPropietario(Jugador jugador){
        return this.propietario == jugador;
    }
    
    public String getNombre() {
        return nombre;
    }
    /*
    protected boolean cancelarHipoteca(Jugador jugador){
        if (this.hipotecado && esEsteElPropietario(jugador)){
            //Necesito jugador
        
        }
        
    }
    */
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
>>>>>>> 325e2d86d15408eebf75a3ac587f3223de1af1ca
