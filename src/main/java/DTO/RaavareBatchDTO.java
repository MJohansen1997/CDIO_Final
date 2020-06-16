package DTO;

public class RaavareBatchDTO {
     // Raavare batch id
    String rbID;
     // Raavare id
    String raavareId;
     // Mængde på lager
    double maengde;

    public RaavareBatchDTO(String rbID, String raavareId, double maengde) {
        this.rbID = rbID;
        this.raavareId = raavareId;
        this.maengde = maengde;
    }

    public RaavareBatchDTO(){}

    public void setRbID(String rbID) {
        this.rbID = rbID;
    }

    public void setRaavareId(String raavareId) {
        this.raavareId = raavareId;
    }

    public void setMaengde(double maengde) {
        this.maengde = maengde;
    }

    public String getRbID() {
        return rbID;
    }

    public String getRaavareId() {
        return raavareId;
    }

    public double getMaengde() {
        return maengde;
    }




}
