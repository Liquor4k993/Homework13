package me.liquor4k.org.skypro.skyshop.basket;

import me.liquor4k.org.skypro.skyshop.product.Product;
import java.util.*;

/**
 * Класс, представляющий корзину для товаров.
 * Использует HashMap для хранения товаров, где ключ - имя товара, значение - список товаров с этим именем.
 */
public class ProductBasket {
    private final HashMap<String, List<Product>> productsMap;
    private int totalProductCount;

    /**
     * Конструктор корзины.
     * Создает пустую корзину.
     */
    public ProductBasket() {
        this.productsMap = new HashMap<>();
        this.totalProductCount = 0;
    }

    /**
     * Добавляет продукт в корзину.
     * @param product продукт для добавления
     */
    public void addProduct(Product product) {
        String productName = product.getName();

        // Используем computeIfAbsent для создания списка при отсутствии
        productsMap.computeIfAbsent(productName, k -> new ArrayList<>()).add(product);
        totalProductCount++;
    }

    /**
     * Удаляет все продукты с указанным именем из корзины.
     * @param name имя продукта для удаления
     * @return список удаленных продуктов
     */
    public List<Product> removeProductByName(String name) {
        List<Product> removedProducts = productsMap.remove(name);

        if (removedProducts != null) {
            totalProductCount -= removedProducts.size();
            return removedProducts;
        }

        return new ArrayList<>();
    }

    /**
     * Вычисляет общую стоимость всех товаров в корзине.
     * @return общая стоимость корзины
     */
    public int getTotalPrice() {
        int total = 0;

        // Перебираем все списки продуктов
        for (List<Product> productList : productsMap.values()) {
            for (Product product : productList) {
                total += product.getPrice();
            }
        }

        return total;
    }

    /**
     * Подсчитывает количество специальных товаров в корзине.
     * @return количество специальных товаров
     */
    public int getSpecialProductsCount() {
        int specialCount = 0;

        // Перебираем все списки продуктов
        for (List<Product> productList : productsMap.values()) {
            for (Product product : productList) {
                if (product.isSpecial()) {
                    specialCount++;
                }
            }
        }

        return specialCount;
    }

    /**
     * Выводит содержимое корзины в консоль.
     * Если корзина пуста, выводит сообщение "в корзине пусто".
     */
    public void printBasket() {
        if (productsMap.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        // Перебираем все списки продуктов
        for (List<Product> productList : productsMap.values()) {
            for (Product product : productList) {
                System.out.println(product.toString());
            }
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
        return productsMap.containsKey(productName);
    }

    /**
     * Получает список всех продуктов с указанным именем.
     * @param productName имя товара
     * @return список продуктов с указанным именем или пустой список, если таких нет
     */
    public List<Product> getProductsByName(String productName) {
        return productsMap.getOrDefault(productName, new ArrayList<>());
    }

    /**
     * Очищает корзину, удаляя все товары.
     */
    public void clearBasket() {
        productsMap.clear();
        totalProductCount = 0;
    }

    /**
     * Возвращает количество товаров в корзине.
     * @return количество товаров
     */
    public int getProductCount() {
        return totalProductCount;
    }

    /**
     * Возвращает количество уникальных наименований товаров в корзине.
     * @return количество уникальных наименований
     */
    public int getUniqueProductCount() {
        return productsMap.size();
    }

    /**
     * Возвращает множество всех имен товаров в корзине.
     * @return множество имен товаров
     */
    public Set<String> getAllProductNames() {
        return productsMap.keySet();
    }

    /**
     * Возвращает список всех товаров в корзине (для тестирования).
     * @return список всех товаров
     */
    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();

        for (List<Product> productList : productsMap.values()) {
            allProducts.addAll(productList);
        }

        return allProducts;
    }
}