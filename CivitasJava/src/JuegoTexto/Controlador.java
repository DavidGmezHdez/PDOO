package JuegoTexto;

import Civitas.CivitasJuego;
import Civitas.Diario;
import Civitas.OperacionesJuego;
import Civitas.Respuestas;
import Civitas.SalidasCarcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import Civitas.Casilla;
import Civitas.GestionesInmobiliarias;
import Civitas.Jugador;
import Civitas.TituloPropiedad;
import Civitas.OperacionInmobiliaria;

public class Controlador {

    private CivitasJuego juego;
    private VistaTextual vista;

    Controlador(CivitasJuego juego, VistaTextual vista) {
        this.juego = juego;
        this.vista = vista;
    }

    void juega() {
        OperacionesJuego operacion;
        Respuestas resp;
        GestionesInmobiliarias gest;
        int numProp;
        int indGest;
        
        vista.setCivitasJuego(juego);
        
        while(!this.juego.finalDelJuego()){
            vista.actualizarVista();
            vista.pausa();
            operacion = juego.siguientePaso();

            if (operacion != OperacionesJuego.PASAR_TURNO)
                vista.mostrarEventos();
            
            if (!juego.finalDelJuego()) {
                switch (operacion) {
                    case COMPRAR:
                        resp = vista.comprar();
                        if (resp == Respuestas.SI) {
                            juego.comprar();
                        }
                        juego.siguientePasoCompletado(operacion);
                        break;
                    case GESTIONAR:
                        vista.gestionar();
                        indGest = vista.getGestion();
                        numProp = vista.getPropiedad();
                        gest = GestionesInmobiliarias.values()[indGest];
                        OperacionInmobiliaria opInmobiliaria = new OperacionInmobiliaria(gest, numProp);
                        System.out.println(indGest);
                        switch (indGest) {
                            case 1:
                                juego.vender(opInmobiliaria.getNumPropiedad());
                                break;
                            case 2:
                                juego.hipotecar(opInmobiliaria.getNumPropiedad());
                                break;
                            case 3:
                                juego.cancelarHipoteca(opInmobiliaria.getNumPropiedad());
                                break;
                            case 4:
                                juego.construirCasa(opInmobiliaria.getNumPropiedad());
                                break;
                            case 5:
                                juego.construirHotel(opInmobiliaria.getNumPropiedad());
                                break;
                            case 0:
                                juego.siguientePasoCompletado(operacion);
                                break;
                        }
<<<<<<< HEAD
                        juego.siguientePasoCompletado(operacion);
=======
>>>>>>> 1a99332136297a7a0cc0fd85eceba1402b9c5840
                        break;
                    case SALIR_CARCEL:
                        switch (vista.salirCarcel()) {
                            case PAGANDO:
                                juego.salirCarcelPagando();
                                break;
                            case TIRANDO:
                                juego.salirCarcelTirando();
                                break;
                        }
                        juego.siguientePasoCompletado(operacion);
                        break;
                    }
                }
            }
            juego.ranking().toString();
    }
}
