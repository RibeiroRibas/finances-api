package br.com.ribeiroribas.services.debitcard;

import br.com.ribeiroribas.domain.enums.DebitCardBank;
import br.com.ribeiroribas.domain.entities.DebitCardTransaction;
import br.com.ribeiroribas.repositories.DebitCardTransactionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class DebitCardService {

    @Inject
    private DebitCardTransactionRepository repository;

    @Inject
    private JsonWebToken jwt;

    @Transactional
    public List<DebitCardTransaction> upload(File pdfFile, DebitCardBank debitCardBank) {
        List<DebitCardTransaction> debitCardTransactions = new ArrayList<>();

        try (PDDocument document = Loader.loadPDF(pdfFile)) {

            FileDebitCardService bankService = debitCardBank.getBankService();

            if (bankService.isPdfOwnedBySelectedBank(document)) {

                debitCardTransactions.addAll(bankService.getTransactions(document));

                debitCardTransactions.forEach(transaction -> {
                    transaction.setTenant(UUID.fromString(jwt.getSubject()));

                    Optional<DebitCardTransaction> byTransactionDate =
                            repository.findByDescriptionAndTenant(getParams(transaction));

                    if (byTransactionDate.isEmpty() || !byTransactionDate.get().equals(transaction)) {
                        repository.persist(transaction);
                        repository.flush();
                    } else {
                        transaction.setId(byTransactionDate.get().getId());
                    }
                });
            }

        } catch (IOException ignored) {
            System.out.println("IOException");
        }
        return debitCardTransactions;
    }

    private Map<String, Object> getParams(DebitCardTransaction transaction) {
        Map<String, Object> params = new HashMap<>();
        params.put("description", transaction.getDescription());
        params.put("tenant", transaction.getTenant());
        return params;
    }
}
