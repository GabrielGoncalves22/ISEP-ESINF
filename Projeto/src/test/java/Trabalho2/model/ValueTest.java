package Trabalho2.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe para realizar testes à classe Value.
 */
public class ValueTest {
    /**
     * Testa o método equals com dois objetos Value iguais.
     */
    @Test
    public void equalsTest1() {
        Value value1 = new Value("hg/ha", 1000, 'E', "Estimated value");
        Value value2 = new Value("hg/ha", 1000, 'E', "Estimated value");

        boolean result = value1.equals(value2);

        Assert.assertTrue(result);
    }

    /**
     * Testa o método equals com dois objetos Value diferentes.
     */
    @Test
    public void equalsTest2() {
        Value value1 = new Value("hg/ha", 1000, 'E', "Estimated value");
        Value value2 = new Value("hg/ha", 1500, 'E', "Estimated value");

        boolean result = value1.equals(value2);

        Assert.assertFalse(result);
    }

    /**
     * Testa o método compareTo com dois objetos Value com o atributo valor de produção igual.
     */
    @Test
    public void compareToTest1() {
        //Valores iguais
        Value value1 = new Value("hg/ha", 1000, 'E', "Estimated value");
        Value value2 = new Value("hg/ha", 1000, 'A', "Official figure");

        int result = value1.compareTo(value2);
        int expResult = 0;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto Value com o valor de produção maior que o de outro objeto.
     */
    @Test
    public void compareToTest2() {
        //Valor 1 maior que Valor 2
        Value value1 = new Value("hg/ha", 1500, 'E', "Estimated value");
        Value value2 = new Value("hg/ha", 1000, 'A', "Official figure");

        int result = value1.compareTo(value2);
        int expResult = 500;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto Value com o valor de produção menor que o de outro objeto.
     */
    @Test
    public void compareToTest3() {
        //Valor 2 maior que Valor 1
        Value value1 = new Value("hg/ha", 1000, 'E', "Estimated value");
        Value value2 = new Value("hg/ha", 1500, 'A', "Official figure");

        int result = value1.compareTo(value2);
        int expResult = -500;

        Assert.assertEquals(expResult, result);
    }
}
