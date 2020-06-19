package API;

import DAO.*;
import DTO.BrugerDTO;

import DTO.ProduktBatchDTO;
import DTO.RaavareDTO;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.*;


@Path("/PB")
public class APIProduktBatch  {
    ProduktBatchDAO dbAccess = new ProduktBatchDAO();

    public APIProduktBatch () throws SQLException,DALException,ClassNotFoundException {

    }

    @POST
    @Path("/createPB")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createPB (String jsonbody) throws SQLException, DALException, ClassNotFoundException{
        IncrementID incID = new IncrementID();
        JSONObject json = new JSONObject(jsonbody);

        try {
            //Laver en produktbatch til indsættelse i DB
            ProduktBatchDTO pbatch = new ProduktBatchDTO(
                    incID.returnID("prodbestilling","pbID"),
                    json.getString("chosenStatus"),
                    json.getString("chosenRecept"));
            //Indsætter i databasen med DAOen
            dbAccess.createProduktBatch(pbatch);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    @PUT
    @Path("/updatePB")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePB(String jsonbody) throws SQLException, DALException, ClassNotFoundException {
        JSONObject json = new JSONObject(jsonbody);

        try {
            ProduktBatchDTO pbatch = new ProduktBatchDTO(
                    json.getString("pbID"),
                    json.getString("status"),
                    json.getString("recID"));

            dbAccess.updateProduktBatch(pbatch);
        } catch (DALException e ) {
            e.printStackTrace();
        }

    }


    @GET
    @Path("/findPB/{pbID}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProduktBatchDTO getPB (@PathParam("pbID") String pbID) throws SQLException, DALException, ClassNotFoundException {
        ProduktBatchDTO pbatch = dbAccess.getProduktBatch(pbID);
        return pbatch;
    }

//    @POST
//    @Path("/deletePB/{pbID}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public void deletePB (@PathParam("pbID") String pbID) throws SQLException, DALException, ClassNotFoundException {
//
//    }

    @GET
    @Path("/allPB")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProduktBatchDTO> getAllBrugere() throws SQLException, DALException, ClassNotFoundException {
        return dbAccess.getProduktBatchList();
    }


}
