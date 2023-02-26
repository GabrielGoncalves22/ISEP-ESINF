package Trabalho2.production;

import Trabalho2.comparator.ValueComparator;
import Trabalho2.dataStructure.AVL;
import Trabalho2.dataStructure.BST;
import Trabalho2.dataStructure.KDTree;
import Trabalho2.model.*;

import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe que representa a produção.
 */
public class Production {
    private AVL< Area > allDataAVL;
    private KDTree< Area > data2DTree;

    public Production() {
        this.allDataAVL = new AVL<>();
        this.data2DTree = new KDTree<>();
    }

    /**
     * Função para procurar na AVL da area o nó com a area passado por parâmetro.
     *
     * @param currentArea Objeto do tipo Area.
     * @return O nó com a area.
     */
    public BST.Node< Area > findNodeAreaAVL(Area currentArea) {
        return this.allDataAVL.find(currentArea);
    }

    /**
     * Método para inserir na AVL geral uma área, um 'item', um elemento e um ano passado por parâmetro.
     *
     * @param currentArea    Objeto do tipo Area.
     * @param currentItem    Objeto do tipo 'Item'.
     * @param currentElement Objeto do tipo Element.
     * @param currentYear    Objeto do tipo Year.
     */
    public void insertNewAreaInAVL(Area currentArea, Item currentItem, Element currentElement, Year currentYear) {
        currentArea.insertNewItemInAVL(currentItem, currentElement, currentYear);
        this.allDataAVL.insert(currentArea);
    }

    /**
     * Atribui um objeto do tipo KDTree de Area ao atributo desta classe responsável por armazenar um objeto deste tipo
     *
     * @param data2DTree Objeto do tipo KDTree de Area a ser armazenado nesta classe
     */
    public void setData2DTree(KDTree< Area > data2DTree) {
        this.data2DTree = data2DTree;
    }

    /**
     * Método para pesquisar na AVL uma determinada Area, 'Item', Elemento e Ano e obter os valores de produção
     * (Value, Unit, Flag, Flag Description).
     *
     * @param currentArea    Objeto do tipo Area.
     * @param currentItem    Objeto do tipo 'Item'.
     * @param currentElement Objeto do tipo Element.
     * @param currentYear    Objeto do tipo Year.
     * @return Os valores de produção (Value, Unit, Flag, Flag Description).
     */
    public Value findValue(Area currentArea, Item currentItem, Element currentElement, Year currentYear) {
        if (currentArea == null || currentItem == null || currentElement == null || currentYear == null) {
            return null;
        } else {
            BST.Node< Area > nodeAreaAVL = this.allDataAVL.find(currentArea);

            if (nodeAreaAVL == null) {
                return null;
            } else {
                BST.Node< Item > nodeItemAVL = nodeAreaAVL.getElement().getItemAVL().find(currentItem);

                if (nodeItemAVL == null) {
                    return null;
                } else {
                    BST.Node< Element > nodeElementAVL = nodeItemAVL.getElement().getElementAVL().find(currentElement);

                    if (nodeElementAVL == null) {
                        return null;
                    } else {
                        BST.Node< Year > nodeYear = nodeElementAVL.getElement().getYearAVL().find(currentYear);

                        if (nodeYear == null) {
                            return null;
                        } else {
                            return nodeYear.getElement().getValue();
                        }
                    }
                }
            }
        }
    }

    //Exercício 2
    /**
     * Método que, passados por parametro, uma Area e dois anos que formem entre si um intervalo,
     * devolve numa estrutura de dados adequada, um intervalo de anos, um item,
     * um element e a média dos valores de produção para um item e um element
     *
     * @param passedArea Objeto de tipo Area.
     * @param firstYear  Objeto de tipo Year.
     * @param lastYear   Objeto de tipo Year.
     * @return           Uma lista, ordenada decrescentemente pela média dos valores de produção.
     */
    public List< AgregatedDataStructure > agregatedAreaItemElement(Area passedArea, Year firstYear, Year lastYear) {
        List< AgregatedDataStructure > result = new ArrayList<>();
        if (passedArea == null || firstYear == null || lastYear == null) {
            return null;
        } else if (firstYear.compareTo(lastYear) > 0) {
            Year tmp = firstYear;
            firstYear = lastYear;
            lastYear = tmp;
        }
        BST.Node< Area > nodeAreaAVL = this.allDataAVL.find(passedArea);
        if (nodeAreaAVL != null) {
            for (Item item : nodeAreaAVL.getElement().getItemAVL().inOrder()) {
                for (Element element : item.getElementAVL().inOrder()) {
                    long sum = 0;
                    double inc = 0;
                    double average;
                    for (Year year : element.getYearAVL().inOrder()) {
                        if (year.compareTo(firstYear) >= 0 && year.compareTo(lastYear) <= 0) {
                            sum += year.getValue().getValue();
                            inc++;
                        }
                    }
                    if (inc != 0) {
                        average = sum / inc;
                        AgregatedDataStructure teste = new AgregatedDataStructure(firstYear, lastYear, item, element, average);
                        result.add(teste);
                    }
                }
            }
            Collections.sort(result);
        }
        return result;
    }
    //Fim do exercicio 2

    //Exercício 3

    /**
     * Método para ordenar de forma decrescente pelo value numa respetiva area
     *
     * @param map mapa que contém a informação a ser tratada.
     * @param <K> variável que representa um determinado valor/objeto chave de um mapa.
     * @return lista ordenada com os países.
     */
    private <K> ArrayList<K> sortByValue(Map<K, Value> map) {
        ArrayList<K> sortedList = new ArrayList<>();
        ArrayList<Value> list = new ArrayList<>();

        for (Map.Entry<K, Value> entry : map.entrySet()) {
            list.add(entry.getValue());
        }

        list.sort(new ValueComparator());

        for (Value value : list) {
            for (Map.Entry<K, Value> entry : map.entrySet()) {
                if (entry.getValue().equals(value)) {
                    sortedList.add(entry.getKey());
                }
            }
        }

        return sortedList;
    }

    /**
     * Método que da return um treeMap com as Areas com o maior valor no ultimo registo para um determinado Item e Elemento
     *
     * @param receivedItem
     * @param receivedElement
     * @return com as Areas com o maior valor
     */
    private Map<Area, Value> areasWithTheHighestValue(Item receivedItem, Element receivedElement) {
        Iterable<Area> areasAVLIterator = this.allDataAVL.inOrder();
        Map<Area, Value> treeMapAreaValue = new TreeMap<>();

        for (Area area : areasAVLIterator) {
            BST.Node<Item> nodeItemAVL = area.getItemAVL().find(receivedItem);

            if (nodeItemAVL != null) {
                Item item = nodeItemAVL.getElement();
                BST.Node<Element> nodeElementAVL = item.findNodeElementAVL(receivedElement);

                if (nodeElementAVL != null) {
                    Year maxYear = nodeElementAVL.getElement().getMaxYear();

                    treeMapAreaValue.put(area, maxYear.getValue());
                }
            }
        }
        return treeMapAreaValue;
    }


    /**
     * Metodo onde vai ser returnado o ArrayList com as n Areas, é neste metodo que é usado o metodo areasWithTheHighestValue e
     * o metodo sortByValue para ordenar o array com todas as areas, posteriormente passa para um novo ArrayList so as N areas pedidas
     *
     * @param receivedItem    Objeto do tipo 'Item'.
     * @param receivedElement Objeto do tipo Element.
     * @param receivedNumber  o numero de araeas pretendidas
     * @return
     */
    public ArrayList<Area> selectNAreasWithTheHighestValue(Item receivedItem, Element receivedElement, int receivedNumber) {
        Map<Area, Value> treeMapAreaValue = areasWithTheHighestValue(receivedItem, receivedElement);
        ArrayList<Area> finalArray = sortByValue(treeMapAreaValue);
        ArrayList<Area> nAreasWithTheHighestValue = new ArrayList<>();
        if (receivedNumber > 0) {
            if (!finalArray.isEmpty()) {
                for (int i = 0; i < receivedNumber; i++) {
                    nAreasWithTheHighestValue.add(finalArray.get(i));

                }
            }
        }

        return nAreasWithTheHighestValue;
    }

    // Exercício 4

    /**
     * Método para encontrar a área (País) mais próximo de uma dada latitude e longitude, que contenha um determinado
     * Item, e que esse Item contenha um determinado Element de um determinado Year.
     *
     * @param latitude       A latitude desejada
     * @param longitude      A longitude desejada
     * @param currentItem    O ‘item’ que queremos que o país tenha
     * @param currentElement O elemento que queremos que o Item tenha
     * @param currentYear    O ano em que queremos que o ‘item’ e o elemento existam
     * @return Devolve o KDTree.Node2D mais próximo das coordenadas desejadas que satisfaçam as condições do ‘item’, do
     * elemento e do ano
     */
    public KDTree.Node2D<Area> nearestArea(double latitude, double longitude, Item currentItem, Element currentElement, Year currentYear) {
        List<Area> validAreas = findAllAreasByItemElementYear(currentItem, currentElement, currentYear);

        if (validAreas.isEmpty())
            return null;

        List<KDTree.Node2D<Area>> validAreaCoordinates = findAllValidNode2D(validAreas);

        if (validAreaCoordinates.isEmpty())
            return null;

        KDTree<Area> kdTree = new KDTree<>();
        kdTree.buildTree(validAreaCoordinates);

        return kdTree.findNearestNeighbour(longitude, latitude);

    }

    /**
     * Encontra todos os países que contenham um determinado item, com um determinado elemento num determinado ano, e
     * devolve uma lista com todos esses países.
     *
     * @param currentItem    O ‘item’ que queremos que o país tenha
     * @param currentElement O elemento que queremos que o Item tenha
     * @param currentYear    O ano em que queremos que o ‘item’ e o elemento existam
     * @return Uma lista com todos os países que satisfaçam a condição
     */
    public List<Area> findAllAreasByItemElementYear(Item currentItem, Element currentElement, Year currentYear) {
        List<Area> validAreas = new ArrayList<>();

        if (currentItem == null || currentElement == null)
            return validAreas;

        Iterable<Area> areasAVLIterator = this.allDataAVL.inOrder();

        for (Area area : areasAVLIterator) {
            if (area.hasItemElementYear(currentItem, currentElement, currentYear)) {
                validAreas.add(area);
            }
        }

        return validAreas;
    }

    /**
     * Método para encontrar todos os Node2D da KDTree que correspondam aos países que são passados por parâmetro, e
     * devolver todos esses KDTree.Node2D numa lista
     *
     * @param validAreas Lista com as áreas que queremos saber as coordenadas e respetivo KDTree.Node2D
     * @return Devolve uma lista que contem todos os KDTree.Node2D que correspondam aos países presentes na lista
     * passada por parâmetro
     */
    public List<KDTree.Node2D<Area>> findAllValidNode2D(List<Area> validAreas) {
        List<KDTree.Node2D<Area>> validAreasCoordinates = new ArrayList<>();

        if (validAreas == null)
            return validAreasCoordinates;

        Map<Area, KDTree.Node2D<Area>> allAreasCoordinates = data2DTree.inOrder();

        for (Area area : validAreas) {
            if (allAreasCoordinates.get(area) != null) {
                validAreasCoordinates.add(allAreasCoordinates.get(area));
            }
        }

        return validAreasCoordinates;
    }
    // Fim do exercício 4

    //exercicio 5
    public long valueProductionGeographicalArea(Item item, Element element, Year year, Double latI, Double longI, Double latF, Double longF) {
        List<Area> listArea = allDataAVL.inOrderList();

        List<Area> list = data2DTree.rangeSearch(listArea, latI, longI, latF, longF);

        long sum = 0;
        for (Area area : list) {
            Value value = findValue(area, item, element, year);
            if (value != null) {
                sum += value.getValue();
            }
        }

        return sum;
    }
    //fim do exercício 5

}
