# encoding:utf-8
require_relative 'sorpresa'
module Civitas
  class SorpresaPorJugador < Sorpresa
    def initialize(texto,valor)
      super(texto)
      @valor = valor
    end
    
    def informe(actual, todos)
      Diario.instance.ocurre_evento("Aplicando sorpresa " + self.to_s + 
        " al jugador " + todos[actual].nombre + " de tipo PorJugador")
    end
    
    def aplicar_a_jugador(actual, todos)
      if(super.jugador_correcto(actual,todos))
        informe(actual,todos)
        valor_actual = @valor * (todos.size-1)
        valor_otros = @valor * -1
        
        for i in todos
          if i==todos[actual]
            i.modificar_saldo(valor_actual)
          else
            i.modificar_saldo(valor_otros)
          end
        end       
      end
    end
    
    def to_s
      "Sorpresa: { \n Texto: #{@texto}  \n Tipo: PagarCobrar   \n Valor: #{@valor} }"
    end
  end
end
