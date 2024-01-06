package br.com.ribeiroribas.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "credit_card_statement")
public class CreditCardStatement {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "due_date")
    private LocalDate dueDate;
    private Double amount;
    @NotNull
    @Column(name = "user_id", length = 36)
    private UUID tenant;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "credit_card_transaction_id")
    private List<CreditCardTransaction> transactions;

    public CreditCardStatement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<CreditCardTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<CreditCardTransaction> expens) {
        if (this.transactions == null) {
            this.transactions = expens;
        } else {
            this.transactions.addAll(expens);
        }
    }

    public UUID getTenant() {
        return tenant;
    }

    public void setTenant(UUID tenant) {
        this.tenant = tenant;
    }

    @Override
    public String toString() {
        return "CreditCardStatement{" +
                "id=" + id +
                ", dueDate=" + dueDate +
                ", amount=" + amount +
                ", userId=" + tenant +
                ", expenses=" + transactions +
                '}';
    }
}
