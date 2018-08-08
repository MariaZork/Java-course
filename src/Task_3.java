/*
Реализуйте метод, возвращающий ответ на вопрос: правда ли, что a + b = c?
Допустимая погрешность – 0.0001 (1E-4)
Можно использовать класс Math и его методы. Класс Math доступен всегда, импортировать его не надо.
В качестве примера написано заведомо неправильное выражение. Исправьте его.

Sample Input:
0.1 0.2 0.3

Sample Output:
true

 */

/**
 *
 * @author Maria Zorkaltseva
 */

public class Task_3 {
    public static boolean doubleExpression(double a, double b, double c) {
        double eps = 0.0001;
        return Math.abs((a+b)-c) <= eps;
    }  
    
    public static void test(){
        System.out.println(doubleExpression(0.1, 0.2, 0.3));
        System.out.println(doubleExpression(0.10288, 0.20888, 0.3));
    }
}
