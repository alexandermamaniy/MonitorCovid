package cargarsintomas.gui;

import cargarsintomas.datos.DatosSintomas;
import cargarsintomas.utils.CrearSintoma;
import cargarsintomas.utils.Paquete;
import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class SintomaJPanel extends JPanel implements ItemListener, ActionListener {
//    private final JLabel title;
    private final JComboBox<String> comboCategoriaSintoma;
    private final JTextField textFieldSintoma;
    private final JButton buttonAgregarSintoma;
    private final Sintomas sintomas;
    private final TablaJPanel tablaJPanel;
    JPanel jPanel1;
    private VentanaFrame frameSintomas;
    private final JButton terminar;

    public SintomaJPanel(Sintomas sintomas,VentanaFrame frameSintomas ){
        this.sintomas = sintomas;
        this.frameSintomas = frameSintomas;
//        title = new JLabel("Sintomas");
//        add(title);
        jPanel1 = new JPanel();
        Border bordejpanel = new TitledBorder(new EtchedBorder(), "Registro de Sintomas");

        jPanel1.setBorder(bordejpanel);
        add(jPanel1);

        comboCategoriaSintoma = new JComboBox<>();
        jPanel1.add(comboCategoriaSintoma);
        comboCategoriaSintoma.addItemListener(this);
        Paquete paquete = new Paquete();
        for(String sintoma: paquete.obtenerClasesPaqueteSintomas() ){
            comboCategoriaSintoma.addItem(sintoma);
        }

        textFieldSintoma = new JTextField();
        jPanel1.add(textFieldSintoma);
        buttonAgregarSintoma = new JButton("Registrar");
        buttonAgregarSintoma.addActionListener(this);
        jPanel1.add(buttonAgregarSintoma);
        tablaJPanel = new TablaJPanel(sintomas);
        jPanel1.add(tablaJPanel);

        terminar = new JButton("Terminar");
        terminar.addActionListener(this);
        add(terminar);

    }

    public void itemStateChanged(ItemEvent e){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
//        title.setBounds(300, 50, 150, 40);

        jPanel1.setBounds(40, 20, 670,650);
        textFieldSintoma.setBounds(35, 35,170,30);
        comboCategoriaSintoma.setBounds(230, 35, 110,30);
        comboCategoriaSintoma.setBackground(Color.WHITE);

        buttonAgregarSintoma.setBounds(360, 35, 100,30);
        tablaJPanel.setBounds(35, 100, 600,350);
        terminar.setBounds(610, 700, 100,30);
    }

    public void actionPerformed(ActionEvent e) {
        Object botonPulsado = e.getSource();
        if (botonPulsado == buttonAgregarSintoma) {
            try {
                String categoria = (String) comboCategoriaSintoma.getSelectedItem();
                String name = textFieldSintoma.getText();
                if(!name.equals("")){
                    CrearSintoma crearSintoma = new CrearSintoma();
                    Sintoma sintoma = crearSintoma.crear(name, categoria, sintomas);
                    sintomas.add(sintoma);
                    tablaJPanel.addRow(new String[]{sintoma.toString(), categoria });
                    textFieldSintoma.setText("");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (botonPulsado == terminar){
            try {
                // llevar al paquete cargarSintomas
                (new DatosSintomas()).guardarDatosSintomas(sintomas);
                synchronized(frameSintomas){
                    frameSintomas.notify();
                }
                frameSintomas.setVisible(false);
                frameSintomas.dispose();
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
