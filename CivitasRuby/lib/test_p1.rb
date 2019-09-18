# encoding:utf-8
require_relative 'dado'
require_relative 'tablero'
require_relative 'tipo_casilla'
require_relative 'tipo_sorpresa'
module Civitas
  class TestP1
    def self.main
      @dado = Dado.instance
      
      
      i=0
      while i < 5
        puts "Empieza el jugador " << @dado.quien_empieza(4) << "\n"
        i=i+1
      end
      
      puts "Último resultado "<< @dado.ultimo_resultado << "\n"
      
      if @dado.salgo_de_la_carcel
        puts "He salido de la cárcel"
      else
        puts "No he salido de la cárcel"
      end
      
      @tablero = Tablero.new(14)
      
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
        puts "Casilla #" << i << ": " << @tablero.get_casilla(i) << "\n"
      end
    end
  end
  TestP1.main
end