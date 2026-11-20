import java.util.Objects;

public final class Editor {
    private String texto = "";

    public String ler() {
        return texto;
    }

    public void escrever(String texto) {
        this.texto = Objects.requireNonNull(texto, "Texto obrigatorio");
    }

    public Estado salvar() {
        return new Estado(texto);
    }

    public void restaurar(Estado estado) {
        this.texto = Objects.requireNonNull(estado, "Estado obrigatorio").texto;
    }

    // Somente o editor pode criar e acessar o conteudo do memento.
    public static final class Estado {
        private final String texto;

        private Estado(String texto) {
            this.texto = texto;
        }
    }
}
