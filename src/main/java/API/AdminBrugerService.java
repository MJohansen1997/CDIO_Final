package API;

import DAO.BrugerDAO;
import DAO.DALException;
import DTO.BrugerDTO;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;


@Path("/login")
public class AdminBrugerService {

    @POST
    @Path("/admin")
    @Produces(MediaType.APPLICATION_JSON)
    public void adminOpret(@FormParam("Userid") String id,
                           @FormParam("Username") String username,
                           @FormParam("Userini") String ini,
                           @FormParam("Usercpr") String cpr,
                           @FormParam("Userrolle") String rolle,
                           @FormParam("Userpword") String password)
            throws SQLException, ClassNotFoundException, DALException {

        BrugerDAO BDAO = new BrugerDAO();
        BrugerDTO bruger = new BrugerDTO(id, username, ini, cpr, rolle, password);


        BDAO.createBruger(bruger);
    }

}
