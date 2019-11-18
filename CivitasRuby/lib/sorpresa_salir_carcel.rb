# encoding:utf-8
require_relative 'sorpresa'
module Civitas
  class SorpresaSalirCarcel < Sorpresa
    def initialize(mazo)
      super("¡Genial! Has conseguido la carta para evitar la cárcel")
      @mazo = mazo
    end
    
    def informe(actual, todos)
      Diario.instance.ocurre_evento("Aplicando sorpresa " + self.to_s + 
        " al jugador " + todos[actual].nombre + " de tipo SalirCarcel")
    end
    
    
    def aplicar_a_jugador(actual, todos)
      if(super.jugador_correcto(actual,todos))
        informe(actual,todos)
        nadie_salvo_conducto=0
        
        for i in todos
          if i.tiene_salvo_conducto
            nadie_salvo_conducto = nadie_salvo_conducto+1
          end
        end
        
        # Si nadie tiene salvo conducto
        if nadie_salvo_conducto==0
          todos[actual].obtener_salvoconducto(self)
          salir_del_mazo
        end
      end
    end
    
    def salir_del_mazo
        @mazo.inhabilitar_carta_especial(self)
    end
    
    def usada
        @mazo.habilitarCartaEspecial(self)
    end
    
    def to_s
      "Sorpresa: { \n Texto: #{@texto}  \n Tipo: SalirCarcel }"
    end
    
  end
end
