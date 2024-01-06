package br.com.ribeiroribas.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Column(length = 50)
    private String name;
    @NotNull
    @Column(name = "tenant", length = 36)
    private UUID tenant;
    @OneToMany(mappedBy = "category")
    private List<DebitCardTransaction> debitTransactions;
    @OneToMany(mappedBy = "category")
    private List<CreditCardTransaction> creditTransactions;

    public Category() {
    }

    public Category(String name, UUID tenant) {
        this.name = name;
        this.tenant = tenant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getTenant() {
        return tenant;
    }

    public void setTenant(UUID tenant) {
        this.tenant = tenant;
    }

    public List<DebitCardTransaction> getDebitTransactions() {
        return debitTransactions;
    }

    public void setDebitTransactions(List<DebitCardTransaction> debitTransactions) {
        this.debitTransactions = debitTransactions;
    }

    public List<CreditCardTransaction> getCreditTransactions() {
        return creditTransactions;
    }

    public void setCreditTransactions(List<CreditCardTransaction> creditTransactions) {
        this.creditTransactions = creditTransactions;
    }
}
