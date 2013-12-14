/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

import grazimba.ttsing.projetejb.*;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author grazimba & ttsing
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
            _jndi_context = new javax.naming.InitialContext();
            
            _vf = (VehiculeFacadeRemote) _jndi_context.lookup("ejb/VehiculeFacade");
            _crise = (CrisisFacadeRemote) _jndi_context.lookup("ejb/CrisisFacade");
            _tl = (TimeoutlogFacadeRemote) _jndi_context.lookup("ejb/TimeoutlogFacade");
            _rp = (RouteplanFacadeRemote) _jndi_context.lookup("ejb/RouteplanFacade");
            _rt = (RouteFacadeRemote) _jndi_context.lookup("ejb/RouteFacade");
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
    public void UpdateRessources() {
        setCrises(_crise.findAll());
        setVehicules(_vf.findAll());
        setTols(_tl.findAll());
        setRoutes(_rt.findAll());
        setToutePlans(_rp.findAll());
        ProjetEJBClient.getCont().RessourcesUpdated();
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
    }
    
    public void AddRoute(Route r){
        _rt.create(r);
        Routeplan tmpRP = getRoutePlanOfCrisis(r.getRoutePK().getIdcrisis());
        if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.FIRE)
            tmpRP.setNbfirevehicule(tmpRP.getNbfirevehicule()+1);
        else
            tmpRP.setNbpolicevehicule(tmpRP.getNbpolicevehicule()+1);
        _rp.edit(tmpRP);
        UpdateRessources();
    }
    
    public void RemoveRouteByVehicule(String idV){
        if((getVehiculesById(idV).getType().equals("Fire") && ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.FIRE) || (getVehiculesById(idV).getType().equals("Police") && ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.POLICE)) {
            Route tmpR = getRouteOfVehi(idV);
            _rt.remove(tmpR);
            Routeplan tmpRP = getRoutePlanOfCrisis(tmpR.getRoutePK().getIdcrisis());
            if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.FIRE)
                tmpRP.setNbfirevehicule(tmpRP.getNbfirevehicule()-1);
            else
                tmpRP.setNbpolicevehicule(tmpRP.getNbpolicevehicule()-1);
            _rp.edit(tmpRP);
            Vehicule v = getVehiculesById(idV);
            v.setEta(null);
            _vf.edit(v);
            UpdateRessources();
        }
        else {
            ProjetEJBClient.getCont().ErrorMessage("You can't remove this vehicule!");
        }
    }
    
    public void EditRouteplan(String s){
        Routeplan tmpRP = getRoutePlanOfCrisis(s);
        while(tmpRP.getNomroute()==null || tmpRP.getNomroute().equals(""))
            tmpRP.setNomroute(ProjetEJBClient.getCont().getRouteName());
        _rp.edit(tmpRP);
        UpdateRessources();
    }
    
    public void ComfirmRouteplan(String s){
        Routeplan tmpRP = getRoutePlanOfCrisis(s);
        tmpRP.setComfirm("t");
        _rp.edit(tmpRP);
        UpdateRessources();
    }
    
    public void DeclineRouteplan(String s){
        Routeplan tmpRP = getRoutePlanOfCrisis(s);
        tmpRP.setComfirm("f");
        tmpRP.setNomroute(null);
        _rp.edit(tmpRP);
        UpdateRessources();
    }
    
    public void setCriseClosed(String s) {
        Date d = new Date();
        Crisis tmpC = getCrisisByID(s);
        
        Timeoutlog tmpTL = getTolOfCrisis(s);
        if(tmpTL != null) {
            if(d.getTime() > tmpTL.getD().getTime())
            {
                if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.POLICE){
                    while(tmpTL.getPscreason()==null || tmpTL.getPscreason().equals(""))
                        tmpTL.setPscreason(ProjetEJBClient.getCont().getReasons());
                }
                if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.FIRE){
                    while(tmpTL.getFscreason()==null || tmpTL.getFscreason().equals(""))
                        tmpTL.setFscreason(ProjetEJBClient.getCont().getReasons());
                }
                _tl.edit(tmpTL);
            }
        }
        
        tmpC.setStatut("Closed");
        _crise.edit(tmpC);
        
        for(int i=0; i<routes.size(); i++) {
            if(routes.get(i).getRoutePK().getIdcrisis().equals(tmpC.getIdcrisis()))
                _rt.remove(routes.get(i));
        }
        
        Routeplan tmpRP = getRoutePlanOfCrisis(tmpC.getIdcrisis());
        tmpRP.setNbfirevehicule(0);
        tmpRP.setNbpolicevehicule(0);
        _rp.edit(tmpRP);
    }
    
    public List<Crisis> getActiveCrisis() {
        List<Crisis> crisesActives = new ArrayList<>();
        for(int i=0; i<crises.size(); i++) {
            if(crises.get(i).getStatut().equals("Active"))
                crisesActives.add(crises.get(i));
        }
        return crisesActives;
    }
    
    public Timeoutlog getTolOfCrisis(String id){
        for(int i=0; i<tol.size(); i++) {
            if(tol.get(i).getIdcrisis().equals(id))
                return tol.get(i);
        }
        return null;
    }
    
    public List<Vehicule> getVehiculesOfCrisis(String id) {
        List<Vehicule> listVehi = new ArrayList<>();
        for(int i=0; i<routes.size(); i++) {
            if(routes.get(i).getRoutePK().getIdcrisis().equals(id)) {
                String idV = routes.get(i).getRoutePK().getIdvehicule();
                for(int j=0; j<vehicule.size(); j++) {
                    if(vehicule.get(j).getIdvehicule().equals(idV))
                        listVehi.add(vehicule.get(j));
                }
            }
        }
        return listVehi;
    }
    
    public Routeplan getRoutePlanOfCrisis(String id) {
        Routeplan tmpRP = null;
        int i=0;
        while(i<toutePlan.size() && tmpRP == null){
            if(toutePlan.get(i).getIdcrisis().equals(id))
                tmpRP=toutePlan.get(i);
            else
                i++;
        }
        return tmpRP;
    }
    
    public List<Vehicule> getVehiculesForCrisis() {
        List<Vehicule> vehiForCrisis = new ArrayList<>();
        for(int i=0; i<vehicule.size(); i++) {
            if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.FIRE && vehicule.get(i).getType().equals("Fire")) {
                if(VehiculeDispo(vehicule.get(i).getIdvehicule())) {
                    vehiForCrisis.add(vehicule.get(i));
                }
            }
            if(ProjetEJBClient.getTypeProgram() == PROGRAM_CLIENT.POLICE && vehicule.get(i).getType().equals("Police")) {
                if(VehiculeDispo(vehicule.get(i).getIdvehicule())) {
                    vehiForCrisis.add(vehicule.get(i));
                }
            }
        }
        return vehiForCrisis;
    }
    
    private boolean VehiculeDispo(String id) {
        if(!VehiculeInUse(id))
            return false;
        for(int i=0; i<routes.size(); i++) {
            if(routes.get(i).getRoutePK().getIdvehicule().equals(id))
                return false;
        }
        return true;
    }
    
    public List<String> getFreeVehiculesIds() {
        List<String> tmpVehiIds = new ArrayList<>();
        for(int i=0; i<vehicule.size(); i++) {
            if(!VehiculeInUse(vehicule.get(i).getIdvehicule()))
                tmpVehiIds.add(vehicule.get(i).getIdvehicule());
        }
        return tmpVehiIds;
    }
    
    private boolean VehiculeInUse(String id) {
        for(int i=0; i<vehicule.size(); i++) {
            if(vehicule.get(i).getUsed().equals("t") && vehicule.get(i).getIdvehicule().equals(id))
                return true;
        }
        return false;
    }
    
    public void setVehiculeInUse(String idV, boolean inuse) {
        Vehicule tmpV = getVehiculesById(idV);
        if(inuse)
            tmpV.setUsed("t");
        else
            tmpV.setUsed("f");
        tmpV.setEta(null);
        tmpV.setPosition("Station");
        removeRoutesOfVehicule(idV);
        _vf.edit(tmpV);
    }
    
    private void removeRoutesOfVehicule(String idV) {
        List<Route> tmpRts = new ArrayList<>();
        for(int i=0; i<routes.size(); i++) {
            if(routes.get(i).getRoutePK().getIdvehicule().equals(idV))
                tmpRts.add(routes.get(i));
        }
        for(int i=0; i<tmpRts.size(); i++) {
            _rt.remove(tmpRts.get(i));
        }
    }
    
    public void setVehiculePosition(String idV, int pos) {
        Vehicule tmpV = getVehiculesById(idV);
        switch (pos) {
            case 0:
                tmpV.setPosition("Station");
                break;
                
            case 1:
                tmpV.setPosition("ERTL");
                break;
                
            case 2:
                tmpV.setPosition("AL");
                break;
                
            case 3:
                tmpV.setPosition("ERTS");
                break;
        }
        _vf.edit(tmpV);
    }
    
    public Route getRouteOfVehi(String idV) {
        Route tmpR = null;
        int i =0;
        while(i<routes.size() && tmpR==null) {
            if(routes.get(i).getRoutePK().getIdvehicule().equals(idV))
                tmpR = routes.get(i);
            else
                i++;
        }
        return tmpR;
    }
    
    public Vehicule getVehiculesById(String idV) {
        Vehicule tmpVehi = null;
        int i=0; 
        while(i<vehicule.size() && tmpVehi==null) {
            if(vehicule.get(i).getIdvehicule().equals(idV))
                tmpVehi=vehicule.get(i);
            else
                i++;
        }
        return tmpVehi;
    }

    public boolean VehiculeIdDispo(String idV){
        boolean find=false;
        for(int i = 0 ; i < ProjetEJBClient.getRessource().getVehicules().size(); i++){
                if(ProjetEJBClient.getRessource().getVehicule(i).getIdvehicule().equals(idV))
                    find=true;
        }
        return find;
    }
    
    public Crisis getCrisisByID(String idC) {
        Crisis tmpCrisis = null;
        int i=0;
        while (i<crises.size() && tmpCrisis==null) {
            if(crises.get(i).getIdcrisis().equals(idC)) {
                tmpCrisis = crises.get(i);
            }
            else {
                i++;
            }
        }
        return tmpCrisis;
    }
    
    public void setVehiculeETA(String idV) {
        String tmpMinute = null;
        long minutes=0;
        while(tmpMinute == null) {
            tmpMinute = ProjetEJBClient.getCont().getETA();
            try {
                minutes = Long.parseLong(tmpMinute);
                if(minutes<1 || minutes>1000)
                    throw new Exception();
            }
            catch(Exception e) {
                ProjetEJBClient.getCont().ErrorMessage("Input must be a long type between 1 and 1000!");
                tmpMinute = null;
            }
        }
        
        Date tmpETA = new Date();
        tmpETA.setTime(tmpETA.getTime() + minutes*60000);
        Vehicule tmpV = getVehiculesById(idV);
        tmpV.setEta(tmpETA);
        _vf.edit(tmpV);
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
     * @param i
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
     * @param i
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
     * @param i
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
     * @param i
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
     * @param i
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
