/*
Реализуйте generic-класс Pair, похожий на Optional, но содержащий пару элементов разных типов и не запрещающий
элементам принимать значение null.

Реализуйте методы getFirst(), getSecond(), equals() и hashCode(), а также статический фабричный метод of().
Конструктор должен быть закрытым (private).

С корректно реализованным классом Pair должен компилироваться и успешно работать следующий код:

Pair<Integer, String> pair = Pair.of(1, "hello");
Integer i = pair.getFirst(); // 1
String s = pair.getSecond(); // "hello"

Pair<Integer, String> pair2 = Pair.of(1, "hello");
boolean mustBeTrue = pair.equals(pair2); // true!
boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!


Пожалуйста, не меняйте модификатор доступа класса Pair. Для корректной проверки класс должен иметь пакетную видимость.
*/

/**
 *
 * @author Maria Zorkaltseva
 */

import java.util.Objects;

public class Task_26 {
    class Pair<T, V> {
        private final T first;
        private final V second;

        private Pair(T first, V second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (!(o instanceof Pair)) return false;

            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) &&
                    Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        public static <T, V> Pair<T, V> of(T first, V second) {
            return new Pair<>(first, second);
        }
    }

    public void test() {
        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getFirst(); // 1
        String s = pair.getSecond(); // "hello"
        System.out.println("First: " + i.toString() + "Second: " + s);

        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2); // true!
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!
    }
}
