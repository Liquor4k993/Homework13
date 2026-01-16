package me.liquor4k.org.skypro.skyshop;

import me.liquor4k.org.skypro.skyshop.product.*;
import me.liquor4k.org.skypro.skyshop.basket.ProductBasket;
import me.liquor4k.org.skypro.skyshop.content.Article;
import me.liquor4k.org.skypro.skyshop.search.SearchEngine;
import me.liquor4k.org.skypro.skyshop.search.Searchable;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация работы интернет-магазина с поиском ===\n");

        // Создание товаров разных типов
        SimpleProduct laptop = new SimpleProduct("Ноутбук Lenovo", 75000);
        SimpleProduct phone = new SimpleProduct("Смартфон Samsung", 35000);
        DiscountedProduct headphones = new DiscountedProduct("Беспроводные наушники Sony", 5000, 20);
        DiscountedProduct tablet = new DiscountedProduct("Планшет Apple iPad", 25000, 15);
        FixPriceProduct usbCable = new FixPriceProduct("USB-C кабель");
        FixPriceProduct mouse = new FixPriceProduct("Игровая мышь");

        // Создание статей
        Article laptopArticle = new Article(
                "Обзор ноутбука Lenovo",
                "Новый ноутбук Lenovo обладает мощным процессором и длительным временем работы от батареи."
        );

        Article headphonesArticle = new Article(
                "Тест беспроводных наушников",
                "Наушники Sony показали отличное качество звука и удобную посадку."
        );

        Article shoppingGuide = new Article(
                "Как выбрать электронику",
                "При выборе электроники обращайте внимание на характеристики, бренд и отзывы."
        );

        // Создание и настройка поискового движка
        SearchEngine searchEngine = new SearchEngine(10);

        // Добавление товаров в поисковый движок
        searchEngine.add(laptop);
        searchEngine.add(phone);
        searchEngine.add(headphones);
        searchEngine.add(tablet);
        searchEngine.add(usbCable);
        searchEngine.add(mouse);

        // Добавление статей в поисковый движок
        searchEngine.add(laptopArticle);
        searchEngine.add(headphonesArticle);
        searchEngine.add(shoppingGuide);

        System.out.println("1. Объектов в поисковом движке: " + searchEngine.getCount() + "\n");

        // Демонстрация поиска
        System.out.println("2. Поиск по запросу 'ноутбук':");
        Searchable[] results = searchEngine.search("ноутбук");
        printSearchResults(results);

        System.out.println("\n3. Поиск по запросу 'Sony':");
        results = searchEngine.search("Sony");
        printSearchResults(results);

        System.out.println("\n4. Поиск по запросу 'игровая':");
        results = searchEngine.search("игровая");
        printSearchResults(results);

        System.out.println("\n5. Поиск по запросу 'электроника':");
        results = searchEngine.search("электроника");
        printSearchResults(results);

        System.out.println("\n6. Поиск по запросу 'кабель':");
        results = searchEngine.search("кабель");
        printSearchResults(results);

        System.out.println("\n7. Поиск по запросу 'xyz' (нет результатов):");
        results = searchEngine.search("xyz");
        printSearchResults(results);

        // Демонстрация getStringRepresentation
        System.out.println("\n8. Демонстрация getStringRepresentation():");
        System.out.println(laptop.getStringRepresentation());
        System.out.println(laptopArticle.getStringRepresentation());

        // Демонстрация работы корзины (из предыдущих заданий)
        System.out.println("\n9. Демонстрация работы корзины:");
        ProductBasket basket = new ProductBasket();
        basket.addProduct(laptop);
        basket.addProduct(headphones);
        basket.addProduct(usbCable);
        basket.printBasket();
    }

    /**
     * Выводит результаты поиска в консоль
     * @param results массив найденных объектов
     */
    private static void printSearchResults(Searchable[] results) {
        System.out.println("Найдено объектов: " + countNonNull(results));

        if (countNonNull(results) == 0) {
            System.out.println("Результатов не найдено.");
            return;
        }

        for (Searchable result : results) {
            if (result != null) {
                System.out.println("  • " + result.getStringRepresentation());
            }
        }

        // Альтернативный вывод через Arrays.toString (для отладки)
        // System.out.println("Массив результатов: " + Arrays.toString(results));
    }

    /**
     * Подсчитывает ненулевые элементы в массиве
     * @param array массив для проверки
     * @return количество ненулевых элементов
     */
    private static int countNonNull(Searchable[] array) {
        int count = 0;
        for (Searchable item : array) {
            if (item != null) {
                count++;
            }
        }
        return count;
    }
}