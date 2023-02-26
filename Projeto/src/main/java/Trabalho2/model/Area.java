package Trabalho2.model;

import Trabalho2.dataStructure.AVL;
import Trabalho2.dataStructure.BST;

import java.util.Objects;

/**
 * Classe abstrata que representa uma localização.
 */
public abstract class Area implements Comparable<Area> {

    /**
     * AVL do país.
     */
    private AVL<Item> itemAVL;

    /**
     * Construtor para inicializar uma instância do objeto Area.
     */
    public Area() {
        this.itemAVL = new AVL<>();
    }

    /**
     * Método abstrato para definir o identificador da localização.
     *
     * @return Devolve o identificador da localização.
     */
    public abstract String getAreaIdentifier();

    /**
     * Função para retornar a AVL da localização.
     *
     * @return AVL da localização.
     */
    public AVL<Item> getItemAVL() {
        return itemAVL;
    }

    /**
     * Função para procurar na AVL do 'item' o nó com o 'item' passado por parâmetro.
     *
     * @param currentItem O 'item'.
     * @return O nó com o 'item'.
     */
    public BST.Node<Item> findNodeItemAVL(Item currentItem) {
        return this.itemAVL.find(currentItem);
    }

    /**
     * Método para inserir na AVL do 'item' um 'item', um elemento e um ano passado por parâmetro.
     *
     * @param currentItem    O 'item'.
     * @param currentElement O elemento.
     * @param currentYear    O ano de produção.
     */
    public void insertNewItemInAVL(Item currentItem, Element currentElement, Year currentYear) {
        currentItem.insertNewElementInAVL(currentElement, currentYear);
        itemAVL.insert(currentItem);
    }

    /**
     * Método para verificar se esta área (País) tem um dado ‘item’, que contem um dado elemento, e se esse elemento foi
     * produzido/recolhido num determinado ano.
     *
     * @param currentItem    O Item que queremos verificar se já foi produzido neste país
     * @param currentElement O Element que queremos verificar se existe no Item pretendido deste país
     * @param currentYear    O Year que queremos verificar se existe no Element pretendido.
     * @return true se estas condições forem verificadas para este país, ou false em caso contrário
     */
    public boolean hasItemElementYear(Item currentItem, Element currentElement, Year currentYear) {

        if (currentItem == null || currentElement == null)
            return false;

        boolean hasItemElementYear = false;

        BST.Node<Item> itemNode = findNodeItemAVL(currentItem);
        if (itemNode != null) {
            BST.Node<Element> elementNode = itemNode.getElement().findNodeElementAVL(currentElement);
            if (elementNode != null) {
                BST.Node<Year> yearNode = elementNode.getElement().findNodeYearAVL(currentYear);
                if (yearNode != null) {
                    hasItemElementYear = true;
                }
            }
        }

        return hasItemElementYear;

    }

    /**
     * Calcula o hashcode para este objeto utilizando o identificador da Area.
     *
     * @return O valor de hash calculado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getAreaIdentifier());
    }

    /**
     * Método para verificar se este objeto atual é igual a outro objeto do mesmo tipo.
     *
     * @param obj O objeto a ser comparado com este atual.
     * @return true se o outro objeto representa uma String do identificador do país igual a este, caso contrário devolve false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Area otherArea = (Area) obj;

        return this.getAreaIdentifier().equals(otherArea.getAreaIdentifier());
    }

    /**
     * Método para comparar este objeto a outro.
     *
     * @param otherArea O objeto que será comparado a este.
     * @return O valor 0 se o outro objeto tiver um identificador de país igual a este. Um valor menos de 0 se a String
     * do identificador do país deste objeto for lexicalmente menor do que o do outro objeto. Um valor mais de 0 se a String
     * do identificador do país deste objeto for lexicalmente maior do que o do outro objeto.
     */
    @Override
    public int compareTo(Area otherArea) {
        return this.getAreaIdentifier().compareTo(otherArea.getAreaIdentifier());
    }
}
