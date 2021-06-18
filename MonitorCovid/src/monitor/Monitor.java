package monitor;

import cargarregistros.CargarRegistros;
import cargarsintomas.CargarSintomas;
import diagnosticos.DiagnosticoSimple;

public class Monitor {

    private Sintomas sintomas;
    private Registros registros;
    private FuncionDiagnostico funcion;
    private int resultadoDiagnostico;
    private CargarRegistros cargarRegistros;

    public Monitor() {
        CargarSintomas cargarSintomas = new CargarSintomas();
        sintomas = cargarSintomas.getSintomas();
        funcion = new DiagnosticoSimple(sintomas);
        registros = new Registros();
        cargarRegistros = new CargarRegistros(sintomas);
    }

    public void monitorear() {
        Registro registro = cargarRegistros.getRegistro();
        registros.push(registro);
        resultadoDiagnostico = funcion.diagnostico(registros);
    }

    public int getResultado() {
        return resultadoDiagnostico;
    }

}
