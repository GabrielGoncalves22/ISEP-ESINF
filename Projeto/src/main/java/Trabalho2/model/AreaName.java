package Trabalho2.model;

/**
 * Classe que representa o nome de uma localização.
 */
public class AreaName extends Area {

    /**
     * Nome do país.
     */
    private String areaName;

    /**
     * Construtor para inicializar uma instância do objeto AreaName.
     *
     * @param areaName Nome do país.
     */
    public AreaName(String areaName) {
        super();
        this.areaName = areaName;
    }

    /**
     * Método para definir o identificador da localização.
     *
     * @return Devolve o identificador da localização.
     */
    @Override
    public String getAreaIdentifier() {
        return this.areaName;
    }
}
