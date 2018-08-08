/*
Давайте научимся комбинировать функции в более сложные функции. Для примера построим следующую комбинацию.
Дан предикат condition и две функции ifTrue и ifFalse. Напишите метод ternaryOperator,
который из них построит новую функцию, возвращающую значение функции ifTrue, если предикат выполнен, и значение ifFalse иначе.

Пример использования метода (можно было все свернуть в одну строчку, но для наглядности все элементы вынесены в отдельные переменные):

Predicate<Object> condition = Objects::isNull;
Function<Object, Integer> ifTrue = obj -> 0;
Function<CharSequence, Integer> ifFalse = CharSequence::length;
Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);

Результирующая функция будет для нулевых ссылок на String возвращать 0, а для ненулевых ссылок возвращать длину строки.

В качестве дополнительного задания самостоятельно разберите, почему у метода ternaryOperator такая сложная сигнатура.
*/

import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Function;

/**
 *
 * @author Maria Zorkaltseva
 */

public class Task_29 {
    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {

        Function<T, U> result = t -> condition.test(t) ? ifTrue.apply(t) : ifFalse.apply(t);
        return result; // your implementation here
    }

    public void test() {
        Predicate<Integer> condition = n -> (n % 2) == 0;
        Function<Integer, String> ifTrue = n -> "Number is even";
        Function<Integer, String> ifFalse = n -> "Number is odd";

        Function<Integer, String> isEven = ternaryOperator(condition, ifTrue, ifFalse);

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println(isEven.apply(sc.nextInt()));
        }
        catch (Exception e) {
        }
    }
}
