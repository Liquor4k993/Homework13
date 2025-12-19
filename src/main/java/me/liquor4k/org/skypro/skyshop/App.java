package me.liquor4k.org.skypro.skyshop;

import me.liquor4k.org.skypro.skyshop.product.DiscountedProduct;
import me.liquor4k.org.skypro.skyshop.product.FixPriceProduct;
import me.liquor4k.org.skypro.skyshop.product.Product;
import me.liquor4k.org.skypro.skyshop.basket.ProductBasket;
import me.liquor4k.org.skypro.skyshop.product.SimpleProduct;

public class App {
    public static void main(String[] args) {
        System.out.println("Демонстрация работы интернет-магазина с наследованием\n");

        // Создание товаров разных типов
        SimpleProduct laptop = new SimpleProduct("Ноутбук", 75000);
        SimpleProduct phone = new SimpleProduct("Смартфон", 35000);
        DiscountedProduct headphones = new DiscountedProduct("Наушники", 5000, 20); // 20% скидка
        DiscountedProduct tablet = new DiscountedProduct("Планшет", 25000, 15); // 15% скидка
        FixPriceProduct usbCable = new FixPriceProduct("USB-кабель");
        FixPriceProduct mouse = new FixPriceProduct("Компьютерная мышь");

        // Создание корзины
        ProductBasket basket = new ProductBasket();

        // Добавление товаров в корзину
        System.out.println("1. Добавление товаров разных типов в корзину:");
        basket.addProduct(laptop);
        basket.addProduct(phone);
        basket.addProduct(headphones);
        basket.addProduct(tablet);
        basket.addProduct(usbCable);
        System.out.println("Добавлено 5 товаров\n");

        // Попытка добавить шестой товар (корзина заполнена)
        System.out.println("2. Попытка добавить товар в заполненную корзину:");
        basket.addProduct(mouse);
        System.out.println();

        // Печать содержимого корзины
        System.out.println("3. Печать содержимого корзины (новый формат):");
        basket.printBasket();
        System.out.println();

        // Получение стоимости корзины
        System.out.println("4. Получение стоимости корзины:");
        System.out.println("Общая стоимость: " + basket.getTotalPrice() + " руб.\n");

        // Поиск товаров
        System.out.println("5. Поиск товаров в корзине:");
        System.out.println("Товар 'Ноутбук' в корзине: " + basket.containsProduct("Ноутбук"));
        System.out.println("Товар 'Клавиатура' в корзине: " + basket.containsProduct("Клавиатура"));
        System.out.println();

        // Получение количества специальных товаров
        System.out.println("6. Количество специальных товаров в корзине:");
        System.out.println("Специальных товаров: " + basket.getSpecialProductsCount() + "\n");

        // Очистка корзины
        System.out.println("7. Очистка корзины:");
        basket.clearBasket();
        System.out.println("Корзина очищена\n");

        // Печать пустой корзины
        System.out.println("8. Печать содержимого пустой корзины:");
        basket.printBasket();
        System.out.println();

        // Демонстрация скидок
        System.out.println("9. Демонстрация работы скидок и фиксированных цен:");
        ProductBasket basket2 = new ProductBasket();

        DiscountedProduct tv = new DiscountedProduct("Телевизор", 50000, 30);
        FixPriceProduct charger = new FixPriceProduct("Зарядное устройство");
        SimpleProduct book = new SimpleProduct("Книга", 500);

        basket2.addProduct(tv);
        basket2.addProduct(charger);
        basket2.addProduct(book);

        System.out.println("Создана новая корзина с товарами:");
        basket2.printBasket();

        // Проверка цен
        System.out.println("\n10. Проверка цен товаров:");
        System.out.println("Телевизор со скидкой 30%: базовая цена " + tv.getBasePrice() +
                ", цена со скидкой " + tv.getPrice());
        System.out.println("Фиксированная цена товара: " + charger.getPrice());
        System.out.println("Обычная цена книги: " + book.getPrice());
    }
}