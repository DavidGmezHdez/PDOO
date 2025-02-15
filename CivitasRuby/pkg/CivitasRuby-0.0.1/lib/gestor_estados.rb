# encoding:utf-8

require_relative 'diario'
require_relative 'estados_juego'
require_relative 'jugador'
require_relative 'operaciones_juego'

module Civitas
  class GestorEstados

    def estado_inicial
      return (EstadosJuego::INICIO_TURNO)
    end

    def operaciones_permitidas(jugador,estado)
      op = nil

      case estado

      when EstadosJuego::INICIO_TURNO
        if (jugador.encarcelado)
          op = OperacionesJuego::SALIR_CARCEL
        else
          op = OperacionesJuego::AVANZAR
        end

      when EstadosJuego::DESPUES_CARCEL
        op = OperacionesJuego::PASAR_TURNO

      when EstadosJuego::DESPUES_AVANZAR
        if (jugador.encarcelado)
          op = OperacionesJuego::PASAR_TURNO
        else
          if (jugador.puede_comprar)
            op = OperacionesJuego::COMPRAR
          else
            if (jugador.tiene_algo_que_gestionar)
              op = OperacionesJuego::GESTIONAR
            else
              op = OperacionesJuego::PASAR_TURNO
            end
          end
        end

      when EstadosJuego::DESPUES_COMPRAR
        if (jugador.tiene_algo_que_gestionar)
          op = OperacionesJuego::GESTIONAR
        else
          op = OperacionesJuego::PASAR_TURNO
        end

      when EstadosJuego::DESPUES_GESTIONAR
        op = OperacionesJuego::PASAR_TURNO
      end

      return op
    end



    def siguiente_estado(jugador,estado,operacion)
      siguiente = nil

      case estado

      when EstadosJuego::INICIO_TURNO
        if (operacion==OperacionesJuego::SALIR_CARCEL)
          siguiente = EstadosJuego::DESPUES_CARCEL
        else
          if (operacion==OperacionesJuego::AVANZAR)
            siguiente = EstadosJuego::DESPUES_AVANZAR
          end
        end


      when EstadosJuego::DESPUES_CARCEL
        if (operacion==OperacionesJuego::PASAR_TURNO)
          siguiente = EstadosJuego::INICIO_TURNO
        end

      when EstadosJuego::DESPUES_AVANZAR
        case operacion
          when OperacionesJuego::PASAR_TURNO
            siguiente = EstadosJuego::INICIO_TURNO
          when
            OperacionesJuego::COMPRAR
              siguiente = EstadosJuego::DESPUES_COMPRAR
          when OperacionesJuego::GESTIONAR
              siguiente = EstadosJuego::DESPUES_GESTIONAR
        end


      when EstadosJuego::DESPUES_COMPRAR
        #if (jugador.tiene_algo_que_gestionar)
        if (operacion==OperacionesJuego::GESTIONAR)
          siguiente = EstadosJuego::DESPUES_GESTIONAR
        #  end
        else
          if (operacion==OperacionesJuego::PASAR_TURNO)
            siguiente = EstadosJuego::INICIO_TURNO
          end
        end

      when EstadosJuego::DESPUES_GESTIONAR
        if (operacion==OperacionesJuego::PASAR_TURNO)
          siguiente = EstadosJuego::INICIO_TURNO
        end
      end

      Diario.instance.ocurre_evento("De: "+estado.to_s+ " con "+operacion.to_s+ " sale: "+siguiente.to_s)

      return siguiente
    end

  end
end
