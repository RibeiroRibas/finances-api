package br.com.ribeiroribas.domain.entities;

import br.com.ribeiroribas.domain.enums.PurchaseOrigin;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "credit_card_transaction")
public class CreditCardTransaction {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "purchase_date", length = 10)
    private LocalDate purchaseDate;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "purchase_value", length = 15)
    private double purchaseValue;
    @Column(length = 7)
    private String installment;
    @Column(length = 13)
    private PurchaseOrigin origin;
    @NotNull
    @Column(name = "tenant", length = 36)
    private UUID tenant;

    public CreditCardTransaction() {
    }

    public CreditCardTransaction(LocalDate purchaseDate, String description, double purchaseValue, String installment, PurchaseOrigin origin) {
        this.purchaseDate = purchaseDate;
        this.description = description;
        this.purchaseValue = purchaseValue;
        this.installment = installment;
        this.origin = origin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getDescription() {
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

    public double getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(double purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public PurchaseOrigin getOrigin() {
        return origin;
    }

    public void setOrigin(PurchaseOrigin origin) {
        this.origin = origin;
    }

    public UUID getTenant() {
        return tenant;
    }

    public void setTenant(UUID tenant) {
        this.tenant = tenant;
    }

    @Override
    public String toString() {
        return "CreditCardTransaction{" +
                "id=" + id +
                ", purchaseDate=" + purchaseDate +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", purchaseValue=" + purchaseValue +
                ", installment='" + installment + '\'' +
                ", origin=" + origin +
                ", userId=" + tenant +
                '}';
    }

    public Object getDescriptionWithoutNumbers() {
        return description.replaceAll("\\d","");    }
}
