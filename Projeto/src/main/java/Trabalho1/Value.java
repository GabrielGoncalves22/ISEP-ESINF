package Trabalho1;

/**
 * Classe que representa valores.
 */
public class Value {
    /**
     * Representa a quantidade de produção.
     */
    private int productionQuantity;

    /**
     * Construtor para inicializar um objeto com uma determinada quantidade de produção.
     *
     * @param productionQuantity Quantidade de produção.
     */
    public Value(int productionQuantity) {
        this.productionQuantity = productionQuantity;
    }

    /**
     * Método para retornar a quantidade de produção.
     *
     * @return A quantidade de produção deste objeto.
     */
    public int getProductionQuantity() {
        return productionQuantity;
    }
}
