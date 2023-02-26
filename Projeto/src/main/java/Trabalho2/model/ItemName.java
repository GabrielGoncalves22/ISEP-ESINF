package Trabalho2.model;

/**
 * Classe que representa o nome do 'item'.
 */
public class ItemName extends Item {

    /**
     * Nome do 'item'.
     */
    private String itemName;

    /**
     * Construtor para inicializar uma instância do objeto ItemName.
     *
     * @param itemName Nome do 'item'.
     */
    public ItemName(String itemName) {
        super();
        this.itemName = itemName;
    }

    /**
     * Método para definir o identificador do 'item'.
     *
     * @return Devolve o identificador do 'item'.
     */
    @Override
    public String getItemIdentifier() {
        return this.itemName;
    }
}
