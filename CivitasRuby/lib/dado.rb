# encoding:utf-8

module Civitas
  class Dado
    include Singleton
    
    def initialize
      @random = 1
      @ultimo_resultado = 1
      @debug = false
      @@salida_carcel = 5
    end
    
      
      
    def tirar
      if(!debug)
        @ultimo_resultado = rand(6)+1
      else
        @ultimo_resultado = 1
      end
      
      return @ultimo_resultado
    end    
    
    
    
    def salgo_de_la_carcel
      @valor = tirar()
      @salir = false
      if(valor >= 5)
        salir = true
      end
      
      return salir
    end
    
    
    
    def quien_empieza (n)
      @primer_jugador = rand(n-1)+1
      return @primer_jugador
    end
    
    
    attr_writer :debug
    attr_reader :ultimo_resultado
    
  end
end
