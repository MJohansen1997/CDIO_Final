package API;

import DAO.*;
import DTO.*;
import org.json.JSONObject;

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
    @Path("/getrnavn")
    public String getRaaNavn(String rid){
        try{
            RaavareBatchDAO rbdao = new RaavareBatchDAO();
            RaavareDAO rdao = new RaavareDAO();
            return rdao.getRaavare(rbdao.getRaavareBatch(rid).getRaavareId()).getRaavareNavn();
        } catch (DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "????";
        }
    }
    @POST
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

    @POST
    @Path("/createpbk")
    public void createProdBatchKomp(String jsonBody){

        try {
            IncrementID incre = new IncrementID();
            JSONObject json = new JSONObject(jsonBody);
            ProduktBatchKompDAO pbdao = new ProduktBatchKompDAO();
            //ProduktBatchKompDTO pb = new ProduktBatchKompDTO(json.getString("pbId"),json.getString("rbId"),);


        } catch (DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
