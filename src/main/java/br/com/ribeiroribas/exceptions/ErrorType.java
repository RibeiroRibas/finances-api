package br.com.ribeiroribas.exceptions;

public enum ErrorType {
    ATTRIBUTE_NOT_FOUND_EXCEPTION("Não foi possível extrair o atributo do arquivo selecionado");
    private final String errorMessage;

    ErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
