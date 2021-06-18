package cargarregistros.datos;

import monitor.Registros;

import java.io.*;

public class DatosRegistros {

    private final String nombreArchivo = "registros.dat";
    private final String nombrePaquete = "cargarregistros";

    private String getPath(){
        File miDir = new File (".");
        String dir="", path="", separador = System.getProperty("file.separator");
        try {
            dir= miDir.getCanonicalPath();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        if ( dir.indexOf("out") >=0 ){
            path = dir+separador+nombrePaquete+separador+nombreArchivo;
        } else {
            path = dir+separador+"src"+separador+nombrePaquete+separador+nombreArchivo;
        }
        return path;
    }

    public Registros leerDatosRegistros() throws IOException, ClassNotFoundException {
        ObjectInputStream file = new ObjectInputStream(new FileInputStream(getPath()));
        Registros registros = (Registros) file.readObject();
        file.close();
        return registros;
    }

    public boolean existeDatosRegistros(){
        File f = new File(getPath());
        return f.exists();
    }

    public void guardarDatosRegistros(Registros registros) throws IOException {
        ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(getPath()));
        file.writeObject(registros);
        file.close();
    }
}
