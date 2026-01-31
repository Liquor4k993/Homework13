package me.liquor4k.org.skypro.skyshop.search;

import java.util.Comparator;

/**
 * Компаратор для сортировки объектов Searchable.
 * Сортирует от самого длинного имени к самому короткому.
 * При равной длине имен сортирует в натуральном порядке.
 */
public class SearchableComparator implements Comparator<Searchable> {

    /**
     * Сравнивает два объекта Searchable
     * @param s1 первый объект
     * @param s2 второй объект
     * @return отрицательное число если s1 должен быть раньше s2,
     *         положительное число если s1 должен быть позже s2,
     *         0 если объекты равны по критериям сравнения
     */
    @Override
    public int compare(Searchable s1, Searchable s2) {
        if (s1 == null && s2 == null) return 0;
        if (s1 == null) return 1; // null идет в конец
        if (s2 == null) return -1; // null идет в конец

        String name1 = s1.getSearchableName();
        String name2 = s2.getSearchableName();

        // Сначала сравниваем по длине имени (от большего к меньшему)
        int lengthComparison = Integer.compare(name2.length(), name1.length());

        // Если длина разная, возвращаем результат сравнения длин
        if (lengthComparison != 0) {
            return lengthComparison;
        }

        // Если длина одинаковая, сравниваем имена в натуральном порядке
        return name1.compareTo(name2);
    }
}