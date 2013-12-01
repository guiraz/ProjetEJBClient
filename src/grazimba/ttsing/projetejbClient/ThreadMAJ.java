/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

import grazimba.ttsing.projetejb.*;
import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author grazimba
 */
public class ThreadMAJ extends Thread{

    public ThreadMAJ() {
        _isActive = true;
    }
    
    /**
     *@desc Run the thread loop
     */
    @Override
    public void run() {
        while(_isActive) {
            
            ProjetEJBClient.getRessource().updateRessources();
            ProjetEJBClient.getCont().RessourcesUpdated();

            try {
                Thread.sleep(5000);
            }
            catch(Exception e) {
                System.err.println("Caught ThreadException: " + e.getMessage());
            }
        }
    }
    
    public void SetActive(boolean active) {
        _isActive = active;
    }
    
    
    /**
     * ThreadMAJ Attributes
     */
    private boolean _isActive;
}
