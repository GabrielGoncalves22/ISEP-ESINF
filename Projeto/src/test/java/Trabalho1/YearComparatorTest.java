package Trabalho1;

import Trabalho1.Value;
import Trabalho1.Year;
import Trabalho1.YearComparator;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;

/**
 * Classe para realizar testes à classe Trabalho1.YearComparator.
 */
public class YearComparatorTest {

    /**
     * Testa o método compare para verificar se a função devolve com anos diferentes os valores ordenados pelos critérios
     * pedidos
     */
    @Test
    public void compareTest1() {
        Year year1 = new Year(2013, new Value(1000));
        Year year2 = new Year(1990, new Value(1500));
        Year year3 = new Year(2012, new Value(1000));
        Year year4 = new Year(1916, new Value(1500));
        Year year5 = new Year(2000, new Value(1000));

        ArrayList<Year> result = new ArrayList<>();
        result.add(year1);
        result.add(year2);
        result.add(year3);
        result.add(year4);
        result.add(year5);

        result.sort(new YearComparator());

        ArrayList<Year> expResult = new ArrayList<>();
        expResult.add(year4);
        expResult.add(year2);
        expResult.add(year5);
        expResult.add(year3);
        expResult.add(year1);

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compare para verificar se a função devolve com anos iguais e valores diferentes os valores ordenado
     * pelos critérios pedidos
     */
    @Test
    public void compareTest2() {
        //Anos com anos iguais e valores diferentes
        Year year1 = new Year(1990, new Value(1000));
        Year year2 = new Year(1990, new Value(1500));
        Year year3 = new Year(1990, new Value(1800));
        Year year4 = new Year(1993, new Value(1500));
        Year year5 = new Year(1993, new Value(1000));

        ArrayList<Year> result = new ArrayList<>();
        result.add(year1);
        result.add(year2);
        result.add(year3);
        result.add(year4);
        result.add(year5);

        result.sort(new YearComparator());

        ArrayList<Year> expResult = new ArrayList<>();
        expResult.add(year3);
        expResult.add(year2);
        expResult.add(year1);
        expResult.add(year4);
        expResult.add(year5);

        Assert.assertEquals(expResult, result);
    }
}
