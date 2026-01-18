package me.liquor4k.org.skypro.skyshop.search;

/**
 * Класс поискового движка для поиска по товарам и статьям.
 */
public class SearchEngine {
    private final Searchable[] searchables;
    private int count;

    /**
     * Конструктор поискового движка
     * @param capacity максимальное количество элементов для поиска
     */
    public SearchEngine(int capacity) {
        this.searchables = new Searchable[capacity];
        this.count = 0;
    }

    /**
     * Добавляет объект для поиска в движок
     * @param searchable объект, реализующий интерфейс Searchable
     */
    public void add(Searchable searchable) {
        if (count < searchables.length) {
            searchables[count] = searchable;
            count++;
        } else {
            System.out.println("Невозможно добавить объект для поиска. Достигнут лимит.");
        }
    }

    /**
     * Выполняет поиск по всем объектам
     * @param query строка для поиска
     * @return массив до 5 найденных объектов
     */
    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int resultIndex = 0;

        for (int i = 0; i < count; i++) {
            if (resultIndex >= 5) {
                break; // Максимум 5 результатов
            }

            Searchable current = searchables[i];
            if (current == null) {
                continue; // Пропускаем null-элементы
            }

            // Ищем вхождение запроса в searchTerm объекта
            if (current.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results[resultIndex] = current;
                resultIndex++;
            }
        }

        return results;
    }

    /**
     * Находит наиболее подходящий объект для поискового запроса
     * @param searchQuery поисковый запрос
     * @return наиболее подходящий объект Searchable
     * @throws BestResultNotFound если объект не найден
     */
    public Searchable findBestMatch(String searchQuery) throws BestResultNotFound {
        Searchable bestMatch = null;
        int maxOccurrences = 0;

        for (int i = 0; i < count; i++) {
            Searchable current = searchables[i];
            if (current == null) {
                continue; // Пропускаем null-элементы
            }

            int occurrences = countOccurrences(current.getSearchTerm(), searchQuery);

            if (occurrences > maxOccurrences) {
                maxOccurrences = occurrences;
                bestMatch = current;
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound(searchQuery);
        }

        return bestMatch;
    }

    /**
     * Подсчитывает количество вхождений подстроки в строку
     * @param text строка для поиска
     * @param substring подстрока для подсчета
     * @return количество вхождений
     */
    private int countOccurrences(String text, String substring) {
        if (substring == null || substring.isEmpty()) {
            return 0;
        }

        int count = 0;
        int index = 0;
        String lowerText = text.toLowerCase();
        String lowerSubstring = substring.toLowerCase();

        while (index < lowerText.length()) {
            int foundIndex = lowerText.indexOf(lowerSubstring, index);

            if (foundIndex == -1) {
                break; // Больше вхождений нет
            }

            count++;
            index = foundIndex + lowerSubstring.length();
        }

        return count;
    }

    /**
     * Возвращает количество добавленных объектов
     * @return количество объектов
     */
    public int getCount() {
        return count;
    }
}