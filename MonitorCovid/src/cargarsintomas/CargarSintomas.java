package cargarsintomas;

import cargarsintomas.datos.DatosSintomas;
import cargarsintomas.gui.VentanaSintomasFrame;
import monitor.Sintoma;
import monitor.Sintomas;

import java.io.IOException;

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
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void cargarSintomasApp() {
        new VentanaSintomasFrame(sintomas);
    }

    public Sintomas getSintomas() {
        return sintomas;
    }

}
