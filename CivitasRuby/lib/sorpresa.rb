# encoding:utf-8
require_relative "mazo_sorpresas"
require_relative "tablero"
require_relative "jugador"
require_relative "tipo_sorpresa"

module Civitas
  class Sorpresa
    def initialize(texto, tablero, valor, mazo, tipo)
      @texto = texto
      @tablero = tablero
      @valor = valor
      @mazo = mazo
      @tipo = tipo
    end
    
    #Constructor para la sorpresa que envía a la cárcel
    def self.new_a_carcel(tipo, tablero)
      init()
      new("¡Directo a la cárcel!", tablero, -1, nil, tipo)
    end
    
    #Constructor para la sorpresa que envía al jugador a otra casilla
    def self.new_otra_casilla(tipo, tablero, valor, texto) 
      init()
      new(texto, tablero, valor, nil, tipo)
    end 
    
    #Constructor para el resto de sorpresas
    def self.new_sorpresas(tipo, valor, texto)
      init()
      new(texto, nil, valor, nil, tipo)
    end
    
    #Constructor para sorpresa que permite evitar la carcel
    def self.new_evitar_carcel(tipo, mazo)
      init()
      new("¡Genial! Has conseguido la carta para evitar la cárcel", nil, -1, mazo, tipo)
    end
    
    
    def init
      @valor = -1
      @mazo = nil
      @tablero = nil      
    end
    
    
    def jugador_correcto(actual, todos)
      es_correcto = false
      if (actual >= 0 && actual < todos.size())
        es_correcto = true
      end
      return es_correcto
    end
    
    
    def informe(actual, todos)
      diario.ocurre_evento("Aplicando sorpresa al jugador " + todos[actual].nombre)
    end
    
    
    def aplicar_a_jugador(actual, todos)
      case(@tipo)
      when TipoSorpresa::IRACARCEL
        aplicar_a_jugador_ir_a_carcel(actual,todos)
      when TipoSorpresa::IRACASILLA
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
      # NO SE SI ESTA BIEN HECHO LO DE CASILLA ACTUAL ???????
      casilla_actual = new Casilla
      casilla_actual = todos[actual].num_casilla_actual
      if(jugador_correcto(actual,todos))
        informe(actual,todos)
        tirada = @tablero.calcular_tirada(casilla_actual,@valor)
        nueva_posicion = @tablero.nueva_posicion(casilla_actual,tirada)
        todos[actual].mover_a_casilla(nueva_posicion)
        # FALTAAAAAAAAAA UNA PARTE ??????
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
      num_propiedades = todos[actual].propiedades.size()
      nuevo_valor = @valor*num_propiedades
      if(jugador_correcto(actual,todos))
        informe(actual,todos)
        todos[actual].modificar_saldo(nuevo_valor)
      end
    end
    
    
    def aplicar_a_jugador_por_jugador(actual,todos)
      if(jugador_correcto(actual,todos))
        
        for i in (todos.size())
          if(i==actual)
            @tipo=TipoSorpresa::PAGARCOBRAR
            @valor *= (todos.size()-1)
            todos[i].recibe(@valor)
          else
            @tipo=TipoSorpresa::PAGARCOBRAR
            @valor *= -1
            todos[i].recibe(@valor)
          end
        end        
      end
    end
    
    
    def aplicar_a_jugador_salir_carcel(actual,todos)
      if(jugador_correcto(actual,todos))
        informe(actual,todos)
        nadie_salvo_conducto=0
        
        for i in (todos.size())
          if(todos[i].tiene_salvo_conducto())
            nadie_salvo_conducto = nadie_salvo_conducto+1
          end
        end
        
        if(nadie_salvo_conducto==0)
          todos[actual].obtener_salvoconducto(self)
          salir_del_mazo
        end
        
      end
    end
    
    
    def salir_del_mazo
      if(@tipo==TipoSorpresa::SALIRCARCEL)
        @mazo.inhabilitarCartaEspecial(self)
      end
    end
    
    
    def usada
      if(@tipo==TipoSorpresa::SALIRCARCEL)
        @mazo.habilitarCartaEspecial(self)
      end
    end
    
    
    def to_s
      "Numero casilla: \n #{@texto}  \n Valor: #{@valor} }"
    end
    
    attr_writer :texto, :valor
    
  end
end
