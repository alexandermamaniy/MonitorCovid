package diagnosticos;


public class VentanaFaseSincronizacion {
    private final VentanaFaseJFrame ventanaJFrame;
    public VentanaFaseSincronizacion(VentanaFaseJFrame ventanaJFrame){
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
        synchronized(ventanaJFrame){
            ventanaJFrame.notify();
        }
        ventanaJFrame.dispose();
    }
}
