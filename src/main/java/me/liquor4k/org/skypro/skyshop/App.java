package me.liquor4k.org.skypro.skyshop;

import me.liquor4k.org.skypro.skyshop.product.*;
import me.liquor4k.org.skypro.skyshop.basket.ProductBasket;
import me.liquor4k.org.skypro.skyshop.content.Article;
import me.liquor4k.org.skypro.skyshop.search.SearchEngine;
import me.liquor4k.org.skypro.skyshop.search.Searchable;
import me.liquor4k.org.skypro.skyshop.search.BestResultNotFound;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация работы интернет-магазина с проверками и улучшенным поиском ===\n");

        // Часть 1: Демонстрация проверки данных в конструкторах
        System.out.println("1. ДЕМОНСТРАЦИЯ ПРОВЕРОК ДАННЫХ В КОНСТРУКТОРАХ:\n");

        // Попытка создания товаров с неправильными данными
        System.out.println("Попытка создания товаров с некорректными данными:");

        // 1.1. Пустое название товара
        System.out.println("\na) Пустое название товара:");
        try {
            SimpleProduct emptyNameProduct = new SimpleProduct("   ", 100);
            System.out.println("ОШИБКА: Должно было быть выброшено исключение!");
        } catch (IllegalArgumentException e) {
            System.out.println("Исключение поймано: " + e.getMessage());
        }

        // 1.2. Цена <= 0
        System.out.println("\nb) Цена <= 0:");
        try {
            SimpleProduct negativePriceProduct = new SimpleProduct("Товар", 0);
            System.out.println("ОШИБКА: Должно было быть выброшено исключение!");
        } catch (IllegalArgumentException e) {
            System.out.println("Исключение поймано: " + e.getMessage());
        }

        // 1.3. Неправильная скидка
        System.out.println("\nc) Скидка вне диапазона 0-100:");
        try {
            DiscountedProduct wrongDiscountProduct = new DiscountedProduct("Товар со скидкой", 1000, 150);
            System.out.println("ОШИБКА: Должно было быть выброшено исключение!");
        } catch (IllegalArgumentException e) {
            System.out.println("Исключение поймано: " + e.getMessage());
        }

        // 1.4. Отрицательная базовая цена
        System.out.println("\nd) Отрицательная базовая цена:");
        try {
            DiscountedProduct negativeBasePriceProduct = new DiscountedProduct("Товар", -100, 10);
            System.out.println("ОШИБКА: Должно было быть выброшено исключение!");
        } catch (IllegalArgumentException e) {
            System.out.println("Исключение поймано: " + e.getMessage());
        }

        // 1.5. Пустое название статьи
        System.out.println("\ne) Пустое название статьи:");
        try {
            Article emptyTitleArticle = new Article("", "Текст статьи");
            System.out.println("ОШИБКА: Должно было быть выброшено исключение!");
        } catch (IllegalArgumentException e) {
            System.out.println("Исключение поймано: " + e.getMessage());
        }

        // Часть 2: Создание корректных объектов
        System.out.println("\n\n2. СОЗДАНИЕ КОРРЕКТНЫХ ОБЪЕКТОВ:\n");

        // Создание корректных товаров
        SimpleProduct laptop = new SimpleProduct("Ноутбук Lenovo IdeaPad", 75000);
        SimpleProduct phone = new SimpleProduct("Смартфон Samsung Galaxy", 35000);
        DiscountedProduct headphones = new DiscountedProduct("Беспроводные наушники Sony WH-1000XM4", 20000, 15);
        DiscountedProduct tablet = new DiscountedProduct("Планшет Apple iPad Pro", 80000, 10);
        FixPriceProduct usbCable = new FixPriceProduct("USB-C кабель 2 метра");
        FixPriceProduct mouse = new FixPriceProduct("Игровая мышь Razer DeathAdder");

        // Создание статей с повторяющимися словами для тестирования поиска
        Article laptopArticle = new Article(
                "Обзор ноутбука Lenovo IdeaPad",
                "Ноутбук Lenovo IdeaPad обладает мощным процессором Intel Core i7. " +
                        "Ноутбук показал отличные результаты в тестах производительности. " +
                        "Ноутбук идеально подходит для работы и учебы."
        );

        Article headphonesArticle = new Article(
                "Тест наушников Sony WH-1000XM4",
                "Наушники Sony WH-1000XM4 показали превосходное качество звука. " +
                        "Шумоподавление Sony работает безупречно. Sony продолжает удивлять."
        );

        Article shoppingGuide = new Article(
                "Как выбрать электронику для дома",
                "Выбор электроники - важная задача. Электроника должна быть надежной. " +
                        "Электроника для дома включает телевизоры, ноутбуки и смартфоны."
        );

        System.out.println("Создано корректных товаров: 6");
        System.out.println("Создано корректных статей: 3");

        // Часть 3: Создание и настройка поискового движка
        System.out.println("\n\n3. ДЕМОНСТРАЦИЯ ПОИСКОВОГО ДВИЖКА:\n");

        SearchEngine searchEngine = new SearchEngine(15);

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

        System.out.println("Объектов в поисковом движке: " + searchEngine.getCount());

        // Часть 4: Демонстрация обычного поиска
        System.out.println("\n4. ОБЫЧНЫЙ ПОИСК (до 5 результатов):");

        System.out.println("\na) Поиск 'ноутбук':");
        Searchable[] results = searchEngine.search("ноутбук");
        printSearchResults(results);

        System.out.println("\nb) Поиск 'Sony':");
        results = searchEngine.search("Sony");
        printSearchResults(results);

        System.out.println("\nc) Поиск 'электроника':");
        results = searchEngine.search("электроника");
        printSearchResults(results);

        // Часть 5: Демонстрация нового метода findBestMatch
        System.out.println("\n\n5. ПОИСК НАИБОЛЕЕ ПОДХОДЯЩЕГО ОБЪЕКТА (findBestMatch):\n");

        // 5.1. Успешный поиск
        System.out.println("a) Успешный поиск наиболее подходящего объекта:");
        try {
            System.out.println("   Запрос: 'ноутбук'");
            Searchable bestMatch = searchEngine.findBestMatch("ноутбук");
            System.out.println("   Найден: " + bestMatch.getStringRepresentation());
            System.out.println("   Текст для поиска: " + bestMatch.getSearchTerm());

            System.out.println("\n   Запрос: 'Sony'");
            bestMatch = searchEngine.findBestMatch("Sony");
            System.out.println("   Найден: " + bestMatch.getStringRepresentation());

            System.out.println("\n   Запрос: 'электроника'");
            bestMatch = searchEngine.findBestMatch("электроника");
            System.out.println("   Найден: " + bestMatch.getStringRepresentation());

        } catch (BestResultNotFound e) {
            System.out.println("   ОШИБКА: " + e.getMessage());
        }

        // 5.2. Поиск с пустым результатом
        System.out.println("\nb) Поиск, когда объект не найден:");
        try {
            System.out.println("   Запрос: 'xyz123abc' (заведомо несуществующий)");
            Searchable bestMatch = searchEngine.findBestMatch("xyz123abc");
            System.out.println("   ОШИБКА: Должно было быть выброшено исключение!");
        } catch (BestResultNotFound e) {
            System.out.println("   Исключение поймано: " + e.getMessage());
        }

        // 5.3. Поиск с пустым запросом
        System.out.println("\nc) Поиск с пустым запросом:");
        try {
            System.out.println("   Запрос: '' (пустая строка)");
            Searchable bestMatch = searchEngine.findBestMatch("");
            System.out.println("   Найден: " + bestMatch.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("   Исключение поймано: " + e.getMessage());
        }

        // 5.4. Демонстрация подсчета вхождений
        System.out.println("\n\n6. ДЕМОНСТРАЦИЯ ПОДСЧЕТА ВХОЖДЕНИЙ:");

        // Создаем статью с множественными вхождениями слова
        Article testArticle = new Article(
                "Тест подсчета вхождений",
                "Java Java Java программирование. Java это язык программирования. " +
                        "Java используется везде. Java Java Java. Конец."
        );

        searchEngine.add(testArticle);

        try {
            System.out.println("   Запрос: 'Java'");
            Searchable bestMatch = searchEngine.findBestMatch("Java");
            System.out.println("   Найден: " + bestMatch.getStringRepresentation());
            System.out.println("   (Слово 'Java' встречается 7 раз в тексте)");
        } catch (BestResultNotFound e) {
            System.out.println("   Исключение: " + e.getMessage());
        }

        // Часть 7: Демонстрация работы корзины (для полноты)
        System.out.println("\n\n7. ДЕМОНСТРАЦИЯ РАБОТЫ КОРЗИНЫ (из предыдущих заданий):\n");

        ProductBasket basket = new ProductBasket();
        basket.addProduct(laptop);
        basket.addProduct(headphones);
        basket.addProduct(usbCable);

        System.out.println("Содержимое корзины:");
        basket.printBasket();

        System.out.println("\n=== Демонстрация завершена ===");
    }

    /**
     * Выводит результаты поиска в консоль
     * @param results массив найденных объектов
     */
    private static void printSearchResults(Searchable[] results) {
        int nonNullCount = 0;
        for (Searchable result : results) {
            if (result != null) {
                nonNullCount++;
            }
        }

        System.out.println("   Найдено объектов: " + nonNullCount);

        if (nonNullCount == 0) {
            System.out.println("   Результатов не найдено.");
            return;
        }

        for (int i = 0; i < results.length; i++) {
            if (results[i] != null) {
                System.out.println("   " + (i + 1) + ". " + results[i].getStringRepresentation());
            }
        }
    }
}