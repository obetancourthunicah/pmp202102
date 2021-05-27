/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicahiccpmp.phonebook.dao;

/**
 *
 * @author obetancourth
 */
public class PhoneBookEntry {

    /**
     * @return the _ID
     */
    public int getID() {
        return _ID;
    }

    /**
     * @param _ID the _ID to set
     */
    public void setID(int _ID) {
        this._ID = _ID;
    }

    /**
     * @return the _NAME
     */
    public String getNAME() {
        return _NAME;
    }

    /**
     * @param _NAME the _NAME to set
     */
    public void setNAME(String _NAME) {
        this._NAME = _NAME;
    }

    /**
     * @return the _PHONE1
     */
    public String getPHONE1() {
        return _PHONE1;
    }

    /**
     * @param _PHONE1 the _PHONE1 to set
     */
    public void setPHONE1(String _PHONE1) {
        this._PHONE1 = _PHONE1;
    }

    /**
     * @return the _PHONE2
     */
    public String getPHONE2() {
        return _PHONE2;
    }

    /**
     * @param _PHONE2 the _PHONE2 to set
     */
    public void setPHONE2(String _PHONE2) {
        this._PHONE2 = _PHONE2;
    }

    /**
     * @return the _EMAIL
     */
    public String getEMAIL() {
        return _EMAIL;
    }

    /**
     * @param _EMAIL the _EMAIL to set
     */
    public void setEMAIL(String _EMAIL) {
        this._EMAIL = _EMAIL;
    }
    
    private int _ID;
    private String _NAME;
    private String _PHONE1;
    private String _PHONE2;
    private String _EMAIL;
    
    public String getFormattedText(){
        return String.format(
                "%d\t%-40s\t%-15s\t%-15s\t%-40s",
                _ID,
                _NAME,
                _PHONE1,
                _PHONE2,
                _EMAIL
        );
    }
}
