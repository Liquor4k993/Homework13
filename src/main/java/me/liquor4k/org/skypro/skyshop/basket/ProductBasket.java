package me.liquor4k.org.skypro.skyshop.basket;

import me.liquor4k.org.skypro.skyshop.product.Product;

/**
 * Класс, представляющий корзину для товаров.
 * Корзина может содержать до 5 товаров.
 */
public class ProductBasket {
    private final Product[] products;
    private int count;

    /**
     * Конструктор корзины.
     * Создает пустую корзину с максимальной вместимостью 5 товаров.
     */
    public ProductBasket() {
        this.products = new Product[5];
        this.count = 0;
    }

    /**
     * Добавляет продукт в корзину.
     * Если в корзине нет свободного места, выводит сообщение об ошибке.
     * @param product продукт для добавления
     */
    public void addProduct(Product product) {
        if (count < products.length) {
            products[count] = product;
            count++;
        } else {
            System.out.println("Невозможно добавить продукт. Корзина заполнена.");
        }
    }

    /**
     * Вычисляет общую стоимость всех товаров в корзине.
     * @return общая стоимость корзины
     */
    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].getPrice();
        }
        return total;
    }

    /**
     * Выводит содержимое корзины в консоль.
     * Если корзина пуста, выводит сообщение "в корзине пусто".
     */
    public void printBasket() {
        if (count == 0) {
            System.out.println("в корзине пусто");
            return;
        }

        for (int i = 0; i < count; i++) {
            Product product = products[i];
            System.out.println(product.getName() + ": " + product.getPrice());
        }
        System.out.println("Итого: " + getTotalPrice());
    }

    /**
     * Проверяет наличие товара с указанным именем в корзине.
     * @param productName имя товара для поиска
     * @return true если товар найден, false в противном случае
     */
    public boolean containsProduct(String productName) {
        for (int i = 0; i < count; i++) {
            if (products[i].getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Очищает корзину, удаляя все товары.
     */
    public void clearBasket() {
        for (int i = 0; i < count; i++) {
            products[i] = null;
        }
        count = 0;
    }
}