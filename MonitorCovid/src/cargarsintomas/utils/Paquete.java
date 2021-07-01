package cargarsintomas.utils;

import monitor.Sintoma;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Paquete {

    private final String nombrePaquete = "sintomas";

    public List<String> obtenerClasesPaqueteSintomas()  {
        List<String> listaClasesPaquete = new ArrayList<>();
        try {
            File[] classes = this.archivosPaquete();
            Class<Sintoma> sintomaClass = Sintoma.class;
            for (File clase: classes){
                try {
                    String nombreClase = clase.getName().split("\\.")[0];
                    Class.forName(nombrePaquete+"."+nombreClase).asSubclass(sintomaClass);
                    listaClasesPaquete.add(nombreClase);
                } catch ( Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return listaClasesPaquete;
    }

    private File[] archivosPaquete() throws IOException {
        File dir = null;
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader()
                .getResources(nombrePaquete);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            dir = new File(url.getFile());
        }
        assert dir != null;
        return  dir.listFiles();
    }
}
