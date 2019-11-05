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

class VistaTextual {
  
  CivitasJuego juegoModel; 
  int iGestion=-1;
  int iPropiedad=-1;
  private static String separador = "=====================";
  
  private Scanner in;
  
  VistaTextual () {
    in = new Scanner (System.in);
  }
  
  void mostrarEstado(String estado) {
    System.out.println (estado);
  }
              
  void pausa() {
    System.out.print ("Pulsa una tecla");
    in.nextLine();
  }

  int leeEntero (int max, String msg1, String msg2) {
    Boolean ok;
    String cadena;
    int numero = -1;
    do {
      System.out.print (msg1);
      cadena = in.nextLine();
      try {  
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException e) { // No se ha introducido un entero
        System.out.println (msg2);
        ok = false;  
      }
      if (ok && (numero < 0 || numero >= max)) {
        System.out.println (msg2);
        ok = false;
      }
    } while (!ok);

    return numero;
  }

  int menu (String titulo, ArrayList<String> lista) {
    String tab = "  ";
    int opcion;
    System.out.println (titulo);
    for (int i = 0; i < lista.size(); i++) {
      System.out.println (tab+i+"-"+lista.get(i));
    }

    opcion = leeEntero(lista.size(),
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo");
    return opcion;
  }

  SalidasCarcel salirCarcel() {
    int opcion = menu ("Elige la forma para intentar salir de la carcel",
      new ArrayList<> (Arrays.asList("Pagando","Tirando el dado")));
    return (SalidasCarcel.values()[opcion]);
  }

  Respuestas comprar(){
    int opcion = menu ("¿Deseas comprar la propiedad?",
      new ArrayList<> (Arrays.asList("Si","No")));
    return (Respuestas.values()[opcion]);
  }

  void gestionar(){
    ArrayList<String> nombresGestion = new ArrayList<> 
        (Arrays.asList("Terminar","Vender","Hipotecar","Cancelar Hipoteca","Construir Casa", "Construir Hotel"));
    ArrayList<String>  nombresPropiedades = new ArrayList<>();
    
    this.iGestion = -1;
    
    while(this.iGestion != 0){
        this.iGestion = this.menu("¿Qué gestion inmobiliaria deseas hacer?", nombresGestion);
        switch (this.iGestion){
            case 1:
                for(int i=0;i<this.juegoModel.getJugadorActual().getPropiedades().size();i++)
                    nombresPropiedades.add(this.juegoModel.getJugadorActual().getPropiedades().get(i).getNombre());
                this.iPropiedad = this.menu("¿Qué propiedad deseas vender?",nombresPropiedades);
                this.juegoModel.vender(iPropiedad);
                break;
            case 2:
                for(int i=0;i<this.juegoModel.getJugadorActual().getPropiedades().size();i++){
                    if(this.juegoModel.getJugadorActual().getPropiedades().get(i).getHipotecado() == false)
                        nombresPropiedades.add(this.juegoModel.getJugadorActual().getPropiedades().get(i).getNombre());
                }
                this.iPropiedad = this.menu("¿Qué propiedad deseas hipotecar?",nombresPropiedades);
                this.juegoModel.hipotecar(iPropiedad);
                break;
            case 3:
                for(int i=0;i<this.juegoModel.getJugadorActual().getPropiedades().size();i++){
                    if(this.juegoModel.getJugadorActual().getPropiedades().get(i).getHipotecado() == true)
                        nombresPropiedades.add(this.juegoModel.getJugadorActual().getPropiedades().get(i).getNombre());
                }
                this.iPropiedad = this.menu("¿Qué propiedad deseas cancelar hipoteca?",nombresPropiedades);
                this.juegoModel.cancelarHipoteca(iPropiedad);
                break;
            case 4:
                for(int i=0;i<this.juegoModel.getJugadorActual().getPropiedades().size();i++){
                    if(this.juegoModel.getJugadorActual().puedoEdificarCasa(this.juegoModel.getJugadorActual().getPropiedades().get(i)))
                        nombresPropiedades.add(this.juegoModel.getJugadorActual().getPropiedades().get(i).getNombre());
                }
                this.iPropiedad = this.menu("¿Qué propiedad deseas construir casa?",nombresPropiedades);
                this.juegoModel.construirCasa(iPropiedad);
                break;
            case 5:
                for(int i=0;i<this.juegoModel.getJugadorActual().getPropiedades().size();i++){
                    if(this.juegoModel.getJugadorActual().puedoEdificarHotel(this.juegoModel.getJugadorActual().getPropiedades().get(i)))
                        nombresPropiedades.add(this.juegoModel.getJugadorActual().getPropiedades().get(i).getNombre());
                }
                this.iPropiedad = this.menu("¿Qué propiedad deseas construir hotel?",nombresPropiedades);
                this.juegoModel.construirHotel(iPropiedad);
                break;
        }
    }
  }
  
  public int getGestion(){
      return this.iGestion;
  }
  
  public int getPropiedad(){
      return this.iPropiedad;
  }
    
  void mostrarSiguienteOperacion(OperacionesJuego operacion){
    System.out.println("Siguiente operacion: " + operacion.toString());
  }

  void mostrarEventos(){
    while(Civitas.Diario.getInstance().eventosPendientes()){
       System.out.println(Civitas.Diario.getInstance().leerEvento());
    }
  }
  
  public void setCivitasJuego(CivitasJuego civitas){ 
        juegoModel=civitas;

    }
  
  void actualizarVista(){
      System.out.println(this.juegoModel.infoJugadorTexto());
      System.out.println(this.juegoModel.getCasillaActual().toString());
  }
  
  
}
