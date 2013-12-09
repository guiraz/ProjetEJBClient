/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

import grazimba.ttsing.projetejb.Route;
import grazimba.ttsing.projetejb.RoutePK;
import javax.swing.JOptionPane;

/**
 *
 * @author Wandy
 */
public class RemoveVoiture {
    
    public RemoveVoiture(){
        
    }
    
    /*
     * Note : -1 la tailel de Jtable si on ne selectionne rien.
     * Faire attention lors de la verification 
     *
     * Erreur : Lors d'un update de la view si on selectionne une ligne, une erreur s'affiche dans la console
     * Mais je ne sais pas comment régler ceci. une erreur mystique comme on le dit!
     * 
     */
    public void Remove(){
      int idVehiR = ProjetEJBClient.getView().getJTableVehicules().getSelectedRow();
      
      if(idVehiR >= 0 && idVehiR < ProjetEJBClient.getView().getNbVehicule()){
          RoutePK rpk = new RoutePK(ProjetEJBClient.getView().getJTableVehicules().getValueAt(idVehiR, 0).toString(),ProjetEJBClient.getView().getComboBoxCrisis().getSelectedItem().toString());
          Route r = new Route(rpk);
          ProjetEJBClient.getCont().RemoveRoute(r); 
      }
      else{
          JOptionPane.showMessageDialog(ProjetEJBClient.getView(), "Aucune ligne selectionne", " Erreur de selection ", JOptionPane.ERROR_MESSAGE);
      }
    }
}
