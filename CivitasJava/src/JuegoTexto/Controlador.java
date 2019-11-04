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

        if (!juego.finalDelJuego()) {
            vista.actualizarVista();
            vista.pausa();
            operacion = juego.siguientePaso();

            if (operacion != OperacionesJuego.PASAR_TURNO) {
                vista.mostrarEventos();
            }
            if (!juego.finalDelJuego()) {
                switch (operacion) {
                    case COMPRAR:
                        resp = vista.comprar();
                        if (resp == Respuestas.SI) {
                            juego.comprar();
                            juego.siguientePasoCompletado(operacion);
                        }

                    case GESTIONAR:
                        vista.gestionar();
                        indGest = vista.getGestion();
                        numProp = vista.getPropiedad();
                        gest = GestionesInmobiliarias.values()[indGest];
                        OperacionInmobiliaria opInmobiliaria = new OperacionInmobiliaria(gest, numProp);
                        switch (gest) {
                            case VENDER:
                                juego.vender(opInmobiliaria.getNumPropiedad());
                            case HIPOTECAR:
                                juego.hipotecar(opInmobiliaria.getNumPropiedad());
                            case CANCELAR_HIPOTECA:
                                juego.cancelarHipoteca(opInmobiliaria.getNumPropiedad());
                            case CONSTRUIR_CASA:
                                juego.construirCasa(opInmobiliaria.getNumPropiedad());
                            case CONSTRUIR_HOTEL:
                                juego.construirHotel(opInmobiliaria.getNumPropiedad());
                            case TERMINAR:
                                juego.siguientePasoCompletado(operacion);
                        }

                    case SALIR_CARCEL:
                        switch (vista.salirCarcel()) {
                            case PAGANDO:
                                juego.salirCarcelPagando();
                            case TIRANDO:
                                juego.salirCarcelTirando();
                        }
                        juego.siguientePasoCompletado(operacion);
                }
            }

        } else {
            juego.ranking().toString();
        }
    }
}
