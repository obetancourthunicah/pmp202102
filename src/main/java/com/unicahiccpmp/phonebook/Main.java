/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicahiccpmp.phonebook;

import com.unicahiccpmp.phonebook.dao.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author obetancourth
 */
public class Main {
    private static String inputData = "R";
    private static ArrayList<PhoneBookEntry> myEntries;
    private static Conn myConnection;
    private static Scanner keyInput;
    private static String LineSeperator;
    public static void main(String args[]) {
        
        myConnection = new Conn();
        myConnection.getConnection();
        keyInput = new Scanner(System.in);
        LineSeperator = new String(new char[118]).replace("\0", "-");
        
        System.out.println(LineSeperator);
        System.out.println("Iniciando Proyecto");
        System.out.println(LineSeperator);
        
        while (!inputData.equalsIgnoreCase("Q")) {
            System.out.println();
            switch (inputData.toUpperCase()) {
                case "R": // refresh
                    
                    break;
                case "N": // new
                    createNew();
                    break;
                case "U": // update
                    
                    break;
                case "D": //Delete
                    
                    break;
            }
            showEntries();
            showMenu();
        } 
    }
  
    public static void showMenu(){
        System.out.println(LineSeperator);
        System.out.println("Q Quit\tR Reload\tN New\tU Update\tD Delete \n Press Key and Enter: ");
        inputData = keyInput.nextLine();
    }
    public static void showEntries(){
        myEntries = myConnection.getAllPhoneBookEntry();
        
        System.out.println(LineSeperator);
        System.out.println(String.format("%s\t%-40s\t%-15s\t%-15s\t%-40s", "#","NAME","PHONE 1", "PHONE 2", "EMAIL"));
        System.out.println(LineSeperator);
        // foreach item in arraylist
        for (PhoneBookEntry _entry : myEntries) { // pod cada elemento en myEntries asignelo a _entry interando
            System.out.println(_entry.getFormattedText());
        }
        /*
        for (int i = 0; i < myEntries.size(); i++){
            System.out.println(myEntries.get(i).getFormattedText());
        }
        */
        System.out.println(LineSeperator);
        System.out.println(String.format("Total Rows: %d",myEntries.size()));
        
    }
    public static void createNew(){
        System.out.println();
        System.out.println("Getting New Data");
        System.out.println(LineSeperator);
        System.out.println("Name: ");
        String _NAME = keyInput.nextLine();
        System.out.println("Phone 1: ");
        String _PHONE1 = keyInput.nextLine();
        System.out.println("Phone 2: ");
        String _PHONE2 = keyInput.nextLine();
        System.out.println("Email: ");
        String _EMAIL = keyInput.nextLine();
        PhoneBookEntry newEntry = new PhoneBookEntry();
        newEntry.setNAME(_NAME);
        newEntry.setPHONE1(_PHONE1);
        newEntry.setPHONE2(_PHONE2);
        newEntry.setEMAIL(_EMAIL);
        
        myConnection.addNewPhoneBookeEntry(newEntry);
        
        System.out.println();
        
    }
}
