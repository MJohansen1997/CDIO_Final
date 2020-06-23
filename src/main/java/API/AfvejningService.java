package API;

import DAO.*;
import DTO.*;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
/** @author Johansen, Mikkel s175194*/
@Path("/afvejning")
public class AfvejningService {

    @POST
    @Path("/verifylab")
    public boolean verifyLaborant(@FormParam("userid") String labid){
        try {
            BrugerDAO dao = new BrugerDAO();
            BrugerDTO dto = dao.getBruger(labid);
            String fuck = dto.getRolle();
            if (dto == null)
                return false;
            return !(fuck.equals("Brugeradminstrator"));
        } catch (DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }

    @POST
    @Path("/verifyprod")
    @Produces(MediaType.APPLICATION_JSON)
    public ReceptDTO verifyProdID(@FormParam("produktionsid") String prodID){
        try {
            ProduktBatchDAO PDAO = new ProduktBatchDAO();
            ProduktBatchDTO PB = PDAO.getProduktBatch(prodID);
            ReceptDAO RDAO = new ReceptDAO();
            if (PB == null)
                return null;

            return RDAO.getRecept(PB.getReceptID());
        }
        catch (DALException e){
            return null;
        }
    }

    @POST
    @Path("/loadprodkomps")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProduktBatchKompDTO> loadprodKomps(@FormParam("produktionsid") String prodid){
        try {
            ProduktBatchKompDAO dao = new ProduktBatchKompDAO();
            return dao.getProduktBatchKompList(prodid);
        } catch (DALException e) {
            e.printStackTrace();
            return null;
        }
    }

    @POST
    @Path("/loadreckomps")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReceptKompDTO> loadKomps(@FormParam("receptid") String recid){
        try {
            ReceptKompDAO dao = new ReceptKompDAO();
            return dao.getReceptKompList(recid);
        } catch (DALException e) {
            e.printStackTrace();
            return null;
        }
    }

    @POST
    @Path("/IdBatch")
    @Produces(MediaType.APPLICATION_JSON)
    public String verifyIdBatch(@QueryParam("rid") String rid, @QueryParam("rbid") String rbid){
        String r;
        String s;
        try {

            RaavareBatchDAO rbdao = new RaavareBatchDAO();
            RaavareDAO rdao = new RaavareDAO();
            if (rbid.equals("null"))
                return "{\"status\":\"LOL\",\"name\":\"" + rdao.getRaavare(rid).getRaavNavn() + "\"}";;
            r = rdao.getRaavare(rid).getRaavNavn();
            s = String.valueOf(rid.equals(rbdao.getRaavareBatch(rbid).getRaavId()));
            return "{\"status\":\"" + s + "\",\"name\":\"" + r + "\"}";
        }catch (DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    @PUT
    @Path("/updatestatus")
    public void changeStatus(@QueryParam("prodid") String prodid, @QueryParam("status") String status){
        try{
            ProduktBatchDAO dao = new ProduktBatchDAO();
            ProduktBatchDTO dto = dao.getProduktBatch(prodid);
            if (status.equals("Afsluttet") && dto.getSlutdato() == null){
                dto.setStatus(status);
                dto.setSlutdato(new Timestamp(System.currentTimeMillis()));
                dao.updateslut(dto);
            }
            else {
                dto.setStatus(status);
                dao.updateProduktBatch(dto);
            }
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/createpbk")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createprodkomp(String jsonBody) throws SQLException, DALException, ClassNotFoundException {
        IncrementID incre = new IncrementID();
        ProduktBatchKompDAO dao = new ProduktBatchKompDAO();
        JSONObject json = new JSONObject(jsonBody);

        ProduktBatchKompDTO user = new ProduktBatchKompDTO(
                json.getString("pbId"),
                json.getString("rbId"),
                json.getDouble("tara"),
                json.getDouble("netto"),
                json.getString("labID")
        );
        dao.createProduktBatchKomp(user);
    }

    @GET
    @Path("/findraavid/{rbid}")
    @Produces(MediaType.APPLICATION_JSON)
    public RaavareBatchDTO getBruger(@PathParam("rbid") String rbid) {
        try {
            RaavareBatchDAO dao = new RaavareBatchDAO();
            return dao.getRaavareBatch(rbid);
        } catch (SQLException | ClassNotFoundException | DALException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PUT
    @Path("/updateraavbatch")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRB(String jsonBody){
        try {
            JSONObject json = new JSONObject(jsonBody);
            RaavareBatchDAO dao = new RaavareBatchDAO();
            RaavareBatchDTO rb = new RaavareBatchDTO(
                    json.getString("rbID"),
                    json.getString("raavId"),
                    json.getDouble("maengde"));

            dao.updateRaavareBatch(rb);
        } catch (DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
