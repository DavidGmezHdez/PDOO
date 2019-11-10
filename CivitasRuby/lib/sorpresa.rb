# encoding:utf-8

require_relative 'mazo_sorpresas'
require_relative 'tipo_sorpresa'
require_relative 'tablero'
require_relative 'jugador'
require_relative 'diario'

module Civitas
  class Sorpresa
    
    def initialize(texto, tablero, valor, mazo, tipo)
      @texto = texto
      @tablero = tablero
      @valor = valor
      @mazo = mazo
      @tipo = tipo
    end
    
    
    attr_writer :texto
    attr_accessor :valor
    attr_reader :tipo
    
    
    #Constructor para la sorpresa que envía a la cárcel
    def self.new_a_carcel(tipo, tablero)
      new("¡Directo a la cárcel!", tablero, 0, nil, tipo)
    end
    
    
    #Constructor para la sorpresa que envía al jugador a otra casilla
    def self.new_otra_casilla(tipo, tablero, valor, texto) 
      new(texto, tablero, valor, nil, tipo)
    end 
    
    
    #Constructor para el resto de sorpresas
    def self.new_sorpresas(tipo, valor, texto)
      new(texto, nil, valor, nil, tipo)
    end
    
    
    #Constructor para sorpresa que permite evitar la carcel
    def self.new_evitar_carcel(tipo, mazo)
      new("¡Genial! Has conseguido la carta para evitar la cárcel", nil, 0, mazo, tipo)
    end
    
    
    def init
      @valor = -1
      @mazo = nil
      @tablero = nil      
    end
    
    
    def jugador_correcto(actual, todos)
      es_correcto = false
      if actual >= 0 && actual < todos.size
        es_correcto = true
      end
      return es_correcto
    end
    
    
    def informe(actual, todos)
      Diario.instance.ocurre_evento("Aplicando sorpresa " + self.to_s + 
        " al jugador " + todos[actual].nombre + " tipo de sorpresa: " + @tipo.to_s)
    end
    
    
    def aplicar_a_jugador(actual, todos)
      case(@tipo)
      when TipoSorpresa::IRCARCEL
        aplicar_a_jugador_ir_a_carcel(actual,todos)
      when TipoSorpresa::IRCASILLA
        aplicar_a_jugador_ir_a_casilla(actual, todos)
      when TipoSorpresa::PAGARCOBRAR
        aplicar_a_jugador_pagar_cobrar(actual, todos)
      when TipoSorpresa::PORCASAHOTEL
        aplicar_a_jugador_por_casa_hotel(actual, todos)
      when TipoSorpresa::PORJUGADOR
        aplicar_a_jugador_por_jugador(actual, todos)
      when TipoSorpresa::SALIRCARCEL
        aplicar_a_jugador_salir_carcel(actual, todos)        
      end
    end
    
    
    def aplicar_a_jugador_ir_a_carcel(actual,todos)
      if(jugador_correcto(actual,todos))
        informe(actual,todos)
        todos[actual].encarcelar(@tablero.num_casilla_carcel);
      end
    end
    
    
    def aplicar_a_jugador_ir_a_casilla(actual,todos)
      casilla_actual = todos[actual].num_casilla_actual
      if(jugador_correcto(actual,todos))
        informe(actual,todos)
        tirada = @tablero.calcular_tirada(casilla_actual,@valor)
        nueva_posicion = @tablero.nueva_posicion(casilla_actual,tirada)
        todos[actual].mover_a_casilla(nueva_posicion)
        @tablero.casillas[nueva_posicion].recibe_jugador(actual,todos)
      end
    end
    
    
    def aplicar_a_jugador_pagar_cobrar(actual,todos)
      if(jugador_correcto(actual,todos))
        informe(actual,todos)
        todos[actual].modificar_saldo(@valor)
      end
    end
    
    
    def aplicar_a_jugador_por_casa_hotel(actual,todos)
      num_propiedades = todos[actual].cantidad_casas_hoteles
      nuevo_valor = @valor*num_propiedades
      if(jugador_correcto(actual,todos))
        informe(actual,todos)
        todos[actual].modificar_saldo(nuevo_valor)
      end
    end
    
    
    def aplicar_a_jugador_por_jugador(actual,todos)
      if(jugador_correcto(actual,todos))
        informe(actual,todos)
        valor_actual = @valor * (todos.size-1)
        valor_otros = @valor * -1
        
        for i in todos
          if i==todos[actual]
            i.modificar_saldo(valor_actual)
          else
            i.modificar_saldo(valor_otros)
          end
        end       
      end
    end
    
    
    def aplicar_a_jugador_salir_carcel(actual,todos)
      if(jugador_correcto(actual,todos))
        informe(actual,todos)
        nadie_salvo_conducto=0
        
        for i in todos
          if i.tiene_salvo_conducto
            nadie_salvo_conducto = nadie_salvo_conducto+1
          end
        end
        
        # Si nadie tiene salvo conducto
        if nadie_salvo_conducto==0
          todos[actual].obtener_salvoconducto(self)
          salir_del_mazo
        end
      end
    end
    
    
    def salir_del_mazo
      if(@tipo==TipoSorpresa::SALIRCARCEL)
        @mazo.inhabilitar_carta_especial(self)
      end
    end
    
    
    def usada
      if(@tipo==TipoSorpresa::SALIRCARCEL)
        @mazo.habilitarCartaEspecial(self)
      end
    end
    
    def to_s
      "Sorpresa: { \n Texto: #{@texto}  \n Tipo: #{@tipo}   \n Valor: #{@valor} }"
    end
    
    
    private :informe, :init, :aplicar_a_jugador_ir_a_carcel, 
      :aplicar_a_jugador_ir_a_casilla, 
      :aplicar_a_jugador_por_casa_hotel, :aplicar_a_jugador_por_jugador,
      :aplicar_a_jugador_salir_carcel
    
  end
end
