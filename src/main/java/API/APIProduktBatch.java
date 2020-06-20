package API;

import DAO.*;
import DTO.BrugerDTO;

import DTO.ProduktBatchDTO;
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
}
