/*
Напишите программу, читающую текст из System.in и выводящую в System.out сумму всех встреченных в тексте вещественных
чисел с точностью до шестого знака после запятой. Числом считается последовательность символов, отделенная
от окружающего текста пробелами или переводами строк и успешно разбираемая методом Double.parseDouble.

На этот раз вам надо написать программу полностью, т.е. объявить класс (с именем Main — таково ограничение
проверяющей системы), метод main, прописать все import'ы.

Sample Input 1:
1 2 3

Sample Output 1:
6.000000

Sample Input 2:
a1 b2 c3

Sample Output 2:
0.000000

Sample Input 3:
-1e3
18 .111 11bbb

Sample Output 3:
-981.889000
*/

import java.util.Scanner;

/**
 *
 * @author Maria Zorkaltseva
 */

public class Task_24 {
    public double sumOfElements() {
        Scanner scanner = new Scanner(System.in);
        double sum = 0;
        String str;
        while (scanner.hasNext()) {
            str = scanner.next();
            try {
                sum += Double.parseDouble(str);
            }
            catch (NumberFormatException e) {
                continue;
            }
        }
        return sum;

    }

    public void test() {
        double summa = sumOfElements();
        System.out.printf("%.6f", summa);
    }
}
