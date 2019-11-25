# encoding:utf-8
require_relative 'casilla'
module Civitas
  class CasillaCarcel < Casilla
    def initialize(nombre,num_casilla_carcel)
      super(nombre)
      @carcel = num_casilla_carcel
    end

    def recibe_jugador(iactual,todos)
      if jugador_correcto(iactual,todos)
          informe(iactual,todos)
          todos[iactual].encarcelar(@carcel)
      end
    end
  end
end
