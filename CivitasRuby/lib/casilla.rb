# encoding:utf-8
module Civitas
  class Casilla
    def initialize(nombre, titulo, cantidad, num_casilla_carcel, mazo)
      @nombre = nombre
      @carcel = num_casilla_carcel
      @importe = cantidad
      @titulo = titulo
      @mazo = mazo
    end
    
    #SORPRESA COMO SE PONDRÍA AQUÍ, COMO ATRIBUTO LOCAL
    def self.new_nombre(nombre)
      init()
      new(nombre, NOSE, NOSE, NOSE, NOSE)
    end
    
    
    def self.new_titulo(titulo)
      init()
      new(NOSE, titulo, NOSE, NOSE, NOSE)
    end
    
    
    def self.new_cantidad(cantidad, nombre)
      init()
      new(nombre, NOSE, cantidad, NOSE, NOSE)
    end
    
    
    def self.new_carcel(num_casilla_carcel, nombre)
      init()
      new(nombre, NOSE, NOSE, num_casilla_carcel, NOSE)
    end
    
    
    def self.new_mazo(mazo, nombre)
      init()
      new(nombre, NOSE, NOSE, NOSE, mazo)
    end
    
    
    def informe(iactual, todos)
      @diario.instance().ocurre_evento("Ha caído en la casilla el jugador" + todos[iactual].nombre)
      to_s
    end
    
    
    def init
      #NO SE A QUE INCIALIZAR ?????????????
    end
    
    
    def jugador_correcto(iactual,todos)
      es_correcto=false
      if(iactual>=0 && iactual>todos.size())
        es_correcto=true
      end
      return es_correcto
    end
    
    
    def recibe_jugador_calle(iactual,todos)
      raise NotImplementedError
    end
    
    
    def recibe_jugador_impuesto(iactual,todos)
      if(jugador_correcto(iactual,todos))
        informe(iactual,todos)
        todos[iactual].paga_impuesto(@importe)
      end
    end
    
    
    def recibe_jugador_juez(iactual,todos)
      if(jugador_correcto(iactual,todos))
        informe(iactual,todos)
        todos[iactual].encarcelar(@carcel)
      end
    end
    
    
    def recibe_jugador_sorpresa(iactual,todos)
      raise NotImplementedError
    end
    
    attr_reader :nombre, :titulo
    
    
    def to_s
      "Casilla { Nombre: \n #{@nombre}  \n Valor: #{@importe} }"
    end
    
    private :init, :informe, :recibe_jugador_sorpresa, :recibe_jugador_juez,
      :recibe_jugador_impuesto, :recibe_jugador_calle
    
  end
end
