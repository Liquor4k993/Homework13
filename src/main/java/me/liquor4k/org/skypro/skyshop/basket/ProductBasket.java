package me.liquor4k.org.skypro.skyshop.basket;

import me.liquor4k.org.skypro.skyshop.product.Product;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

/**
 * Класс, представляющий корзину для товаров.
 * Использует LinkedList для хранения товаров.
 */
public class ProductBasket {
    private final LinkedList<Product> products;

    /**
     * Конструктор корзины.
     * Создает пустую корзину.
     */
    public ProductBasket() {
        this.products = new LinkedList<>();
    }

    /**
     * Добавляет продукт в корзину.
     * @param product продукт для добавления
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Удаляет все продукты с указанным именем из корзины.
     * @param name имя продукта для удаления
     * @return список удаленных продуктов
     */
    public List<Product> removeProductByName(String name) {
        List<Product> removedProducts = new LinkedList<>();
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getName().equals(name)) {
                removedProducts.add(product);
                iterator.remove();
            }
        }

        return removedProducts;
    }

    /**
     * Вычисляет общую стоимость всех товаров в корзине.
     * @return общая стоимость корзины
     */
    public int getTotalPrice() {
        int total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    /**
     * Подсчитывает количество специальных товаров в корзине.
     * @return количество специальных товаров
     */
    public int getSpecialProductsCount() {
        int specialCount = 0;
        for (Product product : products) {
            if (product.isSpecial()) {
                specialCount++;
            }
        }
        return specialCount;
    }

    /**
     * Выводит содержимое корзины в консоль.
     * Если корзина пуста, выводит сообщение "в корзине пусто".
     */
    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        for (Product product : products) {
            System.out.println(product.toString());
        }
        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + getSpecialProductsCount());
    }

    /**
     * Проверяет наличие товара с указанным именем в корзине.
     * @param productName имя товара для поиска
     * @return true если товар найден, false в противном случае
     */
    public boolean containsProduct(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Очищает корзину, удаляя все товары.
     */
    public void clearBasket() {
        products.clear();
    }

    /**
     * Возвращает количество товаров в корзине.
     * @return количество товаров
     */
    public int getProductCount() {
        return products.size();
    }

    /**
     * Возвращает список всех товаров в корзине (для тестирования).
     * @return список товаров
     */
    public List<Product> getProducts() {
        return new LinkedList<>(products);
    }
}