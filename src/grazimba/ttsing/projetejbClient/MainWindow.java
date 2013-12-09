/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

import grazimba.ttsing.projetejb.Crisis;
import grazimba.ttsing.projetejb.Routeplan;
import grazimba.ttsing.projetejb.Timeoutlog;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author grazimba
 */
public final class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.POLICE)
            this.setTitle("Police Station");
        else
            this.setTitle("Fire Station");
            
        initComponents();
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ProjetEJBClient.getCont().ExitQuery();
            }
        });
    }


    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jButtonQuit = new javax.swing.JButton();
        jComboBoxCrisis = new javax.swing.JComboBox();
        jButtonAddCrise = new javax.swing.JButton();
        jScrollPaneVehicules = new javax.swing.JScrollPane();
        jTableVehicules = new javax.swing.JTable();
        jLabelVehicules = new javax.swing.JLabel();
        jLabelDescription = new javax.swing.JLabel();
        jScrollPaneDescription = new javax.swing.JScrollPane();
        jTextAreaDescription = new javax.swing.JTextArea();
        jButtonAddVoiture = new javax.swing.JButton();
        jButtonRemoveVoiture = new javax.swing.JButton();
        _currentCrisis = new Integer(-1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        jComboBoxCrisis.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                CrisisComboBoxItemChanged(evt);
            }
        });

        jLabelTitle.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTitle.setText("");

        jButtonQuit.setText("Quitter");
        jButtonQuit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuitActionPerformed(evt);
            }
        });

        jButtonAddCrise.setText("Add Crise");
        jButtonAddCrise.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddCrisePerformed(evt);
            }
        });

        jTableVehicules.setModel(new MyTableModel(new Object [][] {}));
        jTableVehicules.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPaneVehicules.setViewportView(jTableVehicules);
        jScrollPaneVehicules.setSize(jScrollPaneVehicules.getSize().width, 200);
        jScrollPaneVehicules.setMinimumSize(new Dimension(200, 200));

        jLabelVehicules.setText("Voitures :");

        jLabelDescription.setText("Description :");

        jTextAreaDescription.setEditable(false);
        jTextAreaDescription.setColumns(20);
        jTextAreaDescription.setRows(5);
        jScrollPaneDescription.setViewportView(jTextAreaDescription);
        jScrollPaneDescription.setMinimumSize(new Dimension(200, 200));

        jButtonAddVoiture.setText("Add Voiture");
        jButtonAddVoiture.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonAddVoiturePerformed(e);
            }
        });

        jButtonRemoveVoiture.setText("Remove Voiture");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitle)
                    .addComponent(jComboBoxCrisis)
                    .addComponent(jLabelDescription)
                    .addComponent(jScrollPaneDescription)
                    .addComponent(jLabelVehicules)
                    .addComponent(jScrollPaneVehicules))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(jButtonAddCrise)
                    .addComponent(jButtonAddVoiture)
                    .addComponent(jButtonRemoveVoiture)
                    .addComponent(jButtonQuit))
        );
        layout.linkSize(SwingConstants.HORIZONTAL, jButtonAddCrise, jButtonAddVoiture, jButtonRemoveVoiture, jButtonQuit);
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addComponent(jLabelTitle)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jComboBoxCrisis)
                        .addComponent(jButtonAddCrise))
                    .addComponent(jLabelDescription)
                    .addComponent(jScrollPaneDescription)
                    .addComponent(jLabelVehicules)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPaneVehicules)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonAddVoiture)
                            .addComponent(jButtonRemoveVoiture)))
                    .addComponent(jButtonQuit)
        );

        if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.POLICE)
        jLabelTitle.setText("Police Station");
        else
        jLabelTitle.setText("Fire Station");

        pack();
    }
    
    

    private void jButtonQuitActionPerformed(java.awt.event.ActionEvent evt) {
        Object[] options = { "OK", "CANCEL" };
        if(JOptionPane.showOptionDialog(null, "Voulez vous quitter?", "Quitter", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]) == JOptionPane.OK_OPTION) {
            ProjetEJBClient.getCont().ExitQuery();
        }
    }
    
    private void jButtonAddCrisePerformed(java.awt.event.ActionEvent evt) {
        AddCrisisFrame acf = new AddCrisisFrame();
    }

    private void jButtonAddVoiturePerformed(ActionEvent evt){
        AddVoitureFrame avf = new AddVoitureFrame();
    }
    
    private void CrisisComboBoxItemChanged(ActionEvent evt) {
        _currentCrisis = jComboBoxCrisis.getSelectedIndex();
        RessourcesUpdated();
    }
    
    public void launch() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(true);
            }
        });
    }
    
    public JComboBox getComboBoxCrisis(){
        return jComboBoxCrisis;
    }
    
    public void RessourcesUpdated(){
        
        System.out.println("gui update");
        
        Ressources res = ProjetEJBClient.getRessource();
        
        Integer current = _currentCrisis;
        jComboBoxCrisis.removeAllItems();
        for(int i = 0; i<res.getCrises().size(); i++) {
            if(res.getCrise(i).getStatut().equals("Active"))
                jComboBoxCrisis.addItem(res.getCrise(i).getIdcrisis());
        }
        _currentCrisis = current;
        jComboBoxCrisis.setSelectedIndex(_currentCrisis);
        
        if(_currentCrisis != -1) {
            jTextAreaDescription.setText(null);
            jTextAreaDescription.append("Position : " + res.getCrise(_currentCrisis).getLongitude() + "; " + res.getCrise(_currentCrisis).getLatitude() + "\r\n" + "\r\n");
            jTextAreaDescription.append("Heure de début : " + res.getCrise(_currentCrisis).getT() + "\r\n" + "\r\n");
            
            Integer timeOutId = -1;
            for(int i=0; i<res.getTols().size(); i++) {
                System.err.println();
                if(res.getCrise(_currentCrisis).getIdcrisis().equals(res.getTol(i).getIdcrisis())) {
                    timeOutId = i;
                }
            }   
            
            if(timeOutId != -1) {
                Date cd = new Date();
                long minutes = res.getTol(timeOutId).getD().getTime() - cd.getTime();
                minutes /= 60000;
                jTextAreaDescription.append("Timer : " + minutes + " minutes" + "\r\n" + "\r\n");
            }
            jTextAreaDescription.append("Description : " + res.getCrise(_currentCrisis).getDescription());
            
            jTableVehicules.removeAll();
            Object[][] data = new Object[res.getVehicules().size()][4];
            int itVehicule = 0;
            for(int i = 0; i<res.getRoutes().size(); i++) {
                if(res.getRoute(i).getRoutePK().getIdcrisis().equals(res.getCrise(_currentCrisis).getIdcrisis())) {
                    data[itVehicule][0] = res.getVehicule(i).getIdvehicule();
                    data[itVehicule][1] = res.getVehicule(i).getEta();
                    data[itVehicule][2] = res.getVehicule(i).getPosition();
                    data[itVehicule][3] = res.getVehicule(i).getType();
                    itVehicule ++;
                }
            }
            jTableVehicules.setModel(new MyTableModel(data));
        }
    }
    
    
    private javax.swing.JButton jButtonQuit;
    private javax.swing.JButton jButtonAddCrise;
    private javax.swing.JButton jButtonAddVoiture;
    private javax.swing.JButton jButtonRemoveVoiture;
    private javax.swing.JComboBox jComboBoxCrisis;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelVehicules;
    private javax.swing.JLabel jLabelDescription;
    private javax.swing.JScrollPane jScrollPaneVehicules;
    private javax.swing.JScrollPane jScrollPaneDescription;
    private javax.swing.JTable jTableVehicules;
    private javax.swing.JTextArea jTextAreaDescription;
    
    private Integer _currentCrisis;
}

class MyTableModel extends AbstractTableModel {
    
    String[] columnNames = {"ID Vehicule", "Date d'arrivée estimée", "Position", "Type"};
    
    Object[][] data;
    
    public MyTableModel(Object[][] objs) {
        super();
        data = objs;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        return data[i][i1];
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }
}