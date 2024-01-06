package br.com.ribeiroribas.gateway.resources;

import br.com.ribeiroribas.domain.entities.CreditCardStatement;
import br.com.ribeiroribas.gateway.model.request.SaveCreditCardRequest;
import br.com.ribeiroribas.services.creditcard.CreditCardService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Path("/credit-card")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
public class SaveCreditCardStatementResource {

    @Inject
    private CreditCardService service;

    @POST
    @Path("/upload")
    @RolesAllowed("dev")
    public Response upload(@MultipartForm @Valid SaveCreditCardRequest fileRequest) {
        CreditCardStatement creditCardStatement = service.upload(fileRequest.getPdfFile(), fileRequest.getBank());
        return Response.ok().entity(creditCardStatement).build();
    }
}
