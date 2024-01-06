package br.com.ribeiroribas.services.creditcard;

import br.com.ribeiroribas.domain.entities.CreditCardTransaction;
import br.com.ribeiroribas.domain.enums.PurchaseOrigin;
import br.com.ribeiroribas.helpers.PdfHelp;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaveSicredCreditCardService implements FileCreditCardService {

    private static final String CREDIT_CARD_SICRED = "cartão sicredi";
    private static final String DUE_DATE = "data de vencimento";
    private static final String AMOUNT = "pagamento total";
    private static final String BRAZIL_EXPENSES = "despesas no brasil";
    private static final String INTERNATIONAL_EXPENSES = "despesas no exterior";
    private static final String EMPTY = "";
    private static final String SPACE = " ";
    private static final String DATE_FORMAT = "dd/MM";
    private static final String TRADING_NAME = "Nome fantasia";

    private final PdfHelp pdfHelp;

    public SaveSicredCreditCardService() {
        this.pdfHelp = new PdfHelp();
    }

    @Override
    public boolean isPdfOwnedBySelectedBank(PDDocument document) {
        Iterator<String> fileLinesContent = pdfHelp.getFileLinesContent(document);
        while (fileLinesContent.hasNext()) {
            String lineContent = fileLinesContent.next();
            if (lineContent.toLowerCase().contains(CREDIT_CARD_SICRED)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public LocalDate getDueDate(PDDocument document) {
        Iterator<String> fileLinesContent = pdfHelp.getFileLinesContent(document);
        try {
            while (fileLinesContent.hasNext()) {
                String lineContent = fileLinesContent.next();
                if (lineContent.toLowerCase().contains(DUE_DATE)) {
                    lineContent = fileLinesContent.hasNext() ? fileLinesContent.next() : lineContent;
                    return pdfHelp.parseStrToLocalDate(lineContent);
                }
            }
        } catch (DateTimeParseException ex) {
            System.out.println("DateTimeParseException");
        }
        return null;
    }

    @Override
    public Double getAmount(PDDocument document) {
        Iterator<String> fileLinesContent = pdfHelp.getFileLinesContent(document);
        try {
            while (fileLinesContent.hasNext()) {
                String lineContent = fileLinesContent.next();
                System.out.println(lineContent);
                if (lineContent.toLowerCase().contains(AMOUNT)) {
                    lineContent = fileLinesContent.hasNext() ? fileLinesContent.next() : lineContent;
                    return pdfHelp.parseStrToDouble(lineContent);
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("NumberFormatException");
        }
        return null;
    }

    @Override
    public List<CreditCardTransaction> getBrazilTransactions(PDDocument document) {
        return getTransactionsFromOrigin(document, BRAZIL_EXPENSES);
    }

    @Override
    public List<CreditCardTransaction> getInternationalTransactions(PDDocument document) {
        return getTransactionsFromOrigin(document, INTERNATIONAL_EXPENSES);
    }

    private List<CreditCardTransaction> getTransactionsFromOrigin(PDDocument document, String transactionOrigin) {
        List<CreditCardTransaction> transactions = new ArrayList<>();
        Iterator<String> fileLinesContent = pdfHelp.getFileLinesContent(document);
        try {
            while (fileLinesContent.hasNext()) {
                String lineContent = fileLinesContent.next();
                while (!lineContent.toLowerCase().contains(transactionOrigin) && fileLinesContent.hasNext()) {
                    lineContent = fileLinesContent.next();
                }
                if (lineContent.toLowerCase().contains(transactionOrigin)) {
                    while (!pdfHelp.lineStartsWithDate(lineContent, DATE_FORMAT) && fileLinesContent.hasNext()) {
                        lineContent = fileLinesContent.next();
                    }

                    while (pdfHelp.lineStartsWithDate(lineContent, DATE_FORMAT) && fileLinesContent.hasNext()) {

                        transactions.add(getExpenses(lineContent, transactionOrigin));

                        lineContent = fileLinesContent.next();
                    }
                }
                while (!pdfHelp.isTextPagination(lineContent) && fileLinesContent.hasNext()) {
                    lineContent = fileLinesContent.next();
                }
                if (pdfHelp.isTextPagination(lineContent)) {
                    if (pdfHelp.isLastPage(lineContent)) {
                        return transactions;
                    }
                }
            }
        } catch (DateTimeParseException ex) {
            System.out.println("DateTimeParseException");
        } catch (NumberFormatException ex) {
            System.out.println("NumberFormatException");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("IndexOutOfBoundsException");
        }
        return transactions;
    }


    private CreditCardTransaction getExpenses(String lineContent, String expenseOrigin) throws IndexOutOfBoundsException {
        lineContent = pdfHelp.removeExtraBlankSpaces(lineContent);

        LocalDate purchaseDate = getPurchaseDate(lineContent);
        lineContent = removePurchaseDate(lineContent);

        String installment = getInstallment(lineContent);
        lineContent = removeInstallment(lineContent, installment);

        String description = getDescription(lineContent);

        double purchaseValue = getPurchaseValue(lineContent);

        return new CreditCardTransaction(purchaseDate,
                description,
                purchaseValue,
                installment,
                expenseOrigin.equals(BRAZIL_EXPENSES) ? PurchaseOrigin.BRAZIL : PurchaseOrigin.INTERNATIONAL);
    }

    private double getPurchaseValue(String lineContent) {
        int index = lineContent.lastIndexOf(SPACE);
        String valueStr = lineContent.substring(index).trim();
        return pdfHelp.parseStrToDouble(valueStr);
    }

    private LocalDate getPurchaseDate(String lineContent) throws IndexOutOfBoundsException {
        String purchaseDateStr = lineContent.substring(0, 5);
        purchaseDateStr = purchaseDateStr + "/" + LocalDate.now().getYear();
        return pdfHelp.parseStrToLocalDate(purchaseDateStr);
    }

    private String getDescription(String lineContent) throws IndexOutOfBoundsException {
        Pattern pattern = Pattern.compile("\\s[A-Z]{3}\\s");
        Matcher matcher = pattern.matcher(lineContent);
        String coin = "";
        while (matcher.find()) {
            coin = matcher.group();
        }
        if (!coin.isEmpty()) {
            int index = lineContent.indexOf(coin);
            return lineContent.substring(0, index).trim().replaceAll("\\d", EMPTY);
        }
        return "";
    }

    private String removeInstallment(String lineContent, String installment) {
        return lineContent.replace(installment, EMPTY);
    }

    private String removePurchaseDate(String lineContent) {
        return lineContent.substring(6);
    }

    private String getInstallment(String input) {
        Pattern pattern = Pattern.compile("\\b\\d{2}/\\d{2}\\b");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group();
        }
        return EMPTY;
    }
}
