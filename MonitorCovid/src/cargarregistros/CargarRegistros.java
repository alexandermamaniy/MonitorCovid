package cargarregistros;

import cargarregistros.datos.DatosRegistros;
import cargarregistros.gui.VentanaJFrame;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintomas;

import java.io.IOException;
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
            try {
                for(Registro r: datosRegistros.leerDatosRegistros()){
                    registros.push(r);
                }
            } catch (IOException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    private void cargarSintomasApp(Sintomas sintomasMonitorDisponibles, Sintomas sintomasPaciente) {
        new VentanaJFrame(sintomasMonitorDisponibles, registros, sintomasPaciente);
    }

    public Registro getRegistro() {
        Sintomas sintomasPaciente = new Sintomas();
        cargarSintomasApp(sintomas, sintomasPaciente);
        return new Registro(new Date(),sintomasPaciente);
    }
}
