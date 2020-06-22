package API;

import DAO.DALException;
import DAO.IncrementID;
import DAO.ProduktBatchKompDAO;
import DAO.RaavareDAO;
import DTO.ProduktBatchKompDTO;
import DTO.RaavareDTO;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/produktbatches")
public class APIProduktBatchKomp
{
    ProduktBatchKompDAO dbAccess = new ProduktBatchKompDAO();

    public APIProduktBatchKomp()
    {
    }


    @GET
    @Path("/allProduktBatchKomp")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProduktBatchKompDTO> getAllProduktBatchKomp() throws DALException
    {
        return dbAccess.getProduktBatchKompList();
    }

    @GET
    @Path("/findProduktBatchKomp/{pbkID}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProduktBatchKompDTO> getProduktBatchKomp(@PathParam("pbkID") String pbkID) throws DALException
    {
        return dbAccess.getProduktBatchKompList(pbkID);
    }

    @POST
    @Path("/BatchKompPost")
    @Produces(MediaType.APPLICATION_JSON)
    public ProduktBatchKompDTO getProduktBatchKompPBRB(@QueryParam("pbID") String pbID, @QueryParam("rbID") String rbID) throws DALException {
        ProduktBatchKompDTO fuck = dbAccess.getProduktBatchKomp(pbID, rbID);
        return fuck;
    }

}
