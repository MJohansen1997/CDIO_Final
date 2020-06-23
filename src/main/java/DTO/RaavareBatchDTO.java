package DTO;

public class RaavareBatchDTO {
     // Raavare batch id

    private String rbID;
    private String raavID;
    private double maengde;

    public RaavareBatchDTO(String rbID, String raavID, double maengde) {
        this.rbID = rbID;
        this.raavID = raavID;
        this.maengde = maengde;
    }

    public void setRbID(String rbID) {
        this.rbID = rbID;
    }

    public void setRaavId(String raavID) {
        this.raavID = raavID;
    }

    public void setMaengde(double maengde) {
        this.maengde = maengde;
    }

    public String getRbID() {
        return rbID;
    }

    public String getRaavId() {
        return raavID;
    }

    public double getMaengde() {
        return maengde;
    }




}
