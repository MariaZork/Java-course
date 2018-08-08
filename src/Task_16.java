/*
Реализуйте метод sqrt(), вычисляющий квадратный корень числа. В отличие от Math.sqrt(), это метод при передаче
отрицательного параметра должен бросать исключение java.lang.IllegalArgumentException с сообщением
"Expected non-negative number, got ?", где вместо вопросика будет подставлено фактически переданное в метод число.
*/

/**
 *
 * @author Maria Zorkaltseva
 */

public class Task_16 {
    public static double sqrt(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Expected non-negative number, got " + x);
        }
        return Math.pow(x, 0.5);
    }

    public void test() {
        System.out.println(sqrt(4.0));
        try {
            System.out.println(sqrt(-4.0));
        } catch (IllegalArgumentException IAE){
            System.out.println(IAE.getMessage());
        }
    }
}
