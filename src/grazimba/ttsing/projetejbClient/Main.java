/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

/**
 *
 * @author grazimba
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        _ressource = new Ressources();
        _cont = new Controller();
        _tMAJ = new ThreadMAJ();
        
        _mw = new MainWindow();
        _mw.launch();
        while(!_mw.isVisible()){
            try {
                Thread.sleep(5000);
            }
            catch(Exception e)
            {
                System.err.println("Caught ThreadException: " + e.getMessage());
            }
        }
        
        System.out.println("quit");
        
        _tMAJ.setActive(false);
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
    
    
    /**
     * Main attributes
     */
    private static MainWindow _mw;
    private static Ressources _ressource;
    private static ThreadMAJ _tMAJ;
    private static Controller _cont;
    
}
