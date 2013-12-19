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
 * Les autres classes doivent acceder aux ressources via le Controller
 */
public class Controller {
    
    /*
     * Controller Constructor
     */
    public Controller() {
    }
    
    /*
     * Procedure that launch Update Thread and Gui Threads
     */
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
                ProjetEJBClient.getCreateVehiculeMainWindow().launch();
                break;
        }
        ProjetEJBClient.getThreadMAJ().start();
    }
    
    /*
     * Procedure killing threads and exit the program
     */
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
                ProjetEJBClient.getCreateVehiculeMainWindow().dispose();
                break;
        }
        ProjetEJBClient.getThreadMAJ().SetActive(false);
        System.exit(0);
    }
    
    /*
     * Procedure calling the update of the ressources procedure
     */
    public void UpdateRessources() {
        ProjetEJBClient.getRessource().UpdateRessources();
    }
    
    /*
     * Procedure calling the update of the Gui procedure
     */
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
                ProjetEJBClient.getCreateVehiculeMainWindow().RessourcesUpdated();
                break;
        }
    }
    
    /*
     * Procedure calling the procedure adding a crisis in the DB
     */
    public void AddCrisis(Crisis c, Timeoutlog t, Routeplan rt) {
        ProjetEJBClient.getRessource().AddCrise(c, t, rt);
    }
    
    /*
     * Procedure calling the procedure adding a route in the DB
     */
    public void AddRoute(Route r){
        ProjetEJBClient.getRessource().AddRoute(r);
    }
    
    /*
     * Procedure calling the procedure removing a route in the DB with the id of the vehicule
     */
    public void RemoveRouteByVehicule(String idV){
        ProjetEJBClient.getRessource().RemoveRouteByVehicule(idV);
    }
    
    /*
     * Procedure calling the procedure adding a name to a routeplan in the DB
     */
    public void EditRouteplan(String s){
        ProjetEJBClient.getRessource().EditRouteplan(s);
    }
    
    /*
     * Procedure calling the procedure comfirming a routeplan name in the DB
     */
    public void ComfirmRouteplan(String s){
        ProjetEJBClient.getRessource().ComfirmRouteplan(s);
    }
    
    /*
     * Procedure calling the procedure declining a routeplan name in the DB
     */
    public void DeclineRouteplan(String s){
        ProjetEJBClient.getRessource().DeclineRouteplan(s);
    }
    
    /*
     * Function calling the function asking the user the name of the routeplan (call in Ressources.EditRoutePlan)
     */
    public String getRouteName() {
        return ProjetEJBClient.getMainWindow().getRouteName();
    }
    
    /*
     * Procedure calling the procedure editing a crisis in the DB to close
     */
    public void setCriseClosed(String s) {
        ProjetEJBClient.getRessource().setCriseClosed(s);
    }
    
    /*
     * Function calling the function asking the user reasons why timer elapsed (call in Ressources.setCriseClosed)
     */
    public String getReasons() {
        return ProjetEJBClient.getMainWindow().getReasons();
    }
    
    /*
     * Function calling the function getting all the active crisis
     */
    public List<Crisis> getActiveCrisis() {
        return ProjetEJBClient.getRessource().getActiveCrisis();
    }
    
    /*
     * Function calling the function getting the timeoutlog of the crisis with the id of the same crisis
     */
    public Timeoutlog getTolOfCrisis(String id) {
        return ProjetEJBClient.getRessource().getTolOfCrisis(id);
    }
    
    /*
     * Procedure calling the procedure adding a vehicule in the DB
     */
    public void addVehicule(Vehicule v){
        ProjetEJBClient.getRessource().AddVehicule(v);
    }
    
    /*
     * Function calling the function getting all the vehicules attributed to a crisis with the id of the same crisis
     */
    public List<Vehicule> getVehiculesOfCrisis(String id) {
        return ProjetEJBClient.getRessource().getVehiculesOfCrisis(id);
        
    }
    
    /*
     * Function calling the function getting all the routeplans attributed to a crisis with the id of the crisis
     */
    public Routeplan getRoutePlanOfCrisis(String s) {
        return ProjetEJBClient.getRessource().getRoutePlanOfCrisis(s);
    }
    
    /*
     * Function calling the function getting all the vehicules available for an attribution to a crisis
     */
    public List<Vehicule> getVehiculesForCrisis() {
        return ProjetEJBClient.getRessource().getVehiculesForCrisis();
    }
    
    /*
     * Function calling the function getting all the vehicules not in a current use
     */
    public List<String> getFreeVehiculesIds() {
        return ProjetEJBClient.getRessource().getFreeVehiculesIds();
    }
    
    /*
     * Procedure calling procedure setting vehicule idV used if inuse or not used if !inuse
     */
    public void SetVehiculeInUse(String idV, boolean inuse) {
        ProjetEJBClient.getRessource().setVehiculeInUse(idV, inuse);
    }
    
    /*
     * Procedure calling procedure setting vehicule's position
     */
    public void setVehiculePosition(String idV, int pos) {
        ProjetEJBClient.getRessource().setVehiculePosition(idV, pos);
    }
    
    /*
     * Function calling function getting the route of a vehicule with it's id
     */
    public Route getRouteOfVehi(String idV) {
        return ProjetEJBClient.getRessource().getRouteOfVehi(idV);
    }
    
    /*
     * Function calling function getting a vehicule by it's id
     */
    public Vehicule getVehiculesById(String idV) {
        return ProjetEJBClient.getRessource().getVehiculesById(idV);
    }
    
    /*
     * Predicate calling Predicate saying if a vehicule is in use or not
     */
    public boolean VehiculeIdDispo(String idV){
        return ProjetEJBClient.getRessource().VehiculeIdDispo(idV);
    }
    
    /*
     * Function calling function getting a crisis by it's id
     */
    public Crisis getCrisisByID(String idC) {
        return ProjetEJBClient.getRessource().getCrisisByID(idC);
    }
    
    /*
     * Procedure calling the procedure setting a vehicule's estimated time to arrival by it's id
     */
    public void setVehiculeETA(String idV) {
        ProjetEJBClient.getRessource().setVehiculeETA(idV);
    }
    
    /*
     * Function calling Function asking a user what is the ETA of the vehicule (call in Ressources.setVehiculeETA)
     */
    public String getETA() {
        return ProjetEJBClient.getVehiculeMainWindow().getETA();
    }
    
    /*
     * Procedure calling procedure displaying an error message
     */
    public void ErrorMessage(String m) {
        switch(ProjetEJBClient.getTypeProgram()) {
            case POLICE :
                ProjetEJBClient.getMainWindow().ErrorMessage(m);
                break;
            case FIRE :
                ProjetEJBClient.getMainWindow().ErrorMessage(m);
                break;
            case VEHICULE :
                ProjetEJBClient.getVehiculeMainWindow().ErrorMessage(m);
                break;
            case CREATE_VEHICULES :
                break;
        }
    }
}

