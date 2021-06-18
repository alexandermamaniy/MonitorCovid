package sintomas;

import monitor.Sintoma;

public class PrimeraFase extends Sintoma {

    public PrimeraFase(String n) {
        super(n);
    }

    @Override
    public int peso() {
        return 10;
    }
}
