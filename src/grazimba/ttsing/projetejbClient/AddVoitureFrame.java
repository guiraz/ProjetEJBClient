/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

import grazimba.ttsing.projetejb.Vehicule;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Date;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Wandy
 */
public class AddVoitureFrame extends JFrame {
    
    private Vehicule _vehi;
    //"Station", "en route to Location", "at Location", "en route to Station"
    private String[] _posTab = { "Station", "ERTL", "AL", "ERTS"};
    
    /* Frame */
    private JPanel _contentPane;
    private GridLayout _layout;
    private JButton _okButton;
    private JButton _cancelButton;
    private JComboBox _posList;
    private JTextField _etaTextField;
    
    public AddVoitureFrame(){
        
        _vehi = new Vehicule();
        
        BigInteger bi = new BigInteger(130, new Random());
        _vehi.setIdvehicule(bi.toString(32).substring(0, 9));
        _vehi.setType("Police");
        
        setTitle("Add Voiture");
        InitLayout();
    }
    
    
    private void InitLayout(){
        _contentPane = (JPanel) this.getContentPane();
        _layout = new GridLayout(4,2);
        _contentPane.setLayout(_layout);
        
        _okButton = new JButton("OK");
        _okButton.setPreferredSize(new Dimension(200,25));
        _cancelButton = new JButton("Cancel");
        _posList = new JComboBox(_posTab);
        _etaTextField = new JTextField();
        
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
        
        _contentPane.add(new JLabel("ID Vehicule :"));
        _contentPane.add(new JLabel(_vehi.getIdvehicule()));
        
        _contentPane.add(new JLabel("Estimated time of arrival (min) : "));
        _contentPane.add(_etaTextField);
        
        _contentPane.add(new JLabel("Position : "));
        _contentPane.add(_posList);
        
        _contentPane.add(_okButton);
        _contentPane.add(_cancelButton);
        
        pack();
        setVisible(true);
    }
    
    private void OKButtonActionPerformed(ActionEvent evt){
        try
        {
            Date date = new Date();
            date.setTime( Integer.parseInt(_etaTextField.getText())  * 60000);
            
            _vehi.setEta(date);
            _vehi.setPosition(_posList.getSelectedItem().toString());
            
            ProjetEJBClient.getCont().AddVoiture(_vehi);
             this.dispose();
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "ETA doit etre un nombre", " Erreur de saisie ", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void CancelButtonActionPerformed(ActionEvent evt) {
        this.dispose();
    }
}
