# encoding:utf-8
require_relative 'casilla'
module Civitas
  class CasillaCalle < Casilla
    def initialize (titulo)
      super(titulo.nombre)
      @titulo = titulo 
    end
    
    attr_reader :titulo

    def recibe_jugador(iactual,todos)
      #puts "aqui calle"
      #puts super
      if jugador_correcto(iactual, todos)
          informe(iactual,todos)
          jugador = todos[iactual];

          if !@titulo.tiene_propietario()
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
