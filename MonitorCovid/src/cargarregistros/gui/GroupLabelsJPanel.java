package cargarregistros.gui;

import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GroupLabelsJPanel extends JPanel {
    List<LabelsSintomaJPanel> grupoLabelsSintomas;

    public GroupLabelsJPanel(){
        super();
        grupoLabelsSintomas = new ArrayList<LabelsSintomaJPanel>();
    }

    public void renderizarSintomasSeleccionados(Sintomas sintomasSeleccionados){
        removeAllLabel();
        for (Sintoma s: sintomasSeleccionados){
            LabelsSintomaJPanel a = new LabelsSintomaJPanel(s.toString());
            grupoLabelsSintomas.add(a);
            add(grupoLabelsSintomas.get(grupoLabelsSintomas.size()-1));
            validate();
        }
    }

    public void removeAllLabel(){
        for(LabelsSintomaJPanel label: grupoLabelsSintomas){
            remove(label);
        }
        grupoLabelsSintomas.clear();
    }


}
