package cargarsintomas.utils;

import monitor.Sintoma;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CrearSintoma {

    public Sintoma crear(String nombreSintoma, String categoriaSintoma) {
        Sintoma sintoma = null;
        TratamientoNombreSintoma tratamientoNombreSintoma = new TratamientoNombreSintoma();
        try {
            nombreSintoma = tratamientoNombreSintoma.tratarNombre(nombreSintoma);
            String nombrePaquete = "sintomas";
            Class<?> cl = Class.forName(nombrePaquete + "." + categoriaSintoma);
            Constructor<?> constructor = cl.getConstructor(String.class);
            sintoma = (Sintoma) (constructor.newInstance(new Object[]{nombreSintoma}));

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e ){
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return sintoma;
    }
}
