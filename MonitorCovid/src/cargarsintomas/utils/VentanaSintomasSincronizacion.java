package cargarsintomas.utils;


import cargarsintomas.gui.VentanaSintomasFrame;

public class VentanaSintomasSincronizacion {
    private final VentanaSintomasFrame ventanaSintomasFrame;
    public VentanaSintomasSincronizacion(VentanaSintomasFrame ventanaSintomasFrame){
        this.ventanaSintomasFrame = ventanaSintomasFrame;
    }

    public void detener(){
        synchronized(ventanaSintomasFrame){
            try{
                ventanaSintomasFrame.wait();
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

    public void continuar(){

        synchronized(ventanaSintomasFrame){
            ventanaSintomasFrame.notify();
        }
        ventanaSintomasFrame.dispose();
    }
}
