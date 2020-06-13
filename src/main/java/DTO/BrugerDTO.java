package DTO;

import java.util.List;
/** @author Christensen, Jacob (john.doe@example.com)*/

public class BrugerDTO {

    String brugerID;
    String brugerNavn;
    String password;
    String ini;
    String cpr;
    List<String> roller;

    public BrugerDTO(String brugerID, String brugerNavn, String ini, String cpr, String password) {
        this.brugerID = brugerID;
        this.brugerNavn = brugerNavn;
        this.ini = ini;
        this.cpr = cpr;
        this.password = password;
    }

    public String getBrugerID() {
        return brugerID;
    }

    public String getPassword() {
        return password;
    }

    public String getBrugerNavn() {
        return brugerNavn;
    }

    public String getIni() {
        return ini;
    }

    public String getCpr() {
        return cpr;
    }

    public List<String> getRoller() {
        return roller;
    }
}

