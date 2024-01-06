package br.com.ribeiroribas.repositories;

import br.com.ribeiroribas.domain.entities.DebitCardTransaction;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class DebitCardTransactionRepository implements PanacheRepository<DebitCardTransaction> {

    public Optional<DebitCardTransaction> findByDescriptionAndTenant(Map<String, Object> params) {
        return find("description= :description and tenant= :tenant", params).firstResultOptional();
    }

    public List<DebitCardTransaction> listByTenant(UUID tenant) {
        return list("tenant", tenant);
    }
}
