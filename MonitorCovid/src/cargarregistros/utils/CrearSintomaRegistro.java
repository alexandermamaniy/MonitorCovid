package cargarregistros.utils;

import monitor.Sintoma;
import monitor.Sintomas;

import java.util.HashMap;
import java.util.Map;

public class CrearSintomaRegistro {

    private Map<String, Sintoma> mapSintomas;

    public CrearSintomaRegistro(Sintomas sintomas){
        mapSintomas = new HashMap<>();
        for (Sintoma s: sintomas) {
            mapSintomas.put( s.toString(), s);
        }
    }

    public Sintoma crear(String sintoma){
        return mapSintomas.get(sintoma);
    }
}
