package me.liquor4k.org.skypro.skyshop.search;

/**
 * Интерфейс для объектов, поддерживающих поиск.
 * Реализуется товарами и статьями.
 */
public interface Searchable {
    /**
     * Возвращает термин для поиска
     * @return строка для поиска
     */
    String getSearchTerm();

    /**
     * Возвращает тип контента
     * @return тип контента (например, "PRODUCT" или "ARTICLE")
     */
    String getContentType();

    /**
     * Возвращает имя объекта для поиска
     * @return имя объекта
     */
    String getSearchableName();

    /**
     * Возвращает строковое представление объекта
     * @return строка в формате "имя - тип"
     */
    default String getStringRepresentation() {
        return getSearchableName() + " — " + getContentType();
    }
}