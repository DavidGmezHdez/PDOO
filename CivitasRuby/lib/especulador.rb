# encoding:utf-8
require_relative 'jugador.rb'
module Civitas
  class Especulador < Jugador
    
    attr_accessor :fianza
    @@FACTOR_ESPECULADOR = 2
    
    def self.nuevo_especulador(jugador,fianza)
      @fianza
      especulador = super(jugador)
      especulador.fianza = fianza
      for i in especulador.propiedades
        i.actualiza_propietario_por_conversion(self)
      end
      
      return especulador
    end
    
    def pagar_fianza
      return saldo > @fianza
    end
    
        
    def self.getCasas_Max
      return @@CASAS_MAX
    end
    
    def self.getHoteles_Max
      return @@HOTELES_MAX
    end
    
    def paga_impuesto(cantidad)
      if super.encarcelado
        return false
      else
        return paga(cantidad/2)
      end
    end
    
    def debe_ser_encarcelado
      
      case (encarcelado)
        when true
          return false
      when false
        if tiene_salvoconducto
          perder_salvoconducto
          Diario.instance.ocurre_evento("Jugador " + @nombre + 
               " tiene salvoconducto, no entra en la carcel \n")
          return false
        else
          if !tiene_salvoconducto && pagar_fianza
            modificar_saldo(-@fianza)
            Diario.instance.ocurre_evento("Jugador " + @nombre + 
               " paga con la fianza, no entra en la cárcel \n")
            return false
          else
            return true
          end
        end
      end
    end
    
    
    
    def puedo_edificar_casa(propiedad)
      return puedo_gastar(propiedad.precio_edificar) && propiedad.num_casas < Especulador::getCasas_Max * @@FACTOR_ESPECULADOR
    end
    
    def puedo_edificar_hotel(propiedad)
      return puedo_gastar(propiedad.precio_edificar) && 
        propiedad.num_hoteles < Especulador::getHoteles_Max * @@FACTOR_ESPECULADOR && 
        propiedad.num_casas >= @@CASAS_POR_HOTEL
    end
    
    
    def to_s
      "Jugador: { \n Nombre: #{@nombre} \n Encarcelado: #{@encarcelado} 
 Propiedades: #{@propiedades} \n Saldo: #{@saldo} \n Puede comprar: #{@puede_comprar} 
 Casilla actual: #{@num_casilla_actual} \n Salvoconducto: #{@salvoconducto} \n Fianza: #{@fianza} \n}"
    end
    
  end
end
