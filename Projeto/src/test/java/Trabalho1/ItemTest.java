package Trabalho1;

import Trabalho1.Item;
import Trabalho1.Pair;
import Trabalho1.Value;
import Trabalho1.Year;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe para realizar testes à classe Trabalho1.Item.
 */
public class ItemTest {
    /**
     * Testa o método equals com dois objetos Trabalho1.Item iguais.
     */
    @Test
    public void equalsTest1() {
        Item item1 = new Item("Apple");
        Item item2 = new Item("Apple");

        boolean result = item1.equals(item2);

        Assert.assertTrue(result);
    }

    /**
     * Testa o método equals com dois objetos Trabalho1.Item diferentes.
     */
    @Test
    public void equalsTest2() {
        Item item1 = new Item("Apple");
        Item item2 = new Item("Bananas");

        boolean result = item1.equals(item2);

        Assert.assertFalse(result);
    }

    /**
     * Testa o método compareTo com dois objetos Trabalho1.Item com o atributo nome igual.
     */
    @Test
    public void compareToTest1() {
        Item item1 = new Item("Apple");
        Item item2 = new Item("Apple");

        int result = item1.compareTo(item2);
        int expResult = 0;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto Trabalho1.Item com o nome maior alfabeticamente que o de outro objeto.
     */
    @Test
    public void compareToTest2() {
        Item item1 = new Item("Bananas");
        Item item2 = new Item("Apples");

        int result = item1.compareTo(item2);
        int expResult = 1;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método compareTo com um objeto Trabalho1.Item com o nome menor alfabeticamente que o de outro objeto.
     */
    @Test
    public void compareToTest3() {
        Item item1 = new Item("Apple");
        Item item2 = new Item("Bananas");

        int result = item1.compareTo(item2);
        int expResult = -1;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método getTotalQuantity para verificar se a função devolve os valores que realmente
     * correspondem à realidade.
     */
    @Test
    public void getTotalQuantityTest1() {
        Item item1 = new Item("Apple");
        item1.addYear(new Year(2015, new Value(19345)));
        item1.addYear(new Year(2016, new Value(1300)));
        item1.addYear(new Year(2017, new Value(1905)));
        item1.addYear(new Year(2018, new Value(2345)));
        item1.addYear(new Year(2019, new Value(4345)));

        int result = item1.getTotalQuantity();
        int expResult = 29240;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método getTotalQuantity com quantidades de produção a 0 para verificar se a função devolve os valores
     * que realmente correspondem à realidade.
     */
    @Test
    public void getTotalQuantityTest2() {
        Item item1 = new Item("Apple");
        item1.addYear(new Year(2018, new Value(0)));
        item1.addYear(new Year(2019, new Value(0)));

        int result = item1.getTotalQuantity();
        int expResult = 0;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método getMaximumNumberConsecutiveYears para verificar se a função devolve os valores que realmente
     * correspondem à realidade.
     */
    @Test
    public void getMaximumNumberConsecutiveYearsTest1() {
        Item item1 = new Item("Apple");
        item1.addYear(new Year(2015, new Value(145)));
        item1.addYear(new Year(2016, new Value(1300)));
        item1.addYear(new Year(2017, new Value(905)));
        item1.addYear(new Year(2018, new Value(2345)));
        item1.addYear(new Year(2019, new Value(3345)));

        int result = item1.getMaximumNumberConsecutiveYears();
        int expResult = 3;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método getMaximumNumberConsecutiveYears para verificar se a função devolve os valores que realmente
     * correspondem à realidade.
     */
    @Test
    public void getMaximumNumberConsecutiveYearsTest2() {
        Item item1 = new Item("Apple");
        item1.addYear(new Year(2015, new Value(23095)));
        item1.addYear(new Year(2016, new Value(1345)));
        item1.addYear(new Year(2017, new Value(905)));
        item1.addYear(new Year(2018, new Value(803)));
        item1.addYear(new Year(2019, new Value(730)));

        int result = item1.getMaximumNumberConsecutiveYears();
        int expResult = 1;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método getBiggestDifferenceQuantity para verificar se a função devolve os valores que realmente
     * correspondem à realidade.
     */
    @Test
    public void getBiggestDifferenceQuantityTest1() {
        Item item1 = new Item("Apple");
        item1.addYear(new Year(2015, new Value(19345)));
        item1.addYear(new Year(2016, new Value(1300)));
        item1.addYear(new Year(2017, new Value(905)));
        item1.addYear(new Year(2018, new Value(2345)));
        item1.addYear(new Year(2019, new Value(4345)));

        Pair result = item1.getBiggestDifferenceQuantity();
        Pair expResult = new Pair("2015/2016", 18045);

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método getBiggestDifferenceQuantity para verificar se a função devolve os valores que realmente
     * correspondem à realidade.
     */
    @Test
    public void getBiggestDifferenceQuantityTest2() {
        Item item1 = new Item("Apple");
        item1.addYear(new Year(2015, new Value(0)));
        item1.addYear(new Year(2016, new Value(0)));
        item1.addYear(new Year(2017, new Value(0)));
        item1.addYear(new Year(2018, new Value(0)));
        item1.addYear(new Year(2019, new Value(0)));

        Pair result = item1.getBiggestDifferenceQuantity();
        Pair expResult = new Pair("2015/2016", 0);

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método getYearWithQuantityGreaterOrEqual para verificar se a função devolve os valores que realmente
     * correspondem à realidade.
     */
    @Test
    public void getYearWithQuantityGreaterOrEqualTest1() {
        Item item1 = new Item("Apple");
        item1.addYear(new Year(2015, new Value(345)));
        item1.addYear(new Year(2016, new Value(1300)));
        item1.addYear(new Year(2017, new Value(905)));
        item1.addYear(new Year(2018, new Value(2345)));
        item1.addYear(new Year(2019, new Value(4345)));

        Year result = item1.getYearWithQuantityGreaterOrEqual(1000);
        Year expResult = new Year(2016, new Value(1300));

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método getYearWithQuantityGreaterOrEqual para verificar se a função devolve para uma quantidade em que
     * não existe nenhum ano, o valor NULL.
     */
    @Test
    public void getYearWithQuantityGreaterOrEqualTest2() {
        Item item1 = new Item("Apple");
        item1.addYear(new Year(2015, new Value(345)));
        item1.addYear(new Year(2016, new Value(1300)));
        item1.addYear(new Year(2017, new Value(905)));
        item1.addYear(new Year(2018, new Value(2345)));
        item1.addYear(new Year(2019, new Value(4345)));

        Year result = item1.getYearWithQuantityGreaterOrEqual(5000);

        Assert.assertNull(result);
    }
}
