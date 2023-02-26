package Trabalho2.model;

/**
 * Classe que representa o código M49 de uma localização.
 */
public class AreaCodeM49 extends Area {

    /**
     * Código M49 do país.
     */
    private String codeM49;

    /**
     * Construtor para inicializar uma instância do objeto AreaCodeM49.
     *
     * @param codeM49 Código M49 do país.
     */
    public AreaCodeM49(String codeM49) {
        super();
        this.codeM49 = codeM49;
    }

    /**
     * Método para definir o identificador da localização.
     *
     * @return Devolve o identificador da localização.
     */
    @Override
    public String getAreaIdentifier() {
        return this.codeM49;
    }
}
