package br.com.ribeiroribas.domain.enums;

import br.com.ribeiroribas.services.creditcard.FileCreditCardService;
import br.com.ribeiroribas.services.creditcard.SaveSicredCreditCardService;

public enum CreditCardBank {
    SICRED(new SaveSicredCreditCardService());

    private final FileCreditCardService fileCreditCardService;

    CreditCardBank(FileCreditCardService fileCreditCardService) {
        this.fileCreditCardService = fileCreditCardService;
    }

    public FileCreditCardService getBankService() {
        return fileCreditCardService;
    }
}
