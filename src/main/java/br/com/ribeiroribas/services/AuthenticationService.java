package br.com.ribeiroribas.services;


import br.com.ribeiroribas.gateway.clients.AuthenticationApi;
import br.com.ribeiroribas.gateway.model.request.AuthenticationRequest;
import br.com.ribeiroribas.gateway.model.request.KeyCloakRequest;
import br.com.ribeiroribas.gateway.model.response.AuthenticationResponse;
import br.com.ribeiroribas.gateway.model.response.KeyCloakResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class AuthenticationService {

    @Inject
    @RestClient
    private AuthenticationApi api;

    public AuthenticationResponse auth(AuthenticationRequest request) {
        KeyCloakResponse auth = api.auth(new KeyCloakRequest(request.getUsername(), request.getPassword(), request.getGrantType()));
        return new AuthenticationResponse(auth.getAccessToken(), auth.getTokenType());
    }
}
