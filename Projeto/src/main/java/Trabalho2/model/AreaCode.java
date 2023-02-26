package Trabalho2.model;

/**
 * Classe que representa o código de uma localização.
 */
public class AreaCode extends Area {

    /**
     * Código do país.
     */
    private String areaCode;

    /**
     * Construtor para inicializar uma instância do objeto AreaCode.
     *
     * @param areaCode Código do país.
     */
    public AreaCode(String areaCode) {
        super();
        this.areaCode = areaCode;
    }

    /**
     * Método para definir o identificador da localização.
     *
     * @return Devolve o identificador da localização.
     */
    @Override
    public String getAreaIdentifier() {
        return this.areaCode;
    }
}
