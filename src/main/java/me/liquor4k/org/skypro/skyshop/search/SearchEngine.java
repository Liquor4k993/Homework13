package me.liquor4k.org.skypro.skyshop.search;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс поискового движка для поиска по товарам и статьям.
 * Использует LinkedList для хранения объектов.
 */
public class SearchEngine {
    private final LinkedList<Searchable> searchables;

    /**
     * Конструктор поискового движка
     */
    public SearchEngine() {
        this.searchables = new LinkedList<>();
    }

    /**
     * Добавляет объект для поиска в движок
     * @param searchable объект, реализующий интерфейс Searchable
     */
    public void add(Searchable searchable) {
        searchables.add(searchable);
    }

    /**
     * Выполняет поиск по всем объектам
     * @param query строка для поиска
     * @return список всех найденных объектов
     */
    public List<Searchable> search(String query) {
        List<Searchable> results = new LinkedList<>();

        for (Searchable current : searchables) {
            if (current == null) {
                continue; // Пропускаем null-элементы
            }

            // Ищем вхождение запроса в searchTerm объекта
            if (current.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results.add(current);
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

        for (Searchable current : searchables) {
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
        return searchables.size();
    }

    /**
     * Удаляет объект из поискового движка
     * @param searchable объект для удаления
     * @return true если объект был удален
     */
    public boolean remove(Searchable searchable) {
        return searchables.remove(searchable);
    }

    /**
     * Возвращает список всех объектов в движке (для тестирования)
     * @return список объектов
     */
    public List<Searchable> getAllSearchables() {
        return new LinkedList<>(searchables);
    }
}