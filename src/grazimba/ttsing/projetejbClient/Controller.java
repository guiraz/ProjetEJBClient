/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

import grazimba.ttsing.projetejb.Crisis;
import grazimba.ttsing.projetejb.Routeplan;
import grazimba.ttsing.projetejb.Route;
import grazimba.ttsing.projetejb.Timeoutlog;
import grazimba.ttsing.projetejb.Vehicule;

/**
 *
 * @author grazimba
 */
public class Controller {
    
    public Controller() {
    }
    
    public void LaunchThreads() {
        ProjetEJBClient.getView().launch();
        ProjetEJBClient.getThreadMAJ().start();
    }
    
    public void ExitQuery() {
        ProjetEJBClient.getView().dispose();
        ProjetEJBClient.getThreadMAJ().SetActive(false);
        System.exit(0);
    }
    
    public void UpdateRessources() {
        ProjetEJBClient.getRessource().UpdateRessources();
    }
    
    public void RessourcesUpdated() {
        ProjetEJBClient.getView().RessourcesUpdated();
    }
    
    public void AddCrisis(Crisis c, Timeoutlog t, Routeplan rt) {
        ProjetEJBClient.getRessource().AddCrise(c, t, rt);
    }
    
    public void AddVoiture(Vehicule v){
        ProjetEJBClient.getRessource().AddVehicule(v);
    }
    
    public void AddRoute(Route r){
        ProjetEJBClient.getRessource().AddRoute(r);
    }
    
    public void RemoveRoute(Route r){
        ProjetEJBClient.getRessource().RemoveRoute(r);
    }
    
    public void setCriseClosed(String s) {
        ProjetEJBClient.getRessource().setCriseClosed(s);
    }
    
    public String getReasons() {
        return ProjetEJBClient.getView().getReasons();
    }
}
