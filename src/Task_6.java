/*
Реализуйте метод, проверяющий, является ли заданное число по абсолютной величине 
степенью двойки.

Решать можно разными способами:
воспользовавшись одним удобным статическим методом из класса java.lang.Integer;
применив пару трюков из двоичной арифметики;
написав решение "в лоб" с циклом и условными операторами (можете вернуться к 
этой задаче после просмотра соответствующих уроков).
Воспользуйтесь предоставленным шаблоном. Декларацию класса, метод main и 
обработку ввода-вывода добавит проверяющая система.

Sample Input 1:
0

Sample Output 1:
false

Sample Input 2:
1

Sample Output 2:
true

Sample Input 3:
-2

Sample Output 3:
true

 */

/**
 *
 * @author Maria Zorkaltseva
 */

public class Task_6 {
    /**
 * Checks if given <code>value</code> is a power of two.
 *
 * @param value any number
 * @return <code>true</code> when <code>value</code> is power of two,
 * <code>false</code> otherwise
 */
    public static boolean isPowerOfTwo(int value) {
        value = Math.abs(value); 
        while (value > 1 && ((value % 2) == 0)) {
            value /= 2;
             
        }
        return value == 1;
    }
    
    public static void test(){
        System.out.println(isPowerOfTwo(-2));
    }
}
