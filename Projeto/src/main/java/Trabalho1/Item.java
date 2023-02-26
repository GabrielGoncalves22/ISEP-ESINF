package Trabalho1;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Classe que representa um ‘item’.
 */
public class Item implements Comparable<Item>, ItemInformation {
    /**
     * Nome do ‘item’.
     */
    private String name;

    /**
     * ‘set’ com os registos relativos aos anos existentes neste ‘item’.
     */
    private Set<Year> years;

    /**
     * Construtor para inicializar uma instância do objeto Trabalho1.Item com o parâmetro name.
     *
     * @param name Nome do ‘item’.
     */
    public Item(String name) {
        this.name = name;
        this.years = new TreeSet<>();
    }

    /**
     * Método para devolver o nome do ‘item’.
     *
     * @return O nome do ‘item’.
     */
    public String getName() {
        return name;
    }

    /**
     * Método para devolver o ‘set’ com os anos relativos a este ‘item’.
     *
     * @return O ‘set’ com os anos relativos a este ‘item’.
     */
    public Set<Year> getMapYear() {
        return years;
    }

    /**
     * Método para adicionar um novo ano ao set.
     *
     * @param year Objeto Trabalho1.Year.
     */
    public void addYear(Year year) {
        this.years.add(year);
    }

    /**
     * Método para verificar se este objeto atual é igual a outro objeto do mesmo tipo.
     *
     * @param obj O objeto a ser comparado com este atual.
     * @return true se o outro objeto representa uma String do nome do ‘item’ igual a este, caso contrário devolve
     * false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Item otherItem = (Item) obj;

        return name.equals(otherItem.name);
    }

    /**
     * Calcula o hashcode para este objeto utilizando o nome do ‘item’.
     *
     * @return O valor de hash calculado.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Método para comparar este objeto a outro.
     *
     * @param otherItem O objeto que será comparado a este.
     * @return O valor 0 se o outro objeto tiver um nome do ‘item’ igual a este. Um valor menos de 0 se a String do
     * ‘item’ do país deste objeto for lexicalmente menor do que o do outro objeto. Um valor mais de 0 se a String do
     * ‘item’ do país deste objeto for lexicalmente maior do que o do outro objeto.
     */
    @Override
    public int compareTo(Item otherItem) {
        return name.compareTo(otherItem.name);
    }

    /**
     * Método para calcular a quantidade total produzida deste produto nos diferentes anos.
     *
     * @return A quantidade total de produção deste produto nos diferentes anos.
     */
    @Override
    public int getTotalQuantity() {

        int totalQuantity = 0;

        for (Year year : years) {
            totalQuantity += year.getValue().getProductionQuantity();
        }

        return totalQuantity;
    }

    /**
     * Método para saber qual é o número máximo de anos consecutivos que houve um aumento na quantidade produzida.
     *
     * @return Devolve o número máximo de anos consecutivos em que houve um aumento da quantidade de produção.
     */
    @Override
    public int getMaximumNumberConsecutiveYears() {
        Year currentYear = null;
        int countTemporal = 1, finalCount = 1;

        for (Year year : years) {
            if (currentYear != null) {
                if (year.getValue().getProductionQuantity() > currentYear.getValue().getProductionQuantity()) {
                    countTemporal++;
                } else {
                    countTemporal = 1;
                }
            }

            if (countTemporal > finalCount) {
                finalCount = countTemporal;
            }

            currentYear = year;
        }

        return finalCount;
    }

    /**
     * Método para calcular a maior diferença de quantidade produzida em dois anos diferentes e devolver um objeto do
     * tipo Trabalho1.Pair onde a chave irá conter uma String que indica os dois anos em que essas diferenças ocorreu e como valor
     * a diferença de produção entre esses dois anos.
     *
     * @return Um objeto Trabalho1.Pair com a chave do valor dos dois anos (em String) e com a diferença de produção entre eles.
     */
    @Override
    public Pair<String, Integer> getBiggestDifferenceQuantity() {
        Year currentYear = null;
        int differenceProduction = Integer.MIN_VALUE;
        String pairYears = "";

        for (Year year : years) {
            if (currentYear != null) {
                int productionQuantity = Math.abs(currentYear.getValue().getProductionQuantity() - year.getValue().getProductionQuantity());

                if (productionQuantity > differenceProduction) {
                    differenceProduction = productionQuantity;
                    pairYears = currentYear.getProductionYear() + "/" + year.getProductionYear();
                }
            }

            currentYear = year;
        }

        return new Pair<>(pairYears, differenceProduction);
    }

    /**
     * Método para procurar e devolver o ano com uma quantidade de produção maior ou igual à desejada.
     *
     * @param quantity A quantidade desejada.
     * @return O ano com a quantidade de produção maior ou igual à desejada.
     */
    @Override
    public Year getYearWithQuantityGreaterOrEqual(int quantity) {
        Iterator<Year> iterator = years.iterator();

        while (iterator.hasNext()) {

            Year year = iterator.next();
            if (year.getValue().getProductionQuantity() >= quantity) {
                return year;
            }
        }

        return null;
    }
}
