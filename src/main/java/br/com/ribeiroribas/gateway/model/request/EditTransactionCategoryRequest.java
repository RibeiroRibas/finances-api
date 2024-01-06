package br.com.ribeiroribas.gateway.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class EditTransactionCategoryRequest {

    @NotNull
    @FormParam("category_id")
    @PartType(MediaType.APPLICATION_JSON)
    private Long categoryId;

    @NotNull
    @FormParam("transaction_id")
    @PartType(MediaType.APPLICATION_JSON)
    private Long transactionId;

    @NotNull
    @FormParam("is_edit_all_categories")
    @PartType(MediaType.APPLICATION_JSON)
    private boolean isEditAllCategories;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public boolean isEditAllCategories() {
        return isEditAllCategories;
    }

    public void setEditAllCategories(boolean editAllCategories) {
        isEditAllCategories = editAllCategories;
    }
}
