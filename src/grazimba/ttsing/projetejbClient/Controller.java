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
import java.util.List;

/**
 *
 * @author grazimba & ttsing
 */
public class Controller {
    
    public Controller() {
    }
    
    public void LaunchThreads() {
        ProjetEJBClient.getMainWindow().launch();
        ProjetEJBClient.getThreadMAJ().start();
    }
    
    public void ExitQuery() {
        ProjetEJBClient.getMainWindow().dispose();
        ProjetEJBClient.getThreadMAJ().SetActive(false);
        System.exit(0);
    }
    
    public void UpdateRessources() {
        ProjetEJBClient.getRessource().UpdateRessources();
    }
    
    public void RessourcesUpdated() {
        ProjetEJBClient.getMainWindow().RessourcesUpdated();
    }
    
    public void AddCrisis(Crisis c, Timeoutlog t, Routeplan rt) {
        ProjetEJBClient.getRessource().AddCrise(c, t, rt);
    }
    
    public void AddRoute(Route r){
        ProjetEJBClient.getRessource().AddRoute(r);
    }
    
    public void RemoveRoute(Route r){
        ProjetEJBClient.getRessource().RemoveRoute(r);
    }
    
    public void EditRouteplan(String s){
        ProjetEJBClient.getRessource().EditRouteplan(s);
    }
    
    public void ComfirmRouteplan(String s){
        ProjetEJBClient.getRessource().ComfirmRouteplan(s);
    }
    
    public String getRouteName() {
        return ProjetEJBClient.getMainWindow().getRouteName();
    }
    
    public void setCriseClosed(String s) {
        ProjetEJBClient.getRessource().setCriseClosed(s);
    }
    
    public String getReasons() {
        return ProjetEJBClient.getMainWindow().getReasons();
    }
    
    public List<Crisis> getActiveCrisis() {
        return ProjetEJBClient.getRessource().getActiveCrisis();
    }
    
    public Timeoutlog getTolOf(String id) {
        return ProjetEJBClient.getRessource().getTolOf(id);
    }
    
    public List<Vehicule> getVehiculesOf(String id) {
        return ProjetEJBClient.getRessource().getVehiculesOf(id);
        
    }
    
    public Routeplan getRoutePlanOf(String s) {
        return ProjetEJBClient.getRessource().getRoutePlanOf(s);
    }
    
    public List<Vehicule> getVehiculesForCrisis() {
        return ProjetEJBClient.getRessource().getVehiculesForCrisis();
    }
}

