# encoding:utf-8
require_relative 'sorpresa.rb'
require_relative 'especulador'
module Civitas
  class SorpresaEspeculador < Sorpresa
    def initialize(texto,valor)
      super(texto)
      @valor = valor
    end
    
    def informe(actual, todos)
      Diario.instance.ocurre_evento("Aplicando sorpresa " + self.to_s + 
        " al jugador " + todos[actual].nombre + " de tipo Especulador")
    end
    
    def aplicar_a_jugador(actual, todos)
      if jugador_correcto(actual,todos)
        espc = Especulador.nuevo_especulador(todos[actual],@valor)
        todos.delete_at(actual)
        todos << espc
      end
    end
    
    def to_s
      "Sorpresa: { \n Texto: #{@texto}  \n Tipo: Especulador   \n Valor: #{@valor} }"
    end
    
  end
end
