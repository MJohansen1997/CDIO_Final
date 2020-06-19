package API;

import DAO.*;
import DTO.*;
import org.json.JSONObject;

import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.*;


@Path("/raavarebatch")
public class APIRaavareBatch {
    RaavareBatchDAO dbAccess = new RaavareBatchDAO();

    @GET
    @Path("/allRaavarerBatch")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RaavareBatchDTO> getAllRaavarerBatch() throws SQLException, DALException, ClassNotFoundException
    {
        return dbAccess.getRaavareBatchList();
    }


    public APIRaavareBatch() throws SQLException, DALException, ClassNotFoundException {
    }


    @POST
    @Path("/verifyProjektleder")
    public boolean verifyProjektleder(@FormParam("userid") String ProLederID) {

        return true;
    }

    @POST
    @Path("/verifyRB")
    @Produces(MediaType.APPLICATION_JSON)
    public RaavareDTO verifyRaavareID(@FormParam("rbid") String rbid) {
        try {
            RaavareBatchDAO RBDAO = new RaavareBatchDAO();
            RaavareBatchDTO RBDTO = RBDAO.getRaavareBatch(rbid);
            RaavareDAO RaaDAO = new RaavareDAO();
            return RaaDAO.getRaavare(RBDTO.getRaavareId());

        } catch (DALException e) {
            return null;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
//    @GET
//    @Path("/getrid")
//    public String getRaaID(String rid) {
//        try {
//            RaavareBatchDAO rbdao = new RaavareBatchDAO();
//            return rbdao.getRaavareBatch(rid).getRaavareId();
//        } catch (DALException | SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//
//        }
//    }
/*
    @GET
    @Path("/getmaengde")
    public double getMaengde(){
        try{
            RaavareBatchDTO rbdto = new RaavareBatchDTO();
            return rbdto.getMaengde();
        } catch (DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @POST
    @Path("/IdBatch")
    public String verifyIdBatch(@QueryParam("rid") String rid, @QueryParam("rbid") String rbid){
        try {
            RaavareBatchDAO rbdao = new RaavareBatchDAO();
            RaavareDAO rdao = new RaavareDAO();
            if(!rid.equals(rbdao.getRaavareBatch(rbid).getRaavareId()))
                return rdao.getRaavare(rid).getRaavareNavn();
            else return "";
        }catch (DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
*/

    @POST
    @Path("/createRaavarerBatch")
    public void createRaavareBatch(String jsonBody) throws DALException {
        IncrementID incre = new IncrementID();
        JSONObject json = new JSONObject(jsonBody);
        try {

            RaavareBatchDTO raavareBatchDAOdao = new RaavareBatchDTO(
                    incre.returnID("raavarebatches", "rbID"),
                    json.getString("raavID"),
                    json.getDouble("maengde"));


            dbAccess.createRaavareBatch(raavareBatchDAOdao);

        } catch (DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/updateRaavereBatch")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRaavareBatch(String jsonBody)
    {
        JSONObject json = new JSONObject(jsonBody);


        try
        {
            RaavareBatchDTO raavareBatch = new RaavareBatchDTO(
                    json.getString("rbID"),
                    json.getString("raavNavn"),
                    json.getDouble("maengde"));


            dbAccess.updateRaavareBatch(raavareBatch);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/findRaavareBatch/{rbID}")
    @Produces(MediaType.APPLICATION_JSON)
    public RaavareBatchDTO getRaavareBatch(@PathParam("rbID") String rbID) throws ClassNotFoundException, DALException, SQLException {
        RaavareBatchDTO raavareBatch = dbAccess.getRaavareBatch(rbID);
        return raavareBatch;
    }


//    @GET
//    @Path("/allRaavareBatches")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<RaavareBatchDTO> getAllRaavareBatches() throws SQLException, DALException, ClassNotFoundException {
//        List<RaavareBatchDTO> raav = dbAccess.getRaavareBatchList();
//        for (int i = 0; i < raav.size(); i++) {
//            System.out.println(raav.get(i).getRaavareId());
//            System.out.println(raav.get(i).getRbID());
//        }
//        return raav;
//
//    }
}

