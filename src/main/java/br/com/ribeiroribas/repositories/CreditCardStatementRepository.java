package br.com.ribeiroribas.repositories;

import br.com.ribeiroribas.domain.entities.CreditCardStatement;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.Optional;

@ApplicationScoped
public class CreditCardStatementRepository implements PanacheRepository<CreditCardStatement> {
    public Optional<CreditCardStatement> findByDueDate(LocalDate dueDate) {
        return find("dueDate", dueDate).firstResultOptional();
    }
}
