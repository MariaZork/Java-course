/*
В этой задаче вам нужно реализовать метод, настраивающий параметры логирования.
Конфигурирование в коде позволяет выполнить более тонкую и хитрую настройку, чем при помощи properties-файла.

Требуется выставить такие настройки, чтобы:

1. Логгер с именем "org.stepic.java.logging.ClassA" принимал сообщения всех уровней.
2. Логгер с именем "org.stepic.java.logging.ClassB" принимал только сообщения уровня WARNING и серьезнее.
3. Все сообщения, пришедшие от нижестоящих логгеров на уровень "org.stepic.java", независимо от серьезности
сообщения печатались в консоль в формате XML (*) и не передавались вышестоящим обработчикам на уровнях "org.stepic", "org" и "".

Не упомянутые здесь настройки изменяться не должны.

(*) В реальных программах мы бы конечно печатали XML не в консоль, а в файл. Но проверяющая система не разрешает создавать файлы на диске, поэтому придется так.

Подсказки:

1. Level есть не только у Logger, но и у Handler.
2. Передача сообщения на обработку родительскому Handler'у регулируется свойством useParentHandlers.
*/

/**
 *
 * @author Maria Zorkaltseva
 */

import java.util.logging.*;

public class Task_19 {
    private static void configureLogging() {

        Logger classA = Logger.getLogger("org.stepic.java.logging.ClassA");
        classA.setLevel(Level.ALL);

        Logger classB = Logger.getLogger("org.stepic.java.logging.ClassB");
        classB.setLevel(Level.WARNING);

        Logger root = Logger.getLogger("org.stepic.java");
        root.setLevel(Level.ALL);

        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new XMLFormatter());

        root.addHandler(consoleHandler);
        root.setUseParentHandlers(false);
    }

    public void test() {
        configureLogging();
    }
}
