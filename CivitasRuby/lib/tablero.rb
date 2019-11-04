# encoding:utf-8

require_relative 'casilla'

module Civitas
  class Tablero
  
    def initialize(numero)
      @casillas = Array.new
      salida = Casilla.new_nombre("Salida")
      @casillas << salida
    
      if numero >= 1
        @num_casilla_carcel = numero-1
      else
        @num_casilla_carcel = 1
      end
    
      @por_salida = 0
      @tiene_juez = false
    end
  
  
    attr_reader :casillas, :num_casilla_carcel, :por_salida, :tiene_juez
  
  
    def añade_casilla(casilla)
      carcel = Casilla.new_nombre("Carcel")
      if @casillas.size == @num_casilla_carcel
        @casillas<<carcel
      end
      @casillas<<casilla
    end
  
  
    def añade_juez
      juez = Casilla.new_carcel(@num_casilla_carcel,"Juez")
      if @tiene_juez == false
        @casillas<<juez
      end
      @tiene_juez = true
    end
  
  
    def calcular_tirada(origen,destino)
      nueva_posicion = destino - origen
      if nueva_posicion < 0
        nueva_posicion = nueva_posicion + 20
      end
      return nueva_posicion
    end
  
  
    def correcto
      return @casillas.size > @num_casilla_carcel && @tiene_juez
    end
  
  
    def correcto(num_casilla)
      return correcto(num_casilla) && (num_casilla>=0 && num_casilla<=@casillas.size) 
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
      if !correcto(actual)
        return -1
      else
        posicion = (actual+tirada)%20
      end
    
      if actual+tirada != posicion
        @por_salida = @por_salida+1
      end
      return posicion
    end
    
    def to_s
      puts @casillas.to_s
    end
  
    private :correcto
    
  end
end