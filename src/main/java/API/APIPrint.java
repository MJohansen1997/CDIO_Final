package API;

import DAO.DALException;
import DAO.PrintDAO;
import DTO.PrintDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/print")
public class APIPrint {
    @GET
    @Path("/printproduktbatch")
    @Produces(MediaType.APPLICATION_JSON)
    public PrintDTO getPrintProduktBatch(@PathParam("pbID") String pbID, @PathParam("status") String status, @PathParam("recID") String recID) {
        try {
            PrintDAO dao = new PrintDAO();
            return dao.getPrint(pbID, status, recID);
        } catch (SQLException | ClassNotFoundException | DALException e) {
            e.printStackTrace();
            return null;
        }
    }
}
