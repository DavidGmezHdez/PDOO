# encoding:utf-8
require_relative 'sorpresa'
module Civitas
  class SorpresaPorCasaHotel < Sorpresa
    def initialize(texto,valor)
      super(texto)
      @valor = valor
    end
    
    def informe(actual, todos)
      Diario.instance.ocurre_evento("Aplicando sorpresa " + self.to_s + 
        " al jugador " + todos[actual].nombre + " de tipo PagarCobrar")
    end
    
    def aplicar_a_jugador(actual, todos)
      num_propiedades = todos[actual].cantidad_casas_hoteles
      nuevo_valor = @valor*num_propiedades
      if(super.jugador_correcto(actual,todos))
        informe(actual,todos)
        todos[actual].modificar_saldo(nuevo_valor)
      end
    end
    
    def to_s
      "Sorpresa: { \n Texto: #{@texto}  \n Tipo: PagarCobrar   \n Valor: #{@valor} }"
    end
  end
end
