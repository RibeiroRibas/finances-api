package br.com.ribeiroribas.domain.enums;

import br.com.ribeiroribas.services.debitcard.FileDebitCardService;
import br.com.ribeiroribas.services.debitcard.SaveSicredDebitCardService;

public enum DebitCardBank {
    SICRED(new SaveSicredDebitCardService());

    private SaveSicredDebitCardService sicredDebitCardService;
    private final FileDebitCardService fileDebitCardService;

    DebitCardBank(FileDebitCardService fileDebitCardService) {
        this.fileDebitCardService = fileDebitCardService;
    }

    public FileDebitCardService getBankService() {
        return fileDebitCardService;
    }
}
