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
            System.out.println("Loop");
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
            System.out.println("before ejb");
            _jndi_context = new javax.naming.InitialContext();
            System.out.println("init context");
            VehiculeFacadeRemote vf = (VehiculeFacadeRemote) _jndi_context.lookup("VehiculeFacade");
            CrisisFacadeRemote crise = (CrisisFacadeRemote) _jndi_context.lookup("CrisisFacade");
            TimeoutlogFacadeRemote tl = (TimeoutlogFacadeRemote) _jndi_context.lookup("TimeoutlogFacade");
            RouteplanFacadeRemote rp = (RouteplanFacadeRemote) _jndi_context.lookup("RouteplanFacade");
            RouteFacadeRemote rt = (RouteFacadeRemote) _jndi_context.lookup("RouteFacade");
            
            System.out.println("after ejb");
            
            Main.getRessource().setCrises(crise.findAll());
            Main.getRessource().setVehicules(vf.findAll());
            Main.getRessource().setTols(tl.findAll());
            Main.getRessource().setRoutes(rt.findAll());
            Main.getRessource().setToutePlans(rp.findAll());

            if (_jndi_context != null) {
                _jndi_context.close();
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
    private javax.naming.Context _jndi_context;
}
