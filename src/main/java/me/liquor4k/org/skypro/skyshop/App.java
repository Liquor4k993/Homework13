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
        System.out.println("=== Демонстрация работы интернет-магазина с использованием Set и кастомной сортировкой ===\n");

        // Часть 1: Создание объектов
        System.out.println("1. СОЗДАНИЕ ОБЪЕКТОВ:\n");

        // Создание товаров с разной длиной имен для демонстрации сортировки
        SimpleProduct laptop = new SimpleProduct("Ноутбук Lenovo IdeaPad", 75000);
        SimpleProduct phone = new SimpleProduct("Смартфон", 35000); // Короткое имя
        DiscountedProduct headphones = new DiscountedProduct("Беспроводные наушники Sony WH-1000XM4", 20000, 15); // Длинное имя
        DiscountedProduct tablet = new DiscountedProduct("Планшет Apple iPad Pro 12.9 дюймов", 80000, 10); // Очень длинное имя
        FixPriceProduct usbCable = new FixPriceProduct("Кабель"); // Очень короткое имя (исправлено: только имя)
        FixPriceProduct mouse = new FixPriceProduct("Мышь игровая");
        SimpleProduct keyboard = new SimpleProduct("Клавиатура механическая RGB", 5000);
        DiscountedProduct monitor = new DiscountedProduct("Монитор", 30000, 20); // Короткое имя

        // Создание статей с разной длиной названий
        Article laptopArticle = new Article(
                "Подробный обзор ноутбука Lenovo IdeaPad для программистов",
                "Ноутбук Lenovo IdeaPad обладает мощным процессором."
        );

        Article headphonesArticle = new Article(
                "Тест наушников",
                "Наушники Sony показали превосходное качество звука."
        );

        Article shoppingGuide = new Article(
                "Как выбрать электронику для дома и офиса: подробное руководство",
                "Выбор электроники - важная задача."
        );

        Article appleArticle = new Article(
                "Apple",
                "Техника Apple отличается высоким качеством и дизайном."
        );

        System.out.println("Создано товаров: 8");
        System.out.println("Создано статей: 4");

        // Часть 2: Демонстрация работы корзины
        System.out.println("\n\n2. ДЕМОНСТРАЦИЯ РАБОТЫ КОРЗИНЫ:\n");

        ProductBasket basket = new ProductBasket();

        // Добавление товаров в корзину
        basket.addProduct(laptop);
        basket.addProduct(phone);
        basket.addProduct(headphones);
        basket.addProduct(tablet);
        basket.addProduct(usbCable);
        basket.addProduct(mouse);
        basket.addProduct(keyboard);
        basket.addProduct(monitor);

        System.out.println("   Всего товаров в корзине: " + basket.getProductCount());
        System.out.println("   Уникальных наименований: " + basket.getUniqueProductCount());

        System.out.println("\n   Вывод содержимого корзины:");
        basket.printBasket();

        // Часть 3: Демонстрация работы поискового движка с Set
        System.out.println("\n\n3. ДЕМОНСТРАЦИЯ РАБОТЫ ПОИСКОВОГО ДВИЖКА С SET:\n");

        SearchEngine searchEngine = new SearchEngine();

        // Добавляем объекты (включая потенциальные дубликаты)
        System.out.println("a) Добавление объектов в поисковый движок:");

        // Добавляем все товары
        boolean added1 = searchEngine.add(laptop);
        boolean added2 = searchEngine.add(phone);
        boolean added3 = searchEngine.add(headphones);
        boolean added4 = searchEngine.add(tablet);
        boolean added5 = searchEngine.add(usbCable);
        boolean added6 = searchEngine.add(mouse);
        boolean added7 = searchEngine.add(keyboard);
        boolean added8 = searchEngine.add(monitor);

        // Добавляем все статьи
        boolean added9 = searchEngine.add(laptopArticle);
        boolean added10 = searchEngine.add(headphonesArticle);
        boolean added11 = searchEngine.add(shoppingGuide);
        boolean added12 = searchEngine.add(appleArticle);

        System.out.println("   Успешно добавлено: " + searchEngine.getCount() + " уникальных объектов");

        // Пытаемся добавить дубликаты
        System.out.println("\nb) Попытка добавления дубликатов:");

        SimpleProduct laptopDuplicate = new SimpleProduct("Ноутбук Lenovo IdeaPad", 80000); // Такое же имя, другая цена
        Article articleDuplicate = new Article("Apple", "Другой текст статьи");

        boolean duplicate1Added = searchEngine.add(laptopDuplicate);
        boolean duplicate2Added = searchEngine.add(articleDuplicate);

        System.out.println("   Дубликат ноутбука добавлен: " + duplicate1Added + " (должен быть false)");
        System.out.println("   Дубликат статьи добавлен: " + duplicate2Added + " (должен быть false)");
        System.out.println("   Всего объектов после попытки добавления дубликатов: " + searchEngine.getCount());

        // Проверяем equals и hashCode
        System.out.println("\nc) Проверка equals и hashCode:");
        System.out.println("   laptop.equals(laptopDuplicate): " + laptop.equals(laptopDuplicate));
        System.out.println("   appleArticle.equals(articleDuplicate): " + appleArticle.equals(articleDuplicate));
        System.out.println("   laptop.hashCode() == laptopDuplicate.hashCode(): " +
                (laptop.hashCode() == laptopDuplicate.hashCode()));

        // Часть 4: Демонстрация поиска с TreeSet и кастомной сортировкой
        System.out.println("\n\n4. ДЕМОНСТРАЦИЯ ПОИСКА С КАСТОМНОЙ СОРТИРОВКОЙ:\n");

        System.out.println("a) Поиск объектов, содержащих 'ноутбук' (сортировка по длине имени):");
        TreeSet<Searchable> results = searchEngine.search("ноутбук");
        printSearchResultsFromTreeSet(results);

        System.out.println("\nb) Поиск объектов, содержащих 'Apple' (сортировка по длине имени):");
        results = searchEngine.search("Apple");
        printSearchResultsFromTreeSet(results);

        System.out.println("\nc) Поиск объектов, содержащих 'и' (много результатов, демонстрация сортировки):");
        results = searchEngine.search("и");
        printSearchResultsFromTreeSet(results);

        // Демонстрация сортировки по длине имени
        System.out.println("\nd) Поиск всех объектов (пустой запрос) для демонстрации полной сортировки:");
        results = searchEngine.search("");
        printSearchResultsFromTreeSet(results);

        // Демонстрация сортировки при одинаковой длине имен
        System.out.println("\ne) Создаем дополнительные объекты для демонстрации сортировки при одинаковой длине:");

        SimpleProduct a = new SimpleProduct("AAA", 100);
        SimpleProduct b = new SimpleProduct("BBB", 200);
        SimpleProduct c = new SimpleProduct("CCC", 300);

        SearchEngine testEngine = new SearchEngine();
        testEngine.add(c);
        testEngine.add(a);
        testEngine.add(b);

        TreeSet<Searchable> testResults = testEngine.search("");
        System.out.println("   Объекты с одинаковой длиной имени (3 символа):");
        for (Searchable result : testResults) {
            System.out.println("   - " + result.getSearchableName() + " (длина: " +
                    result.getSearchableName().length() + ")");
        }

        // Часть 5: Демонстрация findBestMatch
        System.out.println("\n\n5. ДЕМОНСТРАЦИЯ ПОИСКА НАИБОЛЕЕ ПОДХОДЯЩЕГО ОБЪЕКТА:\n");

        try {
            System.out.println("a) Запрос: 'ноутбук'");
            Searchable bestMatch = searchEngine.findBestMatch("ноутбук");
            System.out.println("   Найден: " + bestMatch.getStringRepresentation());

            System.out.println("\nb) Запрос: 'Apple'");
            bestMatch = searchEngine.findBestMatch("Apple");
            System.out.println("   Найден: " + bestMatch.getStringRepresentation());

        } catch (BestResultNotFound e) {
            System.out.println("   Исключение: " + e.getMessage());
        }

        // Часть 6: Проверка отсутствия дубликатов в Set
        System.out.println("\n\n6. ПРОВЕРКА ОТСУТСТВИЯ ДУБЛИКАТОВ В SET:\n");

        System.out.println("a) Все объекты в поисковом движке (уникальные по имени):");
        Set<Searchable> allSearchables = searchEngine.getAllSearchables();
        int counter = 1;
        for (Searchable item : allSearchables) {
            System.out.println("   " + counter + ". " + item.getSearchableName() +
                    " (тип: " + item.getContentType() + ")");
            counter++;
        }

        System.out.println("\n   Всего уникальных объектов: " + allSearchables.size());

        // Проверяем, что действительно нет дубликатов по имени
        System.out.println("\nb) Проверка на дубликаты имен:");
        Set<String> names = new HashSet<>();
        for (Searchable item : allSearchables) {
            String name = item.getSearchableName();
            if (names.contains(name)) {
                System.out.println("   ОШИБКА: Найден дубликат имени: " + name);
            } else {
                names.add(name);
            }
        }
        System.out.println("   Проверка завершена. Дубликатов не обнаружено.");

        System.out.println("\n=== Демонстрация завершена ===");
    }

    /**
     * Выводит результаты поиска из TreeSet (отсортированные по длине имени)
     * @param results TreeSet с результатами поиска
     */
    private static void printSearchResultsFromTreeSet(TreeSet<Searchable> results) {
        System.out.println("   Найдено объектов: " + results.size());

        if (results.isEmpty()) {
            System.out.println("   Результатов не найдено.");
            return;
        }

        System.out.println("   Результаты (отсортированы по длине имени, от большего к меньшему):");
        int counter = 1;

        for (Searchable result : results) {
            String name = result.getSearchableName();
            System.out.println("   " + counter + ". " + result.getStringRepresentation() +
                    " (длина имени: " + name.length() + ")");
            counter++;
        }
    }
}