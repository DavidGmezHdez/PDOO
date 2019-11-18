# encoding:utf-8
module Civitas
  class CasillaImpuesto < Casilla
    def initialize(nombre,cantidad)
      super(nombre)
      @cantidad = cantidad
    end

    def recibe_jugador(iactual,todos)
      if(super.jugador_correcto(iactual,todos))
          super.informe(iactual,todos)
          todos[iactual].paga_impuesto(@importe)
      end
    end

      def to_s
        "Casilla { \n Nombre: #{@nombre} \n Valor: #{@cantidad} \n}"
      end

  end
end
