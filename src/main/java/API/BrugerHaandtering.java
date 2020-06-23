package API;

import DAO.BrugerDAO;
import DAO.DALException;
import DAO.IncrementID;
import DTO.BrugerDTO;

import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.*;
/** @author Chistensen, Jacob Kjærby (s174130@student.dtu.dk)*/
@Path("/brugere")
public class BrugerHaandtering {
    BrugerDAO dbAccess = new BrugerDAO();

    public BrugerHaandtering() throws SQLException, DALException, ClassNotFoundException {
    }


    @GET
    @Path("/allUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BrugerDTO> getAllBrugere() throws SQLException, DALException, ClassNotFoundException {
        return dbAccess.getBrugerList();
    }

    @POST
    @Path("/createUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createBruger(String jsonBody) throws SQLException, DALException, ClassNotFoundException {
        IncrementID incre = new IncrementID();
        JSONObject json = new JSONObject(jsonBody);

        try {
            BrugerDTO user = new BrugerDTO(
                    incre.returnID("brugerer", "brugerID"),
                    json.getString("brugerNavn"),
                    json.getString("ini"),
                    json.getString("cpr"),
                    json.getString("rolle"),
                    json.getString("password"));

            dbAccess.createBruger(user);

        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/updateUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateBruger(String jsonBody){
        JSONObject json = new JSONObject(jsonBody);

        System.out.println(json.get("brugerNavn"));

        try {
            BrugerDTO user = new BrugerDTO(
                    json.getString("brugerID"),
                    json.getString("brugerNavn"),
                    json.getString("ini"),
                    json.getString("cpr"),
                    json.getString("rolle"),
                    json.getString("password"));

            dbAccess.updateBruger(user);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/findBruger/{brugerID}")
    @Produces(MediaType.APPLICATION_JSON)
    public BrugerDTO getBruger(@PathParam("brugerID") String brugerID) throws ClassNotFoundException, DALException, SQLException {
        BrugerDTO user = dbAccess.getBruger(brugerID);
        return user;
    }

    @POST
    @Path("/deleteBruger/{brugerID}")
    public void deleteBruger(@PathParam("brugerID") String brugerID) throws ClassNotFoundException, DALException, SQLException {
        dbAccess.deleteUser(brugerID);
    }
}