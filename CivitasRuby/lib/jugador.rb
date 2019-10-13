# encoding:utf-8

require_relative 'dado'
require_relative 'diario'
require_relative 'sorpresa'
require_relative 'titulo_propiedad'

module Civitas
  class Jugador
    
    @@CASAS_MAX = 4
    @@HOTELES_MAX = 4
    @@CASAS_POR_HOTEL = 4
    @@PASO_POR_SALIDA = 1000
    @@PRECIO_LIBERTAD = 200
    @@SALDO_INICIAL = 7500
    
    
    def initialize(nombre, encarcelado = false, saldo = @@SALDO_INICIAL, 
        puede_comprar = true,salvoconducto = nil, num_casilla = 0, 
        propiedades = Array.new)
        @encarcelado = encarcelado
        @nombre = nombre
        @saldo = saldo
        @puede_comprar = puede_comprar
        @salvoconducto = salvoconducto
        @num_casilla_actual= num_casilla
        @propiedades = propiedades
    end
    
   
    attr_reader :nombre, :saldo, :encarcelado, :puede_comprar, :salvoconducto, 
      :num_casilla_actual, :propiedades
    
    
    def self.copia(jugador)
      self.new(jugador.nombre,jugador.encarcelado,jugador.saldo,
        jugador.puede_comprar,jugador.salvoconducto,jugador.num_casilla_actual,
        jugador.propiedades)
    end
    
    
    def cancelar_hipoteca(ip)
      raise NotImplementedError
    end
    
    
    def cantidad_casas_hoteles
      total = 0
      for i in @propiedades
        total = total + i.num_casas + i.num_hoteles
      end
      return total
    end
    
    
    def <=>(otroJugador)
      otro_saldo= otroJugador.saldo
      mi_saldo = @saldo
      if (otro_saldo>mi_saldo)
        return 1 
      end

      if (otro_saldo<mi_saldo)
        return -1 
      end
      return 0
    end
    
    
    def comprar(titulo)
      raise NotImplementedError
    end
    
    
    def construir_casa(ip)
      raise NotImplementedError
    end
    
    
    def construir_hotel(ip)
      raise NotImplementedError
    end
    
    
    def debe_ser_encarcelado
      if @encarcelado
        return false
      else
        if tiene_salvoconducto
          perder_salvoconducto
          Diario.instance.ocurre_evento("Jugador " + @nombre + 
              " tiene salvoconducto, no entra en la carcel \n")
          return false
          else
            return true
          end
        end
    end
    
    
    def en_bancarrota
      return @saldo <= 0
    end
    
    
    def encarcelar(num_casilla_carcel)
      if debe_ser_encarcelado
        mover_a_casilla(num_casilla_carcel)
        @encarcelado = true
        Diario.instance.ocurreEvento("Jugador " + @nombre + "encarcelado, movido a cárcel");
      end
      return @encarcelado
    end
    
    
    def existe_la_propiedad(ip)
      return @propiedades[ip] != nil
    end
    
    
    def self.getCasas_Max
      return @@CASAS_MAX
    end
    
    
    def self.getCasas_Por_Hotel
      return @@CASAS_POR_HOTEL
    end
    
    
    def self.getHoteles_Max
      return @@HOTELES_MAX
    end
    
    
    def self.getPaso_Por_Salida
      return @@PASO_POR_SALIDA
    end
    
    
    def self.getPrecio_Libertad
      return @@PRECIO_LIBERTAD
    end
    
    
    def self.getSaldo_Inicial
      return @@SALDO_INICIAL
    end
    
    
    def hipotecar(ip)
      raise NotImplementedError
    end
    
    
    def modificar_saldo(cantidad)
      @saldo = @saldo + cantidad
      Diario.instance.ocurre_evento("Modificado el saldo del jugador " + @nombre + " con  " + cantidad.to_s )
      return true
    end
    
    
    def mover_a_casilla(num_casilla)
      if @encarcelado
        return false
      else 
        @num_casilla_actual = num_casilla
        @puede_comprar = false
        Diario.instance.ocurre_evento("Jugador " + @nombre + " moviendose a casilla " + num_casilla.to_s )
        return true
      end
    end
    
    
    def obtener_salvoconducto(sorpresa)
      if @encarcelado
        return false
      else
        salvoconducto = sorpresa
        return true
      end
    end
    
    
    def paga(cantidad)
      return modificar_saldo(cantidad*-1)
    end
    
    
    def paga_alquiler(cantidad)
      if @encarcelado
        return false
      else
        return paga(cantidad)
      end
    end
    
    
    def paga_impuesto(cantidad)
      if @encarcelado
        return false
      else
        return paga(cantidad)
      end
    end
    
    
    def pasa_por_salida
      modificar_saldo(@@PASO_POR_SALIDA)
      Diario.instance.ocurre_evento("Jugador " + @nombre + " pasa por salida")
      return true
    end
    
    
    def perder_salvoconducto
      @salvoconducto.usada
      @salvoconducto = nil
    end
    
    
    def puede_comprar_casilla
      if @encarcelado 
        @puede_comprar = false
      else
        @puede_comprar = true
      end
      return @puede_comprar
    end
    
    
    def puede_salir_carcel_pagando
      return @saldo >= @@PRECIO_LIBERTAD
    end
    
    
    def puedo_edificar_casa(propiedad)
      return propiedad.num_casas<4 && @saldo>propiedad.precio_edificar
    end
    
    
    def puedo_edificar_hotel(propiedad)
      return propiedad.num_casas==4 && @saldo>propiedad.precio_edificar
    end
    
    
    def puedo_gastar(precio)
      if @encarcelado 
        return false
      else
        return @saldo >= precio
      end
    end
    
    
    def recibe(cantidad)
      if @encarcelado
        return false
      else
        return modificar_saldo(cantidad)
      end
    end
    
    
    def salir_carcel_pagando
      salir=false
      if @encarcelado && puede_salir_carcel_pagando
        paga(@@PRECIO_LIBERTAD)
        @encarcelado = false
        Diario.instance.ocurre_evento("Jugador " + @nombre + " sale de carcel pagando")
        salir=true
      end
      return salir
    end
    
    
    def salir_carcel_tirando
      salir=false
      if @encarcelado && Dado.instance.salgo_de_la_carcel
        @encarcelado = false
        Diario.instance.ocurre_evento("Jugador " + @nombre + " sale de carcel tirando")
        salir=true
      end
      return salir
    end
    
    
    def tiene_algo_que_gestionar
      return !@propiedades.empty?
    end
    
    
    def tiene_salvoconducto
      return @salvoconducto != nil
    end
    
    
    def vender(ip)
      if @encarcelado
        return false
      else
        if existe_la_propiedad(ip) && @propiedades.index(ip).vender(self)
          Diario.instance.ocurre_evento("Propiedad " + @propiedades.index(ip).nombre + 
              " vendida por el jugador " + @nombre)
          @propiedades.delete_at(ip)
          return true
        else
          return false
        end
      end
    end
    
    
    def to_s
      "Jugador: { \n Nombre: #{@nombre} Encarcelado: #{@encarcelado} 
 Propiedades: #{@propiedades} \n Saldo: #{@saldo} \n Puede comprar: #{@puede_comprar} 
 Casilla actual: #{@num_casilla_actual} \n Salvoconducto: #{@salvoconducto} \n}"
    end
    
     
    protected :debe_ser_encarcelado
    private :existe_la_propiedad, :puede_salir_carcel_pagando, 
      :puedo_edificar_casa, :perder_salvoconducto, :puedo_edificar_hotel, 
      :puedo_gastar
   
  end
end
