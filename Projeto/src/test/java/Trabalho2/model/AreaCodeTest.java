package Trabalho2.model;

import org.junit.Assert;
import org.junit.Test;

public class AreaCodeTest {

    /**
     * Teste ao método getAreaIdentifier para verificar se retorna o valor esperado.
     */
    @Test
    public void getAreaIdentifierTest1() {
        AreaCode areaCode = new AreaCode("56433");

        String result = areaCode.getAreaIdentifier();
        String expectedResult = "56433";

        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Teste ao método getAreaIdentifier para verificar se retorna um valor diferente do esperado.
     */
    @Test
    public void getAreaIdentifierTest2() {
        AreaCode areaCode = new AreaCode("653");

        String result = areaCode.getAreaIdentifier();
        String expectedResult = "31";

        Assert.assertNotEquals(expectedResult, result);
    }

    /**
     * Teste ao método getAreaIdentifier para verificar se retorna null.
     */
    @Test
    public void getAreaIdentifierTest3() {
        AreaCode areaCode = new AreaCode(null);

        String result = areaCode.getAreaIdentifier();

        Assert.assertNull(result);
    }

    /**
     * Testa o método equals com dois objetos AreaCode iguais.
     */
    @Test
    public void equalsTest1() {

        AreaCode area1Code = new AreaCode("123");
        AreaCode area2Code = new AreaCode("123");

        boolean result = area1Code.equals(area2Code);

        Assert.assertTrue(result);
    }

    /**
     * Testa o método equals com dois objetos AreaCode diferentes.
     */
    @Test
    public void equalsTest2() {
        AreaCode area1Code = new AreaCode("123");
        AreaCode area2Code = new AreaCode("5123");

        boolean result = area1Code.equals(area2Code);

        Assert.assertFalse(result);
    }

    /**
     * Testa o método compareTo com dois objetos AreaCode com o atributo código igual.
     */
    @Test
    public void compareToTest1() {
        AreaCode area1Code = new AreaCode("123");
        AreaCode area2Code = new AreaCode("123");

        int result = area1Code.compareTo(area2Code);
        int expResult = 0;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto AreaCode com o código maior que o de outro objeto.
     */
    @Test
    public void compareToTest2() {
        AreaCode area1Code = new AreaCode("124");
        AreaCode area2Code = new AreaCode("123");

        int result = area1Code.compareTo(area2Code);
        int expResult = 1;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto AreaCode com o código menor que o de outro objeto.
     */
    @Test
    public void compareToTest3() {

        AreaCode area1Code = new AreaCode("124");
        AreaCode area2Code = new AreaCode("125");

        int result = area1Code.compareTo(area2Code);
        int expResult = -1;

        Assert.assertEquals(expResult, result);
    }

}
