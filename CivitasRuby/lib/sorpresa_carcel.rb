# encoding:utf-8
require_relative 'sorpresa.rb'
module Civitas
  class SorpresaCarcel < Sorpresa
    def initialize(tablero)
      texto = "¡Directo a la cárcel!"
      super(texto)
      @tablero = tablero
    end
    
    def informe(actual, todos)
      Diario.instance.ocurre_evento("Aplicando sorpresa " + self.to_s + 
        " al jugador " + todos[actual].nombre + " de tipo IrACarcel")
    end
    
    def aplicar_a_jugador(actual, todos)      
      if jugador_correcto(actual,todos)
        informe(actual,todos)
        todos[actual].encarcelar(@tablero.num_casilla_carcel);
      end
    end
    
    def to_s
      "Sorpresa: { \n Texto: #{@texto}  \n Tipo: IrACarcel}"
    end
  end
end
