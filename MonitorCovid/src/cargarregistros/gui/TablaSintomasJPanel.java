package cargarregistros.gui;

import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TablaSintomasJPanel extends JPanel {
    JTable table;
    JScrollPane tableScollPanel;
    DefaultTableModel dataTable;

    public TablaSintomasJPanel(){
        dataTable = new DefaultTableModel();
        table = new JTable(dataTable);
        tableScollPanel = new JScrollPane(table);
        dataTable.addColumn("Sintomas");
        add(tableScollPanel);

    }

    public void addRow(String[] row){
        dataTable.addRow(row);
    }

    public void clear(){
        int rowCount = dataTable.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dataTable.removeRow(i);
        }
    }

    public void addRowSintomas(Sintomas sintomasSeleccionados){
        clear();
        for (Sintoma s: sintomasSeleccionados){
            addRow(new String[]{ s.toString() });
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        tableScollPanel.setBounds(0, 0, 600,200);
    }
}
