/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lukuvinkkikirjasto;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;

/**
 *
 * @author miklas
 */
                
public class Main {

    /**
     * @param args the command line arguments
     */
                
    public static void main(String[] args) {

        Storage storage = new Storage();

        Scanner input = new Scanner(System.in);

        TextUI ui = new TextUI(input, storage);
        ui.run();
        
        

    }
    
}
