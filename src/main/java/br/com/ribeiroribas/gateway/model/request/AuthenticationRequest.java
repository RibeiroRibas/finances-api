package br.com.ribeiroribas.gateway.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class AuthenticationRequest {

    @NotNull(message = "param username cannot be null")
    @FormParam("username")
    @PartType(MediaType.APPLICATION_JSON)
    private String username;
    @NotNull(message = "param password cannot be null")
    @FormParam("password")
    @PartType(MediaType.APPLICATION_JSON)
    private String password;
    @NotNull(message = "param grant_type cannot be null")
    @FormParam("grant_type")
    @PartType(MediaType.APPLICATION_JSON)
    private String grantType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
