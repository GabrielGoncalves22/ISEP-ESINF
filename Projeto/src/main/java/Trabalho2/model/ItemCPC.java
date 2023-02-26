package Trabalho2.model;

/**
 * Classe que representa o código CPC de um 'item'.
 */
public class ItemCPC extends Item {

    /**
     * Código CPC do 'item'.
     */
    private String itemCPC;

    /**
     * Construtor para inicializar uma instância do objeto ItemCPC.
     *
     * @param itemCPC Código CPC do 'item'.
     */
    public ItemCPC(String itemCPC) {
        super();
        this.itemCPC = itemCPC;
    }

    /**
     * Método para definir o identificador do 'item'.
     *
     * @return Devolve o identificador do 'item'.
     */
    @Override
    public String getItemIdentifier() {
        return this.itemCPC;
    }
}
