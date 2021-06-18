package cargarsintomas.datos;

import monitor.Sintomas;

import java.io.*;

public class DatosSintomas {

    private final String nombreArchivo = "sintomas.dat";

    public Sintomas leerDatosSintomas() throws IOException, ClassNotFoundException {
        ObjectInputStream file = new ObjectInputStream(new FileInputStream(nombreArchivo));
        Sintomas sintomas = (Sintomas) file.readObject();
        file.close();
        return sintomas;
    }

    public boolean existeDatosSintomas(){
        File f = new File(nombreArchivo);
        return f.exists();
    }

    public void guardarDatosSintomas(Sintomas sintomas) throws IOException {
        ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
        file.writeObject(sintomas);
        file.close();
    }
}
