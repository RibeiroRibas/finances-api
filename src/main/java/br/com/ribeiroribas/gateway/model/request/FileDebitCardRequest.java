package br.com.ribeiroribas.gateway.model.request;

import br.com.ribeiroribas.domain.enums.DebitCardBank;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import java.io.File;

public class FileDebitCardRequest {
    @FormParam("pdfFile")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private File pdfFile;

    @FormParam("bank")
    @PartType(MediaType.APPLICATION_JSON)
    private String bank;
    public File getPdfFile() {
        return pdfFile;
    }

    public DebitCardBank getBank(){
        return DebitCardBank.valueOf(bank);
    }
}
