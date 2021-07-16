package monitor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Sintomas getSintomasFase(Fase fase){
        Sintomas res = new Sintomas();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String ultimoAccesoString = format.format(fase.getUltimoAcceso());
        String fechaActualString = format.format(new Date());

        if ((fase.getDia()<13) || (fase.getDia()==13 && ultimoAccesoString.equals(fechaActualString )) ) {
            res.sintomas = this.sintomas.stream()
                    .filter(sintoma -> sintoma.getClass().getSimpleName().equals("PrimeraFase")).collect( Collectors.toCollection(HashSet::new));
            return res;
        } else {
            return this;
        }
    }
}
