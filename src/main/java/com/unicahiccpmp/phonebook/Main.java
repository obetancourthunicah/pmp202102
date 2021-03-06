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
                    eliminarRegistro();
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
        //Patch por que nextInt no lee \n asi que se debe correr nextLine para evitar el no poder encontrar el registro
        entradaTeclado.nextLine();
        // Con el ID se debe extraer la data del registro;
        PhoneBookEntry registroAActualizar = miConexion.obtenerUnRegistro(idRegistro);
        
        
        if (registroAActualizar.getID() > 0){
            System.out.println(SeparadorDeLinea);
            System.out.println(registroAActualizar.obtenerTextoConFormato());
            System.out.println(SeparadorDeLinea);
            //Capturar Datos para actualizar registro
            System.out.println("Name (" + registroAActualizar.getNAME() + "):");
            String _NAME = entradaTeclado.nextLine();
            // ! negacion   
            // boolean algo = true;
            // algo != algo; // false
            if (!_NAME.isEmpty() && !_NAME.equals(registroAActualizar.getNAME())){
                registroAActualizar.setNAME(_NAME);
            }
            
            System.out.println("Phone 1 (" + registroAActualizar.getPHONE1()+ "):");
            String _PHONE1 = entradaTeclado.nextLine();
            if (!_PHONE1.isEmpty() && !_PHONE1.equals(registroAActualizar.getPHONE1())){
                registroAActualizar.setPHONE1(_PHONE1);
            }
            
            System.out.println("Phone 2 (" + registroAActualizar.getPHONE2() + "):");
            String _PHONE2 = entradaTeclado.nextLine();
            if (!_PHONE2.isEmpty() && !_PHONE2.equals(registroAActualizar.getPHONE2())){
                registroAActualizar.setPHONE2(_PHONE2);
            }
            
            
            System.out.println("Email (" + registroAActualizar.getEMAIL() + "):");
            String _EMAIL = entradaTeclado.nextLine();
            if (!_EMAIL.isEmpty() && !_EMAIL.equals(registroAActualizar.getEMAIL())){
                registroAActualizar.setEMAIL(_EMAIL);
            }
            
            System.out.println(registroAActualizar.obtenerTextoConFormato());
            miConexion.actualizarRegistro(registroAActualizar);
            System.out.println("Registro Actualizado!!! Presione Enter para Continuar.");
        } else {
            System.out.println("No existe Registro !!! Presione Enter para Continuar.");
        }
        
        entradaTeclado.nextLine();
    }
    public static void eliminarRegistro(){
        System.out.println("Escriba el código del registro a eliminar:");
        int idRegistro = entradaTeclado.nextInt();
        //Patch por que nextInt no lee \n asi que se debe correr nextLine para evitar el no poder encontrar el registro
        entradaTeclado.nextLine();
        // Con el ID se debe extraer la data del registro;
        PhoneBookEntry registroAEliminar = miConexion.obtenerUnRegistro(idRegistro);
        if (registroAEliminar.getID() > 0) {
            System.out.println(SeparadorDeLinea);
            System.out.println(registroAEliminar.obtenerTextoConFormato());
            System.out.println(SeparadorDeLinea);

            System.out.println("¿Desea Eliminar el registro? (S/N):");
            String opcion = entradaTeclado.nextLine();
            if (opcion.toUpperCase().equals("S")){
                miConexion.eliminarRegistro(registroAEliminar);
                System.out.println("Registro Eliminado. Presione Enter para continuar.");
            } else {
                System.out.println("Operación cancelada. Presione Enter para continuar.");
            }
        } else {
            System.out.println("No existe Registro !!! Presione Enter para Continuar.");
        }
        
        entradaTeclado.nextLine();
    }
}
