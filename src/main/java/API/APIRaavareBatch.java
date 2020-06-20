package API;

import DAO.DALException;
import DAO.IncrementID;
import DAO.RaavareBatchDAO;
import DTO.RaavareBatchDTO;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;


@Path("/raavarebatch")
public class APIRaavareBatch {
    RaavareBatchDAO dbAccess = new RaavareBatchDAO();

    public APIRaavareBatch() throws SQLException, DALException, ClassNotFoundException {
    }

    @POST
    @Path("/createRaavarerBatch")
    public void createRaavareBatch(String jsonBody) throws DALException {
        IncrementID incre = new IncrementID();
        JSONObject json = new JSONObject(jsonBody);
        try {

            RaavareBatchDTO RBDTO = new RaavareBatchDTO(incre.returnID("raavarebatches", "rbID"), json.getString("raavID"), json.getDouble("maengde"));
            dbAccess.createRaavareBatch(RBDTO);

        } catch (DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @POST
    @Path("/deleteRB/{rbID}")
    @Produces(MediaType.APPLICATION_JSON)
    public RaavareBatchDTO deleteRB (@PathParam("rbID") String rbID) throws  DALException {
        RaavareBatchDTO raavareBatch = dbAccess.deleteRB(rbID);
        return raavareBatch;
    }

    @POST
    @Path("/updateRaavereBatch")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRaavareBatch(String jsonBody)
    {
        JSONObject json = new JSONObject(jsonBody);
        try
        {
            RaavareBatchDTO raavareBatch = new RaavareBatchDTO(json.getString("rbID"), json.getString("raavID"), json.getDouble("maengde"));
            dbAccess.updateRaavareBatch(raavareBatch);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/getRaavareBatch/{rbID}")
    @Produces(MediaType.APPLICATION_JSON)
    public RaavareBatchDTO getRaavareBatch(@PathParam("rbID") String rbID) throws ClassNotFoundException, DALException, SQLException {
        RaavareBatchDTO raavareBatch = dbAccess.getRaavareBatch(rbID);
        return raavareBatch;
    }


    @GET
    @Path("/allRaavareBatches")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RaavareBatchDTO> getAllRaavareBatches() throws SQLException, DALException, ClassNotFoundException {
        List<RaavareBatchDTO> raav = dbAccess.getRaavareBatchList();
        for (int i = 0; i < raav.size(); i++) {
            System.out.println(raav.get(i).getRaavId());
            System.out.println(raav.get(i).getRbID());
        }
        return raav;

    }
}

