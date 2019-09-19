# encoding:utf-8
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
    
    #La creacion de los atributos de abajo, se crean en el initialize y se 
    #inicializan en init ????????
    def init
      @sorpresas = Array.new
      @cartas_especiales = Array.new
      @ultima_sorpresa
      @sorpresas = Array.new
      @barajada = false
      @usadas = 0      
    end
    
    
    
    def al_mazo(s)
      if(!@barajada)
        @sorpresas << s
      end
    end
    
    #NO TERMINADO
    def siguiente
      if(!@barajada || @usadas==@sorpresas.size() && !@debug)
        @usadas=0
        @barajada = true
      end
      @usadas=@usadas+1
      
    end
    
  end
end
