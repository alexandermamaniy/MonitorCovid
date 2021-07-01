package cargarregistros.utils;

import cargarregistros.gui.VentanaFrame;

public class VentanaSincronizacion {
    public void detener(VentanaFrame ventanaFrame){
        synchronized(ventanaFrame){
            try{
                ventanaFrame.wait();
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

    public void continuar(VentanaFrame ventanaFrame){
        try {
            synchronized(ventanaFrame){
                ventanaFrame.notify();
            }
            ventanaFrame.dispose();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
