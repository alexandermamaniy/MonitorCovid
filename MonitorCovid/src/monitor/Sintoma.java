package monitor;

import java.io.Serializable;
import java.util.Objects;

public abstract class Sintoma implements Comparable<Sintoma>, Serializable {

    private String nombre;

    public Sintoma(String n) {
        nombre = n;
    }

    public abstract int peso();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Sintoma sintoma = (Sintoma) obj;
        return nombre.equals(sintoma.nombre);
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int compareTo(Sintoma sintoma) {
        return nombre.compareTo(sintoma.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
