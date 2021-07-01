package cargarregistros.utils;

import monitor.Sintoma;
import monitor.Sintomas;

public class FormatoSintomas {
    public String formatoSintomas(Sintomas sintomas){
        StringBuilder formato = new StringBuilder();
        for(Sintoma s: sintomas){
            formato.append(s.toString()).append(", ");
        }
        return formato.toString();
    }
}
