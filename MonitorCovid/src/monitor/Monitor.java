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
        if (resultadoDiagnostico==0){
            System.out.println("Usted no tiene ningun sintomas de Covid, pero tiene que seguir cuidandose");
        } else if (resultadoDiagnostico==11){
            System.out.println("Se dectecto sintomas iniciales del covid, usted se encuentra en el dia 1 de la fase 1 de covid, puede que tenga la enfermedad, acuda los mas pronto posible a hacerce una prueba.");
        } else if (resultadoDiagnostico==12){
            System.out.println("Usted sigue prensentado sintomas y se encuentra en el dia 2 de la fase 1 de covid, acuda de forma inmediata a hacerce una prueba.");
        } else if (resultadoDiagnostico==13) {
            System.out.println("Ya es el tercer dia que presenta sintomas de fase 1 de covid, es mas que probable que tenga covid, asista de forma inmediata a un centro medico.");
        } else if (resultadoDiagnostico>=21) {
            System.out.println("Dia "+((resultadoDiagnostico+3-20))+", Usted ya presenta sintomas de fase 2 de covid, asista de forma inmediata a un centro medio a internarse!");
        }
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
