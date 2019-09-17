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
      
    end
  end
  
  TestP1.main
end