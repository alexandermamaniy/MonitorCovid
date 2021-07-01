package cargarsintomas.gui;

import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TablaSintomasJPanel extends JPanel {
    private final JScrollPane tableScollPanel;
    private final DefaultTableModel dataTable;

    public TablaSintomasJPanel(Sintomas sintomas){
        dataTable = new DefaultTableModel();
        JTable table = new JTable(dataTable);
        tableScollPanel = new JScrollPane(table);
        dataTable.addColumn("Nombre sintoma");
        dataTable.addColumn("Categoria");
        for(Sintoma sintoma: sintomas){
            String name = sintoma.toString();
            String categoria = sintoma.getClass().getName().split("\\.")[1];
            dataTable.addRow(new Object[]{name, categoria });
        }
        add(tableScollPanel);
    }

    public void addRow(String[] row){
        dataTable.addRow(row);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        tableScollPanel.setBounds(0, 0, 600,300);
    }
}
