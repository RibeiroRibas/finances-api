package br.com.ribeiroribas.gateway.resources;

import br.com.ribeiroribas.domain.entities.DebitCardTransaction;
import br.com.ribeiroribas.gateway.model.request.FileDebitCardRequest;
import br.com.ribeiroribas.services.debitcard.DebitCardService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.util.List;

@Path("/debit-card")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
public class SaveDebitCardResource {

    @Inject
    private DebitCardService service;

    @POST
    @Path("/upload")
    @RolesAllowed("dev")
    public Response upload(@MultipartForm FileDebitCardRequest fileRequest) {
        List<DebitCardTransaction> transactions = service.upload(fileRequest.getPdfFile(), fileRequest.getBank());
        return Response.ok().entity(transactions).build();
    }
}
