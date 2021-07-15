package monitor;

import java.io.*;

public class DatosFase {

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

        String nombreArchivo = "AlexanderFase.dat";
        if ( !esDesarrollo ){
            path = dir+separador+  nombreArchivo;
        } else {
            path = dir+separador+"src"+separador+  nombreArchivo;
        }
        return path;
    }

    public Fase leerDatosFase() {
        Fase fase = null;
        try {
            if (existeDatosFase()){
                ObjectInputStream file = new ObjectInputStream(new FileInputStream(getPath()));
                fase = (Fase) file.readObject();
                file.close();
            } else {
                fase = new Fase("PrimeraFase");
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return fase;
    }

    public boolean existeDatosFase(){
        File f = new File(getPath());
        return f.exists();
    }

    public void guardarDatosFase(Fase fase){
        try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(getPath()));
            file.writeObject(fase);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
