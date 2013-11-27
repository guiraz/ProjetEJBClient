/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grazimba.ttsing.projetejbClient;

/**
 *
 * @author grazimba
 */
public class Controller {
    
    public Controller() {     
        Main.getThreadMAJ().run();
        System.out.println("thread launched");
        Main.getView().launch();
        System.out.println("view launched");
    }
    
    public void exitQuery() {
        Main.getView().dispose();
        Main.getThreadMAJ().setActive(false);
    }
    
}
