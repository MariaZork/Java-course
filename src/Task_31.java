/*
Напишите метод, находящий в стриме минимальный и максимальный элементы в соответствии порядком, заданным Comparator'ом.

Найденные минимальный и максимальный элементы передайте в minMaxConsumer следующим образом:

minMaxConsumer.accept(min, max);
Если стрим не содержит элементов, то вызовите
minMaxConsumer.accept(null, null);
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Maria Zorkaltseva
 */

public class Task_31 {
    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {

        ArrayList<T> elements = stream.sorted(order::compare)
                .collect(Collectors.toCollection(ArrayList::new));

        if (elements.isEmpty()) {
            minMaxConsumer.accept(null, null);
        }
        else {
            minMaxConsumer.accept(elements.get(0), elements.get(elements.size() - 1));
        }

    }

    public void test() {
        Comparator<Integer> order = (o1, o2) -> o1.compareTo(o2);

        BiConsumer<Integer, Integer> minMaxConsumer = (o1, o2) ->
                        System.out.println("Min: " + o1 + " Max: " + o2);

        findMinMax(Stream.of(-1, 42, 85, 32, 314, 12), order, minMaxConsumer);
    }
}
