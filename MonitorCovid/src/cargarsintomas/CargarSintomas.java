package cargarsintomas;

import cargarsintomas.datos.DatosSintomas;
import cargarsintomas.gui.VentanaFrame;
import monitor.Sintoma;
import monitor.Sintomas;

public class CargarSintomas {

    private Sintomas sintomas;

    public CargarSintomas() {
        cargarSintomas();
        cargarSintomasApp();
    }

    private void cargarSintomas() {
        DatosSintomas datosSintomas = new DatosSintomas();
        sintomas = new Sintomas();
        if(datosSintomas.existeDatosSintomas()){
            try {
                for(Sintoma s: datosSintomas.leerDatosSintomas()){
                    sintomas.add(s);
                }
            } catch (   Exception e){
                e.printStackTrace();
            }
        }
    }

    private void cargarSintomasApp() {
        new VentanaFrame(sintomas);
    }

    public Sintomas getSintomas() {
        return sintomas;
    }

}
