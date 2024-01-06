package br.com.ribeiroribas.services.debitcard;

import br.com.ribeiroribas.domain.entities.Category;
import br.com.ribeiroribas.domain.entities.DebitCardTransaction;
import br.com.ribeiroribas.gateway.model.request.EditTransactionCategoryRequest;
import br.com.ribeiroribas.repositories.CategoryRepository;
import br.com.ribeiroribas.repositories.DebitCardTransactionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EditDebitCardCategoryService {

    @Inject
    private DebitCardTransactionRepository debitCardTransactionRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @Transactional
    public void execute(EditTransactionCategoryRequest request) {
        var categoryOptional = categoryRepository.findByIdOptional(request.getCategoryId());
        var debitCardTransactionOptional =
                debitCardTransactionRepository.findByIdOptional(request.getTransactionId());

        if (categoryOptional.isPresent() && debitCardTransactionOptional.isPresent()) {

            DebitCardTransaction debitCardTransaction = debitCardTransactionOptional.get();
            Category category = categoryOptional.get();

            if (request.isEditAllCategories()) {
                List<DebitCardTransaction> debitCardTransactions =
                        debitCardTransactionRepository.listByTenant(debitCardTransaction.getTenant());

                debitCardTransactions.forEach(transaction -> {
                    if (transaction.getDescriptionWithoutNumbers().equals(debitCardTransaction.getDescriptionWithoutNumbers())) {
                        transaction.setCategory(category);
                    }
                });
            } else {
                debitCardTransaction.setCategory(category);
            }

        }
    }
}
