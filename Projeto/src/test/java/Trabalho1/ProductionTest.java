package Trabalho1;

import Trabalho1.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Classe para realizar testes à classe Trabalho1.Production
 */
public class ProductionTest {
    /**
     * Caminho relativo para o ficheiro com maior tamanho de dados.
     */
    private final static String FILE_NAME = "files/FAOSTAT_data_en_9-7-2022_BIG.csv";

    /**
     * Caminho relativo para o ficheiro com menor tamanho de dados.
     */
    private final static String SMALL_FILE_NAME = "files/FAOSTAT_data_en_9-7-2022_SMALL.csv";

    /**
     * Objeto para tratar os dados relativos ao ficheiro com maior número de dados.
     */
    private static final Production production = new Production();

    /**
     * Objeto para tratar os dados relativos ao ficheiro com menor número de dados.
     */
    private static final Production smallProduction = new Production();

    /**
     * Antes de fazer a análise dos testes, carrega os dados do ficheiro pequeno e do ficheiro grande para memória.
     *
     * @throws Exception Lança uma exceção caso ocorra algum erro ao ler os ficheiros necessários.
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        production.readFile(FILE_NAME);
        smallProduction.readFile(SMALL_FILE_NAME);
    }

    /**
     * Teste ao método para ler dados de um ficheiro em que é esperado que seja encontrado um erro na conversão de uma
     * ‘String’ para inteiro na leitura de uma linha desse mesmo ficheiro, lançando assim uma exceção.
     *
     * @throws FileNotFoundException Exceção lançada caso não exista o ficheiro com o nome utilizado.
     */
    @Test(expected = InvalidLineException.class)
    public void readFileTest1() throws FileNotFoundException {
        Production p = new Production();
        p.readFile("files/ficheiro_teste_com_erro.csv");
    }

    /**
     * Teste ao método para ler dados de um ficheiro em que é esperado que o ficheiro no caminho utilizado não seja
     * encontrado, lançando assim uma exceção.
     *
     * @throws FileNotFoundException Exceção lançada caso não exista o ficheiro com o nome utilizado.
     */
    @Test(expected = FileNotFoundException.class)
    public void readFileTest2() throws FileNotFoundException {
        Production p = new Production();
        p.readFile("files/aaaaa.csv");
    }

    /**
     * Teste ao método para ler dados de um ficheiro em que é passado o valor null em vez de uma String com o caminho
     * para um dado ficheiro.
     *
     * @throws FileNotFoundException Exceção lançada caso não exista o ficheiro com o nome utilizado.
     */
    @Test(expected = FileNotFoundException.class)
    public void readFileTest3() throws FileNotFoundException {
        Production p = new Production();
        p.readFile(null);
    }

    /**
     * Teste ao método readFile em que é verificado se o valor do mapa gerado através da leitura do ficheiro contém a
     * chave e o valor igual ao esperado.
     *
     * @throws FileNotFoundException Exceção lançada caso não exista o ficheiro com o nome utilizado.
     */
    @Test
    public void readFileTest4() throws FileNotFoundException {
        Production p = new Production();
        p.readFile("files/ficheiro_teste_sem_erro.csv");

        Map<Area, Set<Item>> actual = p.getProductionPerArea();

        Map<Area, Set<Item>> expected = new HashMap<>();
        Set<Item> itemSet = new TreeSet<>();

        Item item = new Item("Apples");
        Value value = new Value(15100);
        Year yearOne = new Year(1961, value);
        Year yearTwo = new Year(1962, value);
        item.addYear(yearOne);
        item.addYear(yearTwo);
        itemSet.add(item);
        expected.put(new Area("Afghanistan"), itemSet);

        itemSet = new TreeSet<>();
        item = new Item("Apples");
        Value valueTwo = new Value(10004);
        Value valueThree = new Value(8039);
        yearOne = new Year(1965, valueTwo);
        yearTwo = new Year(1962, valueThree);
        item.addYear(yearOne);
        item.addYear(yearTwo);
        itemSet.add(item);
        expected.put(new Area("Albania"), itemSet);

        Assert.assertEquals(expected, actual);

    }

    /*
     * Testa o método higherValueDifferenceProduction para verificar se a função devolve um Map vazio quando passado um
     * país inexistente no ficheiro
     */
    @Test
    public void higherValueDifferenceProductionTest1() {
        Map<Item, Pair<String, Integer>> result = production.higherValueDifferenceProduction(production.getProductionPerArea(), new Area("San Marino"));

        Assert.assertTrue(result.isEmpty());
    }

    /**
     * Testa o método higherValueDifferenceProduction para verificar se a função devolve para o país "Malawi" que contêm
     * duas frutas os valores que realmente correspondem à realidade.
     */
    @Test
    public void higherValueDifferenceProductionTest2() {
        Map<Item, Pair<String, Integer>> result = production.higherValueDifferenceProduction(production.getProductionPerArea(), new Area("Malawi"));

        Map<Item, Pair<String, Integer>> expResult = new TreeMap<>();
        expResult.put(new Item("Apples"), new Pair<>("2013/2014", 3203));
        expResult.put(new Item("Bananas"), new Pair<>("1998/1999", 207000));

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método higherValueDifferenceProduction para verificar se a função devolve para o país "Portugal" que contêm
     * quatro frutas os valores que realmente correspondem à realidade.
     */
    @Test
    public void higherValueDifferenceProductionTest3() {
        Map<Item, Pair<String, Integer>> result = production.higherValueDifferenceProduction(production.getProductionPerArea(), new Area("Portugal"));

        Map<Item, Pair<String, Integer>> expResult = new TreeMap<>();
        expResult.put(new Item("Apples"), new Pair<>("1985/1986", 143460));
        expResult.put(new Item("Bananas"), new Pair<>("1979/1980", 10579));
        expResult.put(new Item("Blueberries"), new Pair<>("2018/2019", 4100));
        expResult.put(new Item("Cherries"), new Pair<>("2019/2020", 12760));

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método countryWithYearAndValue para verificar se a função devolve na fruta "Apples" e na quantidade 2700000
     * os valores que realmente correspondem à realidade.
     */
    @Test
    public void countryWithYearAndValueTest1() {
        ArrayList<Area> result = production.countryWithYearAndValue(production.getProductionPerArea(), new Item("Apples"), new Value(2700000));

        ArrayList<Area> expResult = new ArrayList<>();
        expResult.add(new Area("France"));
        expResult.add(new Area("United States of America"));
        expResult.add(new Area("USSR"));
        expResult.add(new Area("Germany"));
        expResult.add(new Area("China, mainland"));
        expResult.add(new Area("Iran (Islamic Republic of)"));
        expResult.add(new Area("Poland"));
        expResult.add(new Area("Türkiye"));
        expResult.add(new Area("India"));

        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método countryWithYearAndValue para verificar se a função devolve um Arraylist vazio quando passado um
     * fruto inexistente
     */
    @Test
    public void countryWithYearAndValueTest2() {
        ArrayList<Area> result = production.countryWithYearAndValue(production.getProductionPerArea(), new Item("Coconuts"), new Value(2700000));

        Assert.assertTrue(result.isEmpty());
    }

    /**
     * Testa o método countryWithYearAndValue para verificar se a função devolve um Arraylist vazio quando passado uma
     * quantidade de produção inexistente
     */
    @Test
    public void countryWithYearAndValueTest3() {
        ArrayList<Area> result = production.countryWithYearAndValue(production.getProductionPerArea(), new Item("Apples"), new Value(100000000));

        Assert.assertTrue(result.isEmpty());
    }

    /**
     * Testa o método maximumNumberConsecutiveYears para verificar se a função devolve para o fruto "Brazil nuts, with shell"
     * os valores que realmente correspondem à realidade.
     */
    @Test
    public void maximumNumberConsecutiveYearsTest1() {
        Map<Integer, Set<Area>> result = production.maximumNumberConsecutiveYears(production.getProductionPerArea(), new Item("Brazil nuts, with shell"));

        Map<Integer, Set<Area>> expResult = new TreeMap<>();
        Set<Area> areaSet1 = new TreeSet<>();
        areaSet1.add(new Area("Côte d'Ivoire"));
        areaSet1.add(new Area("Morocco"));
        expResult.put(2, areaSet1);
        Set<Area> areaSet2 = new TreeSet<>();
        areaSet2.add(new Area("Peru"));
        expResult.put(5, areaSet2);
        Set<Area> areaSet3 = new TreeSet<>();
        areaSet3.add(new Area("Bolivia (Plurinational State of)"));
        areaSet3.add(new Area("Brazil"));
        expResult.put(6, areaSet3);


        Assert.assertEquals(expResult, result);
    }

    /**
     * Testa o método maximumNumberConsecutiveYears para verificar se a função devolve um Map vazio quando passado um
     * fruto inexistente
     */
    @Test
    public void maximumNumberConsecutiveYearsTest2() {
        Map<Integer, Set<Area>> result = production.maximumNumberConsecutiveYears(production.getProductionPerArea(), new Item("Pera"));

        Assert.assertTrue(result.isEmpty());
    }

    /**
     * Testa o método getTotalValueList com um mapa vazio e com um valor null.
     */
    @Test
    public void getTotalValueListTest1() {

        Map<Area, Set<Item>> pd = new HashMap<>();
        ArrayList<Pair<Area, Integer>> totalValueList = production.getTotalValueList(pd);
        int actual = totalValueList.size();
        int expected = 0;
        Assert.assertEquals(expected, actual);

        totalValueList = production.getTotalValueList(null);
        Assert.assertNull(totalValueList);

    }

    /**
     * Testa o método getTotalValueList para perceber se os tamanhos dos ArrayList gerados pelos valores dos mapas,
     * criados através da leitura do ficheiro pequeno e outro através do ficheiro grande, estão corretos.
     */
    @Test
    public void getTotalValueListTest2() {

        ArrayList<Pair<Area, Integer>> totalValueList = smallProduction.getTotalValueList(smallProduction.getProductionPerArea());
        int actual = totalValueList.size();
        int expected = 2;
        Assert.assertEquals(expected, actual);

        totalValueList = production.getTotalValueList(production.getProductionPerArea());
        actual = totalValueList.size();
        expected = 187;
        Assert.assertEquals(expected, actual);

    }

    /**
     * Testa o método getTotalValueList para verificar se os valores que são colocados no ArrayList através da
     * utilização dos dados presentes no ficheiro pequeno estão corretos.
     */
    @Test
    public void getTotalValueListTest3() {

        ArrayList<Pair<Area, Integer>> expected = new ArrayList<>();
        expected.add(new Pair<>(new Area("Portugal"), 6176185));
        expected.add(new Pair<>(new Area("Spain"), 21978760));
        ArrayList<Pair<Area, Integer>> actual = smallProduction.getTotalValueList(smallProduction.getProductionPerArea());
        Assert.assertEquals(expected, actual);

    }

    /**
     * Testa o método getMinNumberOfKeysHigherThanQuantity para verificar se é devolvida a contagem correta de países
     * mínimos para a menor quantidade possível (0), quando se passa um ArrayList nulo e um vazio.
     */
    @Test
    public void getMinNumberOfKeysHigherThanQuantityTest1() {

        long quantity = -1;
        int actual = production.getMinNumberOfKeysHigherThanQuantity(null, quantity);
        int expected = 0;
        Assert.assertEquals(expected, actual);

        ArrayList<Pair<Area, Integer>> list = new ArrayList<>();
        actual = production.getMinNumberOfKeysHigherThanQuantity(list, quantity);
        Assert.assertEquals(expected, actual);

    }

    /**
     * Testa o método getMinNumberOfKeysHigherThanQuantity para verificar se a função devolve os valores que realmente
     * correspondem à realidade.
     */
    @Test
    public void getMinNumberOfKeysHigherThanQuantityTest2() {

        ArrayList<Pair<Area, Integer>> list = smallProduction.getTotalValueList(smallProduction.getProductionPerArea());
        long quantity = 28154945;
        int actual = smallProduction.getMinNumberOfKeysHigherThanQuantity(list, quantity);
        int expected = 0;
        Assert.assertEquals(expected, actual);

        quantity = 21978760;
        actual = smallProduction.getMinNumberOfKeysHigherThanQuantity(list, quantity);
        expected = 2;
        Assert.assertEquals(expected, actual);

        quantity = 21978759;
        actual = smallProduction.getMinNumberOfKeysHigherThanQuantity(list, quantity);
        expected = 1;
        Assert.assertEquals(expected, actual);

        list = production.getTotalValueList(production.getProductionPerArea());
        quantity = 1886178944;
        actual = production.getMinNumberOfKeysHigherThanQuantity(list, quantity);
        expected = 3;
        Assert.assertEquals(expected, actual);

        quantity = 1886178943;
        actual = production.getMinNumberOfKeysHigherThanQuantity(list, quantity);
        expected = 2;
        Assert.assertEquals(expected, actual);

    }
}