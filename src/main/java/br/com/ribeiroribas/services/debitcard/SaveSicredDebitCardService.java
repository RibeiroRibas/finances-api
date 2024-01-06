package br.com.ribeiroribas.services.debitcard;

import br.com.ribeiroribas.domain.entities.DebitCardTransaction;
import br.com.ribeiroribas.helpers.PdfHelp;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SaveSicredDebitCardService implements FileDebitCardService {

    private static final String EXTRACT = "extrato";
    private static final String SICRED = "sicredi";
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String SPACE = " ";
    private static final String EMPTY = "";
    private final PdfHelp pdfHelp;

    public SaveSicredDebitCardService() {
        this.pdfHelp = new PdfHelp();
    }

    @Override
    public boolean isPdfOwnedBySelectedBank(PDDocument document) {
        Iterator<String> fileLinesContent = pdfHelp.getFileLinesContent(document);
        boolean isDebitCard = false;
        boolean isSicredBank = false;
        while (fileLinesContent.hasNext()) {
            String lineContent = fileLinesContent.next();
            if (lineContent.toLowerCase().contains(EXTRACT)) {
                isDebitCard = true;
            }
            if (lineContent.toLowerCase().contains(SICRED)) {
                isSicredBank = true;
            }
        }
        return isDebitCard && isSicredBank;
    }

    @Override
    public List<DebitCardTransaction> getTransactions(PDDocument document) {
        Iterator<String> fileLinesContent = pdfHelp.getFileLinesContent(document);
        List<DebitCardTransaction> debitCardTransactions = new ArrayList<>();
        while (fileLinesContent.hasNext()) {
            String lineContent = fileLinesContent.next();

            if (pdfHelp.lineStartsWithDate(lineContent, DATE_FORMAT)) {
                debitCardTransactions.add(getTransaction(lineContent));
            }
        }
        return debitCardTransactions;
    }

    private DebitCardTransaction getTransaction(String lineContent) {
        LocalDate transactionDate = pdfHelp.getLocalDateFromStr(lineContent, DATE_FORMAT);

        lineContent = removeTransactionDate(lineContent);
        lineContent = removeBalance(lineContent);

        int index = lineContent.lastIndexOf(SPACE);
        String transactionValueStr = lineContent.substring(index);
        String description = getDescription(lineContent, transactionValueStr);
        double transactionValue = pdfHelp.parseStrToDouble(transactionValueStr.trim());

        return new DebitCardTransaction(transactionDate, description, transactionValue);
    }

    private String getDescription(String lineContent, String transactionValueStr) {
        String description = lineContent.replace(transactionValueStr, EMPTY);
        return pdfHelp.removeExtraBlankSpaces(description).trim();
    }

    private String removeTransactionDate(String lineContent) {
        return lineContent.substring(lineContent.indexOf(" ")).trim();
    }

    private String removeBalance(String lineContent) {
        int index = lineContent.lastIndexOf(SPACE);
        String balance = lineContent.substring(index);
        return lineContent.replace(balance, EMPTY);
    }

}
