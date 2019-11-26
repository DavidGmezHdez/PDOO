# encoding:utf-8
require_relative 'casilla'
module Civitas
  class CasillaSorpresa < Casilla
    def initialize(nombre,mazo)
      super(nombre)
      @mazo = mazo
    end
    
    def recibe_jugador(iactual,todos)
      if jugador_correcto(iactual,todos)
        sorpresa = @mazo.siguiente
        informe(iactual,todos)
        sorpresa.aplicar_a_jugador(iactual,todos)
      end
    end
  end
end
