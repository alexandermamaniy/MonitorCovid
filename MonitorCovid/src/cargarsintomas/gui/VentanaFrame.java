package cargarsintomas.gui;

import cargarsintomas.datos.DatosSintomas;
import cargarsintomas.utils.VentanaSincronizacion;
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
        sintomaJPanel = new SintomaJPanel(sintomas, this);
        add(sintomaJPanel);
        setVisible(true);

        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we){
                setVisible(false);
            }
        });

    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        VentanaSincronizacion ventanaSincronizacion = new VentanaSincronizacion();
        if (b) {
            ventanaSincronizacion.detener(this);
        } else {
            ventanaSincronizacion.continuar(this);
        }
    }
}
