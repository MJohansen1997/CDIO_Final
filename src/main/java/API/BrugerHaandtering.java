package API;

import DAO.BrugerDAO;
import DAO.DALException;
import DAO.IncrementID;
import DTO.BrugerDTO;
import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.annotation.PostConstruct;

import org.json.JSONException;
import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/brugere")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BrugerHaandtering {
    BrugerDAO dbAccess = new BrugerDAO();

    public BrugerHaandtering() throws SQLException, DALException, ClassNotFoundException {
    }


    @GET
    @Path("/allUsers")
    public List<BrugerDTO> getAllBrugere() throws SQLException, DALException, ClassNotFoundException {
        return dbAccess.getBrugerList();
    }

    @POST
    @Path("/createUser")
    public void createBruger(String jsonBody) throws SQLException, DALException, ClassNotFoundException {
        IncrementID incre = new IncrementID();
        JSONObject json = new JSONObject(jsonBody);

        try{
            BrugerDTO user = new BrugerDTO(
                    incre.returnID("brugerer","brugerID"),
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
    }



