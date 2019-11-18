# encoding:utf-8
module Civitas
  class CasillaCarcel < Casilla
    def initialize(nombre,num_casilla_carcel)
      super(nombre)
      @carcel = num_casilla_carcel
    end

    def recibe_jugador(iactual,todos)
      if(super.jugador_correcto(iactual,todos))
          super.informe(iactual,todos)
          todos[iactual].encarcelar(@carcel)
      end
    end
  end
end
