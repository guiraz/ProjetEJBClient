/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grazimba.ttsing.projetejbClient;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

/**
 *
 * @author Guillaume
 */
public class VehiculeMainWindow extends JFrame{
    
    public VehiculeMainWindow() {
        
        initComponents();
        
        pack();
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ProjetEJBClient.getCont().ExitQuery();
            }
        });
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
    
    public void RessourcesUpdated() {
        if(_currentVehicule!=null && !_currentVehicule.equals("")) {
            _labelId.setText(_currentVehicule);
        }
        else {
            _buttonAL.setEnabled(false);
            _buttonERTL.setEnabled(false);
            _buttonERTS.setEnabled(false);
            _buttonStation.setEnabled(false);
            Object[] possibleValues = ProjetEJBClient.getCont().getFreeVehiculesIds().toArray();
            _currentVehicule = (String) JOptionPane.showInputDialog(this, "Choose a vehicule : ", "Vehicule selection", JOptionPane.QUESTION_MESSAGE, null, possibleValues, possibleValues[0]);
            RessourcesUpdated();
        }
    }
    
    private void initComponents() {
        setTitle("Vehicule Client");
        _currentVehicule = new String("");
    
        _buttonQuit = new JButton("Quit");
        _buttonStation = new JButton("Station");
        _buttonERTS = new JButton("ERTS");
        _buttonAL = new JButton("AL");
        _buttonERTL = new JButton("ERTL");

        _labelTitle = new JLabel("Vehicule");
        _labelTitle.setFont(new java.awt.Font("Ubuntu", 1, 24));
        _labelId = new JLabel("id");
        _labelVehiPos = new JLabel("Vehicule's position : ");

        _textAreaDesc = new JTextArea();
        _textAreaDesc.setEditable(false);
        _scrollPaneDesc = new JScrollPane();
        _scrollPaneDesc.setViewportView(_textAreaDesc);
        _scrollPaneDesc.setMinimumSize(new Dimension(200, 200));
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        _buttonQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonQuitActionPerformed(evt);
            }
        });
        
        _buttonStation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonStationActionPerformed(evt);
            }
        });
        
        _buttonERTS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonERTSActionPerformed(evt);
            }
        });
        
        _buttonAL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonALActionPerformed(evt);
            }
        });
        
        _buttonERTL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonERTLActionPerformed(evt);
            }
        });
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(_labelTitle)
                            .addComponent(_labelId))
                        .addComponent(_scrollPaneDesc)
                        .addComponent(_labelVehiPos)
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(_buttonStation)
                                .addComponent(_buttonERTS))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(_buttonAL)
                                .addComponent(_buttonERTL))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addComponent(_buttonQuit)
                    )
        );
        layout.linkSize(SwingConstants.HORIZONTAL, _buttonStation, _buttonERTS, _buttonAL, _buttonERTL,_buttonQuit);
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(_labelTitle)
                        .addComponent(_labelId))
                    .addComponent(_scrollPaneDesc)
                    .addComponent(_labelVehiPos)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(_buttonStation)
                            .addComponent(_buttonERTS))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(_buttonAL)
                            .addComponent(_buttonERTL))
                    )
                    .addComponent(_buttonQuit)
        );
    }
    
    private void jButtonQuitActionPerformed(ActionEvent evt) {
        Object[] options = { "OK", "CANCEL" };
        if(JOptionPane.showOptionDialog(null, "Voulez vous quitter?", "Quitter", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]) == JOptionPane.OK_OPTION) {
            ProjetEJBClient.getCont().ExitQuery();
        }
    }
    
    private void jButtonStationActionPerformed(ActionEvent evt) {
        //todo
    }
    
    private void jButtonERTSActionPerformed(ActionEvent evt) {
        //todo
    }
    
    private void jButtonALActionPerformed(ActionEvent evt) {
        //todo
    }
    
    private void jButtonERTLActionPerformed(ActionEvent evt) {
        //todo
    }
    
    /**
     * VehiculeMainWindow Attributes
     */
    
    String _currentVehicule;
    
    JButton _buttonQuit;
    JButton _buttonStation;
    JButton _buttonERTS;
    JButton _buttonAL;
    JButton _buttonERTL;
    
    JLabel _labelTitle;
    JLabel _labelId;
    JLabel _labelVehiPos;
    
    JTextArea _textAreaDesc;
    JScrollPane _scrollPaneDesc;
}
