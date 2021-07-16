package cargarregistros;

import cargarregistros.datos.DatosRegistros;
import cargarregistros.gui.VentanaJFrame;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;

import java.util.Date;

public class CargarRegistros {

    private final Sintomas sintomas;
    private Registros registros;

    public CargarRegistros(Sintomas sintomas) {
        this.sintomas = sintomas;
        cargarRegistros();
    }

    private void cargarRegistros() {
        DatosRegistros datosRegistros = new DatosRegistros();
        registros = new Registros();
        if(datosRegistros.existeDatosRegistros()){
            for(Registro r: datosRegistros.leerDatosRegistros()){
                registros.push(r);
            }
        }
    }

    private void cargarSintomasApp( Sintomas sintomasPaciente) {
        new VentanaJFrame(sintomas, registros, sintomasPaciente);
    }

    public Registros getRegistros() {
        Sintomas sintomasPaciente = new Sintomas();
        cargarSintomasApp(sintomasPaciente);
        return registros;
    }

    public Registro getRegistro() {
        Sintomas sintomasPaciente = new Sintomas();
        cargarSintomasApp(sintomasPaciente);
        return new Registro(new Date(), sintomasPaciente);
    }
}
