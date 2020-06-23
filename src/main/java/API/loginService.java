package API;

import DAO.BrugerDAO;
import DAO.DALException;
import DTO.BrugerDTO;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
/** @author Chistensen, Jacob Kjærby (s174130@student.dtu.dk)*/
@Path("/login")
public class loginService{

    @POST
    @Path("/verify")
        public boolean verification(@FormParam("Username") String name, @FormParam("Password") String password)
            throws SQLException, ClassNotFoundException, DALException {
        BrugerDAO BDAO = new BrugerDAO();
        //BrugerDTO user = new BrugerDTO("B0001", "mikkel","MJ" ,"000000-0000",
        //        "Farmaceut", "0000" );
        return BDAO.verifyUser(name, password);
    }

    @GET
    @Path("/getUser")
    @Produces(MediaType.APPLICATION_JSON)
    public BrugerDTO verificationBetweenAll(@QueryParam("Username") String name, @QueryParam("Password") String password) throws SQLException, DALException, ClassNotFoundException {
        BrugerDAO BDAO = new BrugerDAO();

        return BDAO.extractVerifiedUser(name,password);
    }
}
