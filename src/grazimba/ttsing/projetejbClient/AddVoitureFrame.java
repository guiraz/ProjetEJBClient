/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

import grazimba.ttsing.projetejb.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author grazimba & ttsing
 */
public class AddVoitureFrame extends JFrame {
    
    public AddVoitureFrame(String id){
        setTitle("Add Vehicule");
        _idCrisis = id;
        InitLayout();
    }
    
    private void InitLayout(){
        _contentPane = (JPanel) this.getContentPane();
        _layout = new GridLayout(2,2);
        _contentPane.setLayout(_layout);
        
        _okButton = new JButton("OK");
        _okButton.setPreferredSize(new Dimension(200,25));
        _cancelButton = new JButton("Cancel");
        _vehiList = new JComboBox();
        recupVehicules();
        
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
        
        _contentPane.add(new JLabel("Vehicule : "));
        _contentPane.add(_vehiList);
        
        _contentPane.add(_okButton);
        _contentPane.add(_cancelButton);
        
        pack();
        setVisible(true);
    }
    /*
     * Recupere les vehicules qui se trouve dans le Ressources en passant par le Controller
     */
    private void recupVehicules() {
        _vehicules = ProjetEJBClient.getCont().getVehiculesForCrisis();
        for(int i=0; i < _vehicules.size(); i++) {
            _vehiList.addItem(_vehicules.get(i).getIdvehicule());
        }
    }
    
    private void OKButtonActionPerformed(ActionEvent evt){
        RoutePK rpk = new RoutePK(_vehiList.getSelectedItem().toString(), _idCrisis);
        Route r = new Route(rpk);
        ProjetEJBClient.getCont().AddRoute(r);
        this.dispose();
    }
    
    private void CancelButtonActionPerformed(ActionEvent evt) {
        this.dispose();
    }
    
    /**
     * AddVoiture Attributes
     */
    private final String _idCrisis;
    private JPanel _contentPane;
    private GridLayout _layout;
    private JButton _okButton;
    private JButton _cancelButton;
    private JComboBox _vehiList;
    private List<Vehicule> _vehicules;
}
