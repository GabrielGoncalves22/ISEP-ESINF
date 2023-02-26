package Trabalho2.model;

/**
 * Classe que representa o código de um elemento.
 */
public class ElementCode extends Element {

    /**
     * Código do elemento.
     */
    private String elementCode;

    /**
     * Construtor para inicializar uma instância do objeto ElementCode.
     *
     * @param elementCode Código do elemento.
     */
    public ElementCode(String elementCode) {
        super();
        this.elementCode = elementCode;
    }

    /**
     * Método para definir o identificador do elemento.
     *
     * @return Devolve o identificador do elemento.
     */
    @Override
    public String getElementIdentifier() {
        return this.elementCode;
    }
}
