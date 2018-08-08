/*
Реализуйте метод, вычисляющий симметрическую разность двух множеств.
Метод должен возвращать результат в виде нового множества. Изменять переданные в него множества не допускается.

Пример
Симметрическая разность множеств {1, 2, 3} и {0, 1, 2} равна {0, 3}.
*/

/**
 *
 * @author Maria Zorkaltseva
 */

import java.util.*;

public class Task_27 {
    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> set3 = new HashSet<>(set2);
        Set<T> set4 = new HashSet<>(set1);

        for (T item : set1) {
            if (set3.contains(item)) {
                set3.remove(item);
            }
        }

        for (T item : set2) {
            if (set4.contains(item)) {
                set4.remove(item);
            }
        }

        set3.addAll(set4);
        return set3;
    }

    public void test() {
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        Set<Integer> set2 = new HashSet<>();
        set2.add(0);
        set2.add(1);
        set2.add(2);

        Set<Integer> result = symmetricDifference(set1, set2);
        for (Integer item: result) {
            System.out.println(item);
        }
    }
}
