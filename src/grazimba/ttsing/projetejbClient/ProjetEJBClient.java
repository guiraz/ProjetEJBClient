/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

/**
 *
 * @author grazimba
 */
public class ProjetEJBClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        _mw = new MainWindow();
        _model = new Model();
        _tMAJ = new ThreadMAJ();
        _cont = new Controller();
        _ressource = new Ressources();
    }
    
    public static MainWindow getView() {
        return _mw;
    }
    
    public static Ressources getRessource() {
        return _ressource;
    }
    
    public static Controller getCont() {
        return _cont;
    }
    
    public static ThreadMAJ getThreadMAJ() {
        return _tMAJ;
    }
    
    public static Model getModel() {
        return _model;
    }
    
    
    /**
     * Main attributes
     */
    private static MainWindow _mw;
    private static Ressources _ressource;
    private static ThreadMAJ _tMAJ;
    private static Controller _cont;
    private static Model _model;
    
}
