package DTO;

/** @author Christensen, Jacob Kjærby (john.doe@example.com)*/

public class BrugerDTO {

    private String brugerID;
    private String brugerNavn;
    private String ini;
    private String cpr;
    private String rolle;
    private String password;

    public BrugerDTO(String brugerID, String brugerNavn, String ini, String cpr, String rolle, String password) {
        this.brugerID = brugerID;
        this.brugerNavn = brugerNavn;
        this.ini = ini;
        this.cpr = cpr;
        this.rolle = rolle;
        this.password = password;
    }

    public String getBrugerID() {return brugerID;}

    public void setBrugerID(String brugerID) {this.brugerID = brugerID;}

    public String getBrugerNavn() {return brugerNavn; }

    public void setBrugerNavn(String brugerNavn) {this.brugerNavn = brugerNavn; }

    public String getIni() {return ini; }

    public void setIni(String ini) {this.ini = ini; }

    public String getCpr() {return cpr; }

    public void setCpr(String cpr) {this.cpr = cpr; }

    public String getRolle() {return rolle; }

    public void setRolle(String cpr) {this.rolle = rolle; }

    public String getPassword() {return password; }

    public void setPassword(String password) {this.password = password;}
}

