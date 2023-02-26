package Trabalho2.model;

/**
 * Classe que representa valores.
 */
public class Value implements Comparable<Value> {

    /**
     * Representa a unidade do valor.
     */
    private String unit;

    /**
     * Representa a quantidade de produção.
     */
    private long value;

    /**
     * Representa a bandeira.
     */
    private char flag;

    /**
     * Representa a descrição da bandeira.
     */
    private String flagDescription;

    /**
     * Construtor para inicializar um objeto com uma determinada unidade, quantidade de produção e bandeira.
     *
     * @param unit  Representa a unidade do valor.
     * @param value Representa a quantidade de produção.
     * @param flag  Representa a bandeira.
     */
    public Value(String unit, long value, char flag) {
        this.unit = unit;
        this.value = value;
        this.flag = flag;
    }

    /**
     * Construtor para inicializar um objeto com uma determinada unidade, quantidade de produção, bandeira e descrição
     * da bandeira.
     *
     * @param unit            Representa a unidade do valor.
     * @param value           Representa a quantidade de produção.
     * @param flag            Representa a bandeira.
     * @param flagDescription Representa a descrição da bandeira.
     */
    public Value(String unit, long value, char flag, String flagDescription) {
        this.unit = unit;
        this.value = value;
        this.flag = flag;
        this.flagDescription = flagDescription;
    }

    /**
     * Construtor para inicializar um objeto com uma determinada quantidade de produção.
     *
     * @param value Representa a quantidade de produção.
     */
    public Value(long value) {
        this.value = value;
    }

    /**
     * Método para retornar o valor correspondente à quantidade de produção.
     *
     * @return A quantidade de produção.
     */
    public long getValue() {
        return value;
    }

    /**
     * Método para retornar o valor correspondente à bandeira.
     *
     * @return A bandeira.
     */
    public char getFlag() {
        return flag;
    }

    /**
     * Método para definir a descrição da bandeira.
     *
     * @param flagDescription A descrição da bandeira.
     */
    public void setFlagDescription(String flagDescription) {
        this.flagDescription = flagDescription;
    }

    /**
     * Método para verificar se este objeto atual é igual a outro objeto do mesmo tipo.
     *
     * @param obj O objeto a ser comparado com este atual.
     * @return true se o outro objeto representa um valor e contém a mesma unidade, quantidade de produção, bandeira e
     * descrição da bandeira que este.
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

        Value otherValue = (Value) obj;

        return (unit.equals(otherValue.unit)) &&
                (value == otherValue.value) &&
                (flag == otherValue.flag) &&
                (flagDescription.equals(otherValue.flagDescription));
    }

    /**
     * Método para comparar este objeto a outro.
     *
     * @param otherValue O objeto que será comparado a este.
     * @return O valor 0 se o outro objeto tiver uma quantidade de produção igual a este. Um valor menos de 0 se a quantidade de
     * produção deste objeto for menor que o do outro objeto. Um valor maior do que 0 se a quantidade de produção deste objeto
     * for maior que o do outro objeto.
     */
    @Override
    public int compareTo(Value otherValue) {
        return (int) ((int) value - otherValue.value);
    }

    @Override
    public String toString() {
        return "Value{" +
                "unit='" + unit + '\'' +
                ", value=" + value +
                ", flag=" + flag +
                ", flagDescription='" + flagDescription + '\'' +
                '}';
    }
}
