package Trabalho2.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Classe para realizar testes à classe ReadFile
 */
public class ReadFileTest {

    /**
     * Teste ao método para ler dados de um ficheiro em que é esperado que o ficheiro no caminho utilizado não seja
     * encontrado, lançando assim uma exceção.
     *
     * @throws FileNotFoundException Exceção lançada caso não exista o ficheiro com o nome utilizado.
     */
    @Test(expected = FileNotFoundException.class)
    public void readFileTest1() throws IOException {
        ReadFile rf = new ReadFile();
        rf.readFile("files/aaaaa.csv");
    }

    /**
     * Teste ao método para ler dados de um ficheiro em que é passado o valor null em vez de uma String com o caminho
     * para um dado ficheiro.
     *
     * @throws FileNotFoundException Exceção lançada caso não exista o ficheiro com o nome utilizado.
     */
    @Test(expected = FileNotFoundException.class)
    public void readFileTest2() throws IOException {
        ReadFile rf = new ReadFile();
        rf.readFile(null);
    }

    /**
     * Teste ao método readFile em que é verificado se o tamanho da lista retornado contém o número de linhas esperado.
     *
     * @throws FileNotFoundException Exceção lançada caso não exista o ficheiro com o nome utilizado.
     */
    @Test
    public void readFileTest3() throws IOException {
        ReadFile rf = new ReadFile();
        List<String[]> resultList = rf.readFile("files/trabalho2/Production_Crops_Livestock_EU_shuffle_small_read_file_test3.csv");
        int result = resultList.size();
        int expResult = 3;

        Assert.assertEquals(expResult, result);
    }

    /**
     * Teste ao método readFile em que é verificado se a lista retornada está vazia.
     *
     * @throws FileNotFoundException Exceção lançada caso não exista o ficheiro com o nome utilizado.
     */
    @Test
    public void readFileTest4() throws IOException {
        ReadFile rf = new ReadFile();
        List<String[]> resultList = rf.readFile("files/trabalho2/Production_Crops_Livestock_EU_shuffle_small_read_file_test4.csv");

        Assert.assertTrue(resultList.isEmpty());
    }
}
