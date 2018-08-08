/*
По историческим причинам на разных платформах принят разный способ обозначения конца строки в текстовом файле.
На Unix-системах конец строки обозначается символом с кодом 10 ('\n'), на Windows — двумя последовательными
символами с кодами 13 и 10 ('\r' '\n').

Напишите программу, которая будет преобразовывать переводы строк из формата Windows в формат Unix.
Данные в формате Windows подаются программе в System.in, преобразованные данные должны выводиться в System.out.
На этот раз вам надо написать программу полностью, т.е. объявить класс
(с именем Main — таково ограничение проверяющей системы), метод main, прописать все import'ы.

Требуется заменить все вхождения пары символов '\r' и '\n' на один символ '\n'.
Если на входе встречается одиночный символ '\r', за которым не следует '\n', то символ '\r' выводится без изменения.

Кодировка входных данных такова, что символ '\n' представляется байтом 10, а символ '\r' — байтом 13.
Поэтому программа может осуществлять фильтрацию на уровне двоичных данных, не преобразуя байты в символы.

Из-за буферизации данных в System.out в конце вашей программы надо явно вызвать System.out.flush().
Иначе часть выведенных вами данных не будет видна проверяющей системе.

Пример

Из System.in зачитаны следующие байты: 65 13 10 10 13.
Внимание! Это не строка "65 13 10 10 13", а последовательность чисел, возвращаемая методом System.in.read().

В System.out должны быть выведены байты: 65 10 10 13
*/

/**
 *
 * @author Maria Zorkaltseva
 */

import java.io.*;

public class Task_22 {

    public void winToUnixSymbol() {
//        метод через System.in/System.out для сдачи задания
        try {
            int item= System.in.read();
            int prevItem = item;
            while ((item = System.in.read()) != -1) {
                if ((prevItem  == 13) && (item == 10)) {
                    System.out.write(item);
                    item = System.in.read();
                }
                else {
                    System.out.write(prevItem);
                }

                prevItem = item;
            }
            if (prevItem != -1) System.out.write(prevItem);
            System.out.flush();

        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try {
            System.in.close();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void test() {
//        метод для тестирования с помощью байтовых потоков
        byte[] data = {65, 13, 10, 10, 13}; // dec

        InputStream inputStream = new ByteArrayInputStream(data);
        OutputStream outputStream = new ByteArrayOutputStream();

        try {
            int item= inputStream.read();
            int prevItem = item;
            while ((item = inputStream.read()) != -1) {
                if ((prevItem  == 13) && (item == 10)) {
                    outputStream.write(item);
                    item = inputStream.read();
                }
                else {
                    outputStream.write(prevItem);
                }

                prevItem = item;
            }
            if (prevItem != -1) outputStream.write(prevItem);
            outputStream.flush();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try {
            System.in.close();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
