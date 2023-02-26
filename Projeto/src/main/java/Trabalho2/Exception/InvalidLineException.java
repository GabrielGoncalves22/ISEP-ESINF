package Trabalho2.exception;

/**
 * Classe para lançar uma exceção quando uma linha de um dado ficheiro se encontra com valores inválidos.
 */
public class InvalidLineException extends RuntimeException {

    /**
     * Construtor sem parâmetros para lançar a exceção com uma mensagem predefinida.
     */
    public InvalidLineException() {
        super("Existe pelo menos um erro num linha do ficheiro!");
    }

    /**
     * Construtor para lançar uma exceção com uma mensagem onde indica o número da linha onde o erro ocorreu.
     *
     * @param line A linha onde o erro foi encontrado.
     */
    public InvalidLineException(int line) {
        super("Existe um erro na linha " + line + " do ficheiro!");
    }

}
