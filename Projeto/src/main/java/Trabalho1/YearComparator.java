package Trabalho1;

import java.util.Comparator;

/**
 * Classe que representa um comparador para a classe Trabalho1.Year.
 */
public class YearComparator implements Comparator<Year> {
    /**
     * Método para comparar a quantidade de produção entre dois anos.
     *
     * @param year1 Objeto do ano um.
     * @param year2 Objeto do ano dois.
     * @return
     */
    @Override
    public int compare(Year year1, Year year2) {
        int compareYear = year1.getProductionYear() - year2.getProductionYear();

        if (compareYear != 0) {
            return compareYear;
        } else {
            return year2.getValue().getProductionQuantity() - year1.getValue().getProductionQuantity();
        }
    }
}
