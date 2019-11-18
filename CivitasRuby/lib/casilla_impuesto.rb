# encoding:utf-8

class CasillaImpuesto < Casilla
  def initialize(nombre,cantidad)
    @nombre = super(nombre)
    @cantidad = cantidad
  end
  
  def recibe_jugador_impuesto(iactual,todos)
    if(super.jugador_correcto(iactual,todos))
        super.informe(iactual,todos)
        todos[iactual].paga_impuesto(@importe)
    end
  end 
  
end
