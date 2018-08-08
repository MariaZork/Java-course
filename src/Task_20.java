/*
Это задачка совмещает тренировку по материалу предыдущих двух модулей – необходимо разобраться и написать
объект-ориентированный код и при этом коснуться свежих тем – исключений и логирования.

Дан набор классов, описывающих работу гипотетической почтовой системы.
======код классов пропущен, они есть в программе======

Вам необходимо описать набор классов, каждый из которых является MailService:

1) UntrustworthyMailWorker – класс, моделирующий ненадежного работника почты, который вместо того, чтобы передать
почтовый объект непосредственно в сервис почты, последовательно передает этот объект набору третьих лиц, а затем,
в конце концов, передает получившийся объект непосредственно экземпляру RealMailService. У UntrustworthyMailWorker
должен быть конструктор от массива MailService ( результат вызова processMail первого элемента массива передается
на вход processMail второго элемента, и т. д.) и метод getRealMailService, который возвращает ссылку
на внутренний экземпляр RealMailService.

2) Spy – шпион, который логгирует о всей почтовой переписке, которая проходит через его руки. Объект конструируется
от экземпляра Logger, с помощью которого шпион будет сообщать о всех действиях. Он следит только за объектами класса
MailMessage и пишет в логгер следующие сообщения (в выражениях нужно заменить части в фигурных скобках на значения полей почты):
    2.1) Если в качестве отправителя или получателя указан "Austin Powers", то нужно написать в лог сообщение
    с уровнем WARN: Detected target mail correspondence: from {from} to {to} "{message}"
    2.2) Иначе, необходимо написать в лог сообщение с уровнем INFO: Usual correspondence: from {from} to {to}

3) Thief – вор, который ворует самые ценные посылки и игнорирует все остальное. Вор принимает в конструкторе переменную
int – минимальную стоимость посылки, которую он будет воровать. Также, в данном классе должен присутствовать
метод getStolenValue, который возвращает суммарную стоимость всех посылок, которые он своровал. Воровство происходит
следующим образом: вместо посылки, которая пришла вору, он отдает новую, такую же, только с нулевой ценностью
и содержимым посылки "stones instead of {content}".

4) Inspector – Инспектор, который следит за запрещенными и украденными посылками и бьет тревогу в виде исключения,
если была обнаружена подобная посылка. Если он заметил запрещенную посылку с одним из запрещенных содержимым
("weapons" и "banned substance"), то он бросает IllegalPackageException. Если он находит посылку, состоящую из камней
(содержит слово "stones"), то тревога прозвучит в виде StolenPackageException. Оба исключения вы должны объявить
самостоятельно в виде непроверяемых исключений.

Все классы должны быть определены как публичные и статические, так как в процессе проверки ваш код будет подставлен
во внешний класс, который занимается тестированием и проверкой структуры. Для удобства во внешнем классе объявлено
несколько удобных констант и импортировано все содержимое пакета java.util.logging. Для определения, посылкой
или письмом является Sendable объект воспользуйтесь оператором instanceof.
*/

/**
 *
 * @author Maria Zorkaltseva
 */

import java.util.logging.*;

public class Task_20 {
    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    /*
    Интерфейс: сущность, которую можно отправить по почте.
    У такой сущности можно получить от кого и кому направляется письмо.
    */
    public static interface Sendable {
        String getFrom();
        String getTo();
    }

    /*
    Абстрактный класс,который позволяет абстрагировать логику хранения
    источника и получателя письма в соответствующих полях класса.
    */
    public static abstract class AbstractSendable implements Sendable {

        protected final String from;
        protected final String to;

        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractSendable that = (AbstractSendable) o;

            if (!from.equals(that.from)) return false;
            if (!to.equals(that.to)) return false;

            return true;
        }
    }

    /*
    Письмо, у которого есть текст, который можно получить с помощью метода `getMessage`
    */
    public static class MailMessage extends AbstractSendable {

        private final String message;

        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            if (message != null ? !message.equals(that.message) : that.message != null) return false;

            return true;
        }

    }

    /*
    Посылка, содержимое которой можно получить с помощью метода `getContent`
    */
    public static class MailPackage extends AbstractSendable {
        private final Package content;

        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailPackage that = (MailPackage) o;

            if (!content.equals(that.content)) return false;

            return true;
        }
    }

    /*
    Класс, который задает посылку. У посылки есть текстовое описание содержимого и целочисленная ценность.
    */
    public static class Package {
        private final String content;
        private final int price;

        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Package aPackage = (Package) o;

            if (price != aPackage.price) return false;
            if (!content.equals(aPackage.content)) return false;

            return true;
        }
    }

    /*
    Интерфейс, который задает класс, который может каким-либо образом обработать почтовый объект.
    */
    public static interface MailService {
        Sendable processMail(Sendable mail);
    }

    /*
    Класс, в котором скрыта логика настоящей почты
    */
    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // Здесь описан код настоящей системы отправки почты.
            return mail;
        }
    }

    public static class UntrustworthyMailWorker implements MailService {
        private MailService[] mailServices;
        private RealMailService realMailService = new RealMailService();

        public UntrustworthyMailWorker(MailService[] mailServices) {
            this.mailServices = mailServices;
        }

        public RealMailService getRealMailService() {
            return realMailService;
        }

        @Override
        public Sendable processMail(Sendable mail) {

            for (int i = 0; i < mailServices.length; i++) {
                mail = mailServices[i].processMail(mail);
                if (i == mailServices.length - 1) return getRealMailService().processMail(mail);
            }
            return mail;
        }
    }

    public static class Spy implements MailService {
        private Logger logger;

        public Spy(Logger logger) {
            this.logger= logger;
        }

        @Override
        public Sendable processMail(Sendable mail) {

            if (mail instanceof MailMessage) {
                String sender = mail.getFrom();
                String getter = mail.getTo();

                if (sender.equals(AUSTIN_POWERS) || getter.equals(AUSTIN_POWERS)) {
                    logger.log(Level.WARNING,"Detected target mail correspondence: from {0} to {1} \"{2}\"",
                            new Object[] {sender, getter, ((MailMessage) mail).getMessage()});
                }
                else {
                    logger.log(Level.INFO, "Usual correspondence: from {0} to {1}",
                            new Object[] {sender, getter});
                }
            }

            return mail;
        }
    }

    public static class Thief implements MailService {
        private int minPackageCost;
        private int thiefPackageCost;

        public Thief(int minPackageCost) {
            this.minPackageCost = minPackageCost;
        }

        public int getStolenValue() {
            return thiefPackageCost;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                Package toThiefPackage = ((MailPackage) mail).getContent();

                if (toThiefPackage.getPrice() >= minPackageCost) {
                    thiefPackageCost += toThiefPackage.getPrice();
                    Package empty =
                            new Package("stones instead of " + toThiefPackage.getContent(), 0);
                    return new MailPackage(mail.getFrom(), mail.getTo(), empty);
                }
            }
            return mail;
        }
    }

    public static class Inspector implements MailService {
        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                Package gotPackage = ((MailPackage) mail).getContent();
                if (gotPackage.getContent().contains(WEAPONS) ||
                        gotPackage.getContent().contains(BANNED_SUBSTANCE)) {
                    throw new IllegalPackageException();
                }
                if (gotPackage.getContent().contains("stones")) {
                    throw new StolenPackageException();
                }
            }
            return mail;
        }
    }

    public static class IllegalPackageException extends RuntimeException {
        public IllegalPackageException() {
        }
    }

    public static class  StolenPackageException extends RuntimeException {
        public StolenPackageException() {
        }
    }

    public void test() {
        Logger logger = Logger.getLogger(Spy.class.getName());

        Spy spyAgent = new Spy(logger);
        Thief thiefAgent = new Thief(20);
        Inspector inspectorAgent = new Inspector();

        MailService[] Agents = {spyAgent, thiefAgent, inspectorAgent};

        UntrustworthyMailWorker untrustworthyMailWorker = new UntrustworthyMailWorker(Agents);

//        ==messages and packages==
        MailMessage mailMessage0 = new MailMessage(AUSTIN_POWERS, AUSTIN_POWERS, "This message is from spy agent");
        MailMessage mailMessage1 = new MailMessage("James Bond", "James Bond", "This message is from another spy agent");

        Package package0 = new Package(WEAPONS, 21);
        Package package1 = new Package("clothes", 18);
        Package package2 = new Package("stones", 0);

        MailPackage mailPackage0 = new MailPackage(AUSTIN_POWERS, AUSTIN_POWERS, package0);
        MailPackage mailPackage1 = new MailPackage("James Bond", "James Bond", package1);
        MailPackage mailPackage2 = new MailPackage("Theresa May", "Theresa May", package2);

//        ==Spy tests==
        spyAgent.processMail(mailMessage0);
        spyAgent.processMail(mailMessage1);

//        ==Thief tests==
        System.out.println("=======mailPackage0=======");
        System.out.println("Content before thief process: " + mailPackage0.getContent().getContent() +
                ", " + "Price before thief process: " + mailPackage0.getContent().getPrice());
        mailPackage0 = (MailPackage) thiefAgent.processMail(mailPackage0);
        System.out.println("Content after thief process: " + mailPackage0.getContent().getContent() +
                ", " + "Price after thief process: " + mailPackage0.getContent().getPrice());

        System.out.println("=======mailPackage1=======");
        System.out.println("Content before thief process: " + mailPackage1.getContent().getContent() +
                ", " + "Price before thief process: " + mailPackage1.getContent().getPrice());
        mailPackage1 = (MailPackage) thiefAgent.processMail(mailPackage1);
        System.out.println("Content after thief process: " + mailPackage1.getContent().getContent() +
                ", " + "Price after thief process: " + mailPackage1.getContent().getPrice());

//        ==Inspector tests==
        try {
            Sendable mail = inspectorAgent.processMail(mailPackage0);
            System.out.println("From: " + mail.getFrom() + " " + "To: " + mail.getTo());
        }
        catch (IllegalPackageException e1) {
            System.out.println(e1.toString());
        }
        catch (StolenPackageException e2) {
            System.out.println(e2.toString());
        }

        try {
            Sendable mail = inspectorAgent.processMail(mailPackage1);
            System.out.println("From: " + mail.getFrom() + " " + "To: " + mail.getTo());
        }
        catch (IllegalPackageException e1) {
            System.out.println(e1.toString());
        }
        catch (StolenPackageException e2) {
            System.out.println(e2.toString());
        }

        try {
            Sendable mail = inspectorAgent.processMail(mailPackage2);
            System.out.println("From: " + mail.getFrom() + " " + "To: " + mail.getTo());
        }
        catch (IllegalPackageException e1) {
            System.out.println(e1.toString());
        }
        catch (StolenPackageException e2) {
            System.out.println(e2.toString());
        }

        untrustworthyMailWorker.processMail(mailMessage0);
    }
}
