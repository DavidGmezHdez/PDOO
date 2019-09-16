# encoding:utf-8
class Tablero
  def initialize(numero)
    @casillas = Array.new
    @casillas << Casilla.new("salida")
    if numero >= 1
      @num_casilla_carcel = numero
    else
      @num_casilla_carcel = 1
    end
    @por_salida = 0
    @tiene_juez = false
  end
  
  attr_reader :casillas, :num_casilla_carcel, :por_salida, :tiene_juez
  
  def correcto
    return @casillas.size > @num_casilla_carcel && @tiene_juez
  end
  
  def correcto(num_casilla)
    return correcto && (num_casilla>=0 &&num_casilla<=20) 
  end
  
  def añade_casilla(casilla)
    carcel = new Casilla("carcel")
    if @casillas.size == @num_casilla_carcel
      @casillas<<carcel
      @casillas<<casilla
    else
      @casillas<<casilla
    end
  end
  
  def añade_juez
    juez = new Casilla("juez")
    if @tiene_juez == false
      @casillas<<juez
    end
    @tiene_juez = true
  end
  
  def get_casilla(num_casilla)
    if num_casilla >= 0 && num_casilla <= @casillas.size
      return @casillas[num_casilla]
    else
      return null
    end
    
  end
  
  
  
  
end
