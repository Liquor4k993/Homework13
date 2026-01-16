package me.liquor4k.org.skypro.skyshop.product;

/**
 * Класс, представляющий товар с фиксированной ценой.
 */
public class FixPriceProduct extends Product {
    private static final int FIXED_PRICE = 99;

    /**
     * Конструктор товара с фиксированной ценой
     * @param name название товара
     */
    public FixPriceProduct(String name) {
        super(name);
    }

    /**
     * Получить фиксированную цену товара
     * @return фиксированная цена
     */
    @Override
    public int getPrice() {
        return FIXED_PRICE;
    }

    /**
     * Проверяет, является ли товар специальным
     * @return true - товар с фиксированной ценой является специальным
     */
    @Override
    public boolean isSpecial() {
        return true;
    }

    /**
     * Возвращает строковое представление товара
     * @return строка в формате "название: Фиксированная цена FIXED_PRICE"
     */
    @Override
    public String toString() {
        return getName() + ": Фиксированная цена " + FIXED_PRICE;
    }
}