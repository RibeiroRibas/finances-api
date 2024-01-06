package br.com.ribeiroribas.services;

import br.com.ribeiroribas.domain.entities.Category;
import br.com.ribeiroribas.gateway.model.response.CategoryResponse;
import br.com.ribeiroribas.repositories.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CategoryService {

    @Inject
    private CategoryRepository repository;

    @Inject
    private JsonWebToken jwt;

    @Transactional
    public CategoryResponse save(String name) {
        Optional<Category> categoryByName = repository.findByName(name);

        if (categoryByName.isPresent()) {
            return new CategoryResponse(categoryByName.get().getId(), categoryByName.get().getName());
        }

        Category category = new Category(name, UUID.fromString(jwt.getSubject()));
        repository.persist(category);
        repository.flush();
        return new CategoryResponse(category.getId(), category.getName());

    }
}
