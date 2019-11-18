# encoding:utf-8

class CasillaCalle < Casilla
  def initialize (nombre, titulo)
    @nombre = super(nombre)
    @titulo = titulo 
  end
  
  def recibe_jugador_calle(iactual,todos)
    if (super.jugador_correcto(iactual, todos))
        super.informe(iactual,todos)
        jugador = todos[iactual];
        
        if(!@titulo.tiene_propietario())
          jugador.puede_comprar_casilla()
        else
          @titulo.tramitar_alquiler(jugador)
        end
    end
  end
  
end
