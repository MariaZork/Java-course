/*
Напишите программу, которая прочитает из System.in последовательность целых чисел, разделенных пробелами,
затем удалит из них все числа, стоящие на четных позициях, и затем выведет получившуюся последовательность в обратном порядке в System.out.

Все числа влезают в int. Позиции чисел в последовательности нумеруются с нуля.

В этом задании надо написать программу целиком, включая import'ы, декларацию класса Main и метода main.

Sample Input:
1 2 3 4 5 6 7

Sample Output:
6 4 2
*/

import java.util.*;

/**
 *
 * @author Maria Zorkaltseva
 */

public class Task_28 {
    public void deleteEvenAndReverse() {
        LinkedList<Integer> myLinkedList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while (scanner.hasNextInt()) {
            try {
                int number = scanner.nextInt();
                if (i % 2 != 0) myLinkedList.add(number);
                i++;
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        Iterator dIt = myLinkedList.descendingIterator();
        while (dIt.hasNext()) {
            System.out.print(dIt.next() + " ");
        }
    }
}
