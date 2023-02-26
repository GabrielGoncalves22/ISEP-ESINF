package Trabalho2.dataStructure;

import Trabalho2.specificSortingLists.QuickSort2DNode;

import java.awt.geom.Point2D;
import java.util.*;

/**
 * Classe que representa a estrutura de dados KDTree
 *
 * @param <E> Tipo de dados a ser utilizado na construção desta estrutura
 */
public class KDTree<E extends Comparable<E>> {

    /**
     * Classe que representa um nó da estrutura de dados da KDTree
     *
     * @param <E> Tipo de dados a ser utilizado na construção do objeto
     */
    public static class Node2D<E> {

        private Point2D.Double coords;  // Coordenadas correspondentes a este node2D
        private E element;              // O elemento guardado neste node2D
        private Node2D<E> left;         // A referência para o Node2D filho da esquerda (se existir)
        private Node2D<E> right;        // A referência para o Node2D filho da direita (se existir)

        /**
         * Construtor sem parâmetros para inicializar um objeto desta classe
         */
        public Node2D() {
        }

        /**
         * Constrói um nó com um dado elemento, um nó para a esquerda, um nó para a direita e as coordenadas em que este
         * nó se irá encontrar
         *
         * @param e          O elemento a ser guardado
         * @param leftChild  A referência para o nó filho da esquerda
         * @param rightChild A referência para o nó filho da direita
         * @param x          A coordenada x do nó
         * @param y          A coordenada y do nó
         */
        public Node2D(E e, Node2D<E> leftChild, Node2D<E> rightChild, double x, double y) {
            element = e;
            left = leftChild;
            right = rightChild;
            this.coords = new Point2D.Double(x, y);
        }

        /**
         * Método que devolve o elemento atual do nó
         *
         * @return O elemento atual do nó
         */
        public E getElement() {
            return element;
        }

        /**
         * Método que devolve o nó filho da esquerda
         *
         * @return O nó filho da esquerda
         */
        public Node2D<E> getLeft() {
            return left;
        }

        /**
         * Método que devolve o nó filho da direita
         *
         * @return O nó filho da direita
         */
        public Node2D<E> getRight() {
            return right;
        }

        /**
         * Método que devolve as coordenadas deste nó
         *
         * @return As coordenadas deste nó
         */
        public Point2D.Double getCoords() {
            return coords;
        }

        /**
         * Método que devolve o valor x atual das coordenadas
         *
         * @return O valo x atual das coordenadas
         */
        public double getX() {
            return coords.x;
        }

        /**
         * Método que devolve o valor y atual das coordenadas
         *
         * @return O valor y atual das coordenadas
         */
        public double getY() {
            return coords.y;
        }

        /**
         * Método para atribuir um novo elemento a este nó
         *
         * @param element O elemento a ser atribuído a este nó
         */
        public void setElement(E element) {
            this.element = element;
        }

        /**
         * Método para atribuir um novo filho da esquerda a este nó
         *
         * @param leftChild O novo filho da esquerda
         */
        public void setLeft(Node2D<E> leftChild) {
            this.left = leftChild;
        }

        /**
         * Método para atribuir um novo filho da direita a este nó
         *
         * @param rightChild O novo filho da direita
         */
        public void setRight(Node2D<E> rightChild) {
            this.right = rightChild;
        }

        /**
         * Método para atribuir umas novas coordenadas a este nó
         *
         * @param coords As novas coordenadas
         */
        public void setCoords(Point2D.Double coords) {
            this.coords = coords;
        }

        /**
         * Método para atribuir um novo valor de x às coordenadas deste nó
         *
         * @param x O novo valor para a coordenada x
         */
        public void setX(double x) {
            this.coords.x = x;
        }

        /**
         * Método para atribuir um novo valor de y às coordenadas deste nó
         *
         * @param y O novo valor para a coordenada y
         */
        public void setY(double y) {
            this.coords.y = y;
        }

        /**
         * Método para trocar os valores do nó atual por valores de outro nó.
         *
         * @param node2D O outro nó do qual queremos retirar a informação
         */
        protected void setObject(Node2D<E> node2D) {
            element = node2D.getElement();
            left = node2D.getLeft();
            right = node2D.getRight();
            coords = new Point2D.Double(node2D.getX(), node2D.getY());
        }
    }

    /**
     * Método para devolver a raiz da KDTree
     *
     * @return A raiz da KDTree
     */
    public Node2D<E> getRoot() {
        return root;
    }

    /**
     * Comparator que é responsável por comparar os nós pelo seu valor da coordenada x
     */
    private final Comparator<Node2D<E>> cmpX = new Comparator<Node2D<E>>() {
        @Override
        public int compare(Node2D<E> n1, Node2D<E> n2) {
            return Double.compare(n1.getX(), n2.getX());
        }
    };

    /**
     * Comparator que é responsável por comparar os nós pelo seu valor da coordenada y
     */
    private final Comparator<Node2D<E>> cmpY = new Comparator<Node2D<E>>() {
        @Override
        public int compare(Node2D<E> n1, Node2D<E> n2) {
            return Double.compare(n1.getY(), n2.getY());
        }
    };

    /**
     * Nó raiz da KDTree
     */
    protected Node2D<E> root;

    /**
     * Construtor sem parâmetros para inicializar uma instância desta classe
     */
    public KDTree() {
        root = null;
    }

    /**
     * Método para inserir um novo nó à KDTree a partir da sua raiz
     *
     * @param element O elemento a inserir no nó
     * @param x       A coordenada do x que estará associada a este novo nó
     * @param y       A coordenada do y que estará associada a este novo nó
     */
    public void insert(E element, double x, double y) {
        Node2D<E> node2D = new Node2D<>(element, null, null, x, y);
        if (root == null)
            root = node2D;
        else
            insert(root, node2D, true);
    }

    /**
     * Método que realiza a inserção do novo nó na KDTree de forma a colocar este novo nó na posição mais adequada
     *
     * @param currentNode Corresponde ao nó que estamos a verificar atualmente em comparação com o nó a inserir
     * @param node        Corresponde ao novo nó que estamos a inserir
     * @param divX        true para comparar os nós pelas coordenadas do x, ou false para comparar os nós pelas
     *                    coordenadas do y
     */
    private void insert(Node2D<E> currentNode, Node2D<E> node, boolean divX) {
        if (node.coords.equals(currentNode.coords))
            return;

        int cmpResult = (divX ? cmpX : cmpY).compare(node, currentNode);

        if (cmpResult < 0)
            if (currentNode.getLeft() == null)
                currentNode.setLeft(node);
            else
                insert(currentNode.getLeft(), node, !divX);
        else if (currentNode.getRight() == null)
            currentNode.setRight(node);
        else
            insert(currentNode.getRight(), node, !divX);
    }

    /**
     * Método para construir a KDTree completa com a passagem de uma lista que contem todos os nós que devem ser
     * armazenados
     *
     * @param info Lista que contem objetos do tipo Node2D
     */
    public void buildTree(List<Node2D<E>> info) {
        root = buildTree(info, true);
    }

    /**
     * Método para construir a KDTree com uma lista de objetos do tipo Node2D de forma a manter a estrutura da árvore
     * equilibrada
     *
     * @param info Lista que contem objetos do tipo Node2D
     * @param divX true para comparar os nós pelas coordenadas do x, ou false para comparar os nós pelas coordenadas do
     *             y
     * @return
     */
    private Node2D<E> buildTree(List<Node2D<E>> info, boolean divX) {

        if (info == null || info.isEmpty())
            return null;

        QuickSort2DNode.quickSort(info, divX ? cmpX : cmpY);

        int mid = info.size() / 2;

        Node2D<E> node2D = new Node2D<E>();
        node2D.element = info.get(mid).getElement();
        node2D.coords = info.get(mid).getCoords();
        node2D.left = buildTree(info.subList(0, mid), !divX);

        if (mid + 1 <= info.size() - 1)
            node2D.right = buildTree(info.subList(mid + 1, info.size()), !divX);

        return node2D;

    }

    /**
     * Método para encontrar o nó mais próximo de umas dadas coordenadas desta KDTree
     *
     * @param x O valor da coordenada x (longitude)
     * @param y O valor da coordenada y (latitude)
     * @return Devolve o Node2D que se encontram mais próximo das coordenadas fornecidas
     */
    public Node2D<E> findNearestNeighbour(double x, double y) {
        return findNearestNeighbour(root, x, y, root, true);
    }

    /**
     * Método para realizar a procura do nó desta KDTree mais próximo das coordenadas fornecidas
     *
     * @param node2D      O nó atual em que nos encontramos a fazer a procura por um nó mais próximo das coordenadas
     *                    desejadas
     * @param x           O valor da coordenada x (longitude)
     * @param y           O valor da coordenada y (latitude)
     * @param closestNode O Node2D atual mais próximo das coordenadas desejadas
     * @param divX        Para sabermos se neste nível da árvore devemos comparar as coordenadas do x ou as coordenadas
     *                    do y
     * @return Devolve o Node2D mais próximo das coordenadas desejadas
     */
    private Node2D<E> findNearestNeighbour(Node2D<E> node2D, double x, double y, Node2D<E> closestNode, boolean divX) {

        if (node2D == null)
            return null;

        double d = Point2D.distanceSq(node2D.coords.x, node2D.coords.y, x, y);
        double closestDist = Point2D.distanceSq(closestNode.coords.x, closestNode.coords.y, x, y);

        if (closestDist > d)
            closestNode.setObject(node2D);

        double delta = divX ? x - node2D.coords.x : y - node2D.coords.y;
        double delta2 = delta * delta;

        Node2D<E> node1 = delta < 0 ? node2D.left : node2D.right;
        Node2D<E> node2 = delta < 0 ? node2D.right : node2D.left;

        findNearestNeighbour(node1, x, y, closestNode, !divX);

        if (delta2 < closestDist)
            findNearestNeighbour(node2, x, y, closestNode, !divX);

        return closestNode;
    }

    /**
     * Método que devolve um HashMap dos elementos da KDTree, construído pela travessia in-order
     *
     * @return HashMap dos elementos da KDTree pela travessia in-order
     */
    public Map<E, Node2D<E>> inOrder() {
        Map<E, Node2D<E>> snapshot = new HashMap<>();
        if (root != null)
            inOrderSubtree(root, snapshot);   // Preenche o mapa de forma recursiva
        return snapshot;
    }

    /**
     * Adiciona os elementos das subtree presentes no nó atual para um dado HashMap usando a travessia in-order
     *
     * @param node2D   Nó que serve como root da subtree
     * @param snapshot O HashMap onde os valores são colocados
     */
    private void inOrderSubtree(Node2D<E> node2D, Map<E, Node2D<E>> snapshot) {
        if (node2D == null)
            return;
        inOrderSubtree(node2D.getLeft(), snapshot);
        Node2D<E> newNode2D = new Node2D<>(node2D.getElement(), null, null, node2D.getX(), node2D.getY());
        snapshot.put(node2D.element, newNode2D);
        inOrderSubtree(node2D.getRight(), snapshot);
    }

    public List<E> rangeSearch(List<E> result, double initialX, double initialY, double finalX, double finalY) {
        List<E> list = rangeSearch(result, root, true, initialX, initialY, finalX, finalY);
        return list.isEmpty() ? null : list;
    }

    private List<E> rangeSearch(List<E> result, Node2D<E> node2D, boolean divX, double initialX, double initialY, double finalX, double finalY) {
        if (node2D == null)
            return result;

        boolean insideX = node2D.getX() > initialX && node2D.getX() < finalX;
        boolean insideY = node2D.getY() > initialY && node2D.getY() < finalY;

        if (insideX && insideY)
            result.add(node2D.getElement());

        if (divX) {
            if (insideX) {
                result = rangeSearch(result, node2D.getRight(), false, initialX, finalX, initialY, finalY);
                return rangeSearch(result, node2D.getLeft(), false, initialX, finalX, initialY, finalY);
            } else if (node2D.getX() < initialX) {
                return rangeSearch(result, node2D.getRight(), false, initialX, finalX, initialY, finalY);
            } else if (node2D.getX() > finalX) {
                return rangeSearch(result, node2D.getLeft(), false, initialX, finalX, initialY, finalY);
            }
        } else {
            if (insideY) {
                result = rangeSearch(result, node2D.getLeft(), true, initialX, finalX, initialY, finalY);
                return rangeSearch(result, node2D.getRight(), true, initialX, finalX, initialY, finalY);
            } else if (node2D.getY() < initialY) {
                return rangeSearch(result, node2D.getRight(), true, initialX, finalX, initialY, finalY);
            } else if (node2D.getY() > finalY) {
                return rangeSearch(result, node2D.getLeft(), true, initialX, finalX, initialY, finalY);

            }
        }

        return result;
    }
}