# encoding:utf-8s
require_relative 'sorpresa'
module Civitas
  class SorpresaIrACasilla < Sorpresa
    def initialize(texto,tablero,valor)
      super(texto)
      @tablero = tablero
      @valor = valor
    end
    
    
    def informe(actual, todos)
      Diario.instance.ocurre_evento("Aplicando sorpresa " + self.to_s + 
        " al jugador " + todos[actual].nombre + " de tipo IrACasilla")
    end
    
    
    def aplicar_a_jugador(actual, todos)
      casilla_actual = todos[actual].num_casilla_actual
      if(super.jugador_correcto(actual,todos))
        informe(actual,todos)
        tirada = @tablero.calcular_tirada(casilla_actual,@valor)
        nueva_posicion = @tablero.nueva_posicion(casilla_actual,tirada)
        todos[actual].mover_a_casilla(nueva_posicion)
        @tablero.casillas[nueva_posicion].recibe_jugador(actual,todos)
      end
    end
    
    def to_s
      "Sorpresa: { \n Texto: #{@texto}  \n Tipo: IrACasilla   \n Valor: #{@valor} }"
    end
  end
end
