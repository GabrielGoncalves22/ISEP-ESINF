package Trabalho2.model;

import Trabalho2.dataStructure.AVL;
import Trabalho2.dataStructure.BST;

import java.util.Iterator;

/**
 * Classe abstrata que representa elementos.
 */
public abstract class Element implements Comparable<Element> {

    /**
     * AVL do ano.
     */
    private AVL<Year> yearAVL;

    /**
     * Construtor para inicializar uma instância do objeto Element.
     */
    public Element() {
        this.yearAVL = new AVL<>();
    }

    /**
     * Método abstrato para definir o identificador do elemento.
     *
     * @return Devolve o identificador do elemento.
     */
    public abstract String getElementIdentifier();

    /**
     * Função para retornar a AVL do ano.
     *
     * @return AVL do ano.
     */
    public AVL<Year> getYearAVL() {
        return yearAVL;
    }

    /**
     * Função para procurar na AVL do ano o nó com o ano passado por parâmetro.
     *
     * @param currentYear O ano de produção.
     * @return O nó com o ano.
     */
    public BST.Node<Year> findNodeYearAVL(Year currentYear) {
        return this.yearAVL.find(currentYear);
    }

    /**
     * Método para inserir na AVL do ano um ano passado por parâmetro.
     *
     * @param year O ano de produção.
     */
    public void insertNewYearInAvl(Year year) {
        yearAVL.insert(year);
    }

    /**
     * Função para retornar o maior ano de produção do elemento.
     *
     * @return O ano de produção.
     */
    public Year getMaxYear() {
        Iterable<Year> yearAVLIterator = yearAVL.inOrder();
        Year maxYear = null;

        for (Year year : yearAVLIterator) {
            if (maxYear == null) {
                maxYear = year;
            } else {
                if (year.compareTo(maxYear) > 0) {
                    maxYear = year;
                }
            }
        }
        return maxYear;

    }

    /**
     * Método para verificar se este objeto atual é igual a outro objeto do mesmo tipo.
     *
     * @param obj O objeto a ser comparado com este atual.
     * @return true se o outro objeto representa uma String do identificador do elemento igual a este, caso contrário devolve false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Element otherElement = (Element) obj;

        return (getElementIdentifier().equals(otherElement.getElementIdentifier()));
    }

    /**
     * Método para comparar este objeto a outro.
     *
     * @param otherElement O objeto que será comparado a este.
     * @return O valor 0 se o outro objeto tiver um identificador do elemento igual a este. Um valor menos de 0 se a String do
     * elemento deste objeto for lexicalmente menor do que o do outro objeto. Um valor mais de 0 se a String do
     * elemento deste objeto for lexicalmente maior do que o do outro objeto.
     */
    @Override
    public int compareTo(Element otherElement) {
        return getElementIdentifier().compareTo(otherElement.getElementIdentifier());
    }
}
