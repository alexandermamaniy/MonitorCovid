package cargarregistros.gui;

import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class TablaSintomasSeleccionadosJPanel extends JPanel {
    private final JScrollPane tableScollPanel;
    private final DefaultTableModel dataTable;

    public TablaSintomasSeleccionadosJPanel(){
        dataTable = new DefaultTableModel();
        JTable table = new JTable(dataTable);
        tableScollPanel = new JScrollPane(table);
        dataTable.addColumn("Sintomas");
        TableRowSorter sorter = new TableRowSorter(dataTable);
        table.setRowSorter(sorter);
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
