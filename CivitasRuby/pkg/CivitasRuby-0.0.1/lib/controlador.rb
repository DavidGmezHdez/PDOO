# encoding:utf-8
require_relative 'civitas_juego'
require_relative 'vista_textual'
require_relative 'operacion_inmobiliaria'

module Civitas
  class Controlador
    def initialize(juego,vista)
      @juego = juego
      @vista = vista
    end

    def juega
      @vista.setCivitasJuego(@juego)
      while !@juego.final_del_juego
        @vista.actualizarVista
        @vista.pausa
        
        operacion = @juego.siguiente_paso
        if operacion != OperacionesJuego::PASAR_TURNO
          @vista.mostrarEventos
        end
        terminado = @juego.final_del_juego
        if !terminado
          case (operacion)
            when OperacionesJuego::COMPRAR
              respuesta = @vista.comprar
              if respuesta == Respuestas::SI
                @juego.comprar
              end
              @juego.siguiente_paso_completado(operacion)
            when OperacionesJuego::GESTIONAR
              @vista.gestionar
              puts "check gestionar"
              op_inmobiliaria = OperacionInmobiliaria.new(@vista.getGestion, @vista.getPropiedad)
              case (@vista.getGestion)
                when GestionesInmobiliarias::LISTA_GESTIONES_INMOBILIARIAS[0]
                  @juego.siguiente_paso_completado(operacion)
                when GestionesInmobiliarias::LISTA_GESTIONES_INMOBILIARIAS[1]
                  @juego.vender(op_inmobiliaria.get_num_propiedad)
                when GestionesInmobiliarias::LISTA_GESTIONES_INMOBILIARIAS[2]
                  @juego.hipotecar(op_inmobiliaria.get_num_propiedad)
                when GestionesInmobiliarias::LISTA_GESTIONES_INMOBILIARIAS[3]
                  @juego.cancelar_hipoteca(op_inmobiliaria.get_num_propiedad)
                when GestionesInmobiliarias::LISTA_GESTIONES_INMOBILIARIAS[4]
                  @juego.construir_casa(op_inmobiliaria.get_num_propiedad)
                when GestionesInmobiliarias::LISTA_GESTIONES_INMOBILIARIAS[5]
                  @juego.construir_hotel(op_inmobiliaria.get_num_propiedad)
              end
              @juego.siguiente_paso_completado(operacion)
            when OperacionesJuego::SALIR_CARCEL
              case(@vista.salir_carcel)
                when SalidasCarcel::LISTA_SALIDAS_CARCEL[0]
                  @juego.salir_carcel_pagando
                when SalidasCarcel::LISTA_SALIDAS_CARCEL[1]
                  @juego.salir_carcel_tirando
              end
              @juego.siguiente_paso_completado(operacion)
          end
        end
      end
      puts @juego.ranking.to_s
    end
  end
end
