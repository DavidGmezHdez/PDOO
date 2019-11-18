# encoding:utf-8

module Civitas
  class CasillaCalle < Casilla
    def initialize (titulo)
      super(titulo.nombre)
      @titulo = titulo 
    end

    def recibe_jugador(iactual,todos)
      if (super.jugador_correcto(iactual, todos))
          super.informe(iactual,todos)
          jugador = todos[iactual];

          if(!@titulo.tiene_propietario())
            jugador.puede_comprar_casilla()
          else
            @titulo.tramitar_alquiler(jugador)
          end
      end
    end


      def to_s
        "Casilla { \n #{@titulo.to_s} \n}"
      end
  end
end
