/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lukuvinkkikirjasto;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
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

        ArrayList<Tip> tipi = new ArrayList<>();

        Scanner syote = new Scanner(System.in);

        while(true) {

            System.out.println("Let´s create a tip. First insert a header:\n");

            String name = syote.nextLine();

            System.out.println("Insert a description:\n");

            String description = syote.nextLine();

            System.out.println("Insert the creator´s name:\n");

            String creator = syote.nextLine();

            System.out.println("Insert url:\n");

            String URL = syote.nextLine();

            System.out.println("Insert type:\n");

            String type = syote.nextLine();

            System.out.println("Insert a tags and separate with a ',':\n");

            String tags = syote.nextLine();

            System.out.println("Insert a comment:\n ");

            String comment = syote.nextLine();

            System.out.println("Insert a courses and separate with a ',':\n");

            String courses = syote.nextLine();

            System.out.println("Write 'exit' if you wish to stop otherwise let´s create a new one");

            Tip tiptap = new Tip(name, description,creator, URL,type, tags, comment, courses );

            tipi.add(tiptap);
            System.out.println(tipi);

            if(syote.nextLine().equals("exit")){
                break;
            }

        }

    }
    
}
