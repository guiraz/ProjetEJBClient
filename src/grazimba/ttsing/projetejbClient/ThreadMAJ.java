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
            Maj();

            try {
                Thread.sleep(30000);
            }
            catch(Exception e) {
                System.err.println("Caught ThreadException: " + e.getMessage());
            }
        }
    }
    
    public void setActive(boolean active) {
        _isActive = active;
    }
    
    
    private void Maj() {
        
        try
        {
            javax.naming.Context jndi_context = new javax.naming.InitialContext();
            VehiculeFacadeRemote vf = (VehiculeFacadeRemote) jndi_context.lookup("ejb/VehiculeFacade");
            CrisisFacadeRemote crise = (CrisisFacadeRemote) jndi_context.lookup("ejb/CrisisFacade");
            TimeoutlogFacadeRemote tl = (TimeoutlogFacadeRemote) jndi_context.lookup("ejb/TimeoutlogFacade");
            RouteplanFacadeRemote rp = (RouteplanFacadeRemote) jndi_context.lookup("ejb/RouteplanFacade");
            RouteFacadeRemote rt = (RouteFacadeRemote) jndi_context.lookup("ejb/RouteFacade");
            
            Main.getRessource().setCrises(crise.findAll());
            Main.getRessource().setVehicules(vf.findAll());
            Main.getRessource().setTols(tl.findAll());
            Main.getRessource().setRoutes(rt.findAll());
            Main.getRessource().setToutePlans(rp.findAll());

            if (jndi_context != null) {
                jndi_context.close();
            }
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * ThreadMAJ Attributes
     */
    private boolean _isActive;
}
