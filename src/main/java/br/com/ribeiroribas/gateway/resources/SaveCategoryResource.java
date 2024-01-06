package br.com.ribeiroribas.gateway.resources;

import br.com.ribeiroribas.gateway.model.response.CategoryResponse;
import br.com.ribeiroribas.services.CategoryService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("category")
public class SaveCategoryResource {

    @Inject
    private CategoryService service;

    @POST
    @Path("{name}")
    @RolesAllowed("dev")
    public Response save(@PathParam("name") String name) {
        return Response.ok().entity(service.save(name)).build();
    }
}
