package cargarregistros.gui;

import cargarregistros.utils.VentanaSincronizacion;
import monitor.Registros;
import monitor.Sintomas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaFrame extends JFrame {
    public VentanaFrame(Sintomas sintomasMonitorDisponibles, Registros registros, Sintomas sintomasPaciente) {
        Toolkit myScreen = Toolkit.getDefaultToolkit();
        Dimension size = myScreen.getScreenSize();
        int WIDTH = 770;
        int HEIGHT = 800;
        int x = (size.width - WIDTH) / 2;
        int y = (size.height - HEIGHT) / 2;
        setTitle("Monitor Covid Registros");
        setBounds(x, y, WIDTH, HEIGHT);
        setResizable(false);
        RegistroJPanel registroJPanel = new RegistroJPanel(sintomasMonitorDisponibles, registros, sintomasPaciente, this);
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
        VentanaSincronizacion ventanaSincronizacion = new VentanaSincronizacion(this);
        if (b) {
            ventanaSincronizacion.detener();
        } else {
            ventanaSincronizacion.continuar();
        }
    }
}
