package Trabalho1;

import Trabalho1.Area;
import org.junit.Test;
import org.junit.Assert;

/**
 * Classe para realizar testes à classe Trabalho1.Area.
 */
public class AreaTest {
    /**
     * Testa o método equals com dois objetos Trabalho1.Area iguais.
     */
    @Test
    public void equalsTest1() {
        Area area1 = new Area("Portugal");
        Area area2 = new Area("Portugal");
        boolean result = area1.equals(area2);
        Assert.assertTrue(result);
    }

    /**
     * Testa o método equals com dois objetos Trabalho1.Area diferentes.
     */
    @Test
    public void equalsTest2() {
        Area area1 = new Area("Portugal");
        Area area2 = new Area("Espanha");
        boolean result = area1.equals(area2);
        Assert.assertFalse(result);
    }

    /**
     * Testa o método compareTo com dois objetos Trabalho1.Area com o atributo ‘country’ igual.
     */
    @Test
    public void compareToTest1() {
        Area area1 = new Area("Portugal");
        Area area2 = new Area("Portugal");

        int result = area1.compareTo(area2);
        int expResult = 0;
        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto Trabalho1.Area com o nome do país maior alfabeticamente que o de outro objeto.
     */
    @Test
    public void compareToTest2() {
        Area area1 = new Area("Albania");
        Area area2 = new Area("Camaroes");

        int result = area1.compareTo(area2);
        int expResult = -2;
        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto Trabalho1.Area com o nome do país menor alfabeticamente que o de outro objeto.
     */
    @Test
    public void compareToTest3() {
        Area area1 = new Area("Camaroes");
        Area area2 = new Area("Albania");

        int result = area1.compareTo(area2);
        int expResult = 2;
        Assert.assertEquals(expResult, result);
    }
}
