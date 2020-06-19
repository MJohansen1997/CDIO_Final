package DAO;

import DTO.*;

import java.sql.SQLException;
import java.util.List;

public interface IDAO {
    BrugerDTO getBruger(String oprId) throws DALException, SQLException, ClassNotFoundException;

    List<BrugerDTO> getBrugerList() throws DALException;

    void createBruger(BrugerDTO opr) throws DALException, SQLException;

    void updateBruger(BrugerDTO opr) throws DALException, SQLException;


    public interface IRaavareDAO {
        RaavareDTO getRaavare(String raavareId) throws DALException;

        List<RaavareDTO> getRaavareList() throws DALException;

        void createRaavare(RaavareDTO raavare) throws DALException;

        void updateRaavare(RaavareDTO raavare) throws DALException;
    }


    public interface IReceptDAO {
        ReceptDTO getRecept(String receptId) throws DALException;

        List<ReceptDTO> getReceptList() throws DALException;

        void createRecept(ReceptDTO recept) throws DALException;

        void updateRecept(ReceptDTO recept) throws DALException;
    }


    public interface IReceptKompDAO {
        ReceptKompDTO getReceptKomp(String receptId, String raavareId) throws DALException;

        List<ReceptKompDTO> getReceptKompList(String receptId) throws DALException;

        List<ReceptKompDTO> getReceptKompList() throws DALException;

        void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException;

        void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException;
    }


    public interface IRaavareBatchDAO {
        RaavareBatchDTO getRaavareBatch(String rbId) throws DALException, SQLException, ClassNotFoundException;

        List<RaavareBatchDTO> getRaavareBatchList() throws DALException, SQLException, ClassNotFoundException;

        //       List<RaavareBatchDTO> getRaavareBatchListID(String raavareId) throws DALException, SQLException, ClassNotFoundException;

        void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException, SQLException, ClassNotFoundException;

        void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException;
    }


    public interface IProduktBatchDAO {
        ProduktBatchDTO getProduktBatch(String pbId) throws DALException, SQLException, ClassNotFoundException;

        List<ProduktBatchDTO> getProduktBatchList() throws DALException;

        void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException;

        void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
    }

    public interface IProduktBatchKompDAO {
        ProduktBatchKompDTO getProduktBatchKomp(String pbId, String rbId) throws DALException, SQLException, ClassNotFoundException;

        List<ProduktBatchKompDTO> getProduktBatchKompList(String pbId) throws DALException;

        List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException, SQLException, ClassNotFoundException;

        void createProduktBatchKomp(ProduktBatchKompDTO pbk) throws DALException;

        void updateProduktBatchKomp(ProduktBatchKompDTO pbk) throws DALException;
    }
}
