package me.liquor4k.org.skypro.skyshop.product;

/**
 * Абстрактный класс, представляющий товар в интернет-магазине.
 * Является корнем иерархии для всех типов товаров.
 */
public abstract class Product {
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
}