package Trabalho1;

/**
 * Classe que representa uma localização.
 */
public class Area implements Comparable<Area> {

    /**
     * Nome do país.
     */
    private String country;

    /**
     * Construtor para inicializar uma instância do objeto Trabalho1.Area.
     *
     * @param country O nome do país.
     */
    public Area(String country) {
        this.country = country;
    }

    /**
     * Método para devolver o nome do país.
     *
     * @return Devolve o nome do país.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Método para verificar se este objeto atual é igual a outro objeto do mesmo tipo.
     *
     * @param obj O objeto a ser comparado com este atual.
     * @return true se o outro objeto representa uma String do nome do país igual a este, caso contrário devolve false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Area otherArea = (Area) obj;

        return country.equals(otherArea.country);
    }

    /**
     * Calcula o hashcode para este objeto utilizando o nome do país.
     *
     * @return O valor de hash calculado.
     */
    @Override
    public int hashCode() {
        return country.hashCode();
    }

    /**
     * Método para comparar este objeto a outro.
     *
     * @param otherArea O objeto que será comparado a este.
     * @return O valor 0 se o outro objeto tiver um nome de país igual a este. Um valor menos de 0 se a String do nome
     * do país deste objeto for lexicalmente menor do que o do outro objeto. Um valor mais de 0 se a String do nome do
     * país deste objeto for lexicalmente maior do que o do outro objeto.
     */
    @Override
    public int compareTo(Area otherArea) {
        return country.compareTo(otherArea.country);
    }
}
