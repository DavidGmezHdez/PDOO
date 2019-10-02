# encoding:utf-8
require_relative "sorpresa"
require_relative "diario"

module Civitas
  class MazoSorpresas
    def initialize (debug=false)
      @@diario = Diario.instance
      @debug = debug
      init()
      if(debug)
        @@diario.ocurre_evento("debug on")
      end 
    end
    
    def init
      @sorpresas = Array.new
      @cartas_especiales = Array.new
      @ultima_sorpresa
      @barajada = false
      @usadas = 0      
    end
    
    
    
    def al_mazo(s)
      if(!@barajada)
        @sorpresas << s
      end
    end
    
    
    
    def siguiente
      if(!@barajada || @usadas==@sorpresas.size() && !@debug)
        @usadas=0
        @barajada = true
      end
      @usadas=@usadas+1
      @ultima_sorpresa=@sorpresas[0]
      @sorpresas.delete_at(0)
      @sorpresas << @ultima_sorpresa
      return @ultima_sorpresa 
    end
    
    
    
    def inhabilitar_carta_especial(sorpresa)  
      for i in @sorpresas
        if(i==sorpresa)
          @sorpresas.delete(i)
          @cartas_especiales << sorpresa
          @@diario.ocurre_evento("Carta inhabilitada")
        else
          @@diario.ocurre_evento("Carta no inhabilitada")
        end
      end
    end
    
    
    
    def habilitar_carta_especial(sorpresa)
      for i in @cartas_especiales
        if(i==sorpresa)
          @sorpresas << sorpresa
          @cartas_especiales.delete(sorpresa)
          @@diario.ocurre_evento("Carta habilitada")
        else
          @@diario.ocurre_evento("Carta no habilitada")
        end
      end
    end
    
  end
end
