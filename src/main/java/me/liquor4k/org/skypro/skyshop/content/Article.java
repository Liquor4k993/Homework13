package me.liquor4k.org.skypro.skyshop.content;

import me.liquor4k.org.skypro.skyshop.search.Searchable;
import java.util.Objects;

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
     * @throws IllegalArgumentException если название null или пустое
     */
    public Article(String title, String text) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Название статьи не может быть пустым или состоять только из пробелов"
            );
        }

        if (text == null) {
            text = ""; // Разрешаем пустой текст
        }

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

    // Реализация equals и hashCode

    /**
     * Сравнивает статьи по названию
     * @param o объект для сравнения
     * @return true если названия статей равны
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;
        return Objects.equals(title, article.title);
    }

    /**
     * Возвращает хэш-код статьи на основе названия
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}