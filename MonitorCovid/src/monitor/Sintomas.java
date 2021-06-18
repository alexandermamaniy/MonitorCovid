package monitor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Sintomas implements Iterable<Sintoma>, Serializable {

    private Set<Sintoma> sintomas;

    public Sintomas() {
        sintomas = new HashSet<>();
    }

    public void add(Sintoma s) {
        sintomas.add(s);
    }

    @Override
    public Iterator<Sintoma> iterator() {
        return sintomas.iterator();
    }

}
