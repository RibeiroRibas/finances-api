package br.com.ribeiroribas.gateway.model.request;

import br.com.ribeiroribas.domain.enums.CreditCardBank;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import java.io.File;

public class SaveCreditCardRequest {

    @FormParam("pdfFile")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    @NotNull(message = "PDF file cannot be null")
    private File pdfFile;

    @FormParam("bank")
    @PartType(MediaType.APPLICATION_JSON)
    @NotNull(message = "bank cannot be null")
    private String bank;

    public File getPdfFile() {
        return pdfFile;
    }

    public CreditCardBank getBank() {
        return CreditCardBank.valueOf(bank);
    }
}
