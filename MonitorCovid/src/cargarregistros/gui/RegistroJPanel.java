package cargarregistros.gui;


import cargarregistros.datos.DatosRegistros;
import cargarregistros.utils.CrearSintomaRegistro;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

public class RegistroJPanel extends JPanel implements ItemListener, ActionListener {
    private final JLabel title;
    private final JComboBox<String> comboCategoriaSintoma;
    private final JButton buttonAgregarSintoma;
    private final Sintomas sintomasMonitorDisponibles;
    private final Sintomas sintomasPaciente;
    private final Registros registros;
    private final TablaJPanel tablaJPanel;
    GroupLabelsJPanel groupLabelsJPanel;
    JPanel group;
    private VentanaFrame frameRegistro;

    public RegistroJPanel(Sintomas sintomasMonitorDisponibles, Registros registros, Sintomas sintomasPaciente, VentanaFrame frameRegistro){
        this.sintomasMonitorDisponibles = sintomasMonitorDisponibles;
        this.sintomasPaciente = sintomasPaciente;
        this.registros = registros;
        this.frameRegistro = frameRegistro;
        title = new JLabel("Sintomas");
        add(title);
        comboCategoriaSintoma = new JComboBox<>();
        add(comboCategoriaSintoma);
        comboCategoriaSintoma.addItemListener(this);

        for(Sintoma s: sintomasMonitorDisponibles ){
            comboCategoriaSintoma.addItem(s.toString());
        }
        buttonAgregarSintoma = new JButton("Registrar");
        buttonAgregarSintoma.addActionListener(this);
        add(buttonAgregarSintoma);
        groupLabelsJPanel = new GroupLabelsJPanel();
        add(groupLabelsJPanel);
        tablaJPanel = new TablaJPanel(registros);
        add(tablaJPanel);
        group = new JPanel();
    }

    public void itemStateChanged(ItemEvent e){
        if (e.getSource()== comboCategoriaSintoma && e.getStateChange() == ItemEvent.DESELECTED){
            String a = (String)(comboCategoriaSintoma.getSelectedItem());
            CrearSintomaRegistro crearSintomaRegistro = new CrearSintomaRegistro(sintomasMonitorDisponibles);
            sintomasPaciente.add(crearSintomaRegistro.crear(a));
            groupLabelsJPanel.renderizarSintomasSeleccionados(sintomasPaciente);
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        title.setBounds(70, 50, 150, 40);
        comboCategoriaSintoma.setBounds(70, 100, 145,30);
        comboCategoriaSintoma.setBackground(Color.WHITE);
        buttonAgregarSintoma.setBounds(300, 100, 100,30);
        tablaJPanel.setBounds(70, 250, 453,300);
        groupLabelsJPanel.setBounds(70, 150, 600, 30);

    }

    public void actionPerformed(ActionEvent e) {
        Object botonPulsado = e.getSource();
        if (botonPulsado == buttonAgregarSintoma) {
            try {
                DatosRegistros datosRegistros = new DatosRegistros();
                registros.push(new Registro(new Date(), sintomasPaciente));
                datosRegistros.guardarDatosRegistros(registros);
                synchronized(frameRegistro){
                    frameRegistro.notify();
                }
                frameRegistro.setVisible(false);
                frameRegistro.dispose();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                // mostrar mensaje en interfaz
            }
        }
    }
}
