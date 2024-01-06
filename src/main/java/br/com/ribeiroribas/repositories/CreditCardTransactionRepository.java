package br.com.ribeiroribas.repositories;

import br.com.ribeiroribas.domain.entities.CreditCardTransaction;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CreditCardTransactionRepository implements PanacheRepository<CreditCardTransaction> {
    public List<CreditCardTransaction> listByTenant(UUID tenant){
        return list("tenant", tenant);
    }
}
