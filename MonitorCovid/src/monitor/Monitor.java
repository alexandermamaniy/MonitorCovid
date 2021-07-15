package monitor;

import cargarregistros.CargarRegistros;
import cargarsintomas.CargarSintomas;
import diagnosticos.DiagnosticoPorFase;

public class Monitor {

    private Sintomas sintomas;
    private Registros registros;
    private FuncionDiagnostico funcion;
    private int resultadoDiagnostico;
    private CargarRegistros cargarRegistros;

    public Monitor() {
        CargarSintomas cargarSintomas = new CargarSintomas();
        sintomas = cargarSintomas.getSintomas();
        funcion = new DiagnosticoPorFase(sintomas);
        registros = new Registros();
        cargarRegistros = new CargarRegistros(funcion.getSintomasFase());
    }

    public void monitorear() {
        registros = cargarRegistros.getRegistros();
        resultadoDiagnostico = funcion.diagnostico(registros);
        funcion.mostrarDiagnostico(resultadoDiagnostico);
    }

    public int getResultado() {
        return resultadoDiagnostico;
    }

}
