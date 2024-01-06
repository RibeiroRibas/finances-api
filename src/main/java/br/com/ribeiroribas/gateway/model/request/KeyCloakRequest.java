package br.com.ribeiroribas.gateway.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class KeyCloakRequest {
    @NotNull
    @FormParam("username")
    @PartType(MediaType.APPLICATION_JSON)
    private String username;
    @NotNull
    @FormParam("password")
    @PartType(MediaType.APPLICATION_JSON)
    private String password;
    @NotNull
    @FormParam("grant_type")
    @PartType(MediaType.APPLICATION_JSON)
    private String grantType;

    public KeyCloakRequest(String username, String password, String grantType) {
        this.username = username;
        this.password = password;
        this.grantType = grantType;
    }

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

