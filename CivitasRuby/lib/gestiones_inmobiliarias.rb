# encoding:utf-8

module Civitas
  class GestionesInmobiliarias
    VENDER = :vender
    HIPOTECAR = :hipotecar
    CANCELAR_HIPOTECA = :cancelar_hipoteca
    CONSTRUIR_CASA = :construir_casa
    CONSTRUIR_HOTEL = :contruir_hotel
    TERMINAR = :terminar  
    
    lista_gestiones_inmobiliarias = [OperacionesInmobiliarias::VENDER,
      OperacionesInmobiliarias::HIPOTECAR,OperacionesInmobiliarias::CANCELAR_HIPOTECA,
      OperacionesInmobiliarias::CONSTRUIR_CASA,OperacionesInmobiliarias::CONSTRUIR_HOTEL,
      OperacionesInmobiliarias::TERMINAR]
  end
end
