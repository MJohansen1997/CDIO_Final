package API;

import DAO.BrugerDAO;
import DAO.DALException;
import DAO.IncrementID;
import DAO.RaavareDAO;
import DTO.BrugerDTO;

import DTO.RaavareDTO;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.*;

@Path("/raavarer")
public class APIRaavare {
    RaavareDAO dbAccess = new RaavareDAO();

    public APIRaavare() {
    }


    @GET
    @Path("/allRaavarer")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RaavareDTO> getAllRaavarer() throws DALException {
        return dbAccess.getRaavareList();
    }

    @POST
    @Path("/createRaavarer")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createRaavarer(String jsonBody) throws DALException {
        IncrementID incre = new IncrementID();
        JSONObject json = new JSONObject(jsonBody);

        try {
            RaavareDTO raavarer = new RaavareDTO(
                    incre.returnID("raavarer", "raavID"),
                    json.getString("raavNavn"),
                    json.getString("leverandor"));

            dbAccess.createRaavare(raavarer);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/updateRaavere")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateRaavare(String jsonBody) {
        JSONObject json = new JSONObject(jsonBody);
        try {
            RaavareDTO raavare = new RaavareDTO(
                    json.getString("raavID"),
                    json.getString("raavNavn"),
                    json.getString("leverandor"));

            dbAccess.updateRaavare(raavare);

            return true;
        } catch (DALException e) {
            e.printStackTrace();
            return false;
        }
    }

    @GET
    @Path("/findRaavarer/{raavID}")
    @Produces(MediaType.APPLICATION_JSON)
    public RaavareDTO getRaavarer(@PathParam("raavID") String raavID) throws DALException {
        RaavareDTO findRaavare = dbAccess.getRaavare(raavID);
        return findRaavare;
    }

    @POST
    @Path("/deleteRaavarer/{raavID}")
    public void deleteRaavarer(@PathParam("raavID") String raavID) throws DALException {
        dbAccess.deleteRaavarer(raavID);
    }
}