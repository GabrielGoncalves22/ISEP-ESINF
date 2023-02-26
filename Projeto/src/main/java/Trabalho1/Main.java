package Trabalho1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Classe que contém o método main do programa.
 */
public class Main {

    /**
     * Representa a Trabalho1.Area com o nome de país "Portugal".
     */
    static final Area AREA = new Area("Portugal");

    /**
     * Representa a fruta com o nome "Apples".
     */
    static final Item FRUIT = new Item("Apples");

    /**
     * Representa um objeto Valor.
     */
    static final Value VALUE = new Value(5000000);

    /**
     * Representa uma quantidade.
     */
    static final long QUANTITY = 1886199000;

    /**
     * Método main do programa.
     *
     * @param args Argumentos que são recebidos por parâmetro quando a aplicação é inicializada com a utiliação de
     *             parâmetros.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Production production = new Production();

        // Exercício 1
        System.out.println("Nome do ficheiro: ");
        String fileName = scanner.nextLine();

        try {
            production.readFile(fileName);
        } catch (InvalidLineException ex) {
            System.out.println(ex.getMessage());
            return;
        } catch (FileNotFoundException ex) {
            System.out.println("O ficheiro indicado não existe!");
            return;
        }

        Map<Area, Set<Item>> productionPerArea = production.getProductionPerArea();

        printInfo(productionPerArea);
        // Exercício 2
        System.out.println("------------------- Exercício 2 --------------------");
        printHasFruitAndValue(production, FRUIT, VALUE);

        // Exercício 3
        System.out.println("------------------- Exercício 3 --------------------");
        ArrayList<Pair<Area, Integer>> teste = production.getTotalValueList(production.getProductionPerArea());

        int count = production.getMinNumberOfKeysHigherThanQuantity(teste, QUANTITY);
        System.out.printf("O número mínimo de países que, em conjunto, consegue ter uma quantidade de produção " +
                "superior a %d é de %d países.\n", QUANTITY, count);

        // Exercício 4
        System.out.println("------------------- Exercício 4 --------------------");
        printHasAreaAndYear(production, FRUIT);

        // Exercício 5
        System.out.println("------------------- Exercício 5 --------------------");
        printHigherValueDifferenceProduction(production, AREA);
    }

    /**
     * Imprime no ecrã toda a informação sobre a produção presente no mapa [Trabalho1.Area, [Trabalho1.Item, [Trabalho1.Year]]].
     *
     * @param productionPerArea Mapa com toda a informação existente acerca da produção a ser apresentada no ecrã.
     */
    private static void printInfo(Map<Area, Set<Item>> productionPerArea) {
        for (Area area : productionPerArea.keySet()) {
            Set<Item> itemSet = productionPerArea.get(area);

            System.out.println("País: " + area.getCountry());

            for (Item item : itemSet) {
                Set<Year> yearSet = item.getMapYear();

                System.out.println("Fruta: " + item.getName());

                for (Year year : yearSet) {

                    System.out.println("Ano: " + year.getProductionYear() + " -->" + " Quantidade: " + year.getValue().getProductionQuantity());
                }
            }
            System.out.println();
        }
    }

    /**
     * Função para imprimir a informação do maior valor absoluto da diferença de produção nos frutos de um determinado país.
     *
     * @param country um determinado país.
     */
    public static void printHigherValueDifferenceProduction(Production production, Area country) {
        Map<Item, Pair<String, Integer>> list = production.higherValueDifferenceProduction(production.getProductionPerArea(), country);

        if (!list.isEmpty()) {
            for (Item item : list.keySet()) {
                Pair<String, Integer> pair = list.get(item);
                System.out.println("[" + pair.getKey() + ", " + item.getName() + ", " + pair.getValue() + "]");
            }
        } else {
            System.out.println("O país indicado não existe.");
        }
    }

    /**
     * Método para imprimir o nome dos países que contêm pelo menos um ano de produção de um dado fruto e com uma
     * quantidade maior ou igual à pretendida.
     *
     * @param production         Representa o objeto Trabalho1.Production.
     * @param fruit              Representa o objeto Trabalho1.Item.
     * @param quantityProduction Representa o objeto Trabalho1.Value.
     */
    public static void printHasFruitAndValue(Production production, Item fruit, Value quantityProduction) {
        ArrayList<Area> list = production.countryWithYearAndValue(production.getProductionPerArea(), fruit, quantityProduction);

        if (!list.isEmpty()) {
            for (Area area : list) {
                System.out.println(area.getCountry());
            }
        } else {
            System.out.println("Não existe nenhum país com pelo menos um ano de produção do fruto F com quantidade maior ou igual a Q");
        }
    }

    /**
     * Método para imprimir no ecrã os países agrupados pelo número máximo de anos consecutivos em que houve crescimento
     * de quantidade de produção do fruto.
     *
     * @param production Representa o objeto Trabalho1.Production.
     * @param fruit      Representa o objeto Trabalho1.Item.
     */
    public static void printHasAreaAndYear(Production production, Item fruit) {
        Map<Integer, Set<Area>> map = production.maximumNumberConsecutiveYears(production.getProductionPerArea(), fruit);

        if (!map.isEmpty()) {
            for (Integer i : map.keySet()) {
                Set<Area> areaSet = map.get(i);

                System.out.println("---------- Número anos consecutivos --> " + i + " ----------");
                for (Area area : areaSet) {
                    System.out.println(area.getCountry());
                }
            }
        } else {

            System.out.println("A fruta indicada não existe!");
        }
    }

}
