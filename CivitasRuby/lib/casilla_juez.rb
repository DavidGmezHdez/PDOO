# encoding:utf-8

class CasillaJuez < Casilla
  def initialize(nombre,num_casilla_carcel)
    @nombre = super(nombre)
    @carcel = num_casilla_carcel
  end
  
  def recibe_jugador_juez(iactual,todos)
    if(super.jugador_correcto(iactual,todos))
        super.informe(iactual,todos)
        todos[iactual].encarcelar(@carcel)
    end
  end
  
end
