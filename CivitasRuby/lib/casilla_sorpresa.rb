module Civitas
  class CasillaSorpresa < Casilla
    def initialize(nombre,mazo)
      super(nombre)
      @mazo = mazo
    end
    
    def recibe_jugador(iactual,todos)
      if super.jugador_correcto(iactual,todos)
        sorpresa = @mazo.siguiente
        super.informe(iactual,todos)
        sorpresa.aplicar_a_jugador(iactual,todos)
      end
    end
  end
end
