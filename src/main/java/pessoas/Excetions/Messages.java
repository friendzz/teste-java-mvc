package pessoas.Excetions;

public enum Messages {

    PESSOA_NAO_ENCONTRADA ("Pessoa não encontrada"),
    NAO_TEM_DADOS ("Nao tem dados nenhum");

    private final String messages;

    Messages(String messages) {
        this.messages = messages;
    }
    @Override
    public String toString() {
        return messages;
    }

}
