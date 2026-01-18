package me.liquor4k.org.skypro.skyshop.product;

import me.liquor4k.org.skypro.skyshop.search.Searchable;

/**
 * Абстрактный класс, представляющий товар в интернет-магазине.
 * Является корнем иерархии для всех типов товаров.
 * Реализует интерфейс Searchable для поддержки поиска.
 */
public abstract class Product implements Searchable {
    private final String name;

    /**
     * Конструктор товара
     * @param name название товара
     */
    public Product(String name) {
        this.name = name;
    }

    /**
     * Получить название товара
     * @return название товара
     */
    public String getName() {
        return name;
    }

    /**
     * Получить цену товара
     * @return цена товара
     */
    public abstract int getPrice();

    /**
     * Проверяет, является ли товар специальным
     * @return true если товар специальный, false если обычный
     */
    public abstract boolean isSpecial();

    /**
     * Возвращает строковое представление товара
     * @return строка с описанием товара
     */
    @Override
    public abstract String toString();

    // Реализация методов интерфейса Searchable

    /**
     * Возвращает термин для поиска (имя товара)
     * @return имя товара
     */
    @Override
    public String getSearchTerm() {
        return name;
    }

    /**
     * Возвращает тип контента
     * @return "PRODUCT"
     */
    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    /**
     * Возвращает имя объекта для поиска
     * @return название товара
     */
    @Override
    public String getSearchableName() {
        return name;
    }
}