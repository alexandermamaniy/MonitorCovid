package cargarregistros.datos;

import monitor.Registros;

import java.io.*;

public class DatosRegistros {

    private final String nombreArchivo = "registros.dat";

    public Registros leerDatosRegistros() throws IOException, ClassNotFoundException {
        ObjectInputStream file = new ObjectInputStream(new FileInputStream(nombreArchivo));
        Registros registros = (Registros) file.readObject();
        file.close();
        return registros;
    }

    public boolean existeDatosRegistros(){
        File f = new File(nombreArchivo);
        return f.exists();
    }

    public void guardarDatosRegistros(Registros registros) throws IOException {
        ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
        file.writeObject(registros);
        file.close();
    }
}
