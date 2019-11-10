# encoding:utf-8
require 'singleton'

module Civitas
  class Diario
    include Singleton

    def initialize
      @eventos = Array.new
    end

    
    attr_reader :eventos
    
    
    def ocurre_evento(e)
      @eventos << e
    end
    

    def eventos_pendientes
      return (@eventos.length > 0)
    end
    

    def leer_evento
      e = @eventos.shift
      return e
    end

  end
end
