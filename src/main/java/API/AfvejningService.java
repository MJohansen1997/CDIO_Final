package API;

import DAO.DALException;
import DAO.ProduktBatchDAO;
import DTO.ProduktBatchDTO;
import DTO.ReceptDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/afvejning")
public class AfvejningService {

    @POST
    @Path("/verifylab")
    public boolean verifyLaborant(@FormParam("userid") String labid){

        return true;
    }

    @POST
    @Path("/verifyprod")
    public String verifyProdID(@FormParam("produktionsid") String prodID){
        ProduktBatchDTO PB = new ProduktBatchDTO();
        try {
            ProduktBatchDAO PDAO = new ProduktBatchDAO();
            PB = PDAO.getProduktBatch(prodID);
        }
        catch (ClassNotFoundException | DALException | SQLException e){
            return "";
        }
        return PB.getReceptID();
    }

    @Produces
    @POST
    @Path("/getrecept")
    public ReceptDTO getRecept(@FormParam("receptid") String recid){

        return null;
    }
}
