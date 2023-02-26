package Trabalho1;

/**
 * ‘Interface’ que representa o cabeçalho dos métodos necessários para tratar informações dos itens.
 */
public interface ItemInformation {
    int getTotalQuantity();

    int getMaximumNumberConsecutiveYears();

    Pair<String, Integer> getBiggestDifferenceQuantity();

    Year getYearWithQuantityGreaterOrEqual(int quantity);
}
