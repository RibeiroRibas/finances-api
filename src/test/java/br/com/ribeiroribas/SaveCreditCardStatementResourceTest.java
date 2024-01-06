package br.com.ribeiroribas;

import br.com.ribeiroribas.services.creditcard.CreditCardService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.internal.util.IOUtils;
import jakarta.inject.Inject;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static io.restassured.RestAssured.given;

@QuarkusTest
class SaveCreditCardStatementResourceTest {

    @Inject
    CreditCardService service;

    private final String uri = "/upload/credit-card";

    private final String bank = "SICRED";

    @Test
    void whenRequestIsOkThenReturnCreditCardStatement() throws IOException {
        final byte[] bytes = IOUtils.toByteArray(getTestFile());

        given()
                .multiPart("pdfFile", "PDF file name", bytes)
                .multiPart("bank", bank)
                .when().post(uri)
                .then()
                .statusCode(200);
    }

    @Test
    void whenRequestWithoutFileThenBadRequest() throws IOException {
        given()
                .multiPart("bank", bank)
                .when().post(uri)
                .then()
                .statusCode(400)
                .body(CoreMatchers.containsString("file cannot be null"));

    }

    @Test
    void whenRequestWithoutBankThenBadRequest() throws IOException {
        final byte[] bytes = IOUtils.toByteArray(getTestFile());

        given()
                .multiPart("pdfFile", "PDF file name", bytes)
                .when().post(uri)
                .then()
                .statusCode(400)
                .body(CoreMatchers.containsString("bank cannot be null"));
    }

    private InputStream getTestFile() {
        return Objects.requireNonNull(getClass().getResourceAsStream("/sicredi.pdf"));
    }

}