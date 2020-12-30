package br.com.coder.cm;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import br.com.cm.models.Campo;

public class CampoTeste {

    private Campo campo = new Campo(3, 3);

    @Test
    public void testVizinhoReal() {
        Campo vizinho = new Campo(2, 2);

        boolean resultado = this.campo.adicionarVizinhos(vizinho);

        assertTrue(resultado);
    }

}
