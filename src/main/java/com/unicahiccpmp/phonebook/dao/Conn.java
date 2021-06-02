/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicahiccpmp.phonebook.dao;

import java.sql.*;
import java.util.ArrayList;



/**
 *
 * @author obetancourth
 */
public class Conn {
    Connection c = null;
    public void obtenerConeccion(){
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:phonebook.db");
            String SQLCrearTabla = "CREATE TABLE IF NOT EXISTS phones"
                    + " (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + " NAME TEXT NOT NULL,"
                    + " PHONE1 TEXT NOT NULL,"
                    + " PHONE2 TEXT NOT NULL,"
                    + " EMAIL TEXT NOT NULL"
                    + ")";
            Statement comandoSql = c.createStatement();
            comandoSql.executeUpdate(SQLCrearTabla);
            comandoSql.close();
            
        } catch ( Exception e) {
            System.err.println(" Error " + e.getMessage());
            System.exit(0);
        }
    }
    
    public ArrayList<PhoneBookEntry> obtenerRegistros(){
        try{
            if (c == null) {
                obtenerConeccion();
            }
            Statement comandoSql = c.createStatement();
            ResultSet rs = comandoSql.executeQuery("SELECT * FROM phones;");
            ArrayList<PhoneBookEntry> todosMisRegistros = new ArrayList<PhoneBookEntry>();
            while(rs.next()){
                PhoneBookEntry registroIterando = new PhoneBookEntry();
                registroIterando.setID(rs.getInt("ID"));
                registroIterando.setNAME(rs.getString("NAME"));
                registroIterando.setPHONE1(rs.getString("PHONE1"));
                registroIterando.setPHONE2(rs.getString("PHONE2"));
                registroIterando.setEMAIL(rs.getString("EMAIL"));
                
                todosMisRegistros.add(registroIterando);
                
            }
            comandoSql.close();
            return todosMisRegistros;
        } catch( Exception e) {
            System.err.println(" Error " + e.getMessage());
            System.exit(0);
            return null;
        }
    }
    public void agregarNuevoRegistro(PhoneBookEntry newEntry){
        try {
        String sentenciaSql = "INSERT INTO phones (NAME, PHONE1, PHONE2, EMAIL) valueS ('%s','%s','%s','%s');";
        Statement comandoSql = c.createStatement();
        comandoSql.executeUpdate(String.format(sentenciaSql,
                newEntry.getNAME(),
                newEntry.getPHONE1(),
                newEntry.getPHONE2(),
                newEntry.getEMAIL()
            )
        );
        comandoSql.close();
        } catch (Exception e) {
            System.err.println(" Error " + e.getMessage());
            System.exit(0);
        }
    }
    public PhoneBookEntry obtenerUnRegistro( int idRegistro) {
        try{
            String setenciaSql = "SELECT * from phones where ID=%d;";
            Statement comandoSql = c.createStatement();
            ResultSet cursorDeRegistro = comandoSql.executeQuery(
                String.format(setenciaSql, idRegistro)
            );
            PhoneBookEntry miRegistro = new PhoneBookEntry();
            while ( cursorDeRegistro.next()){
                miRegistro.setID(cursorDeRegistro.getInt("ID"));
                miRegistro.setNAME(cursorDeRegistro.getString("NAME"));
                miRegistro.setPHONE1(cursorDeRegistro.getString("PHONE1"));
                miRegistro.setPHONE2(cursorDeRegistro.getString("PHONE2"));
                miRegistro.setEMAIL(cursorDeRegistro.getString("EMAIL"));
            }
            return miRegistro;
        } catch (Exception e) {
            System.err.println(" Error " + e.getMessage());
            System.exit(0);
            return null;
        }
    }
}
