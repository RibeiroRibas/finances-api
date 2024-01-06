package br.com.ribeiroribas.services;

import br.com.ribeiroribas.services.creditcard.CreditCardService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreditCardServiceTest {

    @InjectMock
    CreditCardService service;

    @Test
    void uploadOk(){

    }
}
