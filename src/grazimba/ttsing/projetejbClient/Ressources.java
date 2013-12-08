/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

import grazimba.ttsing.projetejb.*;
import java.util.List;
/**
 *
 * @author grazimba
 */
public class Ressources {
    
    private List<Crisis> crises;
    private List<Route> routes;
    private List<Routeplan> toutePlan;
    private List<Timeoutlog> tol;
    private List<Vehicule> vehicule;
    
    private javax.naming.Context _jndi_context;
    
    private VehiculeFacadeRemote _vf;
    private CrisisFacadeRemote _crise;
    private TimeoutlogFacadeRemote _tl;
    private RouteplanFacadeRemote _rp;
    private RouteFacadeRemote _rt;
    
    public Ressources()
    {
        try
        {
            System.out.println("before context");
            _jndi_context = new javax.naming.InitialContext();
            
            _vf = (VehiculeFacadeRemote) _jndi_context.lookup("ejb/VehiculeFacade");
            _crise = (CrisisFacadeRemote) _jndi_context.lookup("ejb/CrisisFacade");
            _tl = (TimeoutlogFacadeRemote) _jndi_context.lookup("ejb/TimeoutlogFacade");
            _rp = (RouteplanFacadeRemote) _jndi_context.lookup("ejb/RouteplanFacade");
            _rt = (RouteFacadeRemote) _jndi_context.lookup("ejb/RouteFacade");
            
            System.out.println("context ok");
            ProjetEJBClient.getCont().LaunchThreads();
            
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * @desc Call by ThreadMAJ, update ressources from db
     */
    synchronized public void UpdateRessources() {
        setCrises(_crise.findAll());
        setVehicules(_vf.findAll());
        setTols(_tl.findAll());
        setRoutes(_rt.findAll());
        setToutePlans(_rp.findAll());
    }
    
    public void AddCrise(Crisis c, Timeoutlog t, Routeplan rt) {
        _crise.create(c);
        if(t != null)
            _tl.create(t);
        _rp.create(rt);
        UpdateRessources();
    }

    public void AddVehicule(Vehicule v){
        _vf.create(v);
        UpdateRessources();
    }
    
    public void AddRoute(Route r){
        _rt.create(r);
        UpdateRessources();
    }
    
    /**
     * Crisis
     * /
    
    /**
     * @return the crises
     */
    public List<Crisis> getCrises() {
        return crises;
    }

    /**
     * @param crises the crises to set
     */
    private void setCrises(List<Crisis> crises) {
        this.crises = crises;
    }
    
    /**
     * @return the crise at i
     */
    public Crisis getCrise(int i) {
        return crises.get(i);
    }
    
    /**
     * @param crise the crise to set
     */
    private void setCrise(Crisis crise) {
        crises.add(crise);
    }
    
    
    /**
     * Route
     */
    
    

    /**
     * @return the routes
     */
    public List<Route> getRoutes() {
        return routes;
    }

    /**
     * @param routes the routes to set
     */
    private void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
    
    /**
     * @return the route at i
     */
    public Route getRoute(int i) {
        return routes.get(i);
    }

    /**
     * @param route the route to set
     */
    private void setRoute(Route route) {
        this.routes.add(route);
    }
    
    
    
    /**
     * RoutePlan
     */

    
    
    /**
     * @return the toutePlans
     */
    public List<Routeplan> getToutePlans() {
        return toutePlan;
    }

    /**
     * @param toutePlan the toutePlans to set
     */
    private void setToutePlans(List<Routeplan> toutePlan) {
        this.toutePlan = toutePlan;
    }
    
    /**
     * @return the toutePlan at i
     */
    public Routeplan getToutePlan(int i) {
        return toutePlan.get(i);
    }

    /**
     * @param toutePlan the toutePlan to set
     */
    private void setToutePlan(Routeplan toutePlan) {
        this.toutePlan.add(toutePlan);
    }
    
    
    
    /**
     * TimeOutLog
     */

    
    
    /**
     * @return the tols
     */
    public List<Timeoutlog> getTols() {
        return tol;
    }

    /**
     * @param tol the tols to set
     */
    private void setTols(List<Timeoutlog> tol) {
        this.tol = tol;
    }
    
    /**
     * @return the tol at i
     */
    public Timeoutlog getTol(int i) {
        return tol.get(i);
    }

    /**
     * @param tol the tol to set
     */
    private void setTol(Timeoutlog tol) {
        this.tol.add(tol);
    }
    
    
    
    /**
     * Vehicule
     */

    
    
    /**
     * @return the vehicules
     */
    public List<Vehicule> getVehicules() {
        return vehicule;
    }

    /**
     * @param vehicule the vehicules to set
     */
    private void setVehicules(List<Vehicule> vehicule) {
        this.vehicule = vehicule;
    }
    
    /**
     * @return the vehicule at i
     */
    public Vehicule getVehicule(int i) {
        return vehicule.get(i);
    }

    /**
     * @param vehicule the vehicule to set
     */
    private void setVehicule(Vehicule vehicule) {
        this.vehicule.add(vehicule);
    }
    
}
