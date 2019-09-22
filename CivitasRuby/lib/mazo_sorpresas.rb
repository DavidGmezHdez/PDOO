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
    
    
    
    def siguiente
      if(!@barajada || @usadas==@sorpresas.size() && !@debug)
        @usadas=0
        @barajada = true
      end
      @usadas=@usadas+1
      #Guardar la primera carta del array sorpresas en ultima_sorpresa
      @ultima_sorpresa=@sorpresas[0]
      
      #Quitar un elementos de un array en la posicion x es: delete_at(x)
      @sorpresas.delete_at(0)
      
      #Añadir ultima_sorpresa al final del array sorpresas
      @sorpresas << @ultima_sorpresa
      
      #Devolver referencia a esa carta
      #HACER ESO ES DEVOLVER ULTIMA_SORPRESA?????????
      return @ultima_sorpresa 
    end
    
    
    
    def inhabilitar_carta_especial(sorpresa)  
      i=0
      for i in @sorpresas
        if(@sorpresas[i]==sorpresa)
          @sorpresas.delete_at(i)
          @cartas_especiales << sorpresa
          @diario.ocurre_evento("Carta inhabilitada")
        else
          @diario.ocurre_evento("Carta no inhabilitada")
        end
      end
    end
    
    
    
    def habilitar_carta_especial(sorpresa)
      i=0
      for i in @sorpresas
        if(@sorpresas[i]==sorpresa)
          #Así ya se añadiría al final, y no hay ningún problema, porque
          #se borra depués, no  ??????????
          @sorpresas << sorpresa
          @sorpresas.delete_at(i)
          @diario.ocurre_evento("Carta habilitada")
        else
          @diario.ocurre_evento("Carta no habilitada")
        end
      end
    end
    
    
    
    
    
    
  end
end
