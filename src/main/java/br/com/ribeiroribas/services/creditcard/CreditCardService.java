package br.com.ribeiroribas.services.creditcard;

import br.com.ribeiroribas.domain.enums.CreditCardBank;
import br.com.ribeiroribas.domain.entities.CreditCardStatement;
import br.com.ribeiroribas.repositories.CreditCardStatementRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CreditCardService {

    @Inject
    private JsonWebToken jwt;

    @Inject
    private CreditCardStatementRepository repository;

    @Transactional
    public CreditCardStatement upload(File pdfFile, CreditCardBank creditCardBank) {

        CreditCardStatement creditCardStatement = new CreditCardStatement();

        try (PDDocument document = Loader.loadPDF(pdfFile)) {

            FileCreditCardService bankService = creditCardBank.getBankService();

            if (bankService.isPdfOwnedBySelectedBank(document)) {

                creditCardStatement.setDueDate(bankService.getDueDate(document));

                Optional<CreditCardStatement> byDueDate = repository.findByDueDate(creditCardStatement.getDueDate());

                if (byDueDate.isPresent()) {
                    return byDueDate.get();
                }

                creditCardStatement.setAmount(bankService.getAmount(document));

                creditCardStatement.setTransactions(bankService.getBrazilTransactions(document));

                creditCardStatement.setTransactions(bankService.getInternationalTransactions(document));

                creditCardStatement.getTransactions().forEach(creditCardExpense -> creditCardExpense.setTenant(UUID.fromString(jwt.getSubject())));

                creditCardStatement.setTenant(UUID.fromString(jwt.getSubject()));

                repository.persist(creditCardStatement);

            }

        } catch (IOException ignored) {
            System.out.println("IOException");
        }
        return creditCardStatement;
    }

}
