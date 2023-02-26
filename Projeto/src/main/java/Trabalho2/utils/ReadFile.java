package Trabalho2.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela leitura do ficheiro.
 */
public class ReadFile implements LegacySysFileReader {

    /**
     * Método que lê os dados de um ficheiro e os armazena numa lista de arrays de 'strings'.
     *
     * @param filePath Caminho do ficheiro.
     * @return Uma lista de arrays de 'strings' que contém as informações de cada linha do ficheiro.
     */
    public List<String[]> readFile(String filePath) throws IOException {

        if (filePath == null) {
            throw new FileNotFoundException();
        }

        FileReader file = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(file);

        // Ler o cabeçalho
        String header = bufferedReader.readLine();

        List<String[]> arrayList = new ArrayList<>();
        String sentence;

        while ((sentence = bufferedReader.readLine()) != null) {
            String[] columns;

            if (sentence.contains(",\"")) {
                columns = sentence.split(",\"");
            } else {
                columns = sentence.split(",");
            }

            removeTrash(columns);
            arrayList.add(columns);
        }
        bufferedReader.close();

        return arrayList;
    }

    /**
     * Método para remover todo o lixo como ' ou " das colunas.
     *
     * @param columns Um array de strings.
     */
    private void removeTrash(String[] columns) {
        for (int i = 0; i < columns.length; i++) {
            columns[i] = columns[i].replace("'", "");
            columns[i] = columns[i].replace("\"", "");
        }
    }
}
