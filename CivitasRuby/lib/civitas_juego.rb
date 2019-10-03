# encoding:utf-8
module Civitas
  class CivitasJuego
    @@diario = Diario.instance
    def initialize(nombres)
      @indice_jugador_actual = @@dado.quien_empieza(nombres.size)
      
      @jugadores = Array.new
      for i in nombres
        @jugadores << nombres[i]
      end
      
      @gestor_estados = Gestor_estados.new
      @gestor_estados.estado_inicial
      @mazo = MazoSorpesas.new
      @tablero
      inicializar_tablero(@mazo)
      inicializar_mazo_sorpresas(@tablero)
      
    end
    
    def avanza_jugador
      
    end
    
    def actualizar_info
      if @jugadores[@indice_jugador_actual].en_bancarrota
        ranking
      else
        puts @jugadores[@indice_jugador_actual].to_s
      end
      
    end
    
    
    def cancelar_hipoteca(ip)
      return @jugadores[@indice_jugador_actual].cancelar_hipoteca(ip)
    end
    
    def comprar
      
    end
    
    def construir_casa(ip)
      return @jugadores[@indice_jugador_actual].construir_casa(ip)
    end
    
    def construir_hotel(ip)
      return @jugadores[@indice_jugador_actual].construir_hotel(ip)
    end
    
    def contabilizar_pasos_por_salida(jugador_actual)
      
    end
    
    def final_del_juego
      
    end
    
    def get_casilla_actual
      
    end
    
    def get_jugador_actual
      
    end
    
    def hipotecar(ip)
      
    end
    
    def info_jugador_texto
      
    end
    
    def inicializar_mazo_sorpresas(tablero)
      
    end
    
    def inicializar_tablero(mazo)
      
    end
    
    def pasar_turno
      
    end
    
    def ranking
      
    end
    
    def salir_carcel_pagando
      
    end
    
    def salir_carcel_tirando
      
    end
    
    def siguiente_paso
      
    end
    
    def siguiente_paso_completado(operacion)
      
    end
    
    def vender(ip)
      
    end
    
    private :avanza_jugador, :contabilizar_pasos_por_salida, :inicializar_mazo_sorpresas, :inicializar_tablero, :pasar_turno, :ranking
    
    
    
    
    
    
    
  end
end
