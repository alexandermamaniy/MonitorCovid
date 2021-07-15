package monitor;

import cargarregistros.CargarRegistros;
import cargarsintomas.CargarSintomas;
import diagnosticos.DiagnosticoPorFases;

public class Monitor {
    private Fase fase;
    private Sintomas sintomas;
    private Registros registros;
    private FuncionDiagnostico funcion;
    private int resultadoDiagnostico;
    private CargarRegistros cargarRegistros;

    public Monitor() {
        CargarSintomas cargarSintomas = new CargarSintomas();
        sintomas = cargarSintomas.getSintomas();
        funcion = new DiagnosticoPorFases(sintomas);
        registros = new Registros();
        fase = (new DatosFase()).leerDatosFase();
        cargarRegistros = new CargarRegistros(sintomas.getSintomasFase(fase));

    }

    public void monitorear() {
        registros = cargarRegistros.getRegistros();
        resultadoDiagnostico = funcion.diagnostico(registros);
        mostrarDiaFase(resultadoDiagnostico);
        guardarEstado(resultadoDiagnostico);
    }

    private void mostrarDiaFase(int resultadoDiagnostico){
        System.out.println(resultadoDiagnostico);
    }

    private void guardarEstado(int diagnostico){
        if(diagnostico>=13) {
            fase.setNombre("SegundaFase");
        } else {
            fase.setNombre("PrimeraFase");
        }
        fase.setDia(diagnostico);
        (new DatosFase()).guardarDatosFase(fase);
    }

    public int getResultado() {
        return resultadoDiagnostico;
    }

}
