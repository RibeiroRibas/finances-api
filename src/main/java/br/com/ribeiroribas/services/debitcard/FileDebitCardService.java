package br.com.ribeiroribas.services.debitcard;

import br.com.ribeiroribas.domain.entities.DebitCardTransaction;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.time.LocalDate;
import java.util.List;

public interface FileDebitCardService {

    boolean isPdfOwnedBySelectedBank(PDDocument document);

    List<DebitCardTransaction> getTransactions(PDDocument document);

}
