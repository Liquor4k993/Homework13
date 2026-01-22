package me.liquor4k.org.skypro.skyshop;

import me.liquor4k.org.skypro.skyshop.product.*;
import me.liquor4k.org.skypro.skyshop.basket.ProductBasket;
import me.liquor4k.org.skypro.skyshop.content.Article;
import me.liquor4k.org.skypro.skyshop.search.SearchEngine;
import me.liquor4k.org.skypro.skyshop.search.Searchable;
import me.liquor4k.org.skypro.skyshop.search.BestResultNotFound;

import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация работы интернет-магазина с использованием списков ===\n");

        // Часть 1: Создание корректных объектов
        System.out.println("1. СОЗДАНИЕ ОБЪЕКТОВ:\n");

        // Создание товаров
        SimpleProduct laptop = new SimpleProduct("Ноутбук Lenovo IdeaPad", 75000);
        SimpleProduct phone = new SimpleProduct("Смартфон Samsung Galaxy", 35000);
        DiscountedProduct headphones = new DiscountedProduct("Беспроводные наушники Sony", 20000, 15);
        DiscountedProduct tablet = new DiscountedProduct("Планшет Apple iPad Pro", 80000, 10);
        FixPriceProduct usbCable = new FixPriceProduct("USB-C кабель");
        FixPriceProduct mouse = new FixPriceProduct("Игровая мышь Razer");

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

        System.out.println("Создано товаров: 6");
        System.out.println("Создано статей: 3");

        // Часть 2: Демонстрация работы корзины со списком
        System.out.println("\n\n2. ДЕМОНСТРАЦИЯ РАБОТЫ КОРЗИНЫ (LinkedList):\n");

        ProductBasket basket = new ProductBasket();

        // Добавление товаров в корзину (без ограничения на количество)
        System.out.println("a) Добавление товаров в корзину:");
        basket.addProduct(laptop);
        basket.addProduct(phone);
        basket.addProduct(headphones);
        basket.addProduct(tablet);
        basket.addProduct(usbCable);
        basket.addProduct(mouse);
        basket.addProduct(new SimpleProduct("Ноутбук Lenovo IdeaPad", 75000)); // Дубликат
        basket.addProduct(new DiscountedProduct("Беспроводные наушники Sony", 20000, 15)); // Дубликат

        System.out.println("   Товаров в корзине: " + basket.getProductCount());
        System.out.println("   Общая стоимость: " + basket.getTotalPrice() + " руб.");

        System.out.println("\nb) Вывод содержимого корзины:");
        basket.printBasket();

        // Часть 3: Демонстрация удаления продуктов по имени
        System.out.println("\n\n3. ДЕМОНСТРАЦИЯ УДАЛЕНИЯ ПРОДУКТОВ ПО ИМЕНИ:\n");

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

        // 3.3. Попытка удаления несуществующего продукта
        System.out.println("\nc) Попытка удаления несуществующего продукта 'Несуществующий товар':");
        removedProducts = basket.removeProductByName("Несуществующий товар");

        if (removedProducts.isEmpty()) {
            System.out.println("   Список пуст. Товар не найден в корзине.");
        } else {
            System.out.println("   ОШИБКА: Должен быть пустой список!");
        }

        System.out.println("\n   Содержимое корзины:");
        basket.printBasket();

        // Часть 4: Демонстрация работы поискового движка со списком
        System.out.println("\n\n4. ДЕМОНСТРАЦИЯ ПОИСКОВОГО ДВИЖКА (LinkedList):\n");

        SearchEngine searchEngine = new SearchEngine();

        // Добавление всех товаров и статей в поисковый движок
        searchEngine.add(laptop);
        searchEngine.add(phone);
        searchEngine.add(headphones);
        searchEngine.add(tablet);
        searchEngine.add(usbCable);
        searchEngine.add(mouse);
        searchEngine.add(laptopArticle);
        searchEngine.add(headphonesArticle);
        searchEngine.add(shoppingGuide);

        System.out.println("Объектов в поисковом движке: " + searchEngine.getCount());

        // 4.1. Поиск с несколькими результатами
        System.out.println("\na) Поиск 'ноутбук' (несколько результатов):");
        List<Searchable> results = searchEngine.search("ноутбук");
        printSearchResults(results);

        // 4.2. Поиск с одним результатом
        System.out.println("\nb) Поиск 'Samsung' (один результат):");
        results = searchEngine.search("Samsung");
        printSearchResults(results);

        // 4.3. Поиск с многими результатами
        System.out.println("\nc) Поиск 'Sony' (несколько результатов):");
        results = searchEngine.search("Sony");
        printSearchResults(results);

        // 4.4. Поиск без результатов
        System.out.println("\nd) Поиск 'xyz123abc' (нет результатов):");
        results = searchEngine.search("xyz123abc");
        printSearchResults(results);

        // 4.5. Поиск наиболее подходящего объекта
        System.out.println("\n\n5. ПОИСК НАИБОЛЕЕ ПОДХОДЯЩЕГО ОБЪЕКТА:\n");

        try {
            System.out.println("a) Запрос: 'ноутбук'");
            Searchable bestMatch = searchEngine.findBestMatch("ноутбук");
            System.out.println("   Найден: " + bestMatch.getStringRepresentation());

            System.out.println("\nb) Запрос: 'Sony'");
            bestMatch = searchEngine.findBestMatch("Sony");
            System.out.println("   Найден: " + bestMatch.getStringRepresentation());

        } catch (BestResultNotFound e) {
            System.out.println("   Исключение: " + e.getMessage());
        }

        // 4.6. Добавление товаров с повторяющимися словами для демонстрации
        System.out.println("\n\n6. ДОПОЛНИТЕЛЬНАЯ ДЕМОНСТРАЦИЯ:\n");

        // Добавим еще несколько товаров и статей для тестирования
        Article articleWithRepeats = new Article(
                "Статья о ноутбуках и компьютерах",
                "Ноутбуки и компьютеры - это важная техника. Ноутбуки мобильны. " +
                        "Компьютеры мощные. Ноутбуки удобны. Компьютеры стационарны."
        );

        searchEngine.add(articleWithRepeats);

        System.out.println("a) Поиск 'ноутбук' после добавления новой статьи:");
        results = searchEngine.search("ноутбук");
        printSearchResults(results);

        System.out.println("\nb) Поиск 'компьютер' (должна найтись новая статья):");
        results = searchEngine.search("компьютер");
        printSearchResults(results);

        // Часть 5: Демонстрация других методов корзины
        System.out.println("\n\n7. ДОПОЛНИТЕЛЬНЫЕ МЕТОДЫ КОРЗИНЫ:\n");

        System.out.println("a) Проверка наличия товара:");
        System.out.println("   Товар 'Смартфон Samsung Galaxy' в корзине: " +
                basket.containsProduct("Смартфон Samsung Galaxy"));
        System.out.println("   Товар 'Несуществующий' в корзине: " +
                basket.containsProduct("Несуществующий"));

        System.out.println("\nb) Очистка корзины:");
        basket.clearBasket();
        System.out.println("   Товаров в корзине после очистки: " + basket.getProductCount());

        System.out.println("\nc) Вывод пустой корзины:");
        basket.printBasket();

        System.out.println("\n=== Демонстрация завершена ===");
    }

    /**
     * Выводит результаты поиска в консоль
     * @param results список найденных объектов
     */
    private static void printSearchResults(List<Searchable> results) {
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