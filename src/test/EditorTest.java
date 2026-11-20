import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditorTest {
    @Test
    void iniciaVazio() {
        assertEquals("", new Editor().ler());
    }

    @Test
    void restauraTexto() {
        Editor editor = new Editor();
        editor.escrever("Primeira versao");
        Editor.Estado estado = editor.salvar();
        editor.escrever("Segunda versao");

        editor.restaurar(estado);

        assertEquals("Primeira versao", editor.ler());
    }

    @Test
    void preservaEstado() {
        Editor editor = new Editor();
        editor.escrever("Original");
        Editor.Estado estado = editor.salvar();
        editor.escrever("Alterado");
        editor.restaurar(estado);
        editor.escrever("Outra edicao");

        editor.restaurar(estado);

        assertEquals("Original", editor.ler());
    }

    @Test
    void rejeitaNulos() {
        Editor editor = new Editor();
        editor.escrever("Texto");

        assertThrows(NullPointerException.class, () -> editor.escrever(null));
        assertThrows(NullPointerException.class, () -> editor.restaurar(null));
        assertEquals("Texto", editor.ler());
    }
}
