/*
Реализуйте метод, сливающий два отсортированных по неубыванию массива чисел в 
один отсортированный в том же порядке массив. Массивы могут быть любой длины, 
в том числе нулевой.

Предполагается, что вы реализуете алгоритм слияния, имеющий линейную сложность: 
он будет идти по двум исходным массивам и сразу формировать отсортированный 
результирующий массив. Так, чтобы сортировка полученного массива при помощи 
Arrays.sort() уже не требовалась. К сожалению, автоматически это не проверить, 
так что это остается на вашей совести :)

Воспользуйтесь предоставленным шаблоном. Декларацию класса, метод main и 
обработку ввода-вывода добавит проверяющая система.

Пример
Если на вход подаются массивы {0, 2, 2} и {1, 3}, то на выходе должен 
получиться массив {0, 1, 2, 2, 3}
 */

/**
 *
 * @author Maria Zorkaltseva
 */

public class Task_9 {
    /**
    * Merges two given sorted arrays into one
    *
    * @param a1 first sorted array
    * @param a2 second sorted array
    * @return new array containing all elements from a1 and a2, sorted
    */
    public static int[] mergeArrays(int[] a1, int[] a2) {
        int L1 = a1.length; int L2 = a2.length;
        int[] result = new int[L1 + L2];
        int i1 = 0; int i2 = 0; int j = 0;

        while(L1 != 0 && L2 != 0) {
            if (a1[i1] >= a2[i2]) {
                result[j] = a2[i2];
                i2++;
                L2--;
            } 
            else {
                result[j] = a1[i1];
                i1++;
                L1--;
            } 
            j++;    
        }

        if (L2 == 0) {
            for (int i = i1; i < L1 + i1; i++) {
                result[j] = a1[i];
                j++;
            }
        }
        else {
            for (int i = i2; i < L2 + i2; i++) {
                result[j] = a2[i];
                j++;
            }
        }                      
    return result;  
    }
    
    public static void test(){
        int[] a = {0, 2, 2, 10, 10, 20};
        int[] b = {1, 3, 5, 7, 8, 10, 10};
        int[] c = mergeArrays(a, b);
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }          
    }
}
