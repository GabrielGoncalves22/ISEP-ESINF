package Trabalho2.comparator;

import Trabalho2.model.Value;

import java.util.Comparator;

/**
 * Classe que representa um comparador para a classe Value.
 */

public class ValueComparator implements Comparator<Value> {

    /**
     * Método para comparar a quantidade de produção entre dois valores.
     *
     * @param value1 Objeto do valor um.
     * @param value2 Objeto do valor dois.
     * @return A diferença entre o valor de value2 e o value1
     */
    @Override
    public int compare(Value value1, Value value2) {

        return (int) (value2.getValue() - value1.getValue());
    }

}
