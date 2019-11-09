# encoding:utf-8

require_relative 'tipo_casilla'
require_relative 'titulo_propiedad'
require_relative 'sorpresa'
require_relative 'mazo_sorpresas'

module Civitas
  class Casilla
    
    def initialize(tipo,nombre = nil, titulo = nil, cantidad = 0, num_casilla_carcel = 0, mazo = nil)
      @nombre = nombre
      @carcel = num_casilla_carcel
      @importe = cantidad
      @titulo = titulo
      @mazo = mazo
      @sorpresa = nil
      @tipo = tipo
    end
    
    
    attr_reader :nombre, :titulo, :tipo, :carcel, :importe, :sorpresa, :mazo
    
    
    def self.new_nombre(nombre)
      new(TipoCasilla::DESCANSO,nombre)
    end
    
    
    def self.new_titulo(titulo)
      new(TipoCasilla::CALLE,titulo.nombre, titulo)
    end
    
    
    def self.new_cantidad(cantidad, nombre)
      new(TipoCasilla::IMPUESTO,nombre,nil,cantidad)
    end
    
    
    def self.new_carcel(num_casilla_carcel, nombre)
      new(TipoCasilla::JUEZ,nombre,nil,0,num_casilla_carcel)
    end
    
    
    def self.new_mazo(mazo, nombre)
      new(TipoCasilla::SORPRESA,nombre,nil,0, 0,mazo)
    end
    
    
    def informe(iactual, todos)
      Diario.instance.ocurre_evento(" Ha caído en la casilla \n" + self.to_s + " el jugador " + todos[iactual].nombre + "\n")
    end
      
    
    def jugador_correcto(iactual,todos)
      return iactual>=0 && iactual<todos.size
    end
    
    
    def recibe_jugador(iactual,todos)
      case(@tipo)
      when TipoCasilla::CALLE
        recibe_jugador_calle(iactual,todos)
      when TipoCasilla::IMPUESTO
        recibe_jugador_impuesto(iactual, todos)
      when TipoCasilla::JUEZ
        recibe_jugador_juez(iactual, todos)
      when TipoCasilla::SORPRESA
        recibe_jugador_sorpresa(iactual, todos)
      else
        informe(iactual,todos);
      end
    end
    
    
    def recibe_jugador_calle(iactual,todos)
      if jugador_correcto(iactual,todos)
        informe(iactual,todos)
        jugador = todos[iactual];
        
        if(!@titulo.tiene_propietario())
          jugador.puede_comprar_casilla()
        else
          @titulo.tramitar_alquiler(jugador)
        end
      end
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
      if jugador_correcto(iactual,todos)
        @sorpresa = @mazo.siguiente
        informe(iactual,todos)
        @sorpresa.aplicar_a_jugador(iactual,todos)
      end
    end
    
    
    
    def to_s
      case (@tipo)
        when TipoCasilla::CALLE
            "Casilla { \n Nombre: #{@nombre}  \n #{@titulo.to_s}  \n}"
        when TipoCasilla::IMPUESTO
            "Casilla { \n Nombre: #{@nombre}  \n Valor: #{@importe}"
        else
          "Casilla { \n Nombre: #{@nombre} \n Tipo: #{@tipo} \n}"
      end
      
      
    end
    
    
    private :informe, :recibe_jugador_sorpresa, :recibe_jugador_juez,
      :recibe_jugador_impuesto, :recibe_jugador_calle
    
  end
end
