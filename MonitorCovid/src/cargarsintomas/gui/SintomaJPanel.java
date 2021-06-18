package cargarsintomas.gui;

import cargarsintomas.utils.CrearSintoma;
import cargarsintomas.utils.Paquete;
import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SintomaJPanel extends JPanel implements  ActionListener {
    private final JLabel title;
    private final JComboBox<String> comboCategoriaSintoma;
    private final JTextField textFieldSintoma;
    private final JButton buttonAgregarSintoma;
    private final Sintomas sintomas;
    private final TablaJPanel tablaJPanel;

    public SintomaJPanel(Sintomas sintomas){
        this.sintomas = sintomas;
        title = new JLabel("Sintomas");
        add(title);

        comboCategoriaSintoma = new JComboBox<>();
        add(comboCategoriaSintoma);
        Paquete paquete = new Paquete();
        for(String sintoma: paquete.obtenerClasesPaqueteSintomas() ){
            comboCategoriaSintoma.addItem(sintoma);
        }

        textFieldSintoma = new JTextField();
        add(textFieldSintoma);
        buttonAgregarSintoma = new JButton("Registrar");
        buttonAgregarSintoma.addActionListener(this);
        add(buttonAgregarSintoma);
        tablaJPanel = new TablaJPanel(sintomas);
        add(tablaJPanel);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        title.setBounds(300, 50, 150, 40);
        comboCategoriaSintoma.setBounds(70, 100, 145,30);
        comboCategoriaSintoma.setBackground(Color.WHITE);
        textFieldSintoma.setBounds(70, 150,170,30);
        buttonAgregarSintoma.setBounds(250, 150, 100,30);
        tablaJPanel.setBounds(70, 250, 453,300);

    }

    public void actionPerformed(ActionEvent e) {
        Object botonPulsado = e.getSource();
        if (botonPulsado == buttonAgregarSintoma) {
            try {
                String categoria = (String) comboCategoriaSintoma.getSelectedItem();
                String name = textFieldSintoma.getText();
                CrearSintoma crearSintoma = new CrearSintoma();
                Sintoma sintoma = crearSintoma.crear(name, categoria, sintomas);
                sintomas.add(sintoma);
                tablaJPanel.addRow(new String[]{sintoma.toString(), categoria });
                textFieldSintoma.setText("");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                // mostrar mensaje en interfaz
            }
        }
    }
}
