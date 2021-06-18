package cargarregistros.utils;

import monitor.Sintoma;
import monitor.Sintomas;

public class FormatoSintomas {
    public String formatoSintomas(Sintomas sintomas){
        String formato = "";
        for(Sintoma s: sintomas){
            formato += s.toString()+", ";
        }
        return formato;
    }
}
