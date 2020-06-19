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

    public APIRaavare() throws SQLException, DALException, ClassNotFoundException
    {
    }


    @GET
    @Path("/allRaavarer")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RaavareDTO> getAllRaavarer() throws SQLException, DALException, ClassNotFoundException
    {
        return dbAccess.getRaavareList();
    }

    @POST
    @Path("/createRaavarer")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createRaavarer(String jsonBody) throws SQLException, DALException, ClassNotFoundException
    {
        IncrementID incre = new IncrementID();
        JSONObject json = new JSONObject(jsonBody);

        try {
            RaavareDTO raavarer = new RaavareDTO(
                    incre.returnID("raavarer", "raavID"),
                    json.getString("raavNavn"),
                    json.getString("leverandor"));

            dbAccess.createRaavare(raavarer);

        }
        catch (DALException e)
        {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/updateRaavere")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRaavare(String jsonBody)
    {
        JSONObject json = new JSONObject(jsonBody);


        try
        {
            RaavareDTO raavare = new RaavareDTO(
                    json.getString("raavID"),
                    json.getString("raavNavn"),
                    json.getString("leverandor"));


            dbAccess.updateRaavare(raavare);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/findRaavarer/{raavID}")
    @Produces(MediaType.APPLICATION_JSON)
    public RaavareDTO getRaavarer(@PathParam("raavID") String raavID) throws ClassNotFoundException, DALException, SQLException
    {
        RaavareDTO findRaavare = dbAccess.getRaavare(raavID);
        return findRaavare;
    }

    @POST
    @Path("/deleteRaavarer/{raavID}")
    public void deleteRaavarer(@PathParam("raavID") String raavID) throws ClassNotFoundException, DALException, SQLException
    {
        dbAccess.deleteRaavarer(raavID);
    }
}