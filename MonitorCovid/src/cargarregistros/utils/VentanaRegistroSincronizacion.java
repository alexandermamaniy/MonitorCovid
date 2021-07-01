package cargarregistros.utils;

import cargarregistros.gui.VentanaJFrame;

public class VentanaRegistroSincronizacion {
    private final VentanaJFrame ventanaJFrame;
    public VentanaRegistroSincronizacion(VentanaJFrame ventanaJFrame){
        this.ventanaJFrame = ventanaJFrame;
    }
    public void detener(){
        synchronized(ventanaJFrame){
            try{
                ventanaJFrame.wait();
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

    public void continuar(){
        try {
            synchronized(ventanaJFrame){
                ventanaJFrame.notify();
            }
            ventanaJFrame.dispose();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
