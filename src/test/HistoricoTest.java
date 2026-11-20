import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HistoricoTest {
    @Test
    void desfazEmOrdem() {
        Editor editor = new Editor();
        Historico historico = new Historico(editor);
        historico.salvar();
        editor.escrever("Primeira versao");
        historico.salvar();
        editor.escrever("Segunda versao");

        assertTrue(historico.desfazer());
        assertEquals("Primeira versao", editor.ler());
        assertTrue(historico.desfazer());
        assertEquals("", editor.ler());
        assertFalse(historico.desfazer());
    }

    @Test
    void mantemTextoSemEstado() {
        Editor editor = new Editor();
        Historico historico = new Historico(editor);
        editor.escrever("Texto atual");

        assertFalse(historico.desfazer());
        assertEquals("Texto atual", editor.ler());
    }

    @Test
    void separaHistoricos() {
        Editor primeiro = new Editor();
        Editor segundo = new Editor();
        Historico historico = new Historico(primeiro);
        Historico outro = new Historico(segundo);
        primeiro.escrever("Original");
        historico.salvar();
        primeiro.escrever("Editado");
        segundo.escrever("Independente");

        assertTrue(historico.desfazer());
        assertEquals("Original", primeiro.ler());
        assertFalse(outro.desfazer());
        assertEquals("Independente", segundo.ler());
    }

    @Test
    void rejeitaEditorNulo() {
        assertThrows(NullPointerException.class, () -> new Historico(null));
    }
}
