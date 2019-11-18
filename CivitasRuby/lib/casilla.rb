# encoding:utf-8

require_relative 'titulo_propiedad'
require_relative 'sorpresa'
require_relative 'mazo_sorpresas'

module Civitas
  class Casilla
    
    def initialize(nombre)
      @nombre = nombre
    end
    
    attr_reader :nombre
    
    def self.new_mazo(mazo, nombre)
      new(TipoCasilla::SORPRESA,nombre,nil,0, 0,mazo)
    end
    
    
    def informe(iactual, todos)
      Diario.instance.ocurre_evento(" Ha caído en la casilla: \n" + self.to_s + " el jugador " + todos[iactual].nombre + "\n")
    end
      
    
    def jugador_correcto(iactual,todos)
      return iactual>=0 && iactual<todos.size
    end
    
    
    def recibe_jugador(iactual,todos)
      informe(iactual,todos)
    end
    
    def to_s
      "Casilla { \n Nombre: #{@nombre} \n}"
    end
    
    
    private :informe
    
  end
end
