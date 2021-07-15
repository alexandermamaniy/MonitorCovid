package diagnosticos;

import monitor.Sintomas;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FaseJPanel extends JPanel implements ActionListener {
    private final JLabel labelFecha;
    private final JPanel jPanel1;
    private final JButton salir;
    private VentanaFaseJFrame frameFase;


    public FaseJPanel(int diagnostico, VentanaFaseJFrame frameFase){
        this.frameFase = frameFase;

        jPanel1 = new JPanel();
        Border bordejpanel = new TitledBorder(new EtchedBorder(), "Recomendacion de diagnostico");

        jPanel1.setBorder(bordejpanel);
        add(jPanel1);
        labelFecha = new JLabel("Usted se encuentra estable, sin sintomas de Covid");
        if (diagnostico>=11 && diagnostico<=13) {
            labelFecha.setText("Usted se encuentra en el dia "+(diagnostico-10)+" de la primera fase, por favor acuda a hacerce una prueba");
        } else if (diagnostico>=21){
            labelFecha.setText("Usted se encuentra en el dia "+(diagnostico-20)+" de la segunda fase, por favor acuda a un hospital a internarse");
        }


        jPanel1.add(labelFecha);

        salir = new JButton("Terminar");
        salir.addActionListener(this);
        add(salir);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);

        jPanel1.setBounds(40, 70, 670,280);
        labelFecha.setBounds(40, 55, 650,30);
        salir.setBounds(610, 700, 100,30);


    }

    public void actionPerformed(ActionEvent e) {
        Object botonPulsado = e.getSource();

       if ( botonPulsado == salir){
            frameFase.setVisible(false);
        }
    }
}
