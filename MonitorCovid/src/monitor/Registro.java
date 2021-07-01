package monitor;

import java.io.Serializable;
import java.util.Date;

public class Registro implements Serializable {

    private Date fecha;
    private Sintomas sintomas;

    public Registro(Date f, Sintomas s) {
        fecha = f;
        sintomas = s;
    }

    public Sintomas getSintomas() {
        return sintomas;
    }

    public Date getFecha() {
        return fecha;
    }
}
