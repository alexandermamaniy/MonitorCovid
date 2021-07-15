package monitor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Sintomas implements Iterable<Sintoma>, Serializable {

    private Set<Sintoma> sintomas;

    public Sintomas() {
        sintomas = new TreeSet<>();
    }

    public void add(Sintoma s) {
        sintomas.add(s);
    }

    @Override
    public Iterator<Sintoma> iterator() {
        return sintomas.iterator();
    }

    public Sintomas getSintomasFase(Fase fase){
        Sintomas res = new Sintomas();
        if (fase.getNombre().equals("PrimeraFase")){
            res.sintomas = this.sintomas.stream()
                    .filter(sintoma -> sintoma.getClass().getSimpleName().equals(fase.getNombre())).collect( Collectors.toCollection(HashSet::new));
            return res;
        } else {
            return this;
        }
    }
}
