package Trabalho2.model;

import org.junit.Assert;
import org.junit.Test;

public class ItemNameTest {

    /**
     * Teste ao método getItemIdentifier para verificar se retorna o valor esperado.
     */
    @Test
    public void getItemIdentifierTest1() {
        ItemName itemName = new ItemName("Raw milk");

        String result = itemName.getItemIdentifier();
        String expectedResult = "Raw milk";

        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Teste ao método getItemIdentifier para verificar se retorna um valor diferente do esperado.
     */
    @Test
    public void getItemIdentifierTest2() {
        ItemName itemName = new ItemName("Raw milk");

        String result = itemName.getItemIdentifier();
        String expectedResult = "Raw";

        Assert.assertNotEquals(expectedResult, result);
    }

    /**
     * Teste ao método getItemIdentifier para verificar se retorna null.
     */
    @Test
    public void getItemIdentifierTest3() {
        ItemName itemName = new ItemName(null);

        String result = itemName.getItemIdentifier();

        Assert.assertNull(result);
    }


    /**
     * Testa o método equals com dois objetos ItemName iguais.
     */
    @Test
    public void equalsTest1() {
        ItemName itemName1 = new ItemName("Raw milk");
        ItemName itemName2 = new ItemName("Raw milk");

        boolean result = itemName1.equals(itemName2);

        Assert.assertTrue(result);
    }

    /**
     * Testa o método equals com dois objetos ItemName diferentes.
     */
    @Test
    public void equalsTest2() {
        ItemName itemName1 = new ItemName("Raw milk");
        ItemName itemName2 = new ItemName("Raw Ice");

        boolean result = itemName1.equals(itemName2);

        Assert.assertFalse(result);
    }

    /**
     * Testa o método compareTo com dois objetos ItemName com o atributo nome igual.
     */
    @Test
    public void compareToTest1() {
        ItemName itemName1 = new ItemName("Raw milk");
        ItemName itemName2 = new ItemName("Raw milk");

        int result = itemName1.compareTo(itemName2);
        int expResult = 0;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto ItemName com o nome maior que o de outro objeto.
     */
    @Test
    public void compareToTest2() {
        ItemName itemName1 = new ItemName("Raw mill");
        ItemName itemName2 = new ItemName("Raw milk");

        int result = itemName1.compareTo(itemName2);
        ;
        int expResult = 1;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto ItemName com o nome menor que o de outro objeto.
     */
    @Test
    public void compareToTest3() {

        ItemName itemName1 = new ItemName("Raw milk");
        ItemName itemName2 = new ItemName("Raw mill");

        int result = itemName1.compareTo(itemName2);
        ;
        int expResult = -1;

        Assert.assertEquals(expResult, result);
    }
}
