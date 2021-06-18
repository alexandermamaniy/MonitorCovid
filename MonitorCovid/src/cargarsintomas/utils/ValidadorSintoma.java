package cargarsintomas.utils;

import monitor.Sintoma;
import monitor.Sintomas;

public class ValidadorSintoma {

    Sintomas sintomas;

    public ValidadorSintoma(Sintomas sintomas){
        this.sintomas = sintomas;
    }

    public boolean existeSintoma(Sintoma sintoma){
        boolean existe = false;
        String name = sintoma.toString();
        for(Sintoma s: sintomas){
            if(s.toString().equals(name)){
               existe = true;
            }
        }
        return existe;
    }
}
