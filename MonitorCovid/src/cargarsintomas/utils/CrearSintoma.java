package cargarsintomas.utils;

import monitor.Sintoma;
import monitor.Sintomas;

import java.lang.reflect.Constructor;

public class CrearSintoma {

    private final String nombrePaquete = "sintomas";

    public Sintoma crear(String nombreSintoma, String categoriaSintoma, Sintomas sintomas) throws Exception {
        Sintoma sintoma;
        TratamientoNombreSintoma tratamientoNombreSintoma = new TratamientoNombreSintoma();
        ValidadorSintoma validadorSintoma = new ValidadorSintoma(sintomas);
        nombreSintoma = tratamientoNombreSintoma.tratarNombre(nombreSintoma);
        try {
            Class<?> cl = Class.forName(nombrePaquete+"."+categoriaSintoma);
            Constructor<?> constructor = cl.getConstructor(String.class);
            sintoma = (Sintoma) (constructor.newInstance(new Object[]{nombreSintoma}));
            if(validadorSintoma.existeSintoma(sintoma)){
               throw new Exception("Sintoma existente");
            }
        } catch (Exception e){
            throw new Exception("Categoria inexistente");
        }
        return sintoma;
    }
}
