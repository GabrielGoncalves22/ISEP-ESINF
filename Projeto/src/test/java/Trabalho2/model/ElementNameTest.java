package Trabalho2.model;

import org.junit.Assert;
import org.junit.Test;

public class ElementNameTest {

    /**
     * Teste ao método getElementIdentifier para verificar se retorna o valor esperado.
     */
    @Test
    public void getElementIdentifierTest1() {
        ElementName elementName = new ElementName("5152");

        String result = elementName.getElementIdentifier();
        String expectedResult = "5152";

        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Teste ao método getElementIdentifier para verificar se retorna um valor diferente do esperado.
     */
    @Test
    public void getElementIdentifierTest2() {
        ElementName elementName = new ElementName("3121312");

        String result = elementName.getElementIdentifier();
        String expectedResult = "31";

        Assert.assertNotEquals(expectedResult, result);
    }

    /**
     * Teste ao método getElementIdentifier para verificar se retorna um valor diferente do esperado.
     */
    @Test
    public void getElementIdentifierTest3() {
        ElementName elementName = new ElementName(null);
        String result = elementName.getElementIdentifier();

        Assert.assertNull(result);
    }

    /**
     * Testa o método equals com dois objetos ElementName iguais.
     */
    @Test
    public void equalsTest1() {
        ElementName elementName1 = new ElementName("5152");
        ElementName elementName2 = new ElementName("5152");

        boolean result = elementName1.equals(elementName2);

        Assert.assertTrue(result);
    }

    /**
     * Testa o método equals com dois objetos ElementName diferentes.
     */
    @Test
    public void equalsTest2() {
        ElementName elementName1 = new ElementName("5152");
        ElementName elementName2 = new ElementName("98765");

        boolean result = elementName1.equals(elementName2);

        Assert.assertFalse(result);
    }

    /**
     * Testa o método compareTo com dois objetos ElementName com o atributo nome igual.
     */
    @Test
    public void compareToTest1() {
        ElementName elementName1 = new ElementName("5152");
        ElementName elementName2 = new ElementName("5152");

        int result = elementName1.compareTo(elementName2);
        int expResult = 0;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objetos ElementName com o atributo nome maior que o de outro objeto.
     */
    @Test
    public void compareToTest2() {
        ElementName elementName1 = new ElementName("5153");
        ElementName elementName2 = new ElementName("5152");

        int result = elementName1.compareTo(elementName2);
        int expResult = 1;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objetos ElementName com o atributo nomemenor que o de outro objeto.
     */
    @Test
    public void compareToTest3() {
        ElementName elementName1 = new ElementName("5152");
        ElementName elementName2 = new ElementName("5153");

        int result = elementName1.compareTo(elementName2);
        int expResult = -1;

        Assert.assertEquals(expResult, result);
    }

}
