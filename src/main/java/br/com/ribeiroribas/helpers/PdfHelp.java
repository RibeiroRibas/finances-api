package br.com.ribeiroribas.helpers;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
public class PdfHelp {

    public Iterator<String> getFileLinesContent(PDDocument document) {
        try {
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);
            PDFTextStripper tStripper = new PDFTextStripper();
            String fileContent = tStripper.getText(document);
            Stream<String> lines = fileContent.lines();
            return lines.iterator();
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return Collections.emptyIterator();
    }

    public LocalDate parseStrToLocalDate(String dateStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateStr, formatter);
    }

    public double parseStrToDouble(String lineContent) throws NumberFormatException {
        String valueStr = lineContent.replace("R$", "")
                .replace(".", "")
                .replace(",", ".")
                .strip();
        if (valueStr.contains("-")) {
            valueStr = valueStr.replace("-", "");
            return (Double.parseDouble(valueStr)) * -1;
        } else {
            return Double.parseDouble(valueStr);
        }
    }

    public LocalDate getLocalDateFromStr(String lineContent, String format){
        String transactionDateStr = lineContent.substring(0, format.length());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(transactionDateStr, formatter);
    }

    public boolean lineStartsWithDate(String dateStr, String dateFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat);
        try {
            dateStr = dateStr.substring(0, dateFormat.length());
            df.parse(dateStr);
            return true;
        } catch (ParseException | IndexOutOfBoundsException ex) {
            return false;
        }
    }

    public String removeExtraBlankSpaces(String lineContent) {
        while (lineContent.contains("  ")) {
            lineContent = lineContent.replaceAll(" {2}", " ");
        }
        return lineContent;
    }

    public boolean isLastPage(String lineContent) {
        int index = lineContent.indexOf("/");
        String page = lineContent.substring(0, index);
        String totalPage = lineContent.substring(index + 1);
        return page.equals(totalPage);
    }

    public boolean isTextPagination(String input) {
        String regex = "\\b([1-9]|[1-9][0-9])\\b" + "/" + "\\b([1-9]|[1-9][0-9])\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

}
