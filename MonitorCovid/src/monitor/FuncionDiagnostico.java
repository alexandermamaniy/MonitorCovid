package monitor;

public abstract class FuncionDiagnostico {

    private Sintomas sintomas;

    public FuncionDiagnostico(Sintomas s) {
        sintomas = s;
    }

    public abstract int diagnostico(Registros registros);

    public abstract void mostrarDiagnostico(int resultadoDiagnostico);

    public abstract Sintomas getSintomasFase();
}
