/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

import grazimba.ttsing.projetejb.Crisis;
import grazimba.ttsing.projetejb.Routeplan;
import grazimba.ttsing.projetejb.Timeoutlog;

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
    
    public void RessourcesUpdated() {
        ProjetEJBClient.getView().RessourcesUpdated();
    }
    
    public void AddCrisis(Crisis c, Timeoutlog t, Routeplan rt) {
        ProjetEJBClient.getRessource().AddCrise(c, t, rt);
    }
    
}
