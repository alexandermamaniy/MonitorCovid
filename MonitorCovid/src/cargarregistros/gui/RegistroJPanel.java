package cargarregistros.gui;


import cargarregistros.datos.DatosRegistros;
import cargarregistros.utils.CrearSintomaRegistro;
import monitor.Registro;
import monitor.Registros;
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
import java.util.Date;

public class RegistroJPanel extends JPanel implements ItemListener, ActionListener {
//    private final JLabel title;
    private final JComboBox<String> comboCategoriaSintoma;
    private final JButton buttonAgregarSintoma;
    private final Sintomas sintomasMonitorDisponibles;
    private  Sintomas sintomasPaciente;
    private final Registros registros;
    private final TablaJPanel tablaJPanel;
    private final TablaSintomasJPanel tablaSintomasJPanel;
    JPanel jPanel1;
    JPanel jPanel2;
    private VentanaFrame frameRegistro;
    private final JButton salir;

    public RegistroJPanel(Sintomas sintomasMonitorDisponibles, Registros registros, Sintomas sintomasPaciente, VentanaFrame frameRegistro){

        this.sintomasMonitorDisponibles = sintomasMonitorDisponibles;
        this.sintomasPaciente = sintomasPaciente;
        this.registros = registros;
        this.frameRegistro = frameRegistro;

        jPanel1 = new JPanel();
        Border bordejpanel = new TitledBorder(new EtchedBorder(), "Toma de Registros");

        jPanel1.setBorder(bordejpanel);
        add(jPanel1);


        jPanel2 = new JPanel();
        Border bordejpanel2 = new TitledBorder(new EtchedBorder(), "Tabla de Registros");

        jPanel2.setBorder(bordejpanel2);
        add(jPanel2);

//        title = new JLabel("Sintomas");
//        add(title);
        comboCategoriaSintoma = new JComboBox<>();
        jPanel1.add(comboCategoriaSintoma);

        comboCategoriaSintoma.addItemListener(this);

        for(Sintoma s: sintomasMonitorDisponibles ){
            comboCategoriaSintoma.addItem(s.toString());
        }
        buttonAgregarSintoma = new JButton("Registrar");
        buttonAgregarSintoma.addActionListener(this);
        jPanel1.add(buttonAgregarSintoma);
        tablaJPanel = new TablaJPanel(registros);
        jPanel2.add(tablaJPanel);

        tablaSintomasJPanel = new TablaSintomasJPanel();
        jPanel1.add(tablaSintomasJPanel);
//        group = new JPanel();

        salir = new JButton("Terminar");
        salir.addActionListener(this);
        add(salir);

    }

    public void itemStateChanged(ItemEvent e){

        if (e.getSource()== comboCategoriaSintoma && e.getStateChange() == ItemEvent.DESELECTED){

            String a = (String)(comboCategoriaSintoma.getSelectedItem());
            CrearSintomaRegistro crearSintomaRegistro = new CrearSintomaRegistro(sintomasMonitorDisponibles);
            Sintoma s = crearSintomaRegistro.crear(a);
            sintomasPaciente.add(s);
            tablaSintomasJPanel.addRowSintomas(sintomasPaciente);
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
//        title.setBounds(70, 50, 150, 40);
        jPanel1.setBounds(40, 20, 670,330);
        comboCategoriaSintoma.setBounds(35, 35, 200,30);
        comboCategoriaSintoma.setBackground(Color.WHITE);
        buttonAgregarSintoma.setBounds(300, 35, 100,30);
        tablaSintomasJPanel.setBounds(35, 100, 600,200);


        jPanel2.setBounds(40, 370, 670,300);
        tablaJPanel.setBounds(35, 35, 600,230);
        salir.setBounds(610, 700, 100,30);
    }

    public void actionPerformed(ActionEvent e) {
        Object botonPulsado = e.getSource();

        if (botonPulsado == buttonAgregarSintoma) {
            try {
                DatosRegistros datosRegistros = new DatosRegistros();
                registros.push(new Registro(new Date(), sintomasPaciente));
                datosRegistros.guardarDatosRegistros(registros);
                tablaSintomasJPanel.clear();
                tablaJPanel.actualizarTabla();
                sintomasPaciente = new Sintomas();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                // mostrar mensaje en interfaz
            }
        } else  if ( botonPulsado == salir){
            try {
                synchronized(frameRegistro){
                    frameRegistro.notify();
                }
                frameRegistro.setVisible(false);
                frameRegistro.dispose();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
//        if (botonPulsado == buttonAgregarSintoma) {
//            try {
//                DatosRegistros datosRegistros = new DatosRegistros();
//                registros.push(new Registro(new Date(), sintomasPaciente));
//                datosRegistros.guardarDatosRegistros(registros);
//                synchronized(frameRegistro){
//                    frameRegistro.notify();
//                }
//                frameRegistro.setVisible(false);
//                frameRegistro.dispose();
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//                // mostrar mensaje en interfaz
//            }
//        }
    }
}
