
package Civitas;
public class Especulador extends Jugador {
    private int fianza;
    private static int FactorEspeculador = 2;
    
    Especulador(Jugador jugador,int fianza){
        super(jugador);
        this.fianza = fianza;
        for(int i=0;i<super.getPropiedades().size();i++){
            super.getPropiedades().get(i).actualizaPropietarioPorConversion(this);
        }
        super.especulador = true;
    }
    
    boolean pagarFianza(){
        return super.getSaldo() > this.fianza;
    }
    
    public int getFianza(){
        return this.fianza;
    }
    
    protected static int getCasasMax() {
        return CasasMax;
    }
    
    protected static int getHotelesMax() {
        return HotelesMax;
    }
    
    @Override
    boolean pagaImpuesto(float cantidad){
        if(this.encarcelado)
            return false;
        else
            return this.paga(cantidad/2);
    }
    
    @Override
    protected boolean debeSerEncarcelado(){
        if (this.encarcelado)
            return false;
        else if(super.tieneSalvoconducto()){
            super.perderSalvoConducto();
            Diario.getInstance().ocurreEvento("Jugador " + this.nombre + 
                    " usa salvoconducto, no entra en la cárcel");
            return false;
        }
        else if(!super.tieneSalvoconducto() && this.pagarFianza()){
            super.modificarSaldo(-(this.fianza));
            Diario.getInstance().ocurreEvento("Jugador " + this.nombre + 
                    " paga con la fianza, no entra en la cárcel");
            return false;
        }
        else
            return true;
    }
    
    @Override
    public boolean puedoEdificarCasa(TituloPropiedad propiedad){
        return super.puedoGastar(propiedad.getPrecioEdificar()) && propiedad.getNumCasas() < getCasasMax()*FactorEspeculador;
    }
    
    @Override
    public boolean puedoEdificarHotel(TituloPropiedad propiedad){
        return this.puedoGastar(propiedad.getPrecioEdificar()) && propiedad.getNumHoteles() < getHotelesMax()*FactorEspeculador 
                && propiedad.getNumCasas()>=getCasasPorHotel();
    }
    
    
    @Override
    public String toString() {
        return "Jugador{" + "\n Nombre: " + super.getNombre() + ", \n numCasillaActual: " + 
                super.getNumCasillaActual() + ", \n PuedeComprar: " + super.getPuedeComprar() + ", \n Saldo=" + 
                super.getSaldo() + ", \n Encarcelado: " + super.isEncarcelado() + " \n Propiedades: " + 
                super.getPropiedades() + " \n Fianza: " + this.getFianza() + "\n}";
    }
    
    
    
}
