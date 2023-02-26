package Trabalho1;

import Trabalho1.Value;
import Trabalho1.Year;
import org.junit.Test;
import org.junit.Assert;

/**
 * Classe para realizar testes à classe Trabalho1.Year.
 */
public class YearTest {
    /**
     * Testa o método equals com dois objetos Trabalho1.Year iguais.
     */
    @Test
    public void equalsTest1() {
        Year year1 = new Year(2013, new Value(1000));
        Year year2 = new Year(2013, new Value(1000));

        boolean result = year1.equals(year2);

        Assert.assertTrue(result);
    }

    /**
     * Testa o método equals com dois objetos Trabalho1.Year diferentes.
     */
    @Test
    public void equalsTest2() {
        Year year1 = new Year(2013, new Value(1000));
        Year year2 = new Year(2014, new Value(1000));

        boolean result = year1.equals(year2);

        Assert.assertFalse(result);
    }

    /**
     * Testa o método compareTo com dois objetos Trabalho1.Year com o atributo ano de produção igual.
     */
    @Test
    public void compareToTest1() {
        //Anos iguais
        Year year1 = new Year(2013, new Value(1000));
        Year year2 = new Year(2013, new Value(1500));

        int result = year1.compareTo(year2);
        int expResult = 0;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto Trabalho1.Year com o ano de produção maior que o de outro objeto.
     */
    @Test
    public void compareToTest2() {
        //Ano 1 maior que Ano 2
        Year year1 = new Year(2014, new Value(1000));
        Year year2 = new Year(2013, new Value(1500));

        int result = year1.compareTo(year2);
        int expResult = 1;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto Trabalho1.Year com o ano de produção menor que o de outro objeto.
     */
    @Test
    public void compareToTest3() {
        //Ano 2 maior que Ano 1
        Year year1 = new Year(2013, new Value(1000));
        Year year2 = new Year(2014, new Value(1500));

        int result = year1.compareTo(year2);
        int expResult = -1;

        Assert.assertEquals(expResult, result);
    }
}
