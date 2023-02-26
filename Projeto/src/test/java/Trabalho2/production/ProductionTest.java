package Trabalho2.production;

import Trabalho2.auxiliariesTestMethods.AuxiliariesTestMethods;
import Trabalho2.dataStructure.KDTree;
import Trabalho2.model.*;
import Trabalho2.utils.FileToBST;
import Trabalho2.utils.IndexSearch;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para realizar testes à classe Production
 */
public class ProductionTest {
    private final static String FILE_NAME = "files/trabalho2/Production_Crops_Livestock_EU_shuffle_small.csv";

    private final static String FILE_NAME_TEST_SMALL = "files/trabalho2/Production_Crops_Livestock_EU_shuffle_extra_small.csv";

    private final static String FILE_NAME_AREA_COORDINATES = "files/trabalho2/Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv";
    private final static String FILE_NAME_FLAGS_DESCRIPTION = "files/trabalho2/Production_Crops_Livestock_E_Flags.csv";

    private static Production production;

    /**
     * Antes de fazer a análise dos testes, carrega os dados do ficheiro pequeno e do ficheiro grande para memória.
     *
     * @throws Exception Lança uma exceção caso ocorra algum erro ao ler os ficheiros necessários.
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        FileToBST fileToBST = new FileToBST();
        production = fileToBST.convertFileInBST(IndexSearch.INDEX_SEARCH_AREA_NAME, IndexSearch.INDEX_SEARCH_ITEM_NAME,
                IndexSearch.INDEX_SEARCH_ELEMENT_NAME, FILE_NAME, FILE_NAME_AREA_COORDINATES, FILE_NAME_FLAGS_DESCRIPTION);
    }

    /**
     * Testa o método findValue para verificar se a função devolve para o país com o código "98", 'item' com o código "71",
     * elemento com o código "5510" e ano "1993" o valor que corresponde à realidade.
     */
    @Test
    public void findValueTest1() throws Exception {
        FileToBST fileToBST = new FileToBST();
        production = fileToBST.convertFileInBST(IndexSearch.INDEX_SEARCH_AREA_CODE, IndexSearch.INDEX_SEARCH_ITEM_CODE, IndexSearch.INDEX_SEARCH_ELEMENT_CODE,
                FILE_NAME, FILE_NAME_AREA_COORDINATES, FILE_NAME_FLAGS_DESCRIPTION);

        Area areaCode = new AreaCode("98");
        Item itemCode = new ItemCode("71");
        Element elementCode = new ElementCode("5510");
        Year year = new Year(1993);

        Value result = production.findValue(areaCode, itemCode, elementCode, year);
        Value expectedResult = new Value("tonnes", 6273L, 'A', "Official figure");

        Assert.assertEquals(expectedResult, result);
        beforeClass();
    }

    /**
     * Testa o método findValue para verificar se a função devolve para o país com o nome "Croatia", 'item' com o nome "Rye",
     * elemento com o nome "Production" e ano "1993" o valor que corresponde à realidade.
     */
    @Test
    public void findValueTest2() {
        Area areaName = new AreaName("Croatia");
        Item itemName = new ItemName("Rye");
        Element elementName = new ElementName("Production");
        Year year = new Year(1993);

        Value result = production.findValue(areaName, itemName, elementName, year);
        Value expectedResult = new Value("tonnes", 6273L, 'A', "Official figure");

        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Testa o método findValue para verificar se a função devolve para o país com o nome "Croatia", 'item' com o nome "Rye",
     * elemento com o nome "Production" e ano "2023" o null, pois não existe dados para o ano indicado.
     */
    @Test
    public void findValueTest3() {
        Area areaName = new AreaName("Croatia");
        Item itemName = new ItemName("Rye");
        Element elementName = new ElementName("Production");
        Year year = new Year(2023);

        Value result = production.findValue(areaName, itemName, elementName, year);

        Assert.assertNull(result);
    }

    /**
     * Testa o método findValue para verificar se a função devolve para o país com o nome "Croatia", 'item' com o nome "Rye",
     * elemento com o nome "AAAAA" e ano "2019" o null, pois não existe dados para o elemento indicado.
     */
    @Test
    public void findValueTest4() {
        Area areaName = new AreaName("Croatia");
        Item itemName = new ItemName("Rye");
        Element elementName = new ElementName("AAAAA");
        Year year = new Year(2019);

        Value result = production.findValue(areaName, itemName, elementName, year);

        Assert.assertNull(result);
    }

    /**
     * Testa o método findValue para verificar se a função devolve para o país com o nome "Croatia", 'item' com o nome "Laranja",
     * elemento com o nome "Production" e ano "2019" o null, pois não existe dados para o 'item' indicado.
     */
    @Test
    public void findValueTest5() {
        Area areaName = new AreaName("Croatia");
        Item itemName = new ItemName("Laranja");
        Element elementName = new ElementName("Production");
        Year year = new Year(2019);

        Value result = production.findValue(areaName, itemName, elementName, year);

        Assert.assertNull(result);
    }

    /**
     * Testa o método findValue para verificar se a função devolve para o país com o nome "San Marino", 'item' com o nome "Rye",
     * elemento com o nome "Production" e ano "2019" o null, pois não existe dados para o país indicado.
     */
    @Test
    public void findValueTest6() {
        Area areaName = new AreaName("San Marino");
        Item itemName = new ItemName("Rye");
        Element elementName = new ElementName("Production");
        Year year = new Year(2019);

        Value result = production.findValue(areaName, itemName, elementName, year);

        Assert.assertNull(result);
    }

    /**
     * Testa o método findValue para verificar se a função devolve para o país com código M49 "191", 'item' com o nome "Rye",
     * elemento com o código "5510" e ano "2099" o null, pois não existe dados para o país indicado.
     */
    @Test
    public void findValueTest7() throws Exception {
        FileToBST fileToBST = new FileToBST();
        production = fileToBST.convertFileInBST(IndexSearch.INDEX_SEARCH_AREA_CODE_M49, IndexSearch.INDEX_SEARCH_ITEM_NAME,
                IndexSearch.INDEX_SEARCH_ELEMENT_CODE, FILE_NAME, FILE_NAME_AREA_COORDINATES, FILE_NAME_FLAGS_DESCRIPTION);

        Area areaCodeM49 = new AreaCodeM49("191");
        Item itemName = new ItemName("Rye");
        Element elementCode = new ElementCode("5510");
        Year year = new Year(2099);

        Value result = production.findValue(areaCodeM49, itemName, elementCode, year);

        Assert.assertNull(result);

        beforeClass();
    }

    /**
     * Testa o método selectNAreasWithTheHighestValue para verificar se a função devolve para o item com o nome "Beer of barley, malted",
     * elemento com o nome "Production" o valor que corresponde à realidade.
     */
    @Test
    public void selectNAreasWithTheHighestValueTest1() {
        Item itemName = new ItemName("Beer of barley, malted");
        Element elementName = new ElementName("Production");

        ArrayList<Area> result = production.selectNAreasWithTheHighestValue(itemName, elementName, 2);
        ArrayList<Area> expectedResult = new ArrayList<>();
        expectedResult.add(new AreaName("United Kingdom of Great Britain and Northern Ireland"));
        expectedResult.add(new AreaName("Spain"));

        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Testa o método selectNAreasWithTheHighestValue para verificar se a função devolve para o iten com o nome "Peras",
     * elemento com o nome "Production" o ArrayList vazio.
     */
    @Test
    public void selectNAreasWithTheHighestValueTest2() {
        Item itemName = new ItemName("Peras");
        Element elementName = new ElementName("Production");

        ArrayList<Area> result = production.selectNAreasWithTheHighestValue(itemName, elementName, 2);

        Assert.assertTrue(result.isEmpty());
    }

    /**
     * Testa o método selectNAreasWithTheHighestValue para verificar se a função devolve para o iten com o nome "Beer of barley, malted",
     * elemento com o nome "maças" o ArrayList vazio.
     */
    @Test
    public void selectNAreasWithTheHighestValueTest3() {
        Item itemName = new ItemName("Beer of barley, malted");
        Element elementName = new ElementName("maças");

        ArrayList<Area> result = production.selectNAreasWithTheHighestValue(itemName, elementName, 2);

        Assert.assertTrue(result.isEmpty());
    }

    public static void readExercise2TestFile() throws Exception {
        FileToBST fileToBST = new FileToBST();
        production = fileToBST.convertFileInBST(IndexSearch.INDEX_SEARCH_AREA_NAME, IndexSearch.INDEX_SEARCH_ITEM_NAME,
                IndexSearch.INDEX_SEARCH_ELEMENT_NAME, FILE_NAME_TEST_SMALL, FILE_NAME_AREA_COORDINATES, FILE_NAME_FLAGS_DESCRIPTION);
    }

    //Testes do exercicio 2

    /**
     * Testa se a função permanece vazia quando o intervalo é impossivel, para os dados em questão.
     */
    @Test
    public void agregatedAreaItemElementTest1() {
        Area areaName = new AreaName("Croatia");
        Year firstYear = new Year(2050);
        Year lastYear = new Year(2100);

        List<AgregatedDataStructure> result = production.agregatedAreaItemElement(areaName, firstYear, lastYear);

        Assert.assertTrue(result.isEmpty());
    }

    /**
     * Testa se a função permanece vazia quando a area não existe, para os dados em questão.
     */
    @Test
    public void agregatedAreaItemElementTest2() {
        Area areaName = new AreaName("Sin City");
        Year firstYear = new Year(2002);
        Year lastYear = new Year(2012);

        List<AgregatedDataStructure> result = production.agregatedAreaItemElement(areaName, firstYear, lastYear);

        Assert.assertTrue(result.isEmpty());
    }

    /**
     * Testa se a função devolve os valores corretos para um intervalo de anos em que os extremos não têm valor, para os dados em questão.
     *
     * @throws Exception
     */
    @Test
    public void agregatedAreaItemElementTest3() throws Exception {
        readExercise2TestFile();
        Area areaName = new AreaName("Croatia");
        Year firstYear = new Year(1);
        Year lastYear = new Year(2500);

        List<AgregatedDataStructure> result = production.agregatedAreaItemElement(areaName, firstYear, lastYear);

        Item itemName1 = new ItemName("Swine / pigs");
        Element elementName1 = new ElementName("Stocks");
        Item itemName2 = new ItemName("Sheep");
        Element elementName2 = new ElementName("Stocks");
        Item itemName3 = new ItemName("Sugar Crops Primary");
        Element elementName3 = new ElementName("Yield");
        Item itemName4 = new ItemName("Carrots and turnips");
        Element elementName4 = new ElementName("Yield");
        Item itemName5 = new ItemName("Bees");
        Element elementName5 = new ElementName("Stocks");
        Item itemName6 = new ItemName("Cucumbers and gherkins");
        Element elementName6 = new ElementName("Yield");
        Item itemName7 = new ItemName("Buckwheat");
        Element elementName7 = new ElementName("Yield");
        Item itemName8 = new ItemName("Onions and shallots, dry (excluding dehydrated)");
        Element elementName8 = new ElementName("Production");
        Item itemName9 = new ItemName("Treenuts, Total");
        Element elementName9 = new ElementName("Yield");
        Item itemName10 = new ItemName("Rye");
        Element elementName10 = new ElementName("Production");

        List<AgregatedDataStructure> test = new ArrayList<>();
        test.add(new AgregatedDataStructure(firstYear, lastYear, itemName1, elementName1, 1204960.0));
        test.add(new AgregatedDataStructure(firstYear, lastYear, itemName2, elementName2, 488535.0));
        test.add(new AgregatedDataStructure(firstYear, lastYear, itemName3, elementName3, 475585.0));
        test.add(new AgregatedDataStructure(firstYear, lastYear, itemName4, elementName4, 252391.0));
        test.add(new AgregatedDataStructure(firstYear, lastYear, itemName5, elementName5, 111000.0));
        test.add(new AgregatedDataStructure(firstYear, lastYear, itemName6, elementName6, 84921.0));
        test.add(new AgregatedDataStructure(firstYear, lastYear, itemName7, elementName7, 31111.0));
        test.add(new AgregatedDataStructure(firstYear, lastYear, itemName8, elementName8, 26857.0));
        test.add(new AgregatedDataStructure(firstYear, lastYear, itemName9, elementName9, 8582.0));
        test.add(new AgregatedDataStructure(firstYear, lastYear, itemName10, elementName10, 6273.0));

        boolean expResult = true;
        for (int i = 0; i < result.size(); i++) {
            if (!result.get(i).equals(test.get(i))) {
                expResult = false;
            }
        }

        Assert.assertTrue(expResult);
        beforeClass();
    }

    /**
     * Testa se a função devolve os valores corretos para um intervalo de anos e area sem erros, para os dados em questão.
     *
     * @throws Exception
     */
    @Test
    public void agregatedAreaItemElementTest4() throws Exception {
        readExercise2TestFile();
        Area areaName = new AreaName("Croatia");
        Year firstYear = new Year(2002);
        Year lastYear = new Year(2012);

        List<AgregatedDataStructure> result = production.agregatedAreaItemElement(areaName, firstYear, lastYear);

        Item itemName1 = new ItemName("Swine / pigs");
        Element elementName1 = new ElementName("Stocks");
        Item itemName2 = new ItemName("Sugar Crops Primary");
        Element elementName2 = new ElementName("Yield");
        Item itemName3 = new ItemName("Carrots and turnips");
        Element elementName3 = new ElementName("Yield");
        Item itemName4 = new ItemName("Bees");
        Element elementName4 = new ElementName("Stocks");
        Item itemName5 = new ItemName("Treenuts, Total");
        Element elementName5 = new ElementName("Yield");

        List<AgregatedDataStructure> test = new ArrayList<>();
        test.add(new AgregatedDataStructure(firstYear, lastYear, itemName1, elementName1, 1204960.0));
        test.add(new AgregatedDataStructure(firstYear, lastYear, itemName2, elementName2, 475585.0));
        test.add(new AgregatedDataStructure(firstYear, lastYear, itemName3, elementName3, 252391.0));
        test.add(new AgregatedDataStructure(firstYear, lastYear, itemName4, elementName4, 111000.0));
        test.add(new AgregatedDataStructure(firstYear, lastYear, itemName5, elementName5, 8582.0));

        boolean expResult = true;
        for (int i = 0; i < result.size(); i++) {
            if (!result.get(i).equals(test.get(i))) {
                expResult = false;
            }
        }

        Assert.assertTrue(expResult);
        beforeClass();
    }
    //Fim dos testes do exercício 2

    // Testes do exercício 4

    /**
     * Testa o método findAllAreasByItemElementYear em que é verificado se as Areas para um dado ‘item’, elemento e ano
     * são todas encontradas corretamente
     */
    @Test
    public void findAllAreasByItemElementYearTest1() {
        Item item = new ItemName("Carrots and turnips");
        Element element = new ElementName("Production");
        Year year = new Year(2004);

        List<Area> resultList = production.findAllAreasByItemElementYear(item, element, year);
        List<Area> createdList = new ArrayList<>();
        createdList.add(new AreaName("Lithuania"));
        createdList.add(new AreaName("Malta"));
        createdList.add(new AreaName("Poland"));

        Assert.assertTrue(AuxiliariesTestMethods.assertEqualsLists(resultList, createdList));

        resultList.remove(2);

        Assert.assertFalse(AuxiliariesTestMethods.assertEqualsLists(resultList, createdList));

    }

    /**
     * Testa o método findAllAreasByItemElementYear em que é verificado se o valor devolvido ao utilizar objetos com
     * informação que não existe está correto (se a lista vem vazia)
     */
    @Test
    public void findAllAreasByItemElementYearTest2() {
        Item item = new ItemName("Não existe");
        Element element = new ElementName("Production");
        Year year = new Year(2004);

        List<Area> resultList = production.findAllAreasByItemElementYear(item, element, year);
        List<Area> createdList = new ArrayList<>();

        Assert.assertTrue(AuxiliariesTestMethods.assertEqualsLists(resultList, createdList));

    }

    /**
     * Testa o método findAllAreasByItemElementYear em que é verificado se ao passar valores nulos, o método devolve
     * uma lista vazia
     */
    @Test
    public void findAllAreasByItemElementYearTest3() {
        Year year = new Year(2004);

        List<Area> resultList = production.findAllAreasByItemElementYear(null, null, year);
        List<Area> createdList = new ArrayList<>();

        Assert.assertTrue(AuxiliariesTestMethods.assertEqualsLists(resultList, createdList));

    }

    /**
     * Testa o método findAllValidNode2D em que é verificado se os valores que são devolvidos pelo método com a
     * passagem de determinados valores para o Item, para o Element e para o Year estão corretos.
     */
    @Test
    public void findAllValidNode2DTest1() {
        Item item = new ItemName("Carrots and turnips");
        Element element = new ElementName("Production");
        Year year = new Year(2004);
        List<Area> validAreas = production.findAllAreasByItemElementYear(item, element, year);

        List<KDTree.Node2D<Area>> validNodes2DResult = production.findAllValidNode2D(validAreas);

        List<KDTree.Node2D<Area>> validNodes2DExpected = new ArrayList<>();
        KDTree.Node2D<Area> newNode2DOne = new KDTree.Node2D<>(new AreaName("Lithuania"), null, null, 23.881275, 55.169438);
        KDTree.Node2D<Area> newNode2DTwo = new KDTree.Node2D<>(new AreaName("Malta"), null, null, 14.375416, 35.937496);
        KDTree.Node2D<Area> newNode2DThree = new KDTree.Node2D<>(new AreaName("Poland"), null, null, 19.145136, 51.919438);
        validNodes2DExpected.add(newNode2DOne);
        validNodes2DExpected.add(newNode2DTwo);
        validNodes2DExpected.add(newNode2DThree);

        Assert.assertTrue(AuxiliariesTestMethods.assertEqualsListNodes2D(validNodes2DResult, validNodes2DExpected));

        validNodes2DResult.remove(2);
        Assert.assertFalse(AuxiliariesTestMethods.assertEqualsListNodes2D(validNodes2DResult, validNodes2DExpected));

    }

    /**
     * Testa o método findAllValidNode2D em que é verificado se ao utilizar um valor que não existe num objeto, o método
     * devolve o valor que é suposto (lista vazia)
     */
    @Test
    public void findAllValidNode2DTest2() {
        Item item = new ItemName("Carrots and turnips");
        Element element = new ElementName("Não existe");
        Year year = new Year(2004);
        List<Area> validAreas = production.findAllAreasByItemElementYear(item, element, year);
        List<KDTree.Node2D<Area>> validNodes2DResult = production.findAllValidNode2D(validAreas);

        List<KDTree.Node2D<Area>> validNodes2DExpected = new ArrayList<>();

        Assert.assertTrue(AuxiliariesTestMethods.assertEqualsListNodes2D(validNodes2DExpected, validNodes2DResult));

    }

    /**
     * Testa o método findAllValidNode2D quando é passado por parâmetro o valor null
     */
    @Test
    public void findAllValidNode2DTest3() {
        List<KDTree.Node2D<Area>> validNodes2DResult = production.findAllValidNode2D(null);
        List<KDTree.Node2D<Area>> validNodes2DExpected = new ArrayList<>();

        Assert.assertTrue(AuxiliariesTestMethods.assertEqualsListNodes2D(validNodes2DExpected, validNodes2DResult));
    }

    /**
     * Testa o método nearestArea para verificar se ao passar um valor que não existe num dos objetos, o método devolve
     * o valor de null
     */
    @Test
    public void nearestAreaTest1() {
        Item item = new ItemName("Carrots and turnips");
        Element element = new ElementName("This element doesn't exists");
        Year year = new Year(1981);
        KDTree.Node2D<Area> node2D = production.nearestArea(61.92411, 25.748151, item, element, year);

        Assert.assertNull(node2D);
    }

    /**
     * Testa o método nearestArea para verificar se ao passar uma latitude e longitude muito alta, o método devolve
     * na mesma o valor correto
     */
    @Test
    public void nearestAreaTest2() {
        Item item = new ItemName("Carrots and turnips");
        Element element = new ElementName("Yield");
        Year year = new Year(1981);
        KDTree.Node2D<Area> result = production.nearestArea(9999999, 9999999, item, element, year);

        Assert.assertEquals(new AreaName("Finland"), result.getElement());
        Assert.assertEquals(61.92411, result.getY(), 0.00001);
        Assert.assertEquals(25.748151, result.getX(), 0.00001);
    }

    /**
     * Testa o método nearestArea para verificar se o valor devolvido para umas dadas condições, está correto
     */
    @Test
    public void nearestAreaTest3() {
        Item item = new ItemName("Carrots and turnips");
        Element element = new ElementName("Yield");
        Year year = new Year(1981);
        KDTree.Node2D<Area> result = production.nearestArea(61.92411, 25.748151, item, element, year);

        Assert.assertEquals(new AreaName("Finland"), result.getElement());
        Assert.assertEquals(61.92411, result.getY(), 0.00001);
        Assert.assertEquals(25.748151, result.getX(), 0.00001);
    }
    // Fim dos testes do exercício 4

    //Testes do exercício 5
    @Test
    public void valueProductionGeographicalAreaTest1() {
        Item itemName = new ItemName("Carrots and turnips");
        Element elementName = new ElementName("Yield");
        Year year = new Year(1981);

        double latitudeInicial = 61.92411;
        double latitudeFinal = 61.92411;
        double longitudeInicial = 25.748151;
        double longitudeFinal = 25.748151;

        long value = production.valueProductionGeographicalArea(itemName, elementName, year, latitudeInicial, longitudeInicial, latitudeFinal, longitudeFinal);
        long expValue = 180088;

        Assert.assertEquals(expValue, value);
    }

    @Test
    public void valueProductionGeographicalAreaTest2() {
        Item itemName = new ItemName("Carrots and turnips");
        Element elementName = new ElementName("Yield");
        Year year = new Year(2022);

        double latitudeInicial = 61.92411;
        double latitudeFinal = 61.92411;
        double longitudeInicial = 25.748151;
        double longitudeFinal = 25.748151;

        long value = production.valueProductionGeographicalArea(itemName, elementName, year, latitudeInicial, longitudeInicial, latitudeFinal, longitudeFinal);
        long expValue = 0;

        Assert.assertEquals(expValue, value);
    }

    @Test
    public void valueProductionGeographicalAreaTest3() {
        Item itemName = new ItemName("Carrots and turnips");
        Element elementName = new ElementName("Yield");
        Year year = new Year(2022);

        double latitudeInicial = 0;
        double latitudeFinal = 0;
        double longitudeInicial = 0;
        double longitudeFinal = 0;

        long value = production.valueProductionGeographicalArea(itemName, elementName, year, latitudeInicial, longitudeInicial, latitudeFinal, longitudeFinal);
        long expValue = 0;

        Assert.assertEquals(expValue, value);
    }
    //Fim dos testes do exercício 5
}
