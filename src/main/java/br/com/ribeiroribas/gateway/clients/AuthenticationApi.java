package br.com.ribeiroribas.gateway.clients;

import br.com.ribeiroribas.gateway.model.request.KeyCloakRequest;
import br.com.ribeiroribas.gateway.model.response.KeyCloakResponse;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.util.Base64;

@RegisterRestClient(configKey = "keycloak-api")
@ClientHeaderParam(name = "Authorization", value = "{basicAuthHeader}")
public interface AuthenticationApi {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    KeyCloakResponse auth(@MultipartForm @Valid KeyCloakRequest request);

    default String basicAuthHeader() {
        String username = ConfigProvider.getConfig().getValue("quarkus.oidc.client-id", String.class);
        String secret = ConfigProvider.getConfig().getValue("quarkus.oidc.credentials.secret", String.class);
        String credentials = username.concat(":").concat(secret);
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }

}
