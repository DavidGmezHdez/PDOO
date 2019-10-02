<<<<<<< HEAD
# encoding:utf-8

module Civitas
  class Sorpresa
    def initialize(nombre)
      @nombre = nombre
    end
    
    attr_reader :nombre
  end
end
=======
# encoding:utf-8
require_relative "mazo_sorpresas"
require_relative "tablero"
require_relative "jugador"
require_relative "tipo_sorpresa"

module Civitas
  class Sorpresa
    def initialize(texto, tablero, valor, mazo, tipo)
      @texto = texto
      @tablero = tablero
      @valor = valor
      @mazo = mazo
      @tipo = tipo
    end
    
    #Constructor para la sorpresa que envía a la cárcel
    def self.new_a_carcel(tipo, tablero)
      init()
      new("¡Directo a la cárcel!", tablero, -1, nil, tipo)
    end
    
    #Constructor para la sorpresa que envía al jugador a otra casilla
    def self.new_otra_casilla(tipo, tablero, valor, texto) 
      init()
      new(texto, tablero, valor, nil, tipo)
    end 
    
    #Constructor para el resto de sorpresas
    def self.new_sorpresas(tipo, valor, texto)
      init()
      new(texto, nil, valor, nil, tipo)
    end
    
    #Constructor para sorpresa que permite evitar la carcel
    def self.new_evitar_carcel(tipo, mazo)
      init()
      new("¡Genial! Has conseguido la carta para evitar la cárcel", nil, -1, mazo, tipo)
    end
    
    def init
      @valor = -1
      @mazo = nil
      @tablero = nil      
    end
    
    def jugador_correcto(actual, todos)
      contenido=false
      if (actual >= 0 && actual < todos)
        contenido =true
      end
      
      return contenido
    end
    
    #HACERLOOOOOOOOOO ?????????
    def informe(actual, todos)
      nombre_jug=todos[actual]
      diario
    end
    
    def aplicar_a_jugador(actual, todos)
      
    end
    
    
  end
end
>>>>>>> 325e2d86d15408eebf75a3ac587f3223de1af1ca
