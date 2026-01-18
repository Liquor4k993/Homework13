package me.liquor4k.org.skypro.skyshop.search;

/**
 * Исключение, выбрасываемое когда не найден наиболее подходящий объект.
 */
public class BestResultNotFound extends Exception {

    /**
     * Конструктор исключения
     * @param searchQuery поисковый запрос, по которому не найдены результаты
     */
    public BestResultNotFound(String searchQuery) {
        super("Не найден наиболее подходящий объект для поискового запроса: \"" + searchQuery + "\"");
    }

    /**
     * Конструктор исключения с причиной
     * @param searchQuery поисковый запрос
     * @param cause причина исключения
     */
    public BestResultNotFound(String searchQuery, Throwable cause) {
        super("Не найден наиболее подходящий объект для поискового запроса: \"" + searchQuery + "\"", cause);
    }
}