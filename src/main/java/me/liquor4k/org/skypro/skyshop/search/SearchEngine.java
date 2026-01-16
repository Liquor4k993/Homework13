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
     * Возвращает количество добавленных объектов
     * @return количество объектов
     */
    public int getCount() {
        return count;
    }
}