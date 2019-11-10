#encoding:utf-8
require_relative 'operaciones_juego'
require 'io/console'
require_relative 'salidas_carcel'
require_relative 'respuestas'
require_relative 'diario'
require_relative 'gestiones_inmobiliarias'


module Civitas
  class Vista_textual
    
    def initialize
      @juego_model = nil
      @i_gestion = -1
      @i_propiedad = -1
    end
    
    
    def mostrar_estado(estado)
      puts estado
    end

    
    def pausa
      print "Pulsa una tecla"
      STDIN.getch
      print "\n"
    end

    
    def lee_entero(max,msg1,msg2)
      ok = false
      begin
        print msg1
        cadena = gets.chomp
        begin
          if (cadena =~ /\A\d+\Z/)
            numero = cadena.to_i
            ok = true
          else
            raise IOError
          end
        rescue IOError
          puts msg2
        end
        if (ok)
          if (numero >= max)
            ok = false
          end
        end
      end while (!ok)

      return numero
    end



    def menu(titulo,lista)
      tab = "  "
      puts titulo
      index = 0
      lista.each { |l|
        puts tab+index.to_s+"-"+l.to_s
        index += 1
      }

      opcion = lee_entero(lista.length,
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo")
      return opcion
    end

    
    def comprar
      opcion = menu("¿Deseas comprar la propiedad?",Respuestas::LISTA_RESPUESTAS)
      return Respuestas::LISTA_RESPUESTAS[opcion]
    end
    

    def gestionar
      
      nombres_propiedades = Array.new
      @i_gestion = -1
      
      while @i_gestion != 0
        @i_gestion = menu("¿Qué gestion inmobiliaria deseas hacer?",GestionesInmobiliarias::LISTA_GESTIONES_INMOBILIARIAS)  
        case @i_gestion
          when 1
            for i in @juego_model.get_jugador_actual.propiedades
              if !nombres_propiedades.include?(i.nombre)
                nombres_propiedades << i.nombre
              end
            end
            
          if nombres_propiedades.empty?
            puts "No puedes vender ningun propiedad \n"
            @i_propiedad = 0
          else
            @i_propiedad = menu("¿Qué propiedad deseas vender?",nombres_propiedades)
            @juego_model.vender(@i_propiedad)
          end
          
          when 2
            for x in @juego_model.get_jugador_actual.propiedades
              if x.hipotecado == false && !nombres_propiedades.include?(x.nombre)
                nombres_propiedades << x.nombre
              end
            end
            
          if nombres_propiedades.empty?
            puts "No puedes hipotecar ninguna propiedad \n"
            @i_propiedad = 0
          else
            @i_propiedad = menu("¿Qué propiedad deseas hipotecar?",nombres_propiedades)
            @juego_model.hipotecar(@i_propiedad)
          end
          
          when 3
            for y in @juego_model.get_jugador_actual.propiedades
              if y.hipotecado == true && !nombres_propiedades.include?(y.nombre)
                nombres_propiedades << y.nombre
              end
            end
            if nombres_propiedades.empty?
              puts "No puedes cancelar ninguna hipoteca \n"
              @i_propiedad = 0
            else
              @i_propiedad = menu("¿Qué propiedad deseas cancelar hipotecar?",nombres_propiedades)
              @juego_model.cancelar_hipoteca(@i_propiedad)
            end

          when 4
            j=0
            for z in @juego_model.get_jugador_actual.propiedades
              if @juego_model.get_jugador_actual.puedo_edificar_casa(@juego_model.get_jugador_actual.propiedades[j]) == true && !nombres_propiedades.include?(z.nombre)
                nombres_propiedades << z.nombre
              end
              j = j+1
            end
            
          if nombres_propiedades.empty?
            puts "No puedes construir ninguna casa \n"
            @i_propiedad = 0
          else
            @i_propiedad = menu("¿Qué propiedad deseas construir casa?",nombres_propiedades)
            @juego_model.construir_casa(@i_propiedad)
          end
          
          when 5
            j=0
            for w in @juego_model.get_jugador_actual.propiedades
              if @juego_model.get_jugador_actual.puedo_edificar_hotel(@juego_model.get_jugador_actual.propiedades[j]) == true && !nombres_propiedades.include?(w.nombre)
                nombres_propiedades << w.nombre
              end
              j = j+1
            end
            if nombres_propiedades.empty?
              puts "No puedes construir ningun hotel \n"
              @i_propiedad = 0
            else
              @i_propiedad = menu("¿Qué propiedad deseas construir hotel?",nombres_propiedades)
              @juego_model.construir_hotel(@i_propiedad)
            end

        end
      end
    end
    

    def getGestion
      return @i_gestion
    end
    

    def getPropiedad
      return @i_propiedad
    end
    

    def mostrarSiguienteOperacion(operacion)
      puts "Siguiente operacion" + operacion.to_s
    end
    

    def mostrarEventos
      while (Diario.instance.eventos_pendientes)
        puts Diario.instance.leer_evento        
      end
    end
    

    def setCivitasJuego(civitas)
         @juego_model=civitas
    end
    

    def actualizarVista
      puts @juego_model.info_jugador_texto
    end
    
    
    def salir_carcel
      opcion = menu(" Elige la forma para intentar salir de la carcel ", 
        SalidasCarcel::LISTA_SALIDAS_CARCEL)
      return SalidasCarcel::LISTA_SALIDAS_CARCEL[opcion]
    end
        
  end
end
