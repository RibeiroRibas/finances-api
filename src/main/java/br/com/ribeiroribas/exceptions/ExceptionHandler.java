package br.com.ribeiroribas.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<BaseException> {

    @Override
    public Response toResponse(BaseException ex) {
        return null;
    }
}
