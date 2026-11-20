import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public final class Historico {
    private final Editor editor;
    private final Deque<Editor.Estado> estados = new ArrayDeque<>();

    public Historico(Editor editor) {
        this.editor = Objects.requireNonNull(editor, "Editor obrigatorio");
    }

    public void salvar() {
        estados.push(editor.salvar());
    }

    public boolean desfazer() {
        if (estados.isEmpty()) {
            return false;
        }
        editor.restaurar(estados.pop());
        return true;
    }
}
