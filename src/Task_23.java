/*
Реализуйте метод, который зачитает данные из InputStream и преобразует их в строку, используя заданную кодировку.

Пример:
InputStream последовательно возвращает четыре байта: 48 49 50 51.
Метод, вызванный для такого InputStream и кодировки ASCII, должен вернуть строку "0123".
*/

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Maria Zorkaltseva
 */

public class Task_23 {
    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int item;
        while ((item = inputStream.read()) != -1) {
            byteArrayOutputStream.write(item);
        }

        String str = new String(byteArrayOutputStream.toByteArray(), charset);
        return str;
    }

    public void test() {
        byte[] data = {48, 49, 50, 51}; // dec
        InputStream inputStream = new ByteArrayInputStream(data);
        String str;

        try {
            str = readAsString(inputStream, StandardCharsets.US_ASCII);
            System.out.println(str);
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
