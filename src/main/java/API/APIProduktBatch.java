package API;

import DAO.*;
import DTO.*;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author Hansen, Mads Østerlund (s195456@student.dtu.dk)
 **/

@Path("/PB")
public class APIProduktBatch {
    ProduktBatchDAO dbAccess = new ProduktBatchDAO();

    public APIProduktBatch() throws DALException {

    }

    @POST
    @Path("/createPB")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createPB(String jsonbody) throws DALException {
        IncrementID incID = new IncrementID();
        JSONObject json = new JSONObject(jsonbody);

        try {
            ProduktBatchDTO pbatch = new ProduktBatchDTO(
                    incID.returnID("prodbestilling", "pbID"),
                    json.getString("chosenStatus"),
                    json.getString("chosenRecept"),
                    new Timestamp(System.currentTimeMillis()));
            dbAccess.createProduktBatch(pbatch);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    @PUT
    @Path("/updatePB")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePB(String jsonbody) {
        JSONObject json = new JSONObject(jsonbody);

        try {
            ProduktBatchDTO pbatch = new ProduktBatchDTO(
                    json.getString("pbID"),
                    json.getString("status"),
                    null,
                    null);

            dbAccess.updateProduktBatch(pbatch);
        } catch (DALException e) {
            e.printStackTrace();
        }

    }


    @GET
    @Path("/findPB/{pbID}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProduktBatchDTO getPB(@PathParam("pbID") String pbID) throws DALException {
        ProduktBatchDTO pbatch = dbAccess.getProduktBatch(pbID);
        return pbatch;
    }


    @POST
    @Path("/deletePB/{pbID}")
    public void deletePB(@PathParam("pbID") String pbID) throws DALException {
        dbAccess.deleteProduktBatch(pbID);
    }

    @GET
    @Path("/allPB")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProduktBatchDTO> getAllProduktBatches() throws DALException {
        return dbAccess.getProduktBatchList();
    }

    @POST
    @Path("/IdBatch")
    @Produces(MediaType.APPLICATION_JSON)
    public String verifyIdBatch(@QueryParam("pbID") String prodID) {
        /** @Author Mikkel Johansen, s175194*/
        try {
            BrugerDAO brugerDAO = new BrugerDAO();
            ProduktBatchDAO produktBatchDAO = new ProduktBatchDAO();
            ProduktBatchDTO produktBatch = produktBatchDAO.getProduktBatch(prodID);

            ReceptKompDAO receptKompDAO = new ReceptKompDAO();
            List<ReceptKompDTO> receptKompList = receptKompDAO.getReceptKompList(produktBatch.getReceptID());

            RaavareBatchDAO raavareBatchDAO = new RaavareBatchDAO();
            RaavareDAO RaavareDAO = new RaavareDAO();
            List<RaavareDTO> raavareList = new ArrayList<>();
            for (int i = 0; i < receptKompList.size(); i++) {
                raavareList.add(RaavareDAO.getRaavare(receptKompList.get(i).getRaavareID()));
            }

            ProduktBatchKompDAO produktBatchKompDAO = new ProduktBatchKompDAO();
            List<ProduktBatchKompDTO> produktBatchKompList = produktBatchKompDAO.getProduktBatchKompList(prodID);
            JSONObject main = new JSONObject();

            main.put("prodID", prodID);
            main.put("recID", produktBatch.getReceptID());
            main.put("status", produktBatch.getStatus());
            main.put("start", produktBatch.getStartdato());
            if(produktBatch.getSlutdato() == null)
                main.put("slut", "");
            else
                main.put("slut", produktBatch.getSlutdato());

            JSONArray raavlist = new JSONArray();
            outerloop:
            for(int i = 0; i < raavareList.size(); i++) {
                boolean found = true;
                JSONObject raav = new JSONObject();
                raav.put("ravID", raavareList.get(i).getRaavID());
                raav.put("ravName", raavareList.get(i).getRaavNavn());
                ReceptKompDTO unholy = receptKompDAO.getReceptKomp(produktBatch.getReceptID(), raavareList.get(i).getRaavID());
                raav.put("maengde", unholy.getNonNetto());
                raav.put("tolerance", unholy.getTolerance());

                for (int j = 0; j < produktBatchKompList.size(); j++) {
                    if (raavareList.get(i).getRaavID().equals(raavareBatchDAO.getRaavareBatch(
                            produktBatchKompList.get(j).getRbId()).getRaavId())){
                        raav.put("rb", produktBatchKompList.get(j).getRbId());
                        raav.put("netto", produktBatchKompList.get(j).getNetto());
                        raav.put("tara", produktBatchKompList.get(j).getTara());
                        raav.put("ini", brugerDAO.getBruger(produktBatchKompList.get(j).getLabID()).getIni());
                        raavlist.put(raav);
                        continue outerloop;
                    }
                }
                raav.put("rb", "");
                raav.put("netto", 0);
                raav.put("tara", 0);
                raav.put("ini", "");
                raavlist.put(raav);

            }
            main.put("raavlist", raavlist);
            System.out.println(main.toString());

            return main.toString();
        } catch (DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
