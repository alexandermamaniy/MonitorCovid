package cargarsintomas.utils;

public class TratamientoNombreSintoma {
    public String tratarNombre(String nombreSintoma){
        nombreSintoma = nombreSintoma.toUpperCase();
        nombreSintoma = nombreSintoma.replaceAll("[\\s]{2,}", " ");
        nombreSintoma = nombreSintoma.trim();
        return nombreSintoma;
    }
}
