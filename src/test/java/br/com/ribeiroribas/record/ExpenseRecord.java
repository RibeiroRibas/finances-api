package br.com.ribeiroribas.record;

import br.com.ribeiroribas.domain.entities.CreditCardTransaction;
import br.com.ribeiroribas.domain.enums.PurchaseOrigin;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class ExpenseRecord {
    public static List<CreditCardTransaction> getExpenses() {
        return Collections.singletonList(getExpense());
    }

    private static CreditCardTransaction getExpense() {
        return new CreditCardTransaction(LocalDate.now(),
                "MERCADO LIVRE",
                50.0,
                "2/3",
                PurchaseOrigin.BRAZIL);
    }
}
