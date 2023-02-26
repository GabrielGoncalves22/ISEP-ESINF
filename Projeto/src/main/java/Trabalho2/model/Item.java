package Trabalho2.model;

import Trabalho2.dataStructure.AVL;
import Trabalho2.dataStructure.BST;

import java.util.Iterator;

/**
 * Classe abstrata que representa um ‘item’.
 */
public abstract class Item implements Comparable<Item> {

    /**
     * AVL do elemento.
     */
    private AVL<Element> elementAVL;

    /**
     * Construtor para inicializar uma instância do objeto Item.
     */
    public Item() {
        this.elementAVL = new AVL<>();
    }

    /**
     * Método abstrato para definir o identificador do 'item'.
     *
     * @return Devolve o identificador do 'item'.
     */
    public abstract String getItemIdentifier();

    /**
     * Função para retornar a AVL do elemento.
     *
     * @return AVL do elemento.
     */
    public AVL<Element> getElementAVL() {
        return elementAVL;
    }

    /**
     * Função para procurar na AVL do elemento o nó com o elemento passado por parâmetro.
     *
     * @param currentElement O elemento.
     * @return O nó com o elemento.
     */
    public BST.Node<Element> findNodeElementAVL(Element currentElement) {
        return this.elementAVL.find(currentElement);
    }

    /**
     * Método para inserir na AVL do elemento um elemento e um ano passado por parâmetro.
     *
     * @param currentElement O elemento.
     * @param currentYear    O ano de produção.
     */
    public void insertNewElementInAVL(Element currentElement, Year currentYear) {
        currentElement.insertNewYearInAvl(currentYear);
        elementAVL.insert(currentElement);
    }

    /**
     * Método para verificar se este objeto atual é igual a outro objeto do mesmo tipo.
     *
     * @param obj O objeto a ser comparado com este atual.
     * @return true se o outro objeto representa uma String do identificador do 'item' igual a este, caso contrário devolve false.
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

        return (getItemIdentifier().equals(otherItem.getItemIdentifier()));
    }

    /**
     * Método para comparar este objeto a outro.
     *
     * @param otherItem O objeto que será comparado a este.
     * @return O valor 0 se o outro objeto tiver um identificador do ‘item’ igual a este. Um valor menos de 0 se a String do
     * ‘item’ deste objeto for lexicalmente menor do que o do outro objeto. Um valor mais de 0 se a String do
     * ‘item’ deste objeto for lexicalmente maior do que o do outro objeto.
     */
    @Override
    public int compareTo(Item otherItem) {
        return getItemIdentifier().compareTo(otherItem.getItemIdentifier());
    }
}
