package br.com.ribeiroribas.gateway.resources;

import br.com.ribeiroribas.gateway.model.request.EditTransactionCategoryRequest;
import br.com.ribeiroribas.services.creditcard.EditCreditCardCategoryService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Path("/credit-card")
public class EditCreditCardResource {

    @Inject
    private EditCreditCardCategoryService service;

    @PUT
    @Path("/insert-category")
    @RolesAllowed("dev")
    public Response editCreditCardCategory(@MultipartForm @Valid EditTransactionCategoryRequest request) {
        service.execute(request);
        return Response.ok().build();
    }
}
