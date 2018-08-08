/*
* Дан класс ComplexNumber. Переопределите в нем методы equals() и hashCode() так, 
* чтобы equals() сравнивал экземпляры ComplexNumber по содержимому полей re и im,
* а hashCode() был бы согласованным с реализацией equals().

* Реализация hashCode(), возвращающая константу или не учитывающая дробную 
* часть re и im, засчитана не будет

* Пример

* ComplexNumber a = new ComplexNumber(1, 1);
* ComplexNumber b = new ComplexNumber(1, 1);
* // a.equals(b) must return true
* // a.hashCode() must be equal to b.hashCode()

* Подсказка 1. Поищите в классе java.lang.Double статический метод, который 
* поможет в решении задачи.

* Подсказка 2. Если задача никак не решается, можно позвать на помощь среду 
* разработки, которая умеет сама генерировать equals() и hashCode(). Если вы 
* воспользовались помощью IDE, то разберитесь, что было сгенерировано и почему 
* оно выглядит именно так. Когда вас на собеседовании попросят на бумажке 
* реализовать equals() и hashCode() для какого-нибудь простого класса, то среда 
* разработки помочь не сможет.
 */

/**
 *
 * @author Maria Zorkaltseva
 */

public class Task_12 {
    public final class ComplexNumber {
        private final double re;
        private final double im;

        public ComplexNumber(double re, double im) {
            this.re = re;
            this.im = im;
        }

        public double getRe() {
            return re;
        }

        public double getIm() {
            return im;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof ComplexNumber)) return false;
            if (obj == this) return true;
            return (this.getRe() == ((ComplexNumber) obj).getRe() &&
                    this.getIm() == ((ComplexNumber) obj).getIm());
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 41 * hash + (int) (Double.doubleToLongBits(this.re) ^ (Double.doubleToLongBits(this.re) >>> 32));
            hash = 41 * hash + (int) (Double.doubleToLongBits(this.im) ^ (Double.doubleToLongBits(this.im) >>> 32));
            return hash;
        }
        
    }
    
    public void test() {
        ComplexNumber a = new ComplexNumber(1, 2);
        ComplexNumber b = new ComplexNumber(1, 1);
        System.out.println(b.equals(a));
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }
}
