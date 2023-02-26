package Trabalho2.model;

import org.junit.Assert;
import org.junit.Test;

public class ElementCodeTest {

    /**
     * Teste ao método getElementIdentifier para verificar se retorna o valor esperado.
     */
    @Test
    public void getElementIdentifierTest1() {
        ElementCode elementCode = new ElementCode("5152");

        String result = elementCode.getElementIdentifier();
        String expectedResult = "5152";

        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Teste ao método getElementIdentifier para verificar se retorna um valor diferente do esperado.
     */
    @Test
    public void getElementIdentifierTest2() {
        ElementCode elementCode = new ElementCode("512431");

        String result = elementCode.getElementIdentifier();
        String expectedResult = "523";

        Assert.assertNotEquals(expectedResult, result);
    }

    /**
     * Teste ao método getElementIdentifier para verificar se retorna null.
     */
    @Test
    public void getElementIdentifierTest3() {
        ElementCode elementCode = new ElementCode(null);
        String result = elementCode.getElementIdentifier();

        Assert.assertNull(result);
    }

    /**
     * Testa o método equals com dois objetos ElementCode iguais.
     */
    @Test
    public void equalsTest1() {
        ElementCode elementCode1 = new ElementCode("512431");
        ElementCode elementCode2 = new ElementCode("512431");

        boolean result = elementCode1.equals(elementCode2);

        Assert.assertTrue(result);
    }

    /**
     * Testa o método equals com dois objetos ElementCode diferentes.
     */
    @Test
    public void equalsTest2() {
        ElementCode elementCode1 = new ElementCode("512431");
        ElementCode elementCode2 = new ElementCode("412");

        boolean result = elementCode1.equals(elementCode2);

        Assert.assertFalse(result);
    }

    /**
     * Testa o método compareTo com dois objetos ElementCode com o atributo código igual.
     */
    @Test
    public void compareToTest1() {
        ElementCode elementCode1 = new ElementCode("512431");
        ElementCode elementCode2 = new ElementCode("512431");

        int result = elementCode1.compareTo(elementCode2);
        int expResult = 0;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto ElementCode com o código maior que o de outro objeto.
     */
    @Test
    public void compareToTest2() {
        ElementCode elementCode1 = new ElementCode("124");
        ElementCode elementCode2 = new ElementCode("123");


        int result = elementCode1.compareTo(elementCode2);
        int expResult = 1;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto ElementCode com o código menor que o de outro objeto.
     */
    @Test
    public void compareToTest3() {
        ElementCode elementCode1 = new ElementCode("123");
        ElementCode elementCode2 = new ElementCode("124");

        int result = elementCode1.compareTo(elementCode2);
        int expResult = -1;

        Assert.assertEquals(expResult, result);
    }
}
