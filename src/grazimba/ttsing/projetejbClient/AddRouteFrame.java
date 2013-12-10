/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

import grazimba.ttsing.projetejb.Routeplan;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author grazimba & ttsing
 */
public class AddRouteFrame  extends JFrame{
    
    /* Attributs */
    private Routeplan _rp;
    
    /* Frame */
    private JPanel _contentPane;
    private GridLayout _layout;
    private JButton _okButton;
    private JButton _cancelButton;
    private JTextField _nomRouteTextfield;
    private JTextField _nbFireTextfield;
    private JTextField _nbPoliceTextfield;
    
    
    public AddRouteFrame(){
        setTitle("Add Route");
        _rp = new Routeplan();
        InitLayout();
    }
    
    public void InitLayout(){
        _contentPane = (JPanel) this.getContentPane();
        _layout = new GridLayout(4,2);
        _contentPane.setLayout(_layout);
        
        _okButton = new JButton("OK");
        _okButton.setPreferredSize(new Dimension(200,25));
        _cancelButton = new JButton("Cancel");
        
        _nomRouteTextfield = new JTextField();
        _nbFireTextfield = new JTextField();
        _nbPoliceTextfield = new JTextField();
        
        _contentPane.add(new JLabel("Nom route : "));
        _contentPane.add(_nomRouteTextfield);
        
        _contentPane.add(new JLabel("Nombre voiture pompier : "));
        _contentPane.add(_nbFireTextfield);
        
        _contentPane.add(new JLabel("Nombre voiture policier : "));
        _contentPane.add(_nbPoliceTextfield);
        
        _contentPane.add(_okButton);
        _contentPane.add(_cancelButton);
        
        pack();
        setVisible(true);
        
        /* Listener */
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
    }
    
    private void OKButtonActionPerformed(ActionEvent evt){
        int nbFire, nbPolice;
        try
        {       
            nbFire = Integer.parseInt(_nbFireTextfield.getText());
            nbPolice = Integer.parseInt(_nbPoliceTextfield.getText());
            _rp.setNbfirevehicule(nbFire);
            _rp.setNbpolicevehicule(nbPolice);
            _rp.setNomroute(_nomRouteTextfield.getText());
            _rp.setIdcrisis(ProjetEJBClient.getView().getComboBoxCrisis().getSelectedItem().toString());
            
            ProjetEJBClient.getCont().EditRouteplan(_rp);
            this.dispose();
        }
         catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Les champs nombre voiture police et pompier doivent etre des nombres", " Erreur de saisie ", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void CancelButtonActionPerformed(ActionEvent evt) {
        this.dispose();
    }
    
}
