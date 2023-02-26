package Trabalho2.model;

/**
 * Classe que representa o código de um 'item'.
 */
public class ItemCode extends Item {

    /**
     * Código do 'item'.
     */
    private String itemCode;

    /**
     * Construtor para inicializar uma instância do objeto ItemCode.
     *
     * @param itemCode Código do 'item'.
     */
    public ItemCode(String itemCode) {
        super();
        this.itemCode = itemCode;
    }

    /**
     * Método para definir o identificador do 'item'.
     *
     * @return Devolve o identificador do 'item'.
     */
    @Override
    public String getItemIdentifier() {
        return this.itemCode;
    }
}
