/*
Контрольная сумма данных вычисляется по следующему алгоритму:

1. Контрольная сумма представляет собой число типа int. Контрольная сумма пустого набора данных равна нулю.
2. Контрольная сумма непустого набора данных вычисляется по следующей рекуррентной формуле:
   Cn+1=rotateLeft(Cn) xor bn+1 , где Cn — контрольная сумма первых n байт данных,
   rotateLeft — циклический сдвиг бит числа на один бит влево (чтобы не изобретать велосипед, используйте Integer.rotateLeft), bn — n-ный байт данных.
Поскольку метод не открывал данный InputStream, то и закрывать его он не должен. Выброшенное из методов InputStream исключение должно выбрасываться из метода.

Пример:

На вход подан InputStream, последовательно возвращающий три байта: 0x33 0x45 0x01. В качестве контрольной суммы должно быть возвращено число 71.
*/

/**
 *
 * @author Maria Zorkaltseva
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Task_21 {
    public static int checkSumOfStream(InputStream inputStream) throws IOException {

        if (inputStream.read() == -1) return 0;

        inputStream.reset();

        int sum = 0;
        int item;
        while ((item = inputStream.read()) != -1) {
            sum = Integer.rotateLeft(sum, 1) ^ item;
        }
        return sum;
    }

    public void test() {
        byte[] data = {0x33, 0x45, 0x01};
        InputStream inputStream = new ByteArrayInputStream(data);

        try {
            System.out.println(checkSumOfStream(inputStream));
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try {
            inputStream.close();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
