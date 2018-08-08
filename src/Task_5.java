/*
Реализуйте метод, который возвращает букву, стоящую в таблице UNICODE после 
символа "\" (обратный слэш) на расстоянии a.
В качестве примера написано заведомо неправильное выражение. Исправьте его.

Sample Input 1:
32

Sample Output 1:
|

Sample Input 2:
29

Sample Output 2:
y

 */

/**
 *
 * @author Maria Zorkaltseva
 */

public class Task_5 {
    public static char charExpression(int a) {
        char symbol = '\\';
        int tmp = (int) symbol + a;
        symbol = (char) tmp;
        return symbol;
    }
    
    public static void test(){
            System.out.println(charExpression(29));
    }
}
