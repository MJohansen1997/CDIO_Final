package API;

import DAO.BrugerDAO;
import DAO.DALException;
import DTO.BrugerDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/brugere")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BrugerHaandtering {


    @GET
    @Path("/allUsers")
    public List<BrugerDTO> getAllBrugere() throws SQLException, DALException, ClassNotFoundException {
        BrugerDAO alleBrugere = new BrugerDAO();
        return alleBrugere.getBrugerList();
    }
}
