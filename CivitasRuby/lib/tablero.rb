# encoding:utf-8
require_relative 'casilla'

module Civitas
  class Tablero
  
    def initialize(numero)
      @casillas = Array.new
      salida = Casilla.new_nombre("Salida")
      @casillas << salida
    
      if numero >= 1
        @num_casilla_carcel = numero
      else
        @num_casilla_carcel = 1
      end
    
      @por_salida = 0
      @tiene_juez = false
    end
  
  
    attr_reader :casillas, :num_casilla_carcel, :por_salida, :tiene_juez
  
  
    def añade_casilla(casilla)
      carcel = Casilla.new("carcel")
      if @casillas.size == @num_casilla_carcel
        @casillas<<carcel
      end
      @casillas<<casilla
    end
  
  
    def añade_juez
      juez = new Casilla("juez")
      if @tiene_juez == false
        @casillas<<juez
      end
      @tiene_juez = true
    end
  
  
    def calcular_tirada(origen,destino)
      return destino - origen + 20
    end
  
  
    def correcto
      return @casillas.size > @num_casilla_carcel && @tiene_juez
    end
  
  
    def correcto(num_casilla)
      return correcto && (num_casilla>=0 && num_casilla<=@casillas.size) 
    end
  
  
    def get_por_salida
      por_salida_anterior = @por_salida
      if @por_salida > 0
        @por_salida = @por_salida - 1
        return por_salida_anterior
      else
        return @por_salida
      end
    end
  

    def get_casilla(num_casilla)
      if num_casilla >= 0 && num_casilla <= @casillas.size
        return @casillas[num_casilla]
      else
        return nil
      end
    end
  
  
    def nueva_posicion(actual,tirada)
      if !correcto
        return -1
      else
        posicion = (actual+tirada)%20
      end
    
      if actual+tirada != posicion
        @por_salida = @por_salida+1
      end
      return posicion
    end
  
    private :correcto
    
  end
end