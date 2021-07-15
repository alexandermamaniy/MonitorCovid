package cargarregistros.gui;

import cargarregistros.utils.FormatoFecha;
import cargarregistros.utils.FormatoSintomas;
import monitor.Registro;
import monitor.Registros;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TablaRegistrosJPanel extends JPanel {
    private final JScrollPane tableScollPanel;
    private final DefaultTableModel dataTable;
    private final Registros registros;

    public TablaRegistrosJPanel(Registros registros){
        this.registros = registros;
        dataTable = new DefaultTableModel();
        JTable table = new JTable(dataTable);
        tableScollPanel = new JScrollPane(table);
        dataTable.addColumn("Fecha");
        dataTable.addColumn("Sintomas");


        add(tableScollPanel);
    }

    private void clear(){
        int rowCount = dataTable.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dataTable.removeRow(i);
        }

    }

    public void actualizarTabla(){
        clear();

        FormatoFecha formatoFecha = new FormatoFecha();
        FormatoSintomas formatoSintomas = new FormatoSintomas();

        for(Registro r: registros) {
            String fecha = formatoFecha.dateAndHourToString(r.getFecha());
            String sintomasPaciente = formatoSintomas.formatoSintomas(r.getSintomas());
            dataTable.insertRow(0, new Object[]{fecha, sintomasPaciente });
        }
    }

    public void paintComponent(Graphics g){
        tableScollPanel.setBounds(0, 0, 600,230);
        FormatoFecha formatoFecha = new FormatoFecha();
        FormatoSintomas formatoSintomas = new FormatoSintomas();
        dataTable.insertRow(0, new Object[]{"", ""});
        clear();
        for(Registro r: registros) {
            String fecha = formatoFecha.dateAndHourToString(r.getFecha());
            String sintomasPaciente = formatoSintomas.formatoSintomas(r.getSintomas());
            dataTable.insertRow(0, new Object[]{fecha, sintomasPaciente });
        }
    }
}
