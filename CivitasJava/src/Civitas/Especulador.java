
package Civitas;
public class Especulador extends Jugador {
    private int fianza;
    private static int FactorEspeculador = 2;
    
    Especulador(String jugador,int fianza){
        super(jugador);
        this.fianza = fianza;
    }
    
    boolean pagarFianza(){
        return super.getSaldo() > this.fianza;
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
    
    
    
}
