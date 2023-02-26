package Trabalho2.model;

/**
 * Classe que representa um certo ano.
 */
public class Year implements Comparable<Year> {
    /**
     * Representa o ano de produção.
     */
    private int year;

    /**
     * Representa o objeto Value para este ano.
     */
    private Value value;

    /**
     * Construtor para inicializar um objeto deste tipo com um dado ano de produção.
     *
     * @param year O ano de produção.
     */
    public Year(int year) {
        this.year = year;
    }

    /**
     * Construtor para inicializar um objeto deste tipo com um dado ano de produção e um valor representado pelo objeto
     * Value.
     *
     * @param year  O ano de produção.
     * @param value O objeto Value que irá pertencer a este ano.
     */
    public Year(int year, Value value) {
        this.year = year;
        this.value = value;
    }

    /**
     * Método para retornar o valor correspondente a este ano.
     *
     * @return O objeto Value.
     */
    public Value getValue() {
        return value;
    }

    /**
     * Método para retornar o ano de produção.
     *
     * @return O ano de produção.
     */
    public int getYear() {
        return year;
    }

    /**
     * Método para verificar se este objeto atual é igual a outro objeto do mesmo tipo.
     *
     * @param obj O objeto a ser comparado com este atual.
     * @return true se o outro objeto representa um ano de produção e contém a mesma quantidade de produção que este
     * objeto, caso contrário devolve false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Year otherYear = (Year) obj;

        return year == otherYear.year &&
                getValue().equals(otherYear.getValue());
    }

    /**
     * Método para comparar este objeto a outro.
     *
     * @param otherYear O objeto que será comparado a este.
     * @return O valor 0 se o outro objeto tiver um ano de produção igual a este. Um valor menos de 0 se o ano de
     * produção deste objeto for menor que o do outro objeto. Um valor maior do que 0 se o ano de produção deste objeto
     * for maior que o do outro objeto.
     */
    @Override
    public int compareTo(Year otherYear) {
        return year - otherYear.year;
    }
}
