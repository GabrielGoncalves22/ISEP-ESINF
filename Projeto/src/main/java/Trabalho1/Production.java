package Trabalho1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Classe que representa a produção.
 */
public class Production {
    /**
     * Mapa que contém como chave uma dada Trabalho1.Area e como valor um ‘set’ de itens produzidos nessa mesma área.
     */
    private final Map<Area, Set<Item>> productionPerArea = new HashMap<>();

    /**
     * Método para retornar o mapa com a produção de itens por área.
     *
     * @return O mapa com a produção de itens por área.
     */
    public Map<Area, Set<Item>> getProductionPerArea() {
        return productionPerArea;
    }

    /**
     * Método para realizar a leitura e importação de dados de um ficheiro para o mapa com a produção de itens por área.
     *
     * @param filePath O caminho onde o ficheiro se encontra.
     * @throws FileNotFoundException Lançamento de uma exceção caso o caminho dado do ficheiro não exista.
     */
    public void readFile(String filePath) throws FileNotFoundException {

        if (filePath == null) {
            throw new FileNotFoundException();
        }

        File file = new File(filePath);


        Scanner scannerFile = new Scanner(file);
        // Ler o cabeçalho
        String header = scannerFile.nextLine();
        int line = 2;
        while (scannerFile.hasNext()) {
            String sentence;
            sentence = scannerFile.nextLine();

            String[] columns;

            if (sentence.contains("\",\"")) {
                columns = sentence.split("\",\"");
            } else {
                columns = sentence.split(",");
            }

            //Trabalho1.Area
            String country = columns[3].replace("\"", "");
            Area area = new Area(country);

            //Trabalho1.Item
            String name = columns[7].replace("\"", "");
            Item item = new Item(name);

            int productionYear = 0;
            int productionQuantity = 0;

            try {
                // Read Trabalho1.Year
                productionYear = columns[9].equals("") ? 0 : Integer.parseInt(columns[9]);
                // Read Trabalho1.Value
                productionQuantity = columns[11].equals("") ? 0 : Integer.parseInt(columns[11]);
            } catch (NumberFormatException n) {
                throw new InvalidLineException(line);
            } finally {
                Value value = new Value(productionQuantity);
                Year year = new Year(productionYear, value);

                line++;
                add(area, item, year);
            }

        }

        scannerFile.close();

    }

    /**
     * Método para adicionar um ‘item’ e um ano a uma dada area. Caso já exista o ‘item’ na area pretendida, apenas é
     * adicionado a este ‘item’ o novo ano.
     *
     * @param area Objeto do tipo Trabalho1.Area.
     * @param item Objeto do tipo Trabalho1.Item.
     * @param year Objeto do tipo Trabalho1.Year.
     */
    private void add(Area area, Item item, Year year) {
        Set<Item> itemSet = productionPerArea.get(area);

        if (itemSet != null) {
            if (itemSet.contains(item)) {
                for (Item i : itemSet) {
                    if (i.equals(item)) {
                        i.addYear(year);
                    }
                }

            } else {
                item.addYear(year);
                itemSet.add(item);
            }

        } else {
            item.addYear(year);
            itemSet = new TreeSet<>();
            itemSet.add(item);
            productionPerArea.put(area, itemSet);
        }
    }

    /**
     * Função para dado um determinado país, devolver, numa estrutura adequada, o par de anos, o fruto e o maior valor
     * absoluto da diferença de produção.
     *
     * @param production mapa que contém a informação a ser tratada.
     * @param country    um determinado país.
     * @param <K>        variável que representa um determinado valor/objeto chave de um mapa.
     * @param <V>        variável que representa um determinado valor/objeto dentro dum 'set' contido num mapa.
     * @return map com o fruto, o par de anos e o maior valor absoluto da diferença de produção.
     */
    public <K, V extends ItemInformation> Map<V, Pair<String, Integer>> higherValueDifferenceProduction(Map<K, Set<V>> production, K country) {
        Map<V, Pair<String, Integer>> list = new TreeMap<>();
        Set<V> setValue = production.get(country);

        if (setValue != null) {
            for (V value : production.get(country)) {
                list.put(value, value.getBiggestDifferenceQuantity());
            }
        }

        return list;
    }

    /**
     * Função para dado um determinado fruto e uma quantidade de produção, devolver numa lista os países com pelo menos um ano
     * de produção do fruto com quantidade maior ou igual.
     *
     * @param production         mapa que contém a informação a ser tratada.
     * @param fruit              um determinado fruto.
     * @param quantityProduction uma determinada quantidade.
     * @param <K>                variável que representa um determinado valor/objeto chave de um mapa.
     * @param <V>                variável que representa um determinado valor/objeto dentro dum 'set' contido num mapa.
     * @return lista com os países com pelo menos um ano de produção do fruto com quantidade maior ou igual.
     */
    public <K, V extends ItemInformation> ArrayList<K> countryWithYearAndValue(Map<K, Set<V>> production, V fruit, Value quantityProduction) {
        Map<K, Year> map = new HashMap<>();

        for (K key : production.keySet()) {
            for (V value : production.get(key)) {

                if (value.equals(fruit)) {
                    Year year = value.getYearWithQuantityGreaterOrEqual(quantityProduction.getProductionQuantity());

                    if (year != null) {
                        map.put(key, year);
                    }
                }
            }
        }

        return sortByValue(map);
    }

    /**
     * Método para ordenar de forma crescente pelo ano de produção mais baixo em que a produção do fruto foi quantidade
     * maior ou igual e em caso de igualdade do critério anterior, por ordem decrescente da quantidade que foi
     * produzida.
     *
     * @param map mapa que contém a informação a ser tratada.
     * @param <K> variável que representa um determinado valor/objeto chave de um mapa.
     * @return lista ordenada com os países.
     */
    private <K> ArrayList<K> sortByValue(Map<K, Year> map) {
        ArrayList<K> sortedList = new ArrayList<>();
        ArrayList<Year> list = new ArrayList<>();

        for (Map.Entry<K, Year> entry : map.entrySet()) {
            list.add(entry.getValue());
        }

        list.sort(new YearComparator());

        for (Year year : list) {
            for (Map.Entry<K, Year> entry : map.entrySet()) {
                if (entry.getValue().equals(year)) {
                    sortedList.add(entry.getKey());
                }
            }
        }

        return sortedList;
    }

    /**
     * Função para dado um determinado fruto, devolver, numa estrutura adequada, os países agrupados pelo número máximo
     * de anos consecutivos em que houve crescimento de quantidade de produção do fruto.
     *
     * @param production mapa que contém a informação a ser tratada.
     * @param fruit      um determinado fruto.
     * @param <K>        variável que representa um determinado valor/objeto chave de um mapa.
     * @param <V>        variável que representa um determinado valor/objeto dentro dum 'set' contido num mapa.
     * @return map com os países agrupados pelo número máximo de anos consecutivos em que houve crescimento de quantidade
     * de produção.
     */
    public <K, V extends ItemInformation> Map<Integer, Set<K>> maximumNumberConsecutiveYears(Map<K, Set<V>> production, V fruit) {
        Map<Integer, Set<K>> map = new TreeMap<>();

        for (K key : production.keySet()) {
            for (V value : production.get(key)) {

                if (value.equals(fruit)) {
                    int maximumNumberConsecutiveYears = value.getMaximumNumberConsecutiveYears();

                    if (maximumNumberConsecutiveYears != 1) {
                        Set<K> set = map.get(maximumNumberConsecutiveYears);

                        if (set != null) {
                            set.add(key);
                        } else {
                            set = new TreeSet<>();
                            set.add(key);

                            map.put(maximumNumberConsecutiveYears, set);
                        }
                    }
                }
            }
        }

        return map;
    }

    /**
     * Método para calcular a quantidade mínima de campos em que a soma dos seus valores dá mais do que a quantidade
     * desejada, passada por parâmetro.
     *
     * @param list     A lista com os pares de valores (cada par com chave e valor).
     * @param quantity Quantidade a ser ultrapassada pelo menor número possível de campos.
     * @return A quantidade mínima de campos em que a soma das suas quantidades dá mais do que a quantidade passada
     * por parâmetro.
     */
    public <K> int getMinNumberOfKeysHigherThanQuantity(ArrayList<Pair<K, Integer>> list, long quantity) {
        // Contador de campos
        int count = 0;
        // Quantidade com a soma dos valores dos campos
        long actualQuantity = 0;

        // Se o ArrayList estiver com valor null, retornar 0.
        if (list == null) {
            return count;
        }

        // Criar cópia do array original
        ArrayList<Pair<K, Integer>> tempList = new ArrayList<>(list);

        // Ordenação da lista
        Collections.sort(tempList);
        // Trocar a ordem da ordenação
        Collections.reverse(tempList);

        /*for (Trabalho1.Pair pair : countriesProduction) {
            System.out.println(pair.getKey() + " | " + pair.getValue());
        }*/

        Iterator<Pair<K, Integer>> it = tempList.iterator();
        while (actualQuantity <= quantity && it.hasNext()) {
            Pair<K, Integer> pair = it.next();
            count++;
            actualQuantity += pair.getValue();
        }

        if (actualQuantity <= quantity)
            count = 0;

        return count;

    }

    /**
     * Método para transformar um mapa num ArrayList em que irá conter objetos do tipo Trabalho1.Pair, com um valor/objeto chave
     * e um dado valor do tipo inteiro que o representa.
     *
     * @param production Mapa que contém a informação a ser tratada.
     * @param <K>        Variável que representa um determinado valor/objeto chave de um mapa.
     * @param <V>        Variável que representa um determinado valor/objeto dentro dum 'set' contido num mapa.
     * @return Um ArrayList com um valor chave e um valor inteiro que o representa.
     */
    public <K, V extends ItemInformation> ArrayList<Pair<K, Integer>> getTotalValueList(Map<K, Set<V>> production) {

        // Se o mapa for nulo, retornar nulo.
        if (production == null) {
            return null;
        }

        ArrayList<Pair<K, Integer>> totalProduction = new ArrayList<>();

        for (K key : production.keySet()) {
            int sumProductionQuantity = 0;

            for (V value : production.get(key)) {
                sumProductionQuantity += value.getTotalQuantity();
            }

            Pair<K, Integer> areaProduction = new Pair<>(key, sumProductionQuantity);
            totalProduction.add(areaProduction);

        }

        return totalProduction;
    }
}
