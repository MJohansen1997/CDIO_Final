package API;

import DAO.*;
import DTO.ProduktBatchKompDTO;
import DTO.RaavareBatchDTO;
import DTO.RaavareDTO;
import DTO.ReceptKompDTO;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/RaavareBatch")
public class APIRaavareBatch {
    RaavareBatchDAO RBDAO = new RaavareBatchDAO();
    RaavareDAO RDAO = new RaavareDAO();

    public APIRaavareBatch() throws SQLException, DALException, ClassNotFoundException {
    }


    @POST
    @Path("/verifyProjektleder")
    public boolean verifyProjektleder(@FormParam("userid") String ProLederID) {

        return true;
    }


    @POST
    @Path("/verifyRB")
    @Produces(MediaType.APPLICATION_JSON)
    public RaavareDTO verifyRaavareID(@FormParam("rbid") String rbid) {
        try {
            RaavareBatchDAO RBDAO = new RaavareBatchDAO();
            RaavareBatchDTO RBDTO = RBDAO.getRaavareBatch(rbid);
            RaavareDAO RaaDAO = new RaavareDAO();
            return RaaDAO.getRaavare(RBDTO.getRaavareId());

        } catch (DALException e) {
            return null;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    @GET
    @Path("/getrid")
    public String getRaaID(String rid){
        try{
            RaavareBatchDAO rbdao = new RaavareBatchDAO();
            RaavareDAO rdao = new RaavareDAO();
            return rdao.getRaavare(rbdao.getRaavareBatch(rid).getRaavareId()).getRaavareID();
        } catch (DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "????";
        }
    }


    @GET
    @Path("/getmaengde")
    public double getMaengde{
        try{

        } catch (DALException | SQLException | ClassNotFoundException e) {

        }
    }

    @POST
    @Path("/IdBatch")
    public String verifyIdBatch(@QueryParam("rid") String rid, @QueryParam("rbid") String rbid){
        try {
            RaavareBatchDAO rbdao = new RaavareBatchDAO();
            RaavareDAO rdao = new RaavareDAO();
            if(!rid.equals(rbdao.getRaavareBatch(rbid).getRaavareId()))
                return rdao.getRaavare(rid).getRaavareNavn();
            else return "";
        }catch (DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }


    @POST
    @Path("/createRB")
    public void createRaavareBatch(String jsonBody){

        try {
            IncrementID incre = new IncrementID();
            JSONObject json = new JSONObject(jsonBody);
            RaavareBatchDAO raavareBatchDAOdao = new RaavareBatchDAO();

        } catch (DALException e) {
            e.printStackTrace();
        }
    }






    @POST
    @Path("/loadprodkomps")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProduktBatchKompDTO> loadprodKomps(@FormParam("produktionsid") String prodid){
        try {
            ProduktBatchKompDAO dao = new ProduktBatchKompDAO();
            return dao.getProduktBatchKompList(prodid);
        } catch (DALException e) {
            e.printStackTrace();
            return null;
        }
    }

    @POST
    @Path("/loadreckomps")
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

