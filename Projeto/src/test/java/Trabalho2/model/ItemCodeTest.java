package Trabalho2.model;

import org.junit.Assert;
import org.junit.Test;

public class ItemCodeTest {

    /**
     * Teste ao método getItemIdentifier para verificar se retorna o valor esperado.
     */
    @Test
    public void getItemIdentifierTest1() {
        ItemCode itemCode = new ItemCode("51233");

        String result = itemCode.getItemIdentifier();
        String expectedResult = "51233";

        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Teste ao método getItemIdentifier para verificar se retorna um valor diferente do esperado.
     */
    @Test
    public void getItemIdentifierTest2() {
        ItemCode itemCode = new ItemCode("331");

        String result = itemCode.getItemIdentifier();
        String expectedResult = "1";

        Assert.assertNotEquals(expectedResult, result);
    }

    /**
     * Teste ao método getItemIdentifier para verificar se retorna null.
     */
    @Test
    public void getItemIdentifierTest3() {
        ItemCode itemCode = new ItemCode(null);

        String result = itemCode.getItemIdentifier();

        Assert.assertNull(result);
    }

    /**
     * Testa o método equals com dois objetos ItemCode iguais.
     */
    @Test
    public void equalsTest1() {
        ItemCode itemCode1 = new ItemCode("331");
        ItemCode itemCode2= new ItemCode("331");

        boolean result = itemCode1.equals(itemCode2);

        Assert.assertTrue(result);
    }

    /**
     * Testa o método equals com dois objetos ItemCode diferentes.
     */
    @Test
    public void equalsTest2() {
        ItemCode itemCode1 = new ItemCode("331");
        ItemCode itemCode2= new ItemCode("9876");

        boolean result = itemCode1.equals(itemCode2);

        Assert.assertFalse(result);
    }

    /**
     * Testa o método compareTo com dois objetos ItemCode com o atributo código igual.
     */
    @Test
    public void compareToTest1() {
        ItemCode itemCode1 = new ItemCode("331");
        ItemCode itemCode2= new ItemCode("331");

        int result = itemCode1.compareTo(itemCode2);
        int expResult = 0;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto ItemCode com o código maior que o de outro objeto.
     */
    @Test
    public void compareToTest2() {
        ItemCode itemCode1 = new ItemCode("332");
        ItemCode itemCode2= new ItemCode("331");

        int result = itemCode1.compareTo(itemCode2);
        int expResult = 1;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto ItemCode com o código menor que o de outro objeto.
     */
    @Test
    public void compareToTest3() {
        ItemCode itemCode1= new ItemCode("331");
        ItemCode itemCode2 = new ItemCode("332");

        int result = itemCode1.compareTo(itemCode2);
        int expResult = -1;

        Assert.assertEquals(expResult, result);
    }
}
