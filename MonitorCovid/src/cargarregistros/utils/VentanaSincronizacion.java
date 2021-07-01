package cargarregistros.utils;

import cargarregistros.gui.VentanaFrame;

public class VentanaSincronizacion {
    final VentanaFrame ventanaFrame;
    public VentanaSincronizacion(VentanaFrame ventanaFrame){
        this.ventanaFrame = ventanaFrame;
    }
    public void detener(){
        synchronized(ventanaFrame){
            try{
                ventanaFrame.wait();
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

    public void continuar(){
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
