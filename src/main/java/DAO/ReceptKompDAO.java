package DAO;

import DTO.ReceptKompDTO;

import java.util.List;
/** @author Doe, John (john.doe@example.com)*/

public class ReceptKompDAO implements IDAO.IReceptKompDAO {
    @Override
    public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
        return null;
    }

    @Override
    public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
        return null;
    }

    @Override
    public List<ReceptKompDTO> getReceptKompList() throws DALException {
        return null;
    }

    @Override
    public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {

    }

    @Override
    public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {

    }
}