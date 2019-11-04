# encoding:utf-8
require_relative 'civitas_juego'
require_relative 'vista_textual'
require_relative 'controlador'

module Civitas
  class TestP1
    def self.main
      @@vista = Vista_textual.new
      nombres = Array.new
      nombres << "Pepe"
      nombres << "Kaka"
      @@juego = CivitasJuego.new(nombres)
      @@controlador = Controlador.new(@@juego,@@vista)
      @@controlador.juega
    end
  end
  TestP1.main
end
