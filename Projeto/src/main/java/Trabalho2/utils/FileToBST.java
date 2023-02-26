package Trabalho2.utils;

import Trabalho2.exception.InvalidLineException;
import Trabalho2.production.Production;
import Trabalho2.dataStructure.BST;
import Trabalho2.dataStructure.KDTree;
import Trabalho2.model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Classe responsável por converter os ficheiros de produção, coordenadas e bandeiras numa BST.
 */
public class FileToBST implements IndexColumnsFile, IndexColumnsFileAreaCoordinates, IndexColumnsFileFlags, IndexSearch {

    private final Production production;

    /**
     * Construtor para inicializar uma instância do objeto FileToBST.
     */
    public FileToBST() {
        this.production = new Production();
    }

    /**
     * Método para converter os ficheiros de produção, coordenadas e bandeiras numa AVL e numa 2d-Tree.
     *
     * @param indexArea               Index onde a area vai ser pesquisada na AVL
     * @param indexItem               Index onde o 'item' vai ser pesquisada na AVL
     * @param indexElement            Index onde o element vai ser pesquisada na AVL
     * @param filePath                Caminho do ficheiro de produção
     * @param fileAreaCoordinatesPath Caminho do ficheiro de coordenadas
     * @param fileFlagsPath           Caminho do ficheiro de bandeiras
     * @return Um objeto da classe Production com os atributos allDataAVL e
     */
    public Production convertFileInBST(int indexArea, int indexItem, int indexElement, String filePath, String fileAreaCoordinatesPath, String fileFlagsPath) throws IOException {

        Map<Character, String> mapFlags = convertFileFlags(fileFlagsPath);

        ReadFile rf = new ReadFile();
        List<String[]> coordinatesContentFile = rf.readFile(fileAreaCoordinatesPath);
        KDTree<Area> areaKDTree = convertFileInKDTree(coordinatesContentFile);
        production.setData2DTree(areaKDTree);

        List<String[]> contentFile = rf.readFile(filePath);
        for (String[] columns : contentFile) {

            if (columns.length == TOTAL_COLUMNS_FILE_PRODUCTION) {
                // Area
                Area area = createArea(indexArea, columns[INDEX_AREA_CODE], columns[INDEX_AREA_CODE_M49], columns[INDEX_AREA]);

                // Item
                Item item = createItem(indexItem, columns[INDEX_ITEM_CODE], columns[INDEX_ITEM_CPC], columns[INDEX_ITEM]);

                // Element
                Element element = createElement(indexElement, columns[INDEX_ELEMENT_CODE], columns[INDEX_ELEMENT]);

                // Value
                Value value = joinColumnsValueToFlagDescription(mapFlags, columns[INDEX_VALUE_UNIT], columns[INDEX_VALUE], columns[INDEX_VALUE_FLAG]);

                // Year
                Year year = createYear(columns[INDEX_YEAR], value);

                addObjectsToAVL(production, area, item, element, year);
            }
        }

        return production;
    }

    /**
     * Método para adicionar os objetos Area, Item, Element e Year à AVL.
     *
     * @param production     Objeto do tipo Production.
     * @param currentArea    Objeto do tipo Area.
     * @param currentItem    Objeto do tipo 'Item'.
     * @param currentElement Objeto do tipo Element.
     * @param currentYear    Objeto do tipo Year.
     */
    private void addObjectsToAVL(Production production, Area currentArea, Item currentItem, Element currentElement, Year currentYear) {
        BST.Node<Area> nodeAreaAVL = production.findNodeAreaAVL(currentArea);

        if (nodeAreaAVL == null) {
            production.insertNewAreaInAVL(currentArea, currentItem, currentElement, currentYear);
        } else {
            BST.Node<Item> nodeItemAVL = nodeAreaAVL.getElement().findNodeItemAVL(currentItem);

            if (nodeItemAVL == null) {
                nodeAreaAVL.getElement().insertNewItemInAVL(currentItem, currentElement, currentYear);
            } else {

                BST.Node<Element> nodeElementAVL = nodeItemAVL.getElement().findNodeElementAVL(currentElement);
                if (nodeElementAVL == null) {
                    nodeItemAVL.getElement().insertNewElementInAVL(currentElement, currentYear);
                } else {

                    BST.Node<Year> nodeYear = nodeElementAVL.getElement().findNodeYearAVL(currentYear);

                    if (nodeYear == null) {
                        nodeElementAVL.getElement().insertNewYearInAvl(currentYear);
                    }
                }
            }
        }
    }

    /**
     * Método responsável por transformar os dados que vêm numa lista de arrays de Strings na estrutura de dados KTTree
     *
     * @param fileAreaCoordinatesData Lista de arrays de String que contem os dados necessários para construir uma
     *                                KDTree
     * @return Uma estrutura de dados KDTree construída com os dados fornecidos por parâmetro
     */
    private KDTree<Area> convertFileInKDTree(List<String[]> fileAreaCoordinatesData) {
        KDTree<Area> areaKDTree = new KDTree<>();

        List<KDTree.Node2D<Area>> contentFile = this.convertFile2DNodes(fileAreaCoordinatesData);
        areaKDTree.buildTree(contentFile);

        return areaKDTree;
    }

    /**
     * Método para converter o ficheiro das bandeiras num map onde a Key é a flag e o value a flag description.
     *
     * @param fileFlagsPath Caminho do ficheiro das bandeiras
     * @return Um map onde a Key é a flag e o value a flag description.
     */
    private Map<Character, String> convertFileFlags(String fileFlagsPath) throws IOException {
        Map<Character, String> treeMapFlags = new TreeMap<>();
        ReadFile rf = new ReadFile();

        List<String[]> contentFile = rf.readFile(fileFlagsPath);

        for (String[] columns : contentFile) {
            if (columns.length == TOTAL_COLUMNS_FILE_FLAGS) {
                treeMapFlags.put(columns[INDEX_FLAG].charAt(0), columns[INDEX_FLAG_DESCRIPTION]);
            }
        }

        return treeMapFlags;
    }

    /**
     * Função para criar um objeto Area do tipo código, código M49 ou nome dependendo do index passado por parâmetro.
     *
     * @param indexArea O index da área.
     * @param areaCode  O código da área.
     * @param codeM49   O código M49 da área.
     * @param areaName  O nome da área.
     * @return O objeto Area
     */
    private Area createArea(int indexArea, String areaCode, String codeM49, String areaName) {
        switch (indexArea) {
            case INDEX_SEARCH_AREA_CODE:
                return new AreaCode(areaCode);
            case INDEX_SEARCH_AREA_CODE_M49:
                return new AreaCodeM49(codeM49);
            default:
                return new AreaName(areaName);
        }
    }

    /**
     * Função para criar um objeto 'Item' do tipo código, código CPC ou nome dependendo do index passado por parâmetro.
     *
     * @param indexItem O index do 'item'.
     * @param itemCode  O código do 'item'.
     * @param itemCPC   O código CPC do 'item'.
     * @param itemName  O nome do 'item'.
     * @return O objeto Item.
     */
    private Item createItem(int indexItem, String itemCode, String itemCPC, String itemName) {
        switch (indexItem) {
            case INDEX_SEARCH_ITEM_CODE:
                return new ItemCode(itemCode);
            case INDEX_SEARCH_ITEM_CPC:
                return new ItemCPC(itemCPC);
            default:
                return new ItemName(itemName);
        }
    }

    /**
     * Função para criar um objeto Element do tipo código ou nome dependendo do index passado por parâmetro.
     *
     * @param indexElement O index do elemento.
     * @param elementCode  O código do elemento.
     * @param elementName  O nome do elemento.
     * @return O objeto Element.
     */
    private Element createElement(int indexElement, String elementCode, String elementName) {
        if (indexElement == INDEX_SEARCH_ELEMENT_CODE) {
            return new ElementCode(elementCode);
        }
        return new ElementName(elementName);
    }

    /**
     * Função para criar um objeto Year com um dado ano de produção e um valor representado pelo objeto Value.
     *
     * @param year  O ano de produção.
     * @param value O objeto Value que irá pertencer a este ano.
     * @return O objeto Year.
     */
    private Year createYear(String year, Value value) {
        return new Year(parseStringToInteger(year), value);
    }

    /**
     * Função para criar um objeto Value com uma determinada unidade, quantidade de produção e bandeira.
     *
     * @param unit  Representa a unidade do valor.
     * @param value Representa a quantidade de produção.
     * @param flag  Representa a bandeira.
     * @return O objeto Value.
     */
    private Value createValue(String unit, String value, String flag) {
        return new Value(unit, parseStringToLong(value), flag.charAt(0));
    }

    /**
     * Método para juntar a flag description aos restante atributos de um objeto Value.
     *
     * @param mapFlags Map contendo um character e uma string, representado respetivamente a Flag e a Flag Description.
     * @param unit     Representa a unidade do valor.
     * @param value    Representa a quantidade de produção.
     * @param flag     Representa a bandeira.
     * @return O objeto Value
     */
    private Value joinColumnsValueToFlagDescription(Map<Character, String> mapFlags, String unit, String value, String flag) {
        Value valueObject = createValue(unit, value, flag);

        String flagDescription = mapFlags.get(valueObject.getFlag());
        valueObject.setFlagDescription(flagDescription);

        return valueObject;
    }

    /**
     * Função para converter uma 'string' num número inteiro. Caso não seja possível é retornado o valor 0.
     *
     * @param number String a converter.
     * @return Número do tipo inteiro.
     */
    private Integer parseStringToInteger(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException n) {
            return 0;
        }
    }

    /**
     * Função para converter uma 'string' num número longo. Caso não seja possível é retornado o valor 0.
     *
     * @param number String a converter.
     * @return Número do tipo longo.
     */
    private Long parseStringToLong(String number) {
        try {
            BigDecimal bd = new BigDecimal(number);
            return bd.longValue();
        } catch (NumberFormatException n) {
            return 0L;
        }
    }

    /**
     * Método para transformar os dados de uma lista de arrays de String em objetos do tipo KDTree.Node2D de Area e
     * armazena-los numa nova lista
     *
     * @param fileInfo Lista de arrays de String que contem os dados necessários por linha para transformar num objeto
     *                 do tipo KDTree.Node2D de Area
     * @return Uma lista com os vários KDTree.Node2D de Area instanciados
     */
    private List<KDTree.Node2D<Area>> convertFile2DNodes(List<String[]> fileInfo) {
        List<KDTree.Node2D<Area>> node2DList = new ArrayList<>();
        Iterator<String[]> iterator = fileInfo.iterator();
        int line = 1;
        while (iterator.hasNext()) {
            String[] values = iterator.next();
            // Area
            Area area = new AreaName(values[INDEX_AREA_NAME]);

            double x = 0, y = 0;

            try {
                x = Double.parseDouble(values[INDEX_AREA_LONGITUDE]);
                y = Double.parseDouble(values[INDEX_AREA_LATITUDE]);
            } catch (NumberFormatException ex) {
                throw new InvalidLineException(line);
            }

            KDTree.Node2D<Area> node2D = new KDTree.Node2D<Area>(area, null, null, x, y);
            node2DList.add(node2D);

            line++;
        }

        return node2DList;
    }
}
