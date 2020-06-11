package DAO;

import DTO.BrugerDTO;

import java.util.List;
/** @author Christensen, Jacob (john.doe@example.com)*/

public class BrugerDAO implements IDAO {
    @Override

    public BrugerDTO getBruger(String oprId) throws DALException {
        return null;
    }

    @Override
    public List<BrugerDTO> getBrugerList() throws DALException {
        return null;
    }

    @Override
    public void createBruger(BrugerDTO opr) throws DALException {

    }

    @Override
    public void updateBruger(BrugerDTO opr) throws DALException {

    }
}
