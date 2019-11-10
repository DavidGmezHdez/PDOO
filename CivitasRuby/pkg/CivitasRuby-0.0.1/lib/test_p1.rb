# encoding:utf-8
require_relative 'civitas_juego'
require_relative 'vista_textual'
require_relative 'controlador'

module Civitas
  class Test
    
    def self.get_nombre_jugadores
      nombres=Array.new
      i=0
      puts "Introduce el numero de jugadores: "
      numero_jugadores=gets.chomp.to_i
      while i < numero_jugadores
        puts "Introduce los nombres de los jugadores"
        nombres[i] = gets
        i+=1
      end     
      return nombres
    end
    
    def self.main
      @@vista = Vista_textual.new
      nombres = get_nombre_jugadores
      @@juego = CivitasJuego.new(nombres)
      @@controlador = Controlador.new(@@juego,@@vista)
      @@controlador.juega
    end
  end
  Test.main
end
