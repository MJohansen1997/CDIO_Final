package DAO;

import DTO.ReceptDTO;

import java.util.List;
/** @author Johansen, Mikkel (john.doe@example.com)*/
public class ReceptDAO implements IDAO.IReceptDAO {
    @Override
    public ReceptDTO getRecept(int receptId) throws DALException {
        return null;
    }

    @Override
    public List<ReceptDTO> getReceptList() throws DALException {
        return null;
    }

    @Override
    public void createRecept(ReceptDTO recept) throws DALException {

    }

    @Override
    public void updateRecept(ReceptDTO recept) throws DALException {

    }
}