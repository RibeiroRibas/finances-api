package br.com.ribeiroribas.gateway.resources;

import br.com.ribeiroribas.gateway.model.request.AuthenticationRequest;
import br.com.ribeiroribas.gateway.model.response.AuthenticationResponse;
import br.com.ribeiroribas.services.AuthenticationService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Path("/auth")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

    @Inject
    private AuthenticationService service;

    @POST
    public AuthenticationResponse auth(@MultipartForm @Valid AuthenticationRequest request) {
        return service.auth(request);
    }

}
