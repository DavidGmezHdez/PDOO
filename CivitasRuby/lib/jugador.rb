# encoding:utf-8
require_relative 'dado'
require_relative 'diario'
module Civitas
  class Jugador
    @@CASAS_MAX = 4
    @@HOTELES_MAX = 4
    @@CASAS_POR_HOTEL = 4
    @@PASO_POR_SALIDA = 1000
    @@PRECIO_LIBERTAD = 200
    @@SALDO_INICIAL = 2000
    
    @@dado = Dado.instance
    @@diario = Diario.instance
    
    attr_accessor :nombre, :saldo, :encarcelado, :puede_comprar, :salvo_conducto, :num_casilla_actual, :propiedades
    
    def initialize(nombre,encarcelado = false, saldo = @@SALDO_INICIAL, puede_comprar = true,salvo_conducto = nil, num_casilla = 0, propiedades = Array.new)
         @encarcelado = encarcelado
         @nombre = nombre
         @saldo = saldo
         @puede_comprar = puede_comprar
         @salvo_conducto = salvo_conducto
         @num_casilla_actual= num_casilla
         @propiedades = propiedades
    end
   
    def self.copia(jugador)
      self.new(jugador.nombre,jugador.encarcelado,jugador.saldo,jugador.puede_comprar,jugador.salvo_conducto,jugador.num_casilla_actual,jugador.propiedades)
    end
    
    def cancelar_hipoteca(ip)
      
    end
    
    def cantidad_casas_hoteles
      
    end
    
    #compareTo(otro : Jugador) : int
    
    def comprar(titulo)
      
    end
    
    def construir_casa(ip)
      
    end
    
    def construir_hotel(ip)
      
    end
    
    def debe_ser_encarcelado
      
    end
    
    def en_bancarrota
      
    end
    
    def encarcelar(numCasillaCarcel)
      
    end
    
    def existe_la_propiedad(ip)
      
    end
    
    def hipotecar(ip)
      
    end
    
    def modificar_saldo(cantidad)
      
    end
    
    def mover_a_casilla(numCasilla)
      
    end
    
    def obtener_salvoconducto(sorpresa)
      
    end
    
    def paga(cantidad)
      
    end
    
    def paga_alquiler(cantidad)
      
    end
    
    def paga_impuesto(cantidad)
      
    end
    
    def pasa_por_salida
      
    end
    
    def perder_salvoconducto
      
    end
    
    def puede_comprar_casilla
      
    end
    
    def puede_salir_carcel_pagando
      
    end
    
    def puedo_edificar_casa(propiedad)
      
    end
    
    def puedo_edificar_hotel(propiedad)
      
    end
    
    def puedo_gastar(precio)
      
    end
    
    def recibe(cantidad)
      
    end
    
    def salir_carcel_pagando
      
    end
    
    def salir_carcel_tirando
      
    end
    
    def tiene_algo_que_gestionar
      
    end
    
    def tiene_salvoconducto
      
    end
    
    def to_s
      "Jugador: #{@nombre} \n encarcelado: #{@encarcelado} \n 
      propiedades: #{@propiedades} \n saldo: #{@saldo} \n 
      puede comprar: #{@puede_comprar} \n 
      casilla actual: #{@num_casilla_actual}\n salvoconducto: #{@salvoconducto}\n"
    end
    
    def vender(ip)
      
    end
    
    
    protected :debe_ser_encarcelado
    private :existe_la_propiedad, :puedo_salir_carcel_pagando, :puedo_edificar_casa, :perder_salvoconducto, :puedo_edificar_hotel, :puedo_gastar
   
  end
end
