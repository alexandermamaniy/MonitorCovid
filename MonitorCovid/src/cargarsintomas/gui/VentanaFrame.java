package cargarsintomas.gui;

import cargarsintomas.datos.DatosSintomas;
import monitor.Sintomas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class VentanaFrame extends JFrame {
    private int WIDTH, HEIGHT, X, Y;
    private SintomaJPanel sintomaJPanel;

    public VentanaFrame(Sintomas sintomas) {
        Toolkit myScreen = Toolkit.getDefaultToolkit();
        Dimension size = myScreen.getScreenSize();
        WIDTH = 770;
        HEIGHT = 800;
        X = (size.width-WIDTH)/2;
        Y = (size.height-HEIGHT)/2;

        setTitle("Monitor Covid Sintomas");
        setBounds(X, Y, WIDTH, HEIGHT);
        setResizable(false);

//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final VentanaFrame frame = this;

        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we){
                try {
                    // llevar al paquete cargarSintomas
                    (new DatosSintomas()).guardarDatosSintomas(sintomas);
                    synchronized(frame){
                        frame.notify();
                    }
                    frame.setVisible(false);
                    frame.dispose();
                } catch (IOException e){
                    System.out.println(e.getMessage());
                    System.out.println("Error al guardar");
                }
            }
        });
        sintomaJPanel = new SintomaJPanel(sintomas, this);
        add(sintomaJPanel);
        setVisible(true);
        synchronized(frame){
            try{
                frame.wait();
            }
            catch(InterruptedException ex){
            }
        }
    }
}
