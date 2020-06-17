package DAO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class DALMapper implements javax.ws.rs.ext.ExceptionMapper <DALException> {
    @Override
    public Response toResponse(DALException e) {
        return null;
    }
}
