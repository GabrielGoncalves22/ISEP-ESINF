package Trabalho2.model;

import org.junit.Assert;
import org.junit.Test;

public class AreaCodeM49Test {

    /**
     * Teste ao método getAreaIdentifier para verificar se retorna o valor esperado.
     */
    @Test
    public void getAreaIdentifierTest1() {
        AreaCodeM49 areaCodeM49 = new AreaCodeM49("3214");

        String result = areaCodeM49.getAreaIdentifier();
        String expectedResult = "3214";

        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Teste ao método getAreaIdentifier para verificar se retorna um valor diferente do esperado.
     */
    @Test
    public void getAreaIdentifierTest2() {
        AreaCodeM49 areaCodeM49 = new AreaCodeM49("934");

        String result = areaCodeM49.getAreaIdentifier();
        String expectedResult = "31";

        Assert.assertNotEquals(expectedResult, result);
    }

    /**
     * Teste ao método getAreaIdentifier para verificar se retorna null.
     */
    @Test
    public void getAreaIdentifierTest3() {
        AreaCodeM49 areaCodeM49 = new AreaCodeM49(null);

        String result = areaCodeM49.getAreaIdentifier();

        Assert.assertNull(result);
    }

    /**
     * Testa o método equals com dois objetos AreaCodeM49 iguais.
     */
    @Test
    public void equalsTest1() {
        AreaCodeM49 area1CodeM49 = new AreaCodeM49("123");
        AreaCodeM49 area2CodeM49 = new AreaCodeM49("123");

        boolean result = area1CodeM49.equals(area2CodeM49);

        Assert.assertTrue(result);
    }

    /**
     * Testa o método equals com dois objetos AreaCodeM49 diferentes.
     */
    @Test
    public void equalsTest2() {
        AreaCodeM49 area1CodeM49 = new AreaCodeM49("123");
        AreaCodeM49 area2CodeM49 = new AreaCodeM49("12345");

        boolean result = area1CodeM49.equals(area2CodeM49);

        Assert.assertFalse(result);
    }

    /**
     * Testa o método compareTo com dois objetos AreaCodeM49 com o atributo código M49 igual.
     */
    @Test
    public void compareToTest1() {
        //Códigos M49 iguais
        AreaCodeM49 area1CodeM49 = new AreaCodeM49("123");
        AreaCodeM49 area2CodeM49 = new AreaCodeM49("123");

        int result = area1CodeM49.compareTo(area2CodeM49);
        int expResult = 0;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto AreaCodeM49 com o código M49 maior que o de outro objeto.
     */
    @Test
    public void compareToTest2() {
        //Código M49 1 maior que Código M49 2
        AreaCodeM49 area1CodeM49 = new AreaCodeM49("124");
        AreaCodeM49 area2CodeM49 = new AreaCodeM49("123");

        int result = area1CodeM49.compareTo(area2CodeM49);
        int expResult = 1;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto AreaCodeM49 com o código M49 menor que o de outro objeto.
     */
    @Test
    public void compareToTest3() {
        //Código M49 2 maior que Código M49 1
        AreaCodeM49 area1CodeM49 = new AreaCodeM49("123");
        AreaCodeM49 area2CodeM49 = new AreaCodeM49("124");

        int result = area1CodeM49.compareTo(area2CodeM49);
        int expResult = -1;

        Assert.assertEquals(expResult, result);
    }
}
