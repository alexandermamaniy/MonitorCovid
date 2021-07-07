package cargarsintomas.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Paquete {

    public List<String> getPaquete(){
        CodeSource src = Paquete.class.getProtectionDomain().getCodeSource();
        ArrayList<String> paths = new ArrayList<>();
        if (src != null) {
            URL jar = src.getLocation();
            try {
                if ( jar.getPath().endsWith("jar")) {
                    ZipInputStream zip = new ZipInputStream(jar.openStream());

                    while (true) {
                        ZipEntry e = zip.getNextEntry();
                        if (e == null) {
                            break;
                        }
//                        if ( e.getName().startsWith("sintomas") && e.getName().endsWith(".class") ){
//                            paths.add(e.getName());
//                        }
                    }
                } else {
                    fileViewer(new File(jar.toURI()), paths);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e){
                e.printStackTrace();
            }
        }
        System.out.println(paths);
        ArrayList<String> lista = new ArrayList<>();
        for(String ss: paths){
            if(ss.contains("/")) {
                String[] r = ss.split("/");
                lista.add(r[r.length-1].substring(0, r[r.length-1].indexOf('.')));
            } else {
                String[] r = ss.split("\\\\");
                lista.add(r[r.length-1].substring(0, r[r.length-1].indexOf('.')));
            }
        }

        List<String> classNames = new ArrayList<String>();
        try {
            ZipInputStream zip = new ZipInputStream(new FileInputStream("home.jar"));
            for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    // This ZipEntry represents a class. Now, what class does it represent?
                    String className = entry.getName().replace('/', '.'); // including ".class"
                    classNames.add(className.substring(0, className.length() - ".class".length()));
                    System.out.println(className);
                }
            }
        } catch ( IOException e) {
            e.printStackTrace();
        }


        return lista;
    }

    private void fileViewer(File file, ArrayList<String> paths) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                fileViewer(f, paths);
            }
        } else {
            if ( file.getPath().contains("\\sintomas\\") && file.getPath().endsWith(".class") ){
                paths.add(file.getPath());
            }
        }
    }
}
