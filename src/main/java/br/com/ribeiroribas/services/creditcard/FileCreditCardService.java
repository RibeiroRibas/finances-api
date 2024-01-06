package br.com.ribeiroribas.services.creditcard;

import br.com.ribeiroribas.domain.entities.CreditCardTransaction;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.time.LocalDate;
import java.util.List;

public interface FileCreditCardService {

    boolean isPdfOwnedBySelectedBank(PDDocument document);
    LocalDate getDueDate(PDDocument document);
    Double getAmount(PDDocument document);
    List<CreditCardTransaction> getBrazilTransactions(PDDocument document);
    List<CreditCardTransaction> getInternationalTransactions(PDDocument document);
}
