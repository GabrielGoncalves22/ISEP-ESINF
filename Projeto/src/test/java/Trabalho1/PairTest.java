package Trabalho1;

import Trabalho1.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe para realizar testes à classe Trabalho1.Pair
 */
public class PairTest {

    /**
     * Testa o método equals com dois objetos Trabalho1.Pair iguais.
     */
    @Test
    public void equalsTest1() {
        Pair pair1 = new Pair("Portugal", 10);
        Pair pair2 = new Pair("Portugal", 10);
        boolean result = pair1.equals(pair2);
        Assert.assertTrue(result);
    }

    /**
     * Testa o método equals com dois objetos Trabalho1.Pair diferentes.
     */
    @Test
    public void equalsTest2() {
        Pair pair1 = new Pair("Portugal", 10);
        Pair pair2 = new Pair("Espanha", 10);
        boolean result = pair1.equals(pair2);
        Assert.assertFalse(result);
    }

    /**
     * Testa o método compareTo com dois objetos Trabalho1.Pair com o atributo value igual.
     */
    @Test
    public void compareToTest1() {
        Pair pair1 = new Pair("Portugal", 10);
        Pair pair2 = new Pair("Portugal", 10);

        int result = pair1.compareTo(pair2);
        int expResult = 0;
        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto Trabalho1.Pair com o value inferior que o de outro objeto.
     */
    @Test
    public void compareToTest2() {
        Pair pair1 = new Pair("Portugal", 10);
        Pair pair2 = new Pair("Espanha", 20);

        int result = pair1.compareTo(pair2);
        int expResult = -10;
        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto Trabalho1.Pair com o value maior que o de outro objeto.
     */
    @Test
    public void compareToTest3() {
        Pair pair2 = new Pair("Portugal", 10);
        Pair pair1 = new Pair("Espanha", 20);

        int result = pair1.compareTo(pair2);
        int expResult = 10;
        Assert.assertEquals(expResult, result);
    }
}
