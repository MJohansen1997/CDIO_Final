package API;

import DAO.DALException;
import DAO.ProduktBatchDAO;
import DAO.ReceptDAO;
import DAO.ReceptKompDAO;
import DTO.ProduktBatchDTO;
import DTO.ReceptDTO;
import DTO.ReceptKompDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/afvejning")
public class AfvejningService {

    @POST
    @Path("/verifylab")
    public boolean verifyLaborant(@FormParam("userid") String labid){

        return true;
    }

    @POST
    @Path("/verifyprod")
    @Produces(MediaType.APPLICATION_JSON)
    public ReceptDTO verifyProdID(@FormParam("produktionsid") String prodID){
        try {
            ProduktBatchDAO PDAO = new ProduktBatchDAO();
            ProduktBatchDTO PB = PDAO.getProduktBatch(prodID);
            ReceptDAO RDAO = new ReceptDAO();
            return RDAO.getRecept(PB.getReceptID());
        }
        catch (ClassNotFoundException | DALException | SQLException e){
            return null;
        }
    }

    @POST
    @Path("/loadkomps")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReceptKompDTO> loadKomps(@FormParam("receptid") String recid){
        try {
            ReceptKompDAO dao = new ReceptKompDAO();
            return dao.getReceptKompList(recid);
        } catch (DALException e) {
            e.printStackTrace();
            return null;
        }
    }
}
