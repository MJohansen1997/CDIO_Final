package API;


import DAO.DALException;
import DAO.IncrementID;
import DAO.ReceptDAO;
import DTO.BrugerDTO;
import DTO.ReceptDTO;

import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.*;
/** @author Chistensen, Jacob Kjærby (s174130@student.dtu.dk)*/
@Path("/recept")
public class receptService {
    ReceptDAO dbAccess = new ReceptDAO();

    public receptService() throws DALException {
    }



    @GET
    @Path("/allRecepts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReceptDTO> getAllBrugere() throws DALException {
        return dbAccess.getReceptList();
    }

    @POST
    @Path("/createRecept/{receptNavn}")
    public void createRecept(@PathParam("receptNavn") String receptNavn) throws DALException {
        IncrementID incre = new IncrementID();
        ReceptDTO recept = new ReceptDTO(incre.returnID("recepter","recID"),receptNavn);
        dbAccess.createRecept(recept);
    }

    @GET
    @Path("/findRecept/{receptID}")
    @Produces(MediaType.APPLICATION_JSON)
    public ReceptDTO getRecept(@PathParam("receptID") String receptID) throws DALException {
        ReceptDTO recept = dbAccess.getRecept(receptID);
        return recept;
    }

    @POST
    @Path("/updateRecept")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRecept(String jsonBody){
        JSONObject json = new JSONObject(jsonBody);
        try {
            ReceptDTO user = new ReceptDTO(
                    json.getString("receptID"),
                    json.getString("receptNavn"));

            dbAccess.updateRecept(user);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/deleteRecept/{receptID}")
    public void deleteRecept(@PathParam("receptID") String receptID) throws DALException {
        dbAccess.deleteRecept(receptID);
    }


}
