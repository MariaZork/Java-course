/*
Реализуйте метод, выполняющий численное интегрирование заданной функции на
заданном интервале по формуле левых прямоугольников.

Функция задана объектом, реализующим интерфейс
java.util.function.DoubleUnaryOperator. Его метод applyAsDouble()
принимает значение аргумента и возвращает значение функции в заданной точке.

Интервал интегрирования задается его конечными точками a и b, причем a<=b.
Для получения достаточно точного результата используйте шаг сетки не больше 10^−6.

Пример. Вызов

integrate(x -> 1, 0, 10)
должен возвращать значение 10.

P.S. Если задача слишком легкая, то дополнительно можете реализовать
динамический выбор шага сетки по следующему алгоритму:

Вычислить приближенное значение интеграла функции при начальном значении шага сетки (например, 1).
Вычислить приближенное значение интеграла функции при более мелком шаге сетки (например, уменьшить шаг сетки в два раза).
Если разность двух последовательных приближений интеграла функции достаточно мала, то завершаем алгоритм
и возвращаем текущее приближение в качестве результата.
Иначе возвращаемся к шагу 2.
*/

/**
 *
 * @author Maria Zorkaltseva
 */

import  java.util.function.DoubleUnaryOperator;

public class Task_13 {
    public static double integrate(DoubleUnaryOperator f, double a, double b) {
        double h = 1e-6;
        int i = 0;
        double xi = a;
        double integral = 0;

        while (xi < b) {
            xi = a + i * h;
            integral = integral + f.applyAsDouble(xi)*h;
            i = i + 1;
        }

        return integral;
    }

    public void test() {
        System.out.println(integrate(x -> 1, 0, 10));//10.0
        System.out.println(integrate(x -> x + 2, 0, 10));//70.0
        System.out.println(integrate( x -> Math.sin(x) / x , 1, 5));//0.603848
    }
}