package sintomas;

import monitor.Sintoma;

public class SegundaFase extends Sintoma {

    public SegundaFase(String n) {
        super(n);
    }

    @Override
    public int peso() {
        return 100;
    }

}
