/*
Напишите класс AsciiCharSequence, реализующий компактное хранение последовательности ASCII-символов (их коды влезают
в один байт) в массиве байт. По сравнению с классом String, хранящим каждый символ как char, AsciiCharSequence будет
занимать в два раза меньше памяти.

Класс AsciiCharSequence должен:

реализовывать интерфейс java.lang.CharSequence;
иметь конструктор, принимающий массив байт;
определять методы length(), charAt(), subSequence() и toString()
Сигнатуры методов и ожидания по их поведению смотрите в описании интерфейса java.lang.CharSequence (JavaDoc или исходники).

В данном задании методам charAt() и subSequence() всегда будут подаваться корректные входные параметры, поэтому их
проверкой и обработкой ошибок заниматься не нужно. Тем более мы еще не проходили исключения.

P.S. В Java 9 ожидается подобная оптимизация в самом классе String: http://openjdk.java.net/jeps/254
*/


/**
 *
 * @author Maria Zorkaltseva
 */

import java.lang.CharSequence;

public class Task_14 {
    public class AsciiCharSequence implements CharSequence{
        byte[] arr;

        AsciiCharSequence(byte[] arr) {
            this.arr = arr;
        }

        @Override
        public int length() {
            return arr.length;
        }

        @Override
        public char charAt(int index) {
            return (char) arr[index];
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            byte[] newArr = new byte[end - start];
            for (int i = start; i < end; i++) newArr[i-start] = arr[i];
            return new AsciiCharSequence(newArr);
        }

        @Override
        public String toString() {
            return new String(arr);
        }
    }

    public void test() {
        byte[] testArr = {72, 101, 108, 108, 111, 33};
        AsciiCharSequence asciiCharSequence = new AsciiCharSequence(testArr);
        System.out.println("Длина последовательности символов: " + asciiCharSequence.length());
        System.out.println("Символ на позиции 2:" + asciiCharSequence.charAt(1));
        System.out.println("Подпоследовательность символов: " + asciiCharSequence.subSequence(1,5));
        System.out.println("Последовательность символов: " + asciiCharSequence.toString());
    }
}
