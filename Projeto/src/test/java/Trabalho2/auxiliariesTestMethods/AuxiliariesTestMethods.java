package Trabalho2.auxiliariesTestMethods;

import Trabalho2.dataStructure.KDTree;

import java.util.List;

public class AuxiliariesTestMethods {

    /**
     * Método para validar se duas listas tem todos os seus elementos iguais pela mesma ordem
     *
     * @param list1 Primeira lista
     * @param list2 Segunda lista
     * @param <E>   Tipo dos elementos das listas
     * @return true se todos os elementos das duas listas forem iguais pela mesma ordem, ou false em caso contrário
     */
    public static <E> boolean assertEqualsLists(List<E> list1, List<E> list2) {

        if (list1.size() != list2.size()) {
            return false;
        }

        int pos = 0;
        int finalPos = list1.size();
        boolean areEquals = true;

        while (pos < finalPos && areEquals) {
            if (!list1.get(pos).equals(list2.get(pos))) {
                areEquals = false;
            }
            pos++;
        }

        return areEquals;

    }

    /**
     * Método para validar se duas listas que contêm objetos Node2D de um dado tipo são iguais, ou seja, se as duas
     * listas tem elementos iguais na mesma posição
     * @param list1 Primeira lista
     * @param list2 Segunda lista
     * @param <E> Tipo dos elementos das listas
     * @return true se todos os elementos das duas listas forem iguais pela mesma ordem, ou false em caso contrário
     */
    public static <E> boolean assertEqualsListNodes2D(List<KDTree.Node2D<E>> list1, List<KDTree.Node2D<E>> list2) {

        if (list1.size() != list2.size()) {
            return false;
        }

        int pos = 0;
        int finalPos = list1.size();
        boolean areEquals = true;

        while (pos < finalPos && areEquals) {
            KDTree.Node2D<E> node2DOne = list1.get(pos);
            KDTree.Node2D<E> node2DTwo = list2.get(pos);

            if (!node2DOne.getElement().equals(node2DTwo.getElement())
                    || !node2DOne.getCoords().equals(node2DTwo.getCoords())) {
                areEquals = false;
            }
            pos++;
        }

        return areEquals;

    }

}
