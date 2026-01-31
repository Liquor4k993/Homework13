package me.liquor4k.org.skypro.skyshop.search;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс поискового движка для поиска по товарам и статьям.
 * Использует HashSet для хранения уникальных объектов.
 */
public class SearchEngine {
    private final HashSet<Searchable> searchables;

    /**
     * Конструктор поискового движка
     */
    public SearchEngine() {
        this.searchables = new HashSet<>();
    }

    /**
     * Добавляет объект для поиска в движок
     * @param searchable объект, реализующий интерфейс Searchable
     * @return true если объект был добавлен (не был дубликатом)
     */
    public boolean add(Searchable searchable) {
        return searchables.add(searchable);
    }

    /**
     * Выполняет поиск по всем объектам
     * @param query строка для поиска
     * @return TreeSet с найденными объектами, отсортированными по длине имени (от большего к меньшему),
     *         а при равной длине - в натуральном порядке
     */
    public TreeSet<Searchable> search(String query) {
        return searchables.stream()
                .filter(Objects::nonNull)
                .filter(item -> item.getSearchTerm().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>(new SearchableComparator())
                ));
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
                continue;
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
     * Возвращает множество всех объектов в движке (для тестирования)
     * @return множество объектов
     */
    public Set<Searchable> getAllSearchables() {
        return new HashSet<>(searchables);
    }
}