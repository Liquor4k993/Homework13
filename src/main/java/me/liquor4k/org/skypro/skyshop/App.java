package me.liquor4k.org.skypro.skyshop;

import me.liquor4k.org.skypro.skyshop.product.*;
import me.liquor4k.org.skypro.skyshop.basket.ProductBasket;
import me.liquor4k.org.skypro.skyshop.content.Article;
import me.liquor4k.org.skypro.skyshop.search.SearchEngine;
import me.liquor4k.org.skypro.skyshop.search.Searchable;
import me.liquor4k.org.skypro.skyshop.search.BestResultNotFound;

import java.util.*;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация работы интернет-магазина с использованием Map ===\n");

        // Часть 1: Создание объектов
        System.out.println("1. СОЗДАНИЕ ОБЪЕКТОВ:\n");

        // Создание товаров с разными именами для демонстрации сортировки
        SimpleProduct laptop = new SimpleProduct("Ноутбук Lenovo IdeaPad", 75000);
        SimpleProduct phone = new SimpleProduct("Смартфон Samsung Galaxy", 35000);
        DiscountedProduct headphones = new DiscountedProduct("Беспроводные наушники Sony", 20000, 15);
        DiscountedProduct tablet = new DiscountedProduct("Планшет Apple iPad Pro", 80000, 10);
        FixPriceProduct usbCable = new FixPriceProduct("USB-C кабель");
        FixPriceProduct mouse = new FixPriceProduct("Игровая мышь Razer");
        SimpleProduct keyboard = new SimpleProduct("Клавиатура механическая", 5000);
        DiscountedProduct monitor = new DiscountedProduct("Монитор Dell", 30000, 20);

        // Создание статей
        Article laptopArticle = new Article(
                "Обзор ноутбука Lenovo IdeaPad",
                "Ноутбук Lenovo IdeaPad обладает мощным процессором."
        );

        Article headphonesArticle = new Article(
                "Тест наушников Sony",
                "Наушники Sony показали превосходное качество звука."
        );

        Article shoppingGuide = new Article(
                "Как выбрать электронику",
                "Выбор электроники - важная задача."
        );

        Article appleArticle = new Article(
                "Apple техника: обзор",
                "Техника Apple отличается высоким качеством и дизайном."
        );

        System.out.println("Создано товаров: 8");
        System.out.println("Создано статей: 4");

        // Часть 2: Демонстрация работы корзины с HashMap
        System.out.println("\n\n2. ДЕМОНСТРАЦИЯ РАБОТЫ КОРЗИНЫ (HashMap):\n");

        ProductBasket basket = new ProductBasket();

        // Добавление товаров в корзину (включая дубликаты)
        System.out.println("a) Добавление товаров в корзину:");
        basket.addProduct(laptop);
        basket.addProduct(phone);
        basket.addProduct(headphones);
        basket.addProduct(tablet);
        basket.addProduct(usbCable);
        basket.addProduct(mouse);
        basket.addProduct(keyboard);
        basket.addProduct(monitor);

        // Добавляем дубликаты
        basket.addProduct(new SimpleProduct("Ноутбук Lenovo IdeaPad", 75000));
        basket.addProduct(new DiscountedProduct("Беспроводные наушники Sony", 20000, 15));
        basket.addProduct(new SimpleProduct("Смартфон Samsung Galaxy", 35000));

        System.out.println("   Всего товаров в корзине: " + basket.getProductCount());
        System.out.println("   Уникальных наименований: " + basket.getUniqueProductCount());
        System.out.println("   Общая стоимость: " + basket.getTotalPrice() + " руб.");

        System.out.println("\nb) Уникальные наименования в корзине:");
        for (String productName : basket.getAllProductNames()) {
            List<Product> products = basket.getProductsByName(productName);
            System.out.println("   - " + productName + ": " + products.size() + " шт.");
        }

        System.out.println("\nc) Вывод содержимого корзины:");
        basket.printBasket();

        // Часть 3: Демонстрация удаления продуктов по имени (работает быстрее с Map)
        System.out.println("\n\n3. ДЕМОНСТРАЦИЯ УДАЛЕНИЯ ПРОДУКТОВ ПО ИМЕНИ (оптимизировано с Map):\n");

        // 3.1. Удаление существующего продукта
        System.out.println("a) Удаление продукта 'Ноутбук Lenovo IdeaPad':");
        List<Product> removedProducts = basket.removeProductByName("Ноутбук Lenovo IdeaPad");

        System.out.println("   Удалено продуктов: " + removedProducts.size());
        if (!removedProducts.isEmpty()) {
            System.out.println("   Удаленные продукты:");
            for (Product product : removedProducts) {
                System.out.println("   - " + product.toString());
            }
        }

        System.out.println("\n   Содержимое корзины после удаления:");
        basket.printBasket();

        // 3.2. Удаление другого существующего продукта
        System.out.println("\nb) Удаление продукта 'Беспроводные наушники Sony':");
        removedProducts = basket.removeProductByName("Беспроводные наушники Sony");

        System.out.println("   Удалено продуктов: " + removedProducts.size());
        if (!removedProducts.isEmpty()) {
            System.out.println("   Удаленные продукты:");
            for (Product product : removedProducts) {
                System.out.println("   - " + product.toString());
            }
        }

        System.out.println("\n   Содержимое корзины после удаления:");
        basket.printBasket();

        System.out.println("\n   Уникальные наименования в корзине теперь:");
        for (String productName : basket.getAllProductNames()) {
            List<Product> products = basket.getProductsByName(productName);
            System.out.println("   - " + productName + ": " + products.size() + " шт.");
        }

        // 3.3. Попытка удаления несуществующего продукта
        System.out.println("\nc) Попытка удаления несуществующего продукта 'Несуществующий товар':");
        removedProducts = basket.removeProductByName("Несуществующий товар");

        if (removedProducts.isEmpty()) {
            System.out.println("   Список пуст. Товар не найден в корзине.");
        } else {
            System.out.println("   ОШИБКА: Должен быть пустой список!");
        }

        // Часть 4: Демонстрация работы поискового движка с TreeMap
        System.out.println("\n\n4. ДЕМОНСТРАЦИЯ ПОИСКОВОГО ДВИЖКА (TreeMap - результаты отсортированы):\n");

        SearchEngine searchEngine = new SearchEngine();

        // Добавляем в разном порядке для демонстрации сортировки
        searchEngine.add(appleArticle);
        searchEngine.add(keyboard);
        searchEngine.add(mouse);
        searchEngine.add(laptop);
        searchEngine.add(phone);
        searchEngine.add(headphones);
        searchEngine.add(tablet);
        searchEngine.add(usbCable);
        searchEngine.add(monitor);
        searchEngine.add(laptopArticle);
        searchEngine.add(headphonesArticle);
        searchEngine.add(shoppingGuide);

        System.out.println("Объектов в поисковом движке: " + searchEngine.getCount());

        // 4.1. Поиск с несколькими результатами (отсортированными по имени)
        System.out.println("\na) Поиск 'ноутбук' (результаты отсортированы по имени):");
        TreeMap<String, Searchable> resultsMap = searchEngine.search("ноутбук");
        printSearchResultsFromMap(resultsMap);

        // 4.2. Поиск с несколькими результатами (проверяем сортировку)
        System.out.println("\nb) Поиск 'Apple' (результаты отсортированы по имени):");
        resultsMap = searchEngine.search("Apple");
        printSearchResultsFromMap(resultsMap);

        // 4.3. Поиск по букве для демонстрации сортировки
        System.out.println("\nc) Поиск 'S' (результаты отсортированы по имени):");
        resultsMap = searchEngine.search("S");
        printSearchResultsFromMap(resultsMap);

        // 4.4. Поиск с одним результатом
        System.out.println("\nd) Поиск 'Dell' (один результат):");
        resultsMap = searchEngine.search("Dell");
        printSearchResultsFromMap(resultsMap);

        // 4.5. Поиск без результатов
        System.out.println("\ne) Поиск 'xyz123abc' (нет результатов):");
        resultsMap = searchEngine.search("xyz123abc");
        printSearchResultsFromMap(resultsMap);

        // 4.6. Демонстрация обратной совместимости (старый метод)
        System.out.println("\nf) Старый метод поиска (список) для сравнения:");
        List<Searchable> resultsList = searchEngine.searchAsList("ноутбук");
        System.out.println("   Найдено объектов: " + resultsList.size());
        for (Searchable result : resultsList) {
            System.out.println("   - " + result.getStringRepresentation());
        }

        // Часть 5: Демонстрация других методов корзины
        System.out.println("\n\n5. ДОПОЛНИТЕЛЬНЫЕ МЕТОДЫ КОРЗИНЫ:\n");

        System.out.println("a) Проверка наличия товара (работает быстрее с Map):");
        System.out.println("   Товар 'Смартфон Samsung Galaxy' в корзине: " +
                basket.containsProduct("Смартфон Samsung Galaxy"));
        System.out.println("   Товар 'Несуществующий' в корзине: " +
                basket.containsProduct("Несуществующий"));

        System.out.println("\nb) Получение товаров по имени:");
        List<Product> samsungProducts = basket.getProductsByName("Смартфон Samsung Galaxy");
        System.out.println("   Товаров 'Смартфон Samsung Galaxy': " + samsungProducts.size());
        for (Product product : samsungProducts) {
            System.out.println("   - " + product.toString());
        }

        System.out.println("\nc) Очистка корзины:");
        basket.clearBasket();
        System.out.println("   Товаров в корзине после очистки: " + basket.getProductCount());

        System.out.println("\nd) Вывод пустой корзины:");
        basket.printBasket();

        System.out.println("\n=== Демонстрация завершена ===");
    }

    /**
     * Выводит результаты поиска из TreeMap (отсортированные по имени)
     * @param resultsMap TreeMap с результатами поиска
     */
    private static void printSearchResultsFromMap(TreeMap<String, Searchable> resultsMap) {
        System.out.println("   Найдено объектов: " + resultsMap.size());

        if (resultsMap.isEmpty()) {
            System.out.println("   Результатов не найдено.");
            return;
        }

        System.out.println("   Результаты (отсортированы по имени):");
        int counter = 1;

        // TreeMap.values() возвращает значения в порядке сортировки ключей
        for (Searchable result : resultsMap.values()) {
            System.out.println("   " + counter + ". " + result.getStringRepresentation());
            counter++;
        }
    }

    /**
     * Выводит результаты поиска из списка (для обратной совместимости)
     * @param results список найденных объектов
     */
    private static void printSearchResultsFromList(List<Searchable> results) {
        System.out.println("   Найдено объектов: " + results.size());

        if (results.isEmpty()) {
            System.out.println("   Результатов не найдено.");
            return;
        }

        int counter = 1;
        for (Searchable result : results) {
            System.out.println("   " + counter + ". " + result.getStringRepresentation());
            counter++;
        }
    }
}