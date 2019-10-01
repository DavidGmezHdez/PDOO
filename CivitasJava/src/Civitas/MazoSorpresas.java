package Civitas;
import java.util.ArrayList;
import java.util.Collections;

public class MazoSorpresas {
    private ArrayList<Sorpresa> sorpresas;
    private boolean barajada;
    private int usadas;
    private boolean debug;
    private ArrayList<Sorpresa> cartasEspeciales;
    private Sorpresa ultimaSorpresa;
    
    static final private Diario diario = Diario.getInstance();
    
    
    private void init(){
        sorpresas = new ArrayList<>();
        cartasEspeciales = new ArrayList<>();
        barajada = false;
        usadas = 0;
    }
    
    MazoSorpresas(boolean estado){
        debug = estado;
        init();
        if(debug)
            diario.ocurreEvento("debug on");
    }
    
    MazoSorpresas(){
        debug = false;
        init();
    }
    
    void alMazo(Sorpresa s){
        if(!barajada)
            sorpresas.add(s);
    }
    
    Sorpresa siguiente(){
        if((!barajada || usadas == sorpresas.size()) && !debug ){
            Collections.shuffle(sorpresas);
            usadas = 0;
            barajada = true;
        }
        
        usadas++;
        ultimaSorpresa = sorpresas.get(0);
        sorpresas.remove(0);
        sorpresas.add(sorpresas.size(), ultimaSorpresa);
        return ultimaSorpresa;
    }
    
    void inhabilitarCartaEspecial(Sorpresa sorpresa){
        if(sorpresas.contains(sorpresa)){
            sorpresas.remove(sorpresa);
            cartasEspeciales.add(sorpresa);
            diario.ocurreEvento("Carta inhabilitada");
        }
        else
            diario.ocurreEvento("Carta no inhabilitada");
    }
    
    void habilitarCartaEspecial(Sorpresa sorpresa){
        if(cartasEspeciales.contains(sorpresa)){
            cartasEspeciales.remove(sorpresa);
            sorpresas.add(sorpresas.size(), sorpresa);
            diario.ocurreEvento("Carta habilitada");
        }
        else
            diario.ocurreEvento("Carta no habilitada");
    }

    
}
