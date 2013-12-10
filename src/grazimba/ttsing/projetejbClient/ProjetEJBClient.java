/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

enum PROGRAM_CLIENT {FIRE, POLICE};


/**
 *
 * @author grazimba & ttsing
 */
public class ProjetEJBClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        _typeProgram = PROGRAM_CLIENT.POLICE;
        _mw = new MainWindow();
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

    
    public static PROGRAM_CLIENT getTypeProgram() {
        return _typeProgram;
    }
    
    
    /**
     * Main attributes
     */
    private static MainWindow _mw;
    private static Ressources _ressource;
    private static ThreadMAJ _tMAJ;
    private static Controller _cont;
    private static PROGRAM_CLIENT _typeProgram;
    
}