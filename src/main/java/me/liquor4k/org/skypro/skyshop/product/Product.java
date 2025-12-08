package me.liquor4k.org.skypro.skyshop.product;

/**
 * Класс, представляющий товар в интернет-магазине.
 * Значения товара неизменяемы после создания.
 */
public class Product {
    private final String name;
    private final int price;

    /**
     * Конструктор товара
     * @param name название товара
     * @param price цена товара в рублях
     */
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
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
    public int getPrice() {
        return price;
    }
}