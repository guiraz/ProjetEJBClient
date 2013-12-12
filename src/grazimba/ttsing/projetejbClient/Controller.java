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
        switch (ProjetEJBClient.getTypeProgram()) {
            case POLICE :
                ProjetEJBClient.getMainWindow().launch();
                break;
                
            case FIRE :
                ProjetEJBClient.getMainWindow().launch();
                break;
                
            case VEHICULE :
                ProjetEJBClient.getVehiculeMainWindow().launch();
                break;
                
            case CREATE_VEHICULES :
                //ProjetEJBClient.getCreateVehiculeMainWindow().launch();
                break;
        }
        ProjetEJBClient.getThreadMAJ().start();
    }
    
    public void ExitQuery() {
        switch (ProjetEJBClient.getTypeProgram()) {
            case POLICE :
                ProjetEJBClient.getMainWindow().dispose();
                break;
                
            case FIRE :
                ProjetEJBClient.getMainWindow().dispose();
                break;
                
            case VEHICULE :
                ProjetEJBClient.getVehiculeMainWindow().dispose();
                break;
                
            case CREATE_VEHICULES :
                //ProjetEJBClient.getCreateVehiculeMainWindow().dispose();
                break;
        }
        ProjetEJBClient.getThreadMAJ().SetActive(false);
        System.exit(0);
    }
    
    public void UpdateRessources() {
        ProjetEJBClient.getRessource().UpdateRessources();
    }
    
    public void RessourcesUpdated() {
        switch (ProjetEJBClient.getTypeProgram()) {
            case POLICE :
                ProjetEJBClient.getMainWindow().RessourcesUpdated();
                break;
                
            case FIRE :
                ProjetEJBClient.getMainWindow().RessourcesUpdated();
                break;
                
            case VEHICULE :
                ProjetEJBClient.getVehiculeMainWindow().RessourcesUpdated();
                break;
                
            case CREATE_VEHICULES :
                //ProjetEJBClient.getCreateVehiculeMainWindow().RessourcesUpdated();
                break;
        }
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
    
    public void DeclineRouteplan(String s){
        ProjetEJBClient.getRessource().DeclineRouteplan(s);
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
    
    public Timeoutlog getTolOfCrisis(String id) {
        return ProjetEJBClient.getRessource().getTolOfCrisis(id);
    }
    
    public List<Vehicule> getVehiculesOfCrisis(String id) {
        return ProjetEJBClient.getRessource().getVehiculesOfCrisis(id);
        
    }
    
    public Routeplan getRoutePlanOfCrisis(String s) {
        return ProjetEJBClient.getRessource().getRoutePlanOfCrisis(s);
    }
    
    public List<Vehicule> getVehiculesForCrisis() {
        return ProjetEJBClient.getRessource().getVehiculesForCrisis();
    }
    
    public List<String> getFreeVehiculesIds() {
        return ProjetEJBClient.getRessource().getFreeVehiculesIds();
    }
    
    public void SetVehiculeInUse(String idV, boolean inuse) {
        ProjetEJBClient.getRessource().setVehiculeInUse(idV, inuse);
    }
    
    public void setVehiculePosition(String idV, int pos) {
        ProjetEJBClient.getRessource().setVehiculePosition(idV, pos);
    }
    
    public Route getRouteOfVehi(String idV) {
        return ProjetEJBClient.getRessource().getRouteOfVehi(idV);
    }
    
    public String getVehiculePosition(String idV) {
        return ProjetEJBClient.getRessource().getVehiculePosition(idV);
    }
}

