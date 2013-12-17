/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

import grazimba.ttsing.projetejb.Vehicule;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author grazimba & ttsing
 */
public class CreateVehiculeMainWindow extends JFrame{
    
    public CreateVehiculeMainWindow(){
        
        initComponents();
        
        pack();
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ProjetEJBClient.getCont().ExitQuery();
            }
        });
        
    }
    
    public void initComponents(){
        setTitle("Create Vehicule Client");
        
        _textID = new JTextField();
        _comboBoxVehi = new JComboBox(_itemComboBox);
        _buttonAdd = new JButton("Add");
        _buttonQuit = new JButton("Quit");
        
        GridBagConstraints gbc = new GridBagConstraints();
        getContentPane().setLayout(new GridBagLayout());
        
        _textID.setPreferredSize(new Dimension(150,20));
        _comboBoxVehi.setPreferredSize(new Dimension(150,20));
        _buttonQuit.setPreferredSize(new Dimension(90,25));
        _buttonAdd.setPreferredSize(new Dimension(90,25));
        
        _buttonQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonQuitActionPerformed(evt);
            }
        });
        
         _buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        
        gbc.gridx = gbc.gridy = 0;
        gbc.gridwidth = 2; // ce GridBagConstraints prend 2 lignes
        gbc.gridheight = 2; // ce GridBagConstraints prend 2 colonnes
        gbc.anchor = GridBagConstraints.CENTER; // on place au centre notre bouton
        gbc.insets = new Insets(10, 10, 10, 10); // Marge en haut, gauche et droite de 10.
        getContentPane().add(new JLabel("ID : "),gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        getContentPane().add(_textID,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        getContentPane().add(new JLabel("Type : "),gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        getContentPane().add(_comboBoxVehi,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 30, 10, 10);
        getContentPane().add(_buttonAdd,gbc);
        
        
        gbc.gridx = 2;
        gbc.gridy = 4;
        getContentPane().add(_buttonQuit,gbc);
        
        pack();
    }
    
    /*
     * Action Perform pour le boutton quitter
     */
    private void jButtonQuitActionPerformed(ActionEvent evt) {
        Object[] options = { "OK", "CANCEL" };
        if(JOptionPane.showOptionDialog(null, "Do youwant to quit?", "Quit", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]) == JOptionPane.OK_OPTION) {
            ProjetEJBClient.getCont().ExitQuery();
        }
    }
    
    /*
     * Action Performe lors de la creation d'un vehicule
     */
    private void jButtonAddActionPerformed(ActionEvent evt) {
        //On verifie si notre l'ID du vehicule est conforme a notre contrainte
        if(_textID.getText().length() <= 10 && _textID.getText().length() > 0){
            if(!ProjetEJBClient.getCont().VehiculeIdDispo(_textID.getText())){
                v = new Vehicule(_textID.getText(),"Station",_comboBoxVehi.getSelectedItem().toString(),"f");
                v.setEta(null);
                ProjetEJBClient.getCont().addVehicule(v);
                
                Object[] options = { "YES", "NO" };
                if(JOptionPane.showOptionDialog(null, "Vehicule added. \nDo you want to quit?", "Quit", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]) == JOptionPane.OK_OPTION) {
                    ProjetEJBClient.getCont().ExitQuery();
                }
            }
            else
                JOptionPane.showMessageDialog(this, "Already existing id", " Input Error ", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(this, "IDs must contain 10 characters", " Input Error ", JOptionPane.ERROR_MESSAGE);
    }
    
    public void RessourcesUpdated() {
        
    }
    
        public void launch() {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(true);
            }
        });
    }
    
    /**
     * CreateVehiculeMainWindow Attributes
     */
    private JTextField _textID;
    private JComboBox _comboBoxVehi;
    private JButton _buttonAdd;
    private JButton _buttonQuit;
    
    private String[]_itemComboBox={"Police","Fire"};
    
    private Vehicule v;
}
