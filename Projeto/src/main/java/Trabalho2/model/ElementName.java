package Trabalho2.model;

/**
 * Classe que representa o nome de um elemento.
 */
public class ElementName extends Element {

    /**
     * Nome do elemento.
     */
    private String elementName;

    /**
     * Construtor para inicializar uma instância do objeto ElementName.
     *
     * @param elementName Nome do elemento.
     */
    public ElementName(String elementName) {
        super();
        this.elementName = elementName;
    }

    /**
     * Método para definir o identificador do elemento.
     *
     * @return Devolve o identificador do elemento.
     */
    @Override
    public String getElementIdentifier() {
        return this.elementName;
    }
}
