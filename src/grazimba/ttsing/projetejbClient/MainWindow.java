/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

import grazimba.ttsing.projetejb.Crisis;
import grazimba.ttsing.projetejb.Routeplan;
import grazimba.ttsing.projetejb.Timeoutlog;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        jTableVehicules.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPaneVehicules.setViewportView(jTableVehicules);

        jLabelVehicules.setText("Voitures :");

        jLabelDescription.setText("Description :");

        jTextAreaDescription.setEditable(false);
        jTextAreaDescription.setColumns(20);
        jTextAreaDescription.setRows(5);
        jScrollPaneDescription.setViewportView(jTextAreaDescription);

        jButtonAddVoiture.setText("Add Voiture");

        jButtonRemoveVoiture.setText("Remove Voiture");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelVehicules, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneVehicules, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonQuit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonRemoveVoiture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAddVoiture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneDescription)
                            .addComponent(jComboBoxCrisis, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTitle)
                                    .addComponent(jLabelDescription))
                                .addGap(491, 491, 491)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAddCrise, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCrisis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAddCrise))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelDescription)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPaneDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelVehicules)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPaneVehicules, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 35, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonAddVoiture)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemoveVoiture)
                        .addGap(73, 73, 73)
                        .addComponent(jButtonQuit)))
                .addContainerGap())
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
        acf.setVisible(true);
    }


    public void launch() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    
    public void RessourcesUpdated(){
        
        System.out.println("gui update");
        
        Ressources res = ProjetEJBClient.getRessource();
        
        jComboBoxCrisis.removeAllItems();
        for(int i = 0; i<res.getCrises().size(); i++) {
            if(res.getCrise(i).getStatut().equals("Active"))
                jComboBoxCrisis.addItem(res.getCrise(i).getIdcrisis());
        }
        
        
        Object[][] data = new Object[res.getVehicules().size()][4];
        for(int i = 0; i<res.getVehicules().size(); i++) {
            System.out.println("vehicule : " + i);
            data[i][0] = res.getVehicule(i).getIdvehicule();
            data[i][1] = res.getVehicule(i).getEta();
            data[i][2] = res.getVehicule(i).getPosition();
            data[i][3] = res.getVehicule(i).getType();
        }
        String[] columnNames =  {"ID Vehicule", "Date d'arrivée estimée", "Position", "Type"};
        
        jTableVehicules = new JTable(new MyTableModel((data)));
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


class AddCrisisFrame extends JFrame {
    
    private Crisis _crise;
    private Routeplan _rp;
    private Timeoutlog _tol;
    
    private JPanel _contentPane;
    private GridLayout _layout;
    private JButton _okButton;
    private JButton _cancelButton;
    
    public AddCrisisFrame() {
        _crise = new Crisis();
        _rp = new Routeplan();
        _tol = new Timeoutlog();
        
        BigInteger bi = new BigInteger(130, new Random());
        _crise.setIdcrisis(bi.toString(32).substring(0, 9));
        _rp.setIdcrisis(_crise.getIdcrisis());
        _tol.setIdcrisis(_crise.getIdcrisis());
        
        _crise.setT(new Date());
        System.out.println(_crise.getT());
        
        _rp.setNbfirevehicule(0);
        _rp.setNbpolicevehicule(0);
        
        initLayout();

        this.pack();
    }
    
    private void initLayout() {
        _contentPane = (JPanel) this.getContentPane();
        _layout = new GridLayout(7,2);
        _contentPane.setLayout(_layout);
        
        _okButton = new JButton("OK");
        _cancelButton = new JButton("Cancel");
        
        _okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                OKButtonActionPerformed(evt);
            }
        });
        
        _cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });
        
        _contentPane.add(new JLabel("ID Crisis :"));
        _contentPane.add(new JLabel(_crise.getIdcrisis()));
        
        _contentPane.add(new JLabel("Longitude :"));
        _contentPane.add(new JTextField());
        
        _contentPane.add(new JLabel("Latitude :"));
        _contentPane.add(new JTextField());
        
        _contentPane.add(new JLabel("Time :"));
        _contentPane.add(new JLabel(_crise.getT().toString()));
        
        _contentPane.add(new JLabel("Description :"));
        _contentPane.add(new JTextArea());
        
        _contentPane.add(new JRadioButton("Timer :"));
        _contentPane.add(new JTextArea());
        
        _contentPane.add(_okButton);
        _contentPane.add(_cancelButton);
        
        pack();
    }
    
    private void OKButtonActionPerformed(ActionEvent evt) {
        //TODO
        this.dispose();
    }
    
    private void CancelButtonActionPerformed(ActionEvent evt) {
        this.dispose();
    }
    
}
