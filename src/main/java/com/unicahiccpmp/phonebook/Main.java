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
    private static String opcionMenu = "R";
    private static ArrayList<PhoneBookEntry> misRegistros;
    private static Conn miConexion;
    private static Scanner entradaTeclado;
    private static String SeparadorDeLinea;
    public static void main(String args[]) {
        
        miConexion = new Conn();
        miConexion.obtenerConeccion();
        entradaTeclado = new Scanner(System.in);
        SeparadorDeLinea = new String(new char[118]).replace("\0", "-");
        
        System.out.println(SeparadorDeLinea);
        System.out.println("Iniciando Proyecto");
        System.out.println(SeparadorDeLinea);
        
        while (!opcionMenu.equalsIgnoreCase("Q")) {
            System.out.println();
            switch (opcionMenu.toUpperCase()) {
                case "R": // refresh
                    
                    break;
                case "N": // new
                    crearNuevoRegistro();
                    break;
                case "U": // update
                    actualizarRegistro();
                    break;
                case "D": //Delete
                    
                    break;
            }
            mostrarRegistros();
            mostrarMenu();
        } 
    }
  
    public static void mostrarMenu(){
        System.out.println(SeparadorDeLinea);
        System.out.println("Q Salir\tR Recargar\tN Nuevo\tU Actualizar\tD Eliminat\n Presione la opción y Enter: ");
        opcionMenu = entradaTeclado.nextLine();
    }
    public static void mostrarRegistros(){
        misRegistros = miConexion.obtenerRegistros();
        
        System.out.println(SeparadorDeLinea);
        System.out.println(String.format("%s\t%-40s\t%-15s\t%-15s\t%-40s", "#","NAME","PHONE 1", "PHONE 2", "EMAIL"));
        System.out.println(SeparadorDeLinea);
        // foreach item in arraylist
        for (PhoneBookEntry _registro : misRegistros) { // pod cada elemento en myEntries asignelo a _entry interando
            System.out.println(_registro.obtenerTextoConFormato());
        }
        /*
        for (int i = 0; i < myEntries.size(); i++){
            System.out.println(myEntries.get(i).getFormattedText());
        }
        */
        System.out.println(SeparadorDeLinea);
        System.out.println(String.format("Total Rows: %d",misRegistros.size()));
        
    }
    public static void crearNuevoRegistro(){
        System.out.println();
        System.out.println("Getting New Data");
        System.out.println(SeparadorDeLinea);
        System.out.println("Name: ");
        String _NAME = entradaTeclado.nextLine();
        System.out.println("Phone 1: ");
        String _PHONE1 = entradaTeclado.nextLine();
        System.out.println("Phone 2: ");
        String _PHONE2 = entradaTeclado.nextLine();
        System.out.println("Email: ");
        String _EMAIL = entradaTeclado.nextLine();
        PhoneBookEntry nuevoRegistro = new PhoneBookEntry();
        nuevoRegistro.setNAME(_NAME);
        nuevoRegistro.setPHONE1(_PHONE1);
        nuevoRegistro.setPHONE2(_PHONE2);
        nuevoRegistro.setEMAIL(_EMAIL);
     
        miConexion.agregarNuevoRegistro(nuevoRegistro);
        
        System.out.println();
        
    }
    public static void actualizarRegistro(){
        //Primero Obtener el ID de un registro
        System.out.println("Escriba el código del registro a actualizar:");
        int idRegistro = entradaTeclado.nextInt();
        // Con el ID se debe extraer la data del registro;
        PhoneBookEntry registroAActualizar = miConexion.obtenerUnRegistro(idRegistro);
        System.out.println(SeparadorDeLinea);
        System.out.println(registroAActualizar.obtenerTextoConFormato());
        System.out.println(SeparadorDeLinea);
        
        if (registroAActualizar.getID() > 0){
            //Capturar Datos para actualizar registro
            System.out.println("Name (" + registroAActualizar.getNAME() + "):");
            String _NAME = entradaTeclado.nextLine();
            if (_NAME.isEmpty() && !_NAME.equals(registroAActualizar.getNAME())){
                registroAActualizar.setNAME(_NAME);
            }
            System.out.println("Phone 1 (" + registroAActualizar.getPHONE1()+ "):");
            String _PHONE1 = entradaTeclado.nextLine();
            System.out.println("Phone 2 (" + registroAActualizar.getPHONE2() + "):");
            String _PHONE2 = entradaTeclado.nextLine();
            System.out.println("Email (" + registroAActualizar.getEMAIL() + "):");
            String _EMAIL = entradaTeclado.nextLine();
        }
        System.out.println(registroAActualizar.obtenerTextoConFormato());
        String variableHuerfana = entradaTeclado.nextLine();
    }
}
