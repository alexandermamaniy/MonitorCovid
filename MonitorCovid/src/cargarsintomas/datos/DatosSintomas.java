package cargarsintomas.datos;

import monitor.Sintomas;

import java.io.*;

public class DatosSintomas {

    private String getPath(){
        File miDir = new File (".");
        String dir="", path, separador = System.getProperty("file.separator");
        try {
            dir= miDir.getCanonicalPath();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        boolean esDesarrollo = false;
        File file2 = new File(dir);
        String[] a = file2.list();

        assert a != null;
        for (String s : a) {
            if (s.equals("src")) {
                esDesarrollo = true;
                break;
            }
        }

        String nombreArchivo = "sintomas.dat";
        if ( !esDesarrollo ){
            path = dir+separador+  nombreArchivo;
        } else {
            path = dir+separador+"src"+separador+  nombreArchivo;
        }
        return path;
    }

    public Sintomas leerDatosSintomas() {
        Sintomas sintomas = null;
        try {
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(getPath()));
            sintomas = (Sintomas) file.readObject();
            file.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return sintomas;
    }

    public boolean existeDatosSintomas(){
        File f = new File(getPath());
        return f.exists();
    }

    public void guardarDatosSintomas(Sintomas sintomas){
        try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(getPath()));
            file.writeObject(sintomas);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
