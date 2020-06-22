package API;

import DAO.*;
import DTO.BrugerDTO;

import DTO.ProduktBatchDTO;
import DTO.ProduktBatchKompDTO;
import DTO.RaavareDTO;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;


@Path("/PB")
public class APIProduktBatch  {
    ProduktBatchDAO dbAccess = new ProduktBatchDAO();

    public APIProduktBatch () throws DALException {

    }

    @POST
    @Path("/createPB")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createPB (String jsonbody) throws DALException{
        IncrementID incID = new IncrementID();
        JSONObject json = new JSONObject(jsonbody);

        try {
            //Laver en produktbatch til indsættelse i DB
            ProduktBatchDTO pbatch = new ProduktBatchDTO(
                    incID.returnID("prodbestilling","pbID"),
                    json.getString("chosenStatus"),
                    json.getString("chosenRecept"),
                    new Timestamp(System.currentTimeMillis()));
            //Indsætter i databasen med DAOen
            dbAccess.createProduktBatch(pbatch);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    @PUT
    @Path("/updatePB")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePB(String jsonbody) {
        JSONObject json = new JSONObject(jsonbody);

        try {
            ProduktBatchDTO pbatch = new ProduktBatchDTO(
                    json.getString("pbID"),
                    json.getString("status"),
                    null,
                    null);

            dbAccess.updateProduktBatch(pbatch);
        } catch (DALException e ) {
            e.printStackTrace();
        }

    }


    @GET
    @Path("/findPB/{pbID}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProduktBatchDTO getPB (@PathParam("pbID") String pbID) throws DALException {
        ProduktBatchDTO pbatch = dbAccess.getProduktBatch(pbID);
        return pbatch;
    }

    @POST
    @Path("/deletePB/{pbID}")
    public void deletePB (@PathParam("pbID") String pbID) throws  DALException {
        dbAccess.deleteProduktBatch(pbID);
    }

    @GET
    @Path("/allPB")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProduktBatchDTO> getAllProduktBatches() throws DALException {
        return dbAccess.getProduktBatchList();
    }

    @POST
    @Path("/getPBRec")
    @Produces(MediaType.APPLICATION_JSON)
    public ProduktBatchDTO getProduktBatchPBRecB(@QueryParam("pbID") String pbID, @QueryParam("recID") String recID) throws DALException {
        ProduktBatchDTO fuck = dbAccess.getProduktBatch2IDS(pbID, recID);
        return fuck;
    }

    @POST
    @Path("/IdBatch")
    @Produces(MediaType.APPLICATION_JSON)
    public String verifyIdBatch(@QueryParam("rid") String rid, @QueryParam("rbid") String rbid){
        String r;
        String s;
        try {
            RaavareBatchDAO rbdao = new RaavareBatchDAO();
            RaavareDAO rdao = new RaavareDAO();
            r = rdao.getRaavare(rid).getRaavNavn();
            s = String.valueOf(rid.equals(rbdao.getRaavareBatch(rbid).getRaavId()));
            return "{\"status\":\"" + s + "\",\"name\":\"" + r + "\"}";
        }catch (DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

}
