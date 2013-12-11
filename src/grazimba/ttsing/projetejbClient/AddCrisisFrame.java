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
import java.math.BigInteger;
import java.util.Date;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author grazimba & ttsing
 */
class AddCrisisFrame extends JFrame {
    
    private Crisis _crise;
    private Routeplan _rp;
    private Timeoutlog _tol;
    private JPanel _contentPane;
    private GridLayout _layout;
    private JButton _okButton;
    private JButton _cancelButton;
    private JRadioButton _timerRadioButton;
    
    private JTextField _longitudeTextField;
    private JTextField _latitudeTextField;
    private JTextArea _descTextArea;
    private JTextField _timerTextField;
    
    public AddCrisisFrame() {
        _crise = new Crisis();
        _rp = new Routeplan();
        _tol = new Timeoutlog();
        
        BigInteger bi = new BigInteger(130, new Random());
        _crise.setIdcrisis(bi.toString(32).substring(0, 9));
        _rp.setIdcrisis(_crise.getIdcrisis());
        _tol.setIdcrisis(_crise.getIdcrisis());
        
        _crise.setT(new Date());
        
        _crise.setStatut("Active");
        
        _rp.setNbfirevehicule(0);
        _rp.setNbpolicevehicule(0);
        _rp.setNomroute(null);
        _rp.setComfirm("f");
        setTitle("add Crisis");
        initLayout();

        this.pack();
    }
    
    private void initLayout() {
        _contentPane = (JPanel) this.getContentPane();
        _layout = new GridLayout(7,2);
        _contentPane.setLayout(_layout);
        
        _okButton = new JButton("OK");
        _cancelButton = new JButton("Cancel");
        _timerRadioButton = new JRadioButton("Timer (en minutes) : ");
        _timerRadioButton.setSelected(true);
        
        _longitudeTextField = new JTextField();
        _latitudeTextField = new JTextField();
        _descTextArea = new JTextArea();
        _timerTextField = new JTextField();
        
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
        
        _timerRadioButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                TimerRadioButtonActionPerformed(evt);
            }
        });
        
        _contentPane.add(new JLabel("ID Crisis :"));
        _contentPane.add(new JLabel(_crise.getIdcrisis()));
        
        _contentPane.add(new JLabel("Longitude :"));
        _contentPane.add(_longitudeTextField);
        
        _contentPane.add(new JLabel("Latitude :"));
        _contentPane.add(_latitudeTextField);
        
        _contentPane.add(new JLabel("Heures :"));
        _contentPane.add(new JLabel(_crise.getT().toString()));
        
        _contentPane.add(new JLabel("Description :"));
        _contentPane.add(_descTextArea);
        
        _contentPane.add(_timerRadioButton);
        _contentPane.add(_timerTextField);
        
        _contentPane.add(_okButton);
        _contentPane.add(_cancelButton);
        
        pack();
        
        setVisible(true);
    }
    
    private void OKButtonActionPerformed(ActionEvent evt) {
        if((!_longitudeTextField.getText().isEmpty()) && (!_latitudeTextField.getText().isEmpty()))
        {
            if(_timerRadioButton.isSelected())
            {
                if(!_timerTextField.getText().isEmpty())
                {
                    try
                    {
                        _crise.setLongitude(Float.parseFloat(_longitudeTextField.getText()));
                        _crise.setLatitude(Float.parseFloat(_latitudeTextField.getText()));
                        if( (_crise.getLongitude()>= -180 &&  _crise.getLongitude() <= 180) && (_crise.getLatitude() >= -90 && _crise.getLatitude() <= 90) )
                        {
                            if(!_descTextArea.getText().isEmpty())
                                _crise.setDescription(_descTextArea.getText());

                            Date date = new Date();
                            date.setTime(_crise.getT().getTime() + Integer.parseInt(_timerTextField.getText()) * 60000);
                            _tol.setD(date);

                            ProjetEJBClient.getCont().AddCrisis(_crise, _tol, _rp);

                            this.dispose();
                        }
                        else
                        {
                             JOptionPane.showMessageDialog(this, "Longitude [-180,180] et Latitude [-90,90] ", " Erreur de saisie ", JOptionPane.ERROR_MESSAGE);
                        }  
                    }
                    catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(this, "Les champs longitude, latitude et timer doivent etre des nombres", " Erreur de saisie ", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Les champs longitude, latitude et timer sont obligatoires", " Erreur de saisie ", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                _crise.setLongitude(Float.parseFloat(_longitudeTextField.getText()));
                _crise.setLatitude(Float.parseFloat(_latitudeTextField.getText()));
                if(!_descTextArea.getText().isEmpty())
                    _crise.setDescription(_descTextArea.getText());
                ProjetEJBClient.getCont().AddCrisis(_crise, null, _rp);
                
                this.dispose();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Les champs longitude et latitude sont obligatoires", " Erreur de saisie ", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void CancelButtonActionPerformed(ActionEvent evt) {
        this.dispose();
    }
    
    private void TimerRadioButtonActionPerformed(ActionEvent evt) {
        if(_timerRadioButton.isSelected())
            _timerTextField.setEnabled(true);
        else
            _timerTextField.setEnabled(false);
    }
    
}

