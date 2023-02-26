package Trabalho2.model;

import org.junit.Assert;
import org.junit.Test;

public class AreaNameTest {

    /**
     * Teste ao método getAreaIdentifier para verificar se retorna o valor esperado.
     */
    @Test
    public void getAreaIdentifierTest1() {
        AreaName areaName = new AreaName("Croatia");

        String result = areaName.getAreaIdentifier();
        String expectedResult = "Croatia";

        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Teste ao método getAreaIdentifier para verificar se retorna um valor diferente do esperado.
     */
    @Test
    public void getAreaIdentifierTest2() {
        AreaName areaName = new AreaName("Croatia");

        String result = areaName.getAreaIdentifier();
        String expectedResult = "Portugal";

        Assert.assertNotEquals(expectedResult, result);
    }

    /**
     * Teste ao método getAreaIdentifier para verificar se retorna null.
     */
    @Test
    public void getAreaIdentifierTest3() {
        AreaName areaName = new AreaName(null);

        String result = areaName.getAreaIdentifier();

        Assert.assertNull(result);
    }

    /**
     * Testa o método equals com dois objetos AreaName iguais.
     */
    @Test
    public void equalsTest1() {
        AreaName areaName1 = new AreaName("Croatia");
        AreaName areaName2 = new AreaName("Croatia");

        boolean result = areaName1.equals(areaName2);

        Assert.assertTrue(result);
    }

    /**
     * Testa o método equals com dois objetos AreaName diferentes.
     */
    @Test
    public void equalsTest2() {
        AreaName areaName1 = new AreaName("Croatia");
        AreaName areaName2 = new AreaName("Portugal");

        boolean result = areaName1.equals(areaName2);

        Assert.assertFalse(result);
    }

    /**
     * Testa o método compareTo com dois objetos AreaName com o atributo nome igual.
     */
    @Test
    public void compareToTest1() {

        AreaName areaName1 = new AreaName("Croatia");
        AreaName areaName2 = new AreaName("Croatia");

        int result = areaName1.compareTo(areaName2);
        int expResult = 0;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto AreaName com o nome maior que o de outro objeto.
     */
    @Test
    public void compareToTest2() {
        AreaName areaName1 = new AreaName("Croatib");
        AreaName areaName2 = new AreaName("Croatia");

        int result = areaName1.compareTo(areaName2);
        int expResult = 1;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto AreaName com o nome menor que o de outro objeto.
     */
    @Test
    public void compareToTest3() {
        AreaName areaName1 = new AreaName("Croatia");
        AreaName areaName2 = new AreaName("Croatib");

        int result = areaName1.compareTo(areaName2);
        int expResult = -1;

        Assert.assertEquals(expResult, result);
    }
}
