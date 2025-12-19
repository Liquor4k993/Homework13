package me.liquor4k.org.skypro.skyshop;

import me.liquor4k.org.skypro.skyshop.product.Product;
import me.liquor4k.org.skypro.skyshop.basket.ProductBasket;

public class App {
    public static void main(String[] args) {
        // Создание продуктов
        Product laptop = new Product("Ноутбук", 75000);
        Product phone = new Product("Смартфон", 35000);
        Product headphones = new Product("Наушники", 5000);
        Product tablet = new Product("Планшет", 25000);
        Product mouse = new Product("Мышь", 1500);
        Product keyboard = new Product("Клавиатура", 3000); // Дополнительный продукт для демонстрации заполненной корзины

        // Создание корзины
        ProductBasket basket = new ProductBasket();

        System.out.println("Демонстрация работы корзины\n");

        // 1. Добавление продукта в корзину
        System.out.println("1. Добавление продукта в корзину:");
        basket.addProduct(laptop);
        basket.addProduct(phone);
        System.out.println("Добавлены ноутбук и смартфон\n");

        // 2. Добавление продукта в заполненную корзину
        System.out.println("2. Попытка добавления в заполненную корзину:");
        basket.addProduct(headphones);
        basket.addProduct(tablet);
        basket.addProduct(mouse);
        // Попытка добавить шестой продукт (корзина рассчитана на 5)
        basket.addProduct(keyboard);
        System.out.println();

        // 3. Печать содержимого корзины с несколькими товарами
        System.out.println("3. Печать содержимого корзины:");
        basket.printBasket();
        System.out.println();

        // 4. Получение стоимости корзины с несколькими товарами
        System.out.println("4. Получение стоимости корзины:");
        System.out.println("Общая стоимость: " + basket.getTotalPrice() + " руб.\n");

        // 5. Поиск товара, который есть в корзине
        System.out.println("5. Поиск товара, который есть в корзине:");
        String searchProduct = "Ноутбук";
        boolean found = basket.containsProduct(searchProduct);
        System.out.println("Товар '" + searchProduct + "' в корзине: " + found + "\n");

        // 6. Поиск товара, которого нет в корзине
        System.out.println("6. Поиск товара, которого нет в корзине:");
        searchProduct = "Клавиатура";
        found = basket.containsProduct(searchProduct);
        System.out.println("Товар '" + searchProduct + "' в корзине: " + found + "\n");

        // 7. Очистка корзины
        System.out.println("7. Очистка корзины:");
        basket.clearBasket();
        System.out.println("Корзина очищена\n");

        // 8. Печать содержимого пустой корзины
        System.out.println("8. Печать содержимого пустой корзины:");
        basket.printBasket();
        System.out.println();

        // 9. Получение стоимости пустой корзины
        System.out.println("9. Получение стоимости пустой корзины:");
        System.out.println("Общая стоимость: " + basket.getTotalPrice() + " руб.\n");

        // 10. Поиск товара по имени в пустой корзине
        System.out.println("10. Поиск товара по имени в пустой корзине:");
        searchProduct = "Ноутбук";
        found = basket.containsProduct(searchProduct);
        System.out.println("Товар '" + searchProduct + "' в корзине: " + found);
    }
}