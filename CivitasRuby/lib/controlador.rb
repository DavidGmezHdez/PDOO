# encoding:utf-8
require_relative 'civitas_juego'
require_relative 'vista_textual'
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
                @juego.siguiente_paso_completado(operacion)
              end
            when OperacionesJuego::GESTIONAR
              @vista.gestionar
              op_inmobiliaria = OperacionInmobiliaria.new(@vista.getGestion, @vista.getPropiedad)
              case (@vista.getGestion)
                when GestionesInmobiliarias::VENDER
                  @juego.vender(op_inmobiliaria.get_num_propiedad)
                when GestionesInmobiliarias::HIPOTECAR
                  @juego.hipotecar(op_inmobiliaria.get_num_propiedad)
                when GestionesInmobiliarias::CANCELAR_HIPOTECA
                  @juego.cancelar_hipoteca(op_inmobiliaria.get_num_propiedad)
                when GestionesInmobiliarias::CONSTRUIR_CASA
                  @juego.construir_casa(op_inmobiliaria.get_num_propiedad)
                when GestionesInmobiliarias::CONSTRUIR_HOTEL
                  @juego.construir_hotel(op_inmobiliaria.get_num_propiedad)
                when GestionesInmobiliarias::TERMINAR
                  @juego.siguiente_paso_completado(operacion)
              end
            when OperacionesJuego::SALIR_CARCEL
              case(@vista.salir_carcel)
                when SalidasCarcel::PAGANDO
                  @juego.salir_carcel_pagando
                when SalidasCarcel::TIRANDO
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
