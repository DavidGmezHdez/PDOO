# encoding:utf-8
module Civitas
  class TituloPropiedad
    def initialize(nombre,alquiler_base,factor_revalorizacion,hipoteca_base,precio_compra,precio_edificar)
      @nombre = nombre
      @alquiler_base = alquiler_base
      @factor_revalorizacion = factor_revalorizacion
      @hipoteca_base = hipoteca_base
      @precio_compra = precio_compra
      @precio_edificar = precio_edificar
      @hipotecado = false
      @num_casas = 0
      @num_hoteles = 0
      @facor_intereses_hipoteca = 1.1
      @propietario = nil
    end
    
    attr_accessor :nombre, :hipotecado, :hipoteca_base, :num_casas, :num_hoteles, :precio_compra, :precio_edificar, :propietario
    
    def to_s
      "TituloPropiedad: #{@nombre} \n Alquiler base #{@alquiler_base} \n 
      Factor Revalorizacion: #{@factor_revalorizacion} \n Hipoteca Base: #{@hipoteca_base} \n 
      Precio Compra: #{@precio_compra} \n 
      Precio Edificar: #{@precio_edificar}\n Hipotecado: #{@hipotecado}\n Num Casas: #{@num_casas}\n
      Num Hoteles: #{@num_hoteles}\n Propietario: #{@hpropietario}\n"
    end
    
    
    def actualiza_propietario_por_conversion(jugador)
    
    end
    
    def cancelar_hipoteca(jugador)
       if @hipotecado && es_este_el_propietario(jugador)
         jugador.paga(get_importe_cancelar_hipoteca)
         @hipotecado = false
         return true
       else
         return false
       end 
    end
    tiene_propietario
    def cantidad_casas_hoteles()
        return @num_casas + @num_hoteles
    end
    
    def comprar(jugador)
      if tiene_propietario
        return false
      else
        @propietario = jugador
        @jugador.paga(@precio_compra)
        return true
      end
        
    end
    
    def construir_casa(jugador)
        resultado = false
        if este_es_el_propietario(jugador)
          jugador.paga(@precio_edificar)
          @num_casas = @num_casas + 1
          resultado = true
        end
      return resultado
    end
    
    def construir_hotel(jugador)
        resultado = false
        if este_es_el_propietario(jugador)
          jugador.paga(@precio_edificar)
          @num_casas = @num_casas + 1
          resultado = true
        end
      return resultado
        
    end
    
    def derruir_casas(n,jugador)
      if este_es_el_propietario(jugador) && @num_casas >= n
        @num_casas = @num_casas - n
        return true
      else
        return false
      end
    end
    
    def es_este_el_propietario(jugador)
        return @propietario == jugador
    end
    
    
    def get_importe_cancelar_hipoteca
        return @hipoteca_base * @factor_intereses_hipoteca
    end
    
    
    def get_precio_alquiler
        if @hipotecado || propietario_encarcelado
          return 0
        else
          return 1234
        end
    end

    
    def get_precio_venta
        return @precio_compra + (cantidad_casas_hoteles() * @precio_edificar * @factor_revalorizacion);
    end


    
    def hipotecar(jugador)
        if !@hipotecado && es_este_el_propietario(jugador)
          @jugador.recibe(@hipoteca_base)
          @hipotecado = true
          return true
        else
          return false
        end
    end
    
    def propietario_encarcelado
      return @propietario.encarcelado
    end
    
    def tiene_propietario
        return @propietario != nil
    end
    
    def tramitar_alquiler(jugador)
      if tiene_propietario && !es_este_el_propietario(jugador)
        jugador.paga_alquiler(@alquiler_base)
        @propietario.recibe(@alquiler_base)
      end
    end
    
    def vender(jugador)
        
    end
    
    private :propietario_encarcelado, :es_este_el_propietario, :get_precio_venta

  end
end