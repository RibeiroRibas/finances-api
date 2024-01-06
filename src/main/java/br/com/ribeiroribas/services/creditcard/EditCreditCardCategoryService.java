package br.com.ribeiroribas.services.creditcard;

import br.com.ribeiroribas.domain.entities.Category;
import br.com.ribeiroribas.domain.entities.CreditCardTransaction;
import br.com.ribeiroribas.domain.entities.DebitCardTransaction;
import br.com.ribeiroribas.gateway.model.request.EditTransactionCategoryRequest;
import br.com.ribeiroribas.repositories.CategoryRepository;
import br.com.ribeiroribas.repositories.CreditCardTransactionRepository;
import br.com.ribeiroribas.repositories.DebitCardTransactionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EditCreditCardCategoryService {

    @Inject
    private CreditCardTransactionRepository creditCardTransactionRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @Transactional
    public void execute(EditTransactionCategoryRequest request) {
        var categoryOptional = categoryRepository.findByIdOptional(request.getCategoryId());
        var creditCardTransactionOptional =
                creditCardTransactionRepository.findByIdOptional(request.getTransactionId());

        if (categoryOptional.isPresent() && creditCardTransactionOptional.isPresent()) {

            CreditCardTransaction creditCardTransaction = creditCardTransactionOptional.get();
            Category category = categoryOptional.get();

            if (request.isEditAllCategories()) {
                List<CreditCardTransaction> creditCardTransactions =
                        creditCardTransactionRepository.listByTenant(creditCardTransaction.getTenant());

                creditCardTransactions.forEach(transaction -> {
                    if (transaction.getDescriptionWithoutNumbers().equals(creditCardTransaction.getDescriptionWithoutNumbers())) {
                        transaction.setCategory(category);
                    }
                });
            } else {
                creditCardTransaction.setCategory(category);
            }
        }
    }
}
