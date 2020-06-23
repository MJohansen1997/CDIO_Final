package API;

import DAO.DALException;
import DAO.IncrementID;
import DAO.ReceptKompDAO;
import DTO.BrugerDTO;
import DTO.ReceptKompDTO;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
/** @author Johansen, Mikkel s175194*/
@Path("rk")
public class APIReceptKomp {
    ReceptKompDAO dbAccess = new ReceptKompDAO();

    public APIReceptKomp() throws DALException {
    }


    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReceptKompDTO> getAllBrugere() throws DALException {
        return dbAccess.getReceptKompList();
    }

    @GET
    @Path("/findKomps/{receptID}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReceptKompDTO> getBruger(@PathParam("receptID") String receptID) {
        try {
            List<ReceptKompDTO> list = dbAccess.getReceptKompList(receptID);
            if (list.size() == 0)
                return null;
            else
                return list;
        } catch (DALException e) {
            return null;
        }
    }

    @POST
    @Path("/createKomp")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean createkomp(String jsonBody) {
        JSONObject json = new JSONObject(jsonBody);

        try {
            ReceptKompDTO user = new ReceptKompDTO(
                    json.getString("ReceptID"),
                    json.getString("RaavareID"),
                    json.getDouble("nonNetto"),
                    json.getDouble("Tolerance"));

            dbAccess.createReceptKomp(user);
            return true;
        } catch (DALException e) {
            return false;
        }
    }

    @POST
    @Path("/deletekomp")
    public boolean deleteBruger(@FormParam("receptID") String rid, @FormParam("raavID") String raav) {
        try {
            dbAccess.deleteReceptKomp(rid, raav);
            return true;
        } catch (DALException e) {
            e.printStackTrace();
            return false;
        }
    }
}
