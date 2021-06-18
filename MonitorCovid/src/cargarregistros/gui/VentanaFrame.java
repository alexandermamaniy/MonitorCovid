package cargarregistros.gui;

import monitor.Registros;
import monitor.Sintomas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaFrame extends JFrame {
    private int WIDTH, HEIGHT, X, Y;
    private RegistroJPanel registroJPanel;

    public VentanaFrame(Sintomas sintomasMonitorDisponibles, Registros registros, Sintomas sintomasPaciente) {
        Toolkit myScreen = Toolkit.getDefaultToolkit();
        Dimension size = myScreen.getScreenSize();
        WIDTH = 700;
        HEIGHT = 700;
        X = (size.width-WIDTH)/2;
        Y = (size.height-HEIGHT)/2;

        setTitle("Monitor Covid Registros");
        setBounds(X, Y, WIDTH, HEIGHT);
        setResizable(false);

        final VentanaFrame frameRegistro = this;

        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent we){
                try {
                    synchronized(frameRegistro){
                        frameRegistro.notify();
                    }
                    frameRegistro.setVisible(false);
                    frameRegistro.dispose();
                } catch (Exception e){
                    System.out.println(e.getMessage());
                    System.out.println("Error al guardar");
                }
            }
        });
        registroJPanel = new RegistroJPanel(sintomasMonitorDisponibles, registros, sintomasPaciente, this);

        add(registroJPanel);

        setVisible(true);
        synchronized(this){
            try{
                this.wait();
            }
            catch(InterruptedException ex){
            }
        }
    }
}
