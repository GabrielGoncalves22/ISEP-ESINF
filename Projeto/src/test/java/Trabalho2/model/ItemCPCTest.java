package Trabalho2.model;

import org.junit.Assert;
import org.junit.Test;

public class ItemCPCTest {

    /**
     * Teste ao método getItemIdentifier para verificar se retorna o valor esperado.
     */
    @Test
    public void getItemIdentifierTest1() {
        ItemCPC itemCPC = new ItemCPC("3333");

        String result = itemCPC.getItemIdentifier();
        String expectedResult = "3333";

        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Teste ao método getItemIdentifier para verificar se retorna um valor diferente do esperado.
     */
    @Test
    public void getItemIdentifierTest2() {
        ItemCPC itemCPC = new ItemCPC("661");

        String result = itemCPC.getItemIdentifier();
        String expectedResult = "31";

        Assert.assertNotEquals(expectedResult, result);
    }

    /**
     * Teste ao método getItemIdentifier para verificar se retorna null.
     */
    @Test
    public void getItemIdentifierTest3() {
        ItemCPC itemCPC = new ItemCPC(null);

        String result = itemCPC.getItemIdentifier();

        Assert.assertNull(result);
    }

    /**
     * Testa o método equals com dois objetos ItemCPC iguais.
     */
    @Test
    public void equalsTest1() {

        ItemCPC itemCPC1 = new ItemCPC("661");
        ItemCPC itemCPC2 = new ItemCPC("661");

        boolean result = itemCPC1.equals(itemCPC2);

        Assert.assertTrue(result);
    }

    /**
     * Testa o método equals com dois objetos ItemCPC diferentes.
     */
    @Test
    public void equalsTest2() {
        ItemCPC itemCPC1 = new ItemCPC("661");
        ItemCPC itemCPC2 = new ItemCPC("654");

        boolean result = itemCPC1.equals(itemCPC2);

        Assert.assertFalse(result);
    }

    /**
     * Testa o método compareTo com dois objetos ItemCPC com o atributo código igual.
     */
    @Test
    public void compareToTest1() {
        ItemCPC itemCPC1 = new ItemCPC("661");
        ItemCPC itemCPC2 = new ItemCPC("661");

        int result = itemCPC1.compareTo(itemCPC2);
        int expResult = 0;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto ItemCPC com o código maior que o de outro objeto.
     */
    @Test
    public void compareToTest2() {
        ItemCPC itemCPC1 = new ItemCPC("662");
        ItemCPC itemCPC2 = new ItemCPC("661");

        int result = itemCPC1.compareTo(itemCPC2);
        int expResult = 1;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto ItemCPC com o código menor que o de outro objeto.
     */
    @Test
    public void compareToTest3() {
        ItemCPC itemCPC1 = new ItemCPC("661");
        ItemCPC itemCPC2 = new ItemCPC("662");

        int result = itemCPC1.compareTo(itemCPC2);
        int expResult = -1;

        Assert.assertEquals(expResult, result);
    }
}
