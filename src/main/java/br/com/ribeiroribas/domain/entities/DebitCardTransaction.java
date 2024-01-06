package br.com.ribeiroribas.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "debit_card_transaction")
public class DebitCardTransaction {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "transaction_date")
    private LocalDate transactionDate;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "transaction_value")
    private double transactionValue;
    @NotNull
    @Column(name = "tenant", length = 36)
    private UUID tenant;

    public DebitCardTransaction() {
    }

    public DebitCardTransaction(LocalDate transactionDate, String description, double transactionValue) {
        this.transactionDate = transactionDate;
        this.description = description;
        this.transactionValue = transactionValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescriptionWithoutNumbers() {
        return description.replaceAll("\\d","");
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(double transactionValue) {
        this.transactionValue = transactionValue;
    }

    public UUID getTenant() {
        return tenant;
    }

    public void setTenant(UUID tenant) {
        this.tenant = tenant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DebitCardTransaction that = (DebitCardTransaction) o;
        return Double.compare(transactionValue, that.transactionValue) == 0 && Objects.equals(transactionDate, that.transactionDate) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionDate, description, transactionValue);
    }

    @Override
    public String toString() {
        return "DebitCardTransaction{" +
                "id=" + id +
                ", transactionDate=" + transactionDate +
                ", description='" + description + '\'' +
                ", transactionValue=" + transactionValue +
                '}';
    }
}
