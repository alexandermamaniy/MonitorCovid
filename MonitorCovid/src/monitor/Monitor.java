package monitor;

import cargarregistros.CargarRegistros;
import cargarsintomas.CargarSintomas;
import diagnosticos.DiagnosticoPorFases;

import java.util.Date;

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
        if (resultadoDiagnostico==0){
            System.out.println("Usted no tiene ningun sintomas de Covid, pero tiene que seguir cuidandose.");
        } else if (resultadoDiagnostico==11){
            System.out.println("Dia "+((resultadoDiagnostico-10)) + " de la primera fase.");
            System.out.println("Se dectecto sintomas iniciales del covid, puede que tenga la enfermedad, acuda los mas pronto posible a hacerce una prueba.");
        } else if (resultadoDiagnostico==12){
            System.out.println("Dia "+((resultadoDiagnostico-10)) + " de la primera fase.");
            System.out.println("Usted sigue presentando sintomas de covid, asista de forma inmediata a un centro medico a hacerce una prueba.");
        } else if (resultadoDiagnostico==13) {
            System.out.println("Dia "+((resultadoDiagnostico-10)) + " de la primera fase.");
            System.out.println("Usted sigue presentando sintomas de covid, y ya esta por entrar a la segunda fase de la enfermedad, asista de forma inmediata a un centro medico.");
        } else if (resultadoDiagnostico>=21) {
            System.out.println("Dia "+((resultadoDiagnostico-20)) + " de la segunda fase.");
            System.out.println("Usted ya presenta sintomas de segunda fase de covid, asista de forma inmediata a un centro medio a internarse, esta en peligro.");
        }
    }

    private void guardarEstado(int diagnostico){
        if(diagnostico>13) {
            fase.setNombre("SegundaFase");
        } else {
            fase.setNombre("PrimeraFase");
        }
        fase.setDia(diagnostico);
        fase.setUltimoAcceso(new Date());
        (new DatosFase()).guardarDatosFase(fase);
    }

    public int getResultado() {
        return resultadoDiagnostico;
    }

}
