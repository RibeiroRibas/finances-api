package br.com.ribeiroribas.repositories;

import br.com.ribeiroribas.domain.entities.Category;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category> {
    public Optional<Category> findByName(String name) {
        return find("name", name).firstResultOptional();
    }
}
