# encoding:utf-8

require_relative 'mazo_sorpresas'
require_relative 'tablero'
require_relative 'jugador'
require_relative 'diario'

module Civitas                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
  class Sorpresa    
    def initialize(texto)
      @texto = texto
    end
  
    attr_writer :texto
    
    def jugador_correcto(actual, todos)
      es_correcto = false
      if actual >= 0 && actual < todos.size
        es_correcto = true
      end
      return es_correcto
    end
        
  end
end
