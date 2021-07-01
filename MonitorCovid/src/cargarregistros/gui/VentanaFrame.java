package cargarregistros.gui;

import cargarregistros.utils.VentanaSincronizacion;
import monitor.Registros;
import monitor.Sintomas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaFrame extends JFrame {
    private int WIDTH, HEIGHT, X, Y;
    private RegistroJPanel registroJPanel;
//    final VentanaFrame frameRegistro = this;
    public VentanaFrame(Sintomas sintomasMonitorDisponibles, Registros registros, Sintomas sintomasPaciente) {
        Toolkit myScreen = Toolkit.getDefaultToolkit();
        Dimension size = myScreen.getScreenSize();
        WIDTH = 770;
        HEIGHT = 800;
        X = (size.width-WIDTH)/2;
        Y = (size.height-HEIGHT)/2;

        setTitle("Monitor Covid Registros");
        setBounds(X, Y, WIDTH, HEIGHT);
        setResizable(false);
        registroJPanel = new RegistroJPanel(sintomasMonitorDisponibles, registros, sintomasPaciente, this);
        add(registroJPanel);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we){
                setVisible(false);
            }
        });
        setVisible(true);
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
