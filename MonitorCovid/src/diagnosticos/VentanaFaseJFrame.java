package diagnosticos;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaFaseJFrame extends JFrame {
    public VentanaFaseJFrame(int diagnostico) {
        Toolkit myScreen = Toolkit.getDefaultToolkit();
        Dimension size = myScreen.getScreenSize();
        int WIDTH = 770;
        int HEIGHT = 800;
        int x = (size.width - WIDTH) / 2;
        int y = (size.height - HEIGHT) / 2;
        setTitle("Monitor Covid Diagnostico");
        setBounds(x, y, WIDTH, HEIGHT);
        setResizable(false);
        FaseJPanel registroJPanel = new FaseJPanel(diagnostico,this);
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
        VentanaFaseSincronizacion ventanaRegistroSincronizacion = new VentanaFaseSincronizacion(this);
        if (b) {
            ventanaRegistroSincronizacion.detener();
        } else {
            ventanaRegistroSincronizacion.continuar();
        }
    }
}
