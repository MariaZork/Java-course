/*
Реализуйте метод, возвращающий true, если среди четырех его аргументов 
ровно два истинны (любые). Во всех остальных случаях метод должен возвращать false.

Воспользуйтесь шаблоном кода, который предлагает система. Ввод-вывод будет 
сделан за вас. Вам надо только проанализировать переданные в метод 
booleanExpression значения (a, b, c, d) и вернуть результат. Попробуйте 
составить формулу с использованием булевых операторов. Если не получается, 
вернитесь к этому заданию после просмотра степов про условные операторы и циклы.

При записи сложных выражений рекомендуется использовать скобки, чтобы 
не запутаться в порядке применения операторов.

В качестве примера уже указано заведомо некорректное решение задачи. Исправьте его.

Совет тем, у кого не проходит какой-то из тестов. В данной задаче возможно всего 
16 комбинаций значений входных параметров. Их можно выписать на бумажку, 
посчитать для них правильные ответы и сравнить с тем, что выдает ваше решение. 
Попробуйте самостоятельно проделать это, найти ошибку и исправить решение.

Sample Input 1:
false false false false

Sample Output 1:
false

Sample Input 2:
true true true true

Sample Output 2:
false

Sample Input 3:
false false true true

Sample Output 3:
true
 */

/**
 *
 * @author Maria Zorkaltseva
 */

public class Task_1 {
    public static boolean booleanExpression(boolean a, boolean b, boolean c, boolean d) {

        return ((a && b) & ((a || b) ^ (c || d))) |
               ((a && c) & ((a || c) ^ (b || d))) |
               ((a && d) & ((a || d) ^ (b || c))) |
               ((b && c) & ((b || c) ^ (a || d))) |
               ((b && d) & ((b || d) ^ (a || c))) |
               ((c && d) & ((c || d) ^ (a || b)));
    }
    
    public static void test(){
      
        boolean response1 = booleanExpression(true, true, true, true); //0000 
        
        boolean response2 = booleanExpression(true, false, false, false); //1000
        boolean response3 = booleanExpression(false, true, false, false); //0100
        boolean response4 = booleanExpression(false, false, true, false); //0010
        boolean response5 = booleanExpression(false, false, false, true); //0001
        
        boolean response6 = booleanExpression(true, true, false, false); //1100 
        boolean response7 = booleanExpression(false, false, true, true); //0011
        boolean response8 = booleanExpression(false, true, true, false); //0110      
        boolean response9 = booleanExpression(true, false, false, true); //1001
        
        boolean response10 = booleanExpression(true, true, true, false); //1110
        boolean response11 = booleanExpression(false, true, true, true); //0111
        boolean response12 = booleanExpression(true, false, true, true); //1011     
        boolean response13 = booleanExpression(true, true, false, true); //1100
        
        boolean response14 = booleanExpression(true, false, true, false); //1010
        boolean response15 = booleanExpression(false, true, false, true); //0101
        
        boolean response16 = booleanExpression(false, false, false, false); //0000
        
        System.out.println(response1);
        System.out.println(response2);
        System.out.println(response3);
        System.out.println(response4);
        
        System.out.println(response5);
        System.out.println(response6);
        System.out.println(response7);
        System.out.println(response8);
        
        System.out.println(response9);
        System.out.println(response10);
        System.out.println(response11);
        System.out.println(response12);
        
        System.out.println(response13);
        System.out.println(response14);
        System.out.println(response15);
        System.out.println(response16);
    }
}
