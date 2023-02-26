package Trabalho2.specificSortingLists;

import Trabalho2.dataStructure.KDTree;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Classe que representa o algoritmo de QuickSort para ordenar uma lista com objetos do tipo KDTree.Node2D de um
 * determinado tipo
 */
public class QuickSort2DNode {


    /**
     * Método responsável por chamar o método que realiza a ordenação de uma lista que contenha KDTree.Node2D de um
     * determinado tipo utilizando o algoritmo Quicksort
     *
     * @param v          A lista que contem todos os KDTree.Node2D a serem ordenados
     * @param comparator O comparator que deve ser utilizado para realizar a comparação dos diferentes valores ao
     *                   realizar a ordenação
     * @param <E>        O tipo de dados que o KDTree.Node2D tem
     */
    public static <E extends Comparable<E>> void quickSort(List<KDTree.Node2D<E>> v, Comparator<KDTree.Node2D<E>> comparator) {
        quickSort(v, 0, v.size() - 1, comparator);
    }

    /**
     * Método recursivo que realiza a ordenação da lista do tipo KDTree.Node2D de um determinado tipo
     *
     * @param v          A lista que contem todos os KDTree.Node2D a serem ordenados
     * @param left       Posição do início da lista que será trabalhada
     * @param right      Posição do fim da lista que será trabalhada
     * @param comparator O comparator que deve ser utilizado para realizar a comparação dos diferentes valores ao
     *                   realizar a ordenação
     * @param <E>        O tipo de dados que o KDTree.Node2D tem
     */
    private static <E extends Comparable<E>> void quickSort(List<KDTree.Node2D<E>> v, int left, int right, Comparator<KDTree.Node2D<E>> comparator) {
        KDTree.Node2D<E> pivot = v.get((left + right) / 2);
        int i = left, j = right;

        while (i <= j) {
            while (comparator.compare(v.get(i), pivot) < 0) {
                i++;
            }
            while (comparator.compare(v.get(j), pivot) > 0) {
                j--;
            }
            if (i <= j) {
                Collections.swap(v, i, j);
                i++;
                j--;
            }
        }
        if (left < j) {
            quickSort(v, left, j, comparator);
        }
        if (right > i) {
            quickSort(v, i, right, comparator);
        }

    }

}
