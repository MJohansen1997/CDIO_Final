package API;

import DAO.BrugerDAO;
import DAO.DALException;
import DTO.BrugerDTO;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

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

   /* @GET
    @Path("/getUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BrugerDTO verificationBetweenAll(String jsonBody) throws SQLException, DALException, ClassNotFoundException {
        BrugerDAO BDAO = new BrugerDAO();
        JSONObject Json = new JSONObject();
        List<BrugerDTO> AlleUsers = BDAO.getBrugerList();

        for (int i = 0; i < AlleUsers.size(); i++) {
            if(AlleUsers.get(i).getBrugerNavn().equals(Json.getString("Username")) && AlleUsers.get(i).getPassword().equals(Json.getString("Password"))){
                return AlleUsers.get(i);
            }
        }
        return null;
    }*/
}
