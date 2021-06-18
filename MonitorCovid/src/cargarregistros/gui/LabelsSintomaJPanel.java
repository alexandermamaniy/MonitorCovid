package cargarregistros.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LabelsSintomaJPanel extends JLabel {

    public LabelsSintomaJPanel(String label){
        super(label);
        this.setBorder(new LineBorder(Color.BLACK, 3));
    }
}
