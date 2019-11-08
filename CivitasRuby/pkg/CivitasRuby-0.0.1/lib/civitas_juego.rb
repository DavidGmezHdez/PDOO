#encoding:utf-8

require_relative 'dado'
require_relative 'jugador'
require_relative 'estados_juego'
require_relative 'gestor_estados'
require_relative 'tablero'
require_relative 'mazo_sorpresas'
require_relative 'tipo_sorpresa'
require_relative 'casilla'

module Civitas
  class CivitasJuego
    
    @@dado = Dado.instance
    def initialize(nombres)
      @indice_jugador_actual = @@dado.quien_empieza(nombres.size)
      
      @jugadores = Array.new
      nombres.each do |nombre|
        @jugadores << Jugador.new(nombre)
      end
      
      @gestor_estados = GestorEstados.new
      @estado = @gestor_estados.estado_inicial
      
      @mazo = MazoSorpresas.new(false)
      @tablero = Tablero.new(15)
      
     
      inicializar_tablero(@mazo)
       inicializar_mazo_sorpresas(@tablero)
      
      @@dado.debug = true
      
    end
    
    
    def avanza_jugador
      jugador_actual = @jugadores[@indice_jugador_actual]
      posicion_actual = jugador_actual.num_casilla_actual
      tirada = @@dado.tirar
      posicion_nueva = @tablero.calcular_tirada(posicion_actual, tirada)
      puts " Resultado de la tirada: " + tirada.to_s + " Posicion nueva: " + posicion_nueva.to_s 
      casilla = @tablero.get_casilla(posicion_nueva)
      contabilizar_pasos_por_salida(jugador_actual)
      jugador_actual.mover_a_casilla(posicion_nueva)
      casilla.recibe_jugador(@indice_jugador_actual, @jugadores)
      contabilizar_pasos_por_salida(jugador_actual)
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
      jugador_actual = @jugadores[@indice_jugador_actual]
      num_casilla_actual = jugador_actual.num_casilla_actual
      casilla = @tablero.get_casilla(num_casilla_actual)
      titulo = casilla.titulo
      res = jugador_actual.comprar(titulo)
      
      return res
    end
    
    
    def construir_casa(ip)
      return @jugadores[@indice_jugador_actual].construir_casa(ip)
    end
    
    
    def construir_hotel(ip)
      return @jugadores[@indice_jugador_actual].construir_hotel(ip)
    end
    
    
    def contabilizar_pasos_por_salida(jugador_actual)
      i=0
      while i < @tablero.por_salida
        jugador_actual.pasa_por_salida
      end
    end
    
    
    def final_del_juego
      fin = false
      for i in @jugadores
        if i.en_bancarrota
          fin = true
        end
      end
      return fin
    end
    
    
    def get_casilla_actual
      return @tablero.get_casilla(@jugadores[@indice_jugador_actual].num_casilla_actual)
    end
    
    
    def get_jugador_actual
      return @jugadores[@indice_jugador_actual]
    end
    
    
    def hipotecar(ip)
      return @jugadores[@indice_jugador_actual].hipotecar(ip)
    end
    
    
    def info_jugador_texto
      return @jugadores[@indice_jugador_actual].to_s
    end
    
    
    def inicializar_mazo_sorpresas(tablero)
      @mazo.al_mazo(Sorpresa.new_sorpresas(TipoSorpresa::PAGARCOBRAR, -200,"Te vas a la ruleta, crees ganar pero el ruso de al lado te hace la jugada, pierdes 200 euros"))
      @mazo.al_mazo(Sorpresa.new_evitar_carcel(TipoSorpresa::SALIRCARCEL, @mazo))
      @mazo.al_mazo(Sorpresa.new_sorpresas(TipoSorpresa::PORCASAHOTEL, 300,"Gracias a la burbuja del alquiler, la gente compra más casas y hay más turistas en hoteles, ganas 300 euros."))
      @mazo.al_mazo(Sorpresa.new_sorpresas(TipoSorpresa::PORJUGADOR, 200,"Pides a la gente que te de dinero para comprar un regalo en común, pero acabas quedándotelo tu para ir a Pedro"))
      @mazo.al_mazo(Sorpresa.new_sorpresas(TipoSorpresa::PAGARCOBRAR, 500,"Recibes un sobre con la letra B escrita, recibes 500 euros"))
      @mazo.al_mazo(Sorpresa.new_sorpresas(TipoSorpresa::PORJUGADOR, -50,"Dijiste que invitarías a chupitos pero no lo hiciste, pagas 50 euros a cada uno"))
      @mazo.al_mazo(Sorpresa.new_otra_casilla(TipoSorpresa::IRCASILLA, tablero, 10,"Pides un Uber que te lleva la casilla mitad del tablero"))
      @mazo.al_mazo(Sorpresa.new_a_carcel(TipoSorpresa::IRCARCEL,tablero))
      @mazo.al_mazo(Sorpresa.new_otra_casilla(TipoSorpresa::IRCASILLA, tablero, 5,"Alquilas una bici amarilla que te lleva a la casilla 5, luego la tiras al río"))
      @mazo.al_mazo(Sorpresa.new_sorpresas(TipoSorpresa::PORCASAHOTEL, -500,"Mala suerte, Hacienda te ha pillado saltándote la declaración de bienes, debes 500 euros"))
      
    end
    
    
    def inicializar_tablero(mazo)
      @tablero.añade_casilla(Casilla.new_titulo(TituloPropiedad.new("Calle Willyrex", 625, 75, 12, 350, 400)))
      @tablero.añade_casilla(Casilla.new_mazo(mazo, "Sorpresa"))
      @tablero.añade_casilla(Casilla.new_titulo(TituloPropiedad.new("Calle Guerrero", 700, 50, 10, 550, 250)))
      @tablero.añade_juez
      @tablero.añade_casilla(Casilla.new_titulo(TituloPropiedad.new("Calle Picaporte", 740, 55, 19, 300, 575)))
      @tablero.añade_casilla(Casilla.new_nombre("Parking: Coche Seguro, Precio !Barato"))
      @tablero.añade_casilla(Casilla.new_titulo(TituloPropiedad.new("Calle Petunia", 925, 90, 17, 875, 600)))
      @tablero.añade_casilla(Casilla.new_mazo(mazo, "Sorpresa"))
      @tablero.añade_casilla(Casilla.new_titulo(TituloPropiedad.new("Calle Ruby", 500, 95, 14, 175, 275)))
      @tablero.añade_casilla(Casilla.new_titulo(TituloPropiedad.new("Calle Focus", 830, 100, 16, 675, 500)))
      @tablero.añade_casilla(Casilla.new_titulo(TituloPropiedad.new("Calle Motorola", 777, 85, 15, 750, 470)))
      @tablero.añade_casilla(Casilla.new_cantidad(500, "Impuesto"))
      @tablero.añade_casilla(Casilla.new_titulo(TituloPropiedad.new("Calle Rengar", 900, 80, 12, 200, 450)))
      @tablero.añade_casilla(Casilla.new_titulo(TituloPropiedad.new("Calle Potter", 675, 60, 20, 475, 750)))
      @tablero.añade_casilla(Casilla.new_mazo(mazo, "Sorpresa"))
      @tablero.añade_casilla(Casilla.new_titulo(TituloPropiedad.new("Calle Jesucristo", 1000, 60, 11, 250, 325)))
      @tablero.añade_casilla(Casilla.new_titulo(TituloPropiedad.new("Calle Giorgio", 890, 65, 13, 1000, 300)))
      @tablero.añade_casilla(Casilla.new_titulo(TituloPropiedad.new("Calle Fideo", 550, 80, 15, 600, 750)))
    end
    
    
    def pasar_turno
      @indice_jugador_actual = (@indice_jugador_actual+1)%@jugadores.size
    end
    
    
    def ranking
      @jugadores = @jugadores.sort
      return @jugadores
    end
    
    
    def salir_carcel_pagando
      return @jugadores[@indice_jugador_actual].salir_carcel_pagando
    end
    
    
    def salir_carcel_tirando
      return @jugadores[@indice_jugador_actual].salir_carcel_tirando
    end
    
    
    def siguiente_paso
      
      jugador_actual = @jugadores[@indice_jugador_actual]
      operacion = @gestor_estados.operaciones_permitidas(jugador_actual, @estado)
      case operacion
        when OperacionesJuego::PASAR_TURNO
          pasar_turno
          siguiente_paso_completado(operacion)
        when OperacionesJuego::AVANZAR
          avanza_jugador
          siguiente_paso_completado(operacion)
      end
      return operacion
    end
    
    
    def siguiente_paso_completado(operacion)
      @estado = @gestor_estados.siguiente_estado(@jugadores[@indice_jugador_actual], @estado, operacion)
    end
    
    
    def vender(ip)
      @jugadores[@indice_jugador_actual].vender(ip)
    end
    
    attr_reader :tablero, :mazo, :indice_jugador_actual, :jugadores, :dado
    private :avanza_jugador, :contabilizar_pasos_por_salida, :inicializar_mazo_sorpresas, :inicializar_tablero, :pasar_turno, :ranking
    
  end
end
