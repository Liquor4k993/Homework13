package me.liquor4k.org.skypro.skyshop.product;

/**
 * Класс, представляющий товар со скидкой.
 */
public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discount;

    /**
     * Конструктор товара со скидкой
     * @param name название товара
     * @param basePrice базовая цена товара
     * @param discount скидка в процентах (0-100)
     */
    public DiscountedProduct(String name, int basePrice, int discount) {
        super(name);
        this.basePrice = basePrice;
        this.discount = discount;
    }

    /**
     * Получить цену товара с учетом скидки
     * @return цена товара со скидкой
     */
    @Override
    public int getPrice() {
        return basePrice - (basePrice * discount / 100);
    }

    /**
     * Получить базовую цену товара
     * @return базовая цена
     */
    public int getBasePrice() {
        return basePrice;
    }

    /**
     * Получить размер скидки
     * @return скидка в процентах
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * Проверяет, является ли товар специальным
     * @return true - товар со скидкой является специальным
     */
    @Override
    public boolean isSpecial() {
        return true;
    }

    /**
     * Возвращает строковое представление товара
     * @return строка в формате "название: цена (скидка%)"
     */
    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + discount + "%)";
    }
}