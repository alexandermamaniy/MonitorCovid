package cargarsintomas.gui;

import cargarsintomas.utils.VentanaSintomasSincronizacion;
import monitor.Sintomas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaSintomasFrame extends JFrame {

    public VentanaSintomasFrame(Sintomas sintomas) {
        Toolkit myScreen = Toolkit.getDefaultToolkit();
        Dimension size = myScreen.getScreenSize();
        int WIDTH = 770;
        int HEIGHT = 800;
        int x = (size.width - WIDTH) / 2;
        int y = (size.height - HEIGHT) / 2;
        setTitle("Monitor Covid Sintomas");
        setBounds(x, y, WIDTH, HEIGHT);
        setResizable(false);
        SintomaJPanel sintomaJPanel = new SintomaJPanel(sintomas, this);
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
        VentanaSintomasSincronizacion ventanaSintomasSincronizacion = new VentanaSintomasSincronizacion(this);
        if (b) {
            ventanaSintomasSincronizacion.detener();
        } else {
            ventanaSintomasSincronizacion.continuar();
        }
    }
}
