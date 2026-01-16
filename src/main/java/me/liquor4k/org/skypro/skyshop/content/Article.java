package me.liquor4k.org.skypro.skyshop.content;

import me.liquor4k.org.skypro.skyshop.search.Searchable;

/**
 * Класс, представляющий статью о товаре.
 * Статья является неизменяемым объектом.
 */
public class Article implements Searchable {
    private final String title;
    private final String text;

    /**
     * Конструктор статьи
     * @param title название статьи
     * @param text текст статьи
     */
    public Article(String title, String text) {
        this.title = title;
        this.text = text;
    }

    /**
     * Получить название статьи
     * @return название статьи
     */
    public String getTitle() {
        return title;
    }

    /**
     * Получить текст статьи
     * @return текст статьи
     */
    public String getText() {
        return text;
    }

    /**
     * Преобразует статью в строку для отображения
     * @return строка в формате "Название\nТекст"
     */
    @Override
    public String toString() {
        return title + "\n" + text;
    }

    // Реализация методов интерфейса Searchable

    /**
     * Возвращает термин для поиска (название + текст)
     * @return строка для поиска
     */
    @Override
    public String getSearchTerm() {
        return toString();
    }

    /**
     * Возвращает тип контента
     * @return "ARTICLE"
     */
    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    /**
     * Возвращает имя объекта для поиска
     * @return название статьи
     */
    @Override
    public String getSearchableName() {
        return title;
    }
}