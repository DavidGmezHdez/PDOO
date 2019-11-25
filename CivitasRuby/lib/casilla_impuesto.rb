require_relative 'casilla'
module Civitas
  class CasillaImpuesto < Casilla
    def initialize(nombre,cantidad)
      super(nombre)
      @cantidad = cantidad
    end

    def recibe_jugador(iactual,todos)
      if jugador_correcto(iactual,todos)
          informe(iactual,todos)
          todos[iactual].paga_impuesto(@importe)
      end
    end

      def to_s
        "Casilla { \n Nombre: #{@nombre} \n Valor: #{@cantidad} \n}"
      end

  end
end
