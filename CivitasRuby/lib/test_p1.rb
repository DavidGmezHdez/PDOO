# encoding:utf-8
require_relative 'dado'
require_relative 'tablero'
require_relative 'tipo_casilla'
require_relative 'tipo_sorpresa'
require_relative 'operaciones_juego'
require_relative 'estados_juego'
require_relative 'mazo_sorpresas'


module Civitas
  class TestP1
    def self.main
      @@dado = Dado.instance
      @@diario = Diario.instance
     
      i=0
      while i < 5
        puts "Empieza el jugador " + @@dado.quien_empieza(4).to_s + "\n"
        i=i+1
      end
      
      @@dado.tirar
      puts "Ultimo resultado " + @@dado.ultimo_resultado.to_s + "\n"
      
      if @@dado.salgo_de_la_carcel
        puts "He salido de la cárcel"
      else
        puts "No he salido de la cárcel"
      end
      
      puts "Tipo casilla: " +  TipoCasilla::CALLE.to_s
      puts "Tipo sorpresa: " + TipoSorpresa::IRCASILLA.to_s
      puts "Operaciones juego: " + OperacionesJuego::AVANZAR.to_s
      puts "Estados juego: " + EstadosJuego::INICIO_TURNO.to_s
      
      @mazo = MazoSorpresas.new(false)
      
      sorpresa1 = Sorpresa.new("sorpresa1")
      sorpresa2 = Sorpresa.new("sorpresa2")
      
      @mazo.al_mazo(sorpresa1)
      @mazo.al_mazo(sorpresa2)
      
      puts "Siguiente sorpresa: " + @mazo.siguiente.nombre
      
      @mazo.habilitar_carta_especial(sorpresa1)
      puts @@diario.leer_evento
      
      @mazo.inhabilitar_carta_especial(sorpresa2)
      puts @@diario.leer_evento
      
      puts"Introduce el numero de la casilla cárcel (menor o igual que el numero de casillas del tablero) \n"
      numero_carcel = gets.chomp.to_i
      
      if numero_carcel > 20
        numero_carcel = 20 - 1
      end
      
      @tablero = Tablero.new(numero_carcel)
      
      calle_willyrex = Casilla.new("Calle Willyrex")
      calle_guerrero = Casilla.new("Calle Guerrero")
      calle_picaporte = Casilla.new("Calle Picaporte")
      calle_cabra = Casilla.new("Calle Cabra")
      calle_giorgio = Casilla.new("Calle Giorgio")
      calle_potter = Casilla.new("Calle Potter")
      calle_petunia = Casilla.new("Calle Petunia")
      calle_motorola = Casilla.new("Calle Motorola")
      calle_focus = Casilla.new("Calle Focus")
      calle_rengar = Casilla.new("Calle Rengar")
      calle_yisus = Casilla.new("Calle Yisus")
      calle_ruby = Casilla.new("Calle Ruby")
      calle_java = Casilla.new("Calle Java")
      calle_diseño = Casilla.new("Calle Diseño")
      calle_net = Casilla.new("Calle Net")
      calle_anchoa = Casilla.new("Calle Anchoa")
      calle_frijoles = Casilla.new("Calle Frijoles")
      calle_caramelo = Casilla.new("Calle Caramelo")
      calle_fideo = Casilla.new("Calle Fideo")
     
      @tablero.añade_casilla(calle_willyrex)
      @tablero.añade_casilla(calle_guerrero)
      @tablero.añade_casilla(calle_picaporte)
      @tablero.añade_casilla(calle_cabra)
      @tablero.añade_casilla(calle_giorgio)
      @tablero.añade_casilla(calle_potter)
      @tablero.añade_casilla(calle_petunia)
      @tablero.añade_casilla(calle_motorola)
      @tablero.añade_casilla(calle_focus)
      @tablero.añade_casilla(calle_rengar)
      @tablero.añade_casilla(calle_yisus)
      @tablero.añade_casilla(calle_ruby)
      @tablero.añade_casilla(calle_java)
      @tablero.añade_casilla(calle_diseño)
      @tablero.añade_casilla(calle_net)
      @tablero.añade_casilla(calle_anchoa)
      @tablero.añade_casilla(calle_frijoles)
      @tablero.añade_casilla(calle_caramelo)
      @tablero.añade_casilla(calle_fideo)
      
      i = 0
      while i < 20
        puts "Casilla #" + i.to_s + ": " + @tablero.casillas[i].nombre + "\n"
        i=i+1
      end

    end
  end
  TestP1.main
>>>>>>> 325e2d86d15408eebf75a3ac587f3223de1af1ca
end