package com.lab3.Laboratory3;

import com.lab3.Laboratory3.ImportOfReactors.BurnUpImportChain;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class JView extends JFrame {

    JButton jButton = new JButton("Choose Excel:");
    JButton jButton2 = new JButton("Choose Reactors File:");
    JButton jButton3 = new JButton("Result for regions");
    JButton jButton4 = new JButton("Result for companies");
    JButton jButton5 = new JButton("Result for countries");
    JFileChooser chooser = new JFileChooser();
    JPanel jPanel = new JPanel();
    JPanel jPanel2 = new JPanel();
    JTable table = new JTable();
    JTextField jTextField = new JTextField();
    JScrollPane jScrollPane = new JScrollPane();

    public JView(){
        super("Laboratory 3");
        this.setBounds(100,100, 480,550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        jButton.addActionListener(new ButtonActionListener());
        jButton2.addActionListener(new ButtonActionListenerForJsonImport());
        jButton3.addActionListener(new ButtonActionListenerForRegionsResults());
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.addActionListener(new ButtonActionListenerForCompaniesResults());
        jButton4.setEnabled(false);
        jButton5.addActionListener(new ButtonActionListenerForCountriesResults());
        jButton5.setEnabled(false);
        jPanel.add(jButton);
        jPanel.add(jButton2);
        jPanel.add(jTextField);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
        jPanel2.add(jButton5);
        jTextField.setText("Choose Excel import");
        jScrollPane.setViewportView(table);
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        container.add(jScrollPane);
        container.add(jPanel);
        container.add(jPanel2);
    }

    class ButtonActionListener implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("", "xlsx");
            chooser.setFileFilter(filter);
            chooser.setCurrentDirectory(new File("./src/main/resources"));
            chooser.showDialog(null, "Choose file:");
            File file = chooser.getSelectedFile();
            try {
                ExcelReader.readWorkbookToDatabase(file);
                System.out.println("excel imported");
                jTextField.setText("Excel Imported");
                jButton2.setEnabled(true);
            } catch (IOException | InvalidDataAccessResourceUsageException | DataIntegrityViolationException |NullPointerException e) {
                System.out.println("Error while import:" + e.getMessage());
                jTextField.setText("Import error");
            }
        }
    }

    class ButtonActionListenerForJsonImport implements ActionListener{
        public void actionPerformed (ActionEvent event) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("", "xml", "json", "yaml");
            chooser.setFileFilter(filter);
            chooser.setCurrentDirectory(new File("./src/main/resources"));
            chooser.showDialog(null, "Choose file:");
            File file = chooser.getSelectedFile();
            try {
                BurnUpImportChain.fillBurnUpAndVolumeOfConsume(file);
                System.out.println("json imported");
                jTextField.setText("Reactors Imported");
                jButton3.setEnabled(true);
                jButton4.setEnabled(true);
                jButton5.setEnabled(true);
            } catch (JAXBException | IOException e ) {
                System.out.println("Error while import:" + e.getMessage());
                jTextField.setText("Import error");
            }
        }
    }

    class ButtonActionListenerForRegionsResults implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Регион");
            tableModel.addColumn("Объем ежегодного потребления, т.");
            for (ResultRow resultRow : ResultCalculator.calculateResultForRegions()) {
                tableModel.addRow(new Object[]{resultRow.getCategory(), resultRow.getVolumeOfConsume()});
            }
            table.setModel(tableModel);
            System.out.println("result calculated");
            jTextField.setText("Result for regions");
        }
    }

    class ButtonActionListenerForCompaniesResults implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Компания");
            tableModel.addColumn("Объем ежегодного потребления, т.");
            for (ResultRow resultRow : ResultCalculator.calculateResultForCompanies()) {
                tableModel.addRow(new Object[]{resultRow.getCategory(), resultRow.getVolumeOfConsume()});
            }
            table.setModel(tableModel);
            System.out.println("result calculated");
            jTextField.setText("Result for companies");
        }
    }

    class ButtonActionListenerForCountriesResults implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Страна");
            tableModel.addColumn("Объем ежегодного потребления, т.");
            for (ResultRow resultRow : ResultCalculator.calculateResultForCountries()) {
                tableModel.addRow(new Object[]{resultRow.getCategory(), resultRow.getVolumeOfConsume()});
            }
            table.setModel(tableModel);
            System.out.println("result calculated");
            jTextField.setText("Result for countries");
        }
    }
}
