#encoding:utf-8

require_relative 'dado'
require_relative 'jugador'
require_relative 'estados_juego'
require_relative 'gestor_estados'
require_relative 'tablero'
require_relative 'mazo_sorpresas'



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
      
      @@dado.debug = false
      
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
      @mazo.al_mazo(SorpresaEspeculador.new("¡Te conviertes en especulador", 100))
      @mazo.al_mazo(SorpresaPagarCobrar.new("Te vas a la ruleta, crees ganar pero el ruso de al lado te hace la jugada, pierdes 200 euros",-200))
      @mazo.al_mazo(SorpresaSalirCarcel.new(@mazo))
      @mazo.al_mazo(SorpresaPorCasaHotel.new("Gracias a la burbuja del alquiler, la gente compra más casas y hay más turistas en hoteles, ganas 300 euros.",300))
      @mazo.al_mazo(SorpresaPorJugador.new("Pides a la gente que te de dinero para comprar un regalo en común, pero acabas quedándotelo tu para ir a Pedro",200))
      @mazo.al_mazo(SorpresaPagarCobrar.new("Recibes un sobre con la letra B escrita, recibes 500 euros",500))
      @mazo.al_mazo(SorpresaPorJugador.new("Dijiste que invitarías a chupitos pero no lo hiciste, pagas 50 euros a cada uno",-50))
      @mazo.al_mazo(SorpresaIrACasilla.new("Pides un Uber que te lleva la casilla mitad del tablero",tablero,10))
      @mazo.al_mazo(SorpresaCarcel.new(tablero))
      @mazo.al_mazo(SorpresaIrACasilla.new("Alquilas una bici amarilla que te lleva a la casilla 5, luego la tiras al río",tablero, 5))
      @mazo.al_mazo(SorpresaPorCasaHotel.new("Mala suerte, Hacienda te ha pillado saltándote la declaración de bienes, debes 500 euros",-500))
    end
    
    
    def inicializar_tablero(mazo)
      @tablero.añade_casilla(CasillaCalle.new(TituloPropiedad.new("Calle Willyrex", 625, 0.70, 12, 350, 400)))
      @tablero.añade_casilla(CasillaSorpresa.new("Sorpresa",mazo))
      @tablero.añade_casilla(CasillaCalle.new(TituloPropiedad.new("Calle Guerrero", 700, 0.50, 10, 550, 250)))
      @tablero.añade_juez
      @tablero.añade_casilla(CasillaCalle.new(TituloPropiedad.new("Calle Picaporte", 740, 0.55, 19, 300, 575)))
      @tablero.añade_casilla(Casilla.new("Parking: Coche Seguro, Precio !Barato"))
      @tablero.añade_casilla(CasillaCalle.new(TituloPropiedad.new("Calle Petunia", 925, 0.40, 17, 875, 600)))
      @tablero.añade_casilla(CasillaSorpresa.new(mazo, "Sorpresa"))
      @tablero.añade_casilla(CasillaCalle.new(TituloPropiedad.new("Calle Ruby", 500, 0.95, 14, 175, 275)))
      @tablero.añade_casilla(CasillaCalle.new(TituloPropiedad.new("Calle Focus", 830, 1, 16, 675, 500)))
      @tablero.añade_casilla(CasillaCalle.new(TituloPropiedad.new("Calle Motorola", 777, 0.70, 15, 750, 470)))
      @tablero.añade_casilla(CasillaImpuesto.new("Impuesto",500))
      @tablero.añade_casilla(CasillaCalle.new(TituloPropiedad.new("Calle Rengar", 900, 0.80, 12, 200, 450)))
      @tablero.añade_casilla(CasillaCalle.new(TituloPropiedad.new("Calle Potter", 675, 0.20, 20, 475, 750)))
      @tablero.añade_casilla(CasillaSorpresa.new("Sorpesa",mazo))
      @tablero.añade_casilla(CasillaCalle.new(TituloPropiedad.new("Calle Jesucristo", 1000, 0.90, 11, 250, 325)))
      @tablero.añade_casilla(CasillaCalle.new(TituloPropiedad.new("Calle Giorgio", 890, 0.30, 13, 1000, 300)))
      @tablero.añade_casilla(CasillaCalle.new(TituloPropiedad.new("Calle Fideo", 550, 0.65, 15, 600, 750)))
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
