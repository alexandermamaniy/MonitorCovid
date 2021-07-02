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
import java.io.IOException;

public class SintomaJPanel extends JPanel implements ActionListener {
    private final JComboBox<String> comboCategoriaSintoma;
    private final JTextField textFieldSintoma;
    private final JButton buttonAgregarSintoma;
    private final Sintomas sintomas;
    private final TablaSintomasJPanel tablaSintomasJPanel;
    private final JPanel jPanel1;
    private final VentanaSintomasFrame frameSintomas;
    private final JButton terminar;
    private final JLabel labelNombreSintoma;
    private final JLabel labelCategoriaSintoma;


    public SintomaJPanel(Sintomas sintomas, VentanaSintomasFrame frameSintomas ){
        this.sintomas = sintomas;
        this.frameSintomas = frameSintomas;
        jPanel1 = new JPanel();
        Border bordejpanel = new TitledBorder(new EtchedBorder(), "Registro de Sintomas");

        jPanel1.setBorder(bordejpanel);
        add(jPanel1);

        labelNombreSintoma = new JLabel("Nombre de Sintoma");
        jPanel1.add(labelNombreSintoma);
        labelCategoriaSintoma = new JLabel("Categoria sintoma");
        jPanel1.add(labelCategoriaSintoma);

        comboCategoriaSintoma = new JComboBox<>();
        jPanel1.add(comboCategoriaSintoma);
        Paquete paquete = new Paquete();
        for(String sintoma: paquete.obtenerClasesPaqueteSintomas() ){
            comboCategoriaSintoma.addItem(sintoma);
        }
        textFieldSintoma = new JTextField();
        jPanel1.add(textFieldSintoma);
        buttonAgregarSintoma = new JButton("Registrar");
        buttonAgregarSintoma.addActionListener(this);
        jPanel1.add(buttonAgregarSintoma);
        tablaSintomasJPanel = new TablaSintomasJPanel(sintomas);
        jPanel1.add(tablaSintomasJPanel);

        terminar = new JButton("Terminar");
        terminar.addActionListener(this);
        add(terminar);

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        jPanel1.setBounds(40, 20, 670,650);
        labelNombreSintoma.setBounds(35, 25, 170,30);
        textFieldSintoma.setBounds(35, 55,170,30);
        labelCategoriaSintoma.setBounds(230, 25, 170,30);
        comboCategoriaSintoma.setBounds(230, 55, 110,30);
        comboCategoriaSintoma.setBackground(Color.WHITE);
        buttonAgregarSintoma.setBounds(360, 55, 100,30);
        tablaSintomasJPanel.setBounds(35, 130, 600,350);
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
                    Sintoma sintoma = crearSintoma.crear(name, categoria);
                    sintomas.add(sintoma);
                    tablaSintomasJPanel.addRow(new String[]{sintoma.toString(), categoria });
                    textFieldSintoma.setText("");
                    (new DatosSintomas()).guardarDatosSintomas(sintomas);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (botonPulsado == terminar){
            frameSintomas.setVisible(false);
        }
    }
}
