/*
Вспомним нашего старого знакомого робота из Task_11.java.
Теперь мы будем давать роботу команды удаленно, подключаясь к нему по беспроводному каналу связи.

Подключение к роботу представляется в программе интерфейсом RobotConnection:
public interface RobotConnection extends AutoCloseable {
    void moveRobotTo(int x, int y);
    @Override
    void close();
}

Да, робот с тех пор поумнел и стал понимать команду на перемещение в заданную точку (метод moveRobotTo).
Ему больше не нужны пошаговые инструкции.
RobotConnection — это временно устанавливаемое соединение, которое надо закрывать, когда оно больше не нужно.
Для закрытия соединения в интерфейсе есть метод close().

За установку соединения отвечает RobotConnectionManager:
public interface RobotConnectionManager {
    RobotConnection getConnection();
}

Метод getConnection() делает попытку соединиться с роботом и возвращает установленное соединение,
через которое можно отдавать роботу команды.
Установка соединения может завершиться неуспешно, а также уже установленное соединение может внезапно разорваться.
Всякое бывает. Поэтому любой метод RobotConnectionManager
и RobotConnection может бросить непроверяемое исключение RobotConnectionException:

public class RobotConnectionException extends RuntimeException {

    public RobotConnectionException(String message) {
        super(message);

    }

    public RobotConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}

Ваша задача — реализовать метод который устанавливает соединение с роботом, отдает ему команду на перемещение
в заданную точку и затем закрывает соединение, выполняя при необходимости повтор этой последовательности до трех раз.

При этом:

1. Попытка отдать команду роботу считается успешной, если удалось установить соединение и выполнить метод
RobotConnection.moveRobotTo() без исключений. Ошибка закрытия соединения не важна и должна игнорироваться.

2. Если первая попытка подключиться и отдать команду оказалась неуспешной, то закрываем соединение и
выполняем вторую попытку. Если вторая тоже не удалась, то выполняется третья.
После третьей неудачи метод должен бросить исключение RobotConnectionException.

3. Метод отвечает за открытие и закрытие соединения. Если соединение удалось установить, то оно обязательно
должно быть закрыто несмотря на возможные исключения, случившиеся в дальнейшем во время работы метода.

4. Если во время работы метода случилось исключение любого типа, отличного от RobotConnectionException, метод должен
завершиться и выбросить это исключение, не выполняя повторных попыток пообщаться с роботом.
Единственное, что метод должен сделать перед выбросом этого исключения — закрыть открытое соединение RobotConnection.
*/

/**
 *
 * @author Maria Zorkaltseva
 */

import java.util.Random;

public class Task_18 {
    public interface RobotConnection extends AutoCloseable {
        void moveRobotTo(int ToX, int ToY);
        @Override
        void close();
    }

    public interface RobotConnectionManager {
        RobotConnection getConnection();
    }

    public class RobotConnectionException extends RuntimeException {

        public RobotConnectionException(String message) {
            super(message);
        }

        public RobotConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public class RobotConnectionManagerImplementation implements RobotConnectionManager {

        private boolean isConnect;
        Random random = new Random();

        @Override
        public RobotConnection getConnection() {

            isConnect = random.nextBoolean();
            if (!isConnect) {
                throw new RobotConnectionException("Failed connection with robot");
            }
            RobotConnection rc = new RobotConnectionImplementation(0, 0, Direction.DOWN);
            return rc;
        }
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public class RobotConnectionImplementation implements RobotConnection {
        Direction direction;
        int x;
        int y;
        boolean isConnect;

        RobotConnectionImplementation(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public Direction getDirection() {
            return direction;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void turnLeft() {
            // повернуться на 90 градусов против часовой стрелки
            switch (direction) {
                case UP:
                    direction = Direction.LEFT;
                    break;
                case DOWN:
                    direction = Direction.RIGHT;
                    break;
                case LEFT:
                    direction = Direction.DOWN;
                    break;
                case RIGHT:
                    direction = Direction.UP;
                    break;
                default:
                    direction = getDirection();
            }
        }

        public void turnRight() {
            // повернуться на 90 градусов по часовой стрелке
            switch (direction) {
                case UP:
                    direction = Direction.RIGHT;
                    break;
                case DOWN:
                    direction = Direction.LEFT;
                    break;
                case LEFT:
                    direction = Direction.UP;
                    break;
                case RIGHT:
                    direction = Direction.DOWN;
                    break;
                default:
                    direction = getDirection();
            }
        }

        public void stepForward() {
            // шаг в направлении взгляда
            // за один шаг робот изменяет одну свою координату на единицу
            switch (direction) {
                case UP:
                    y = y + 1;
                    break;
                case DOWN:
                    y = y - 1;
                    break;
                case LEFT:
                    x = x - 1;
                    break;
                case RIGHT:
                    x = x + 1;
                    break;
                default:
                    x = getX();
                    y = getY();
            }
        }

        @Override
        public void close() {
            System.out.println("Method for resources close");
        }

        @Override
        public void moveRobotTo(int toX, int toY) {
            Random random = new Random();

            toX = toX - getX();
            toY = toY - getY();

            while (getX() < toX) {
                while (getDirection() != Direction.RIGHT)
                    turnRight();
                stepForward();
            }

            while (getX() > toX ){
                while (getDirection() != Direction.LEFT)
                    turnLeft();
                stepForward();
            }

            while (getY() < toY) {
                while (getDirection() != Direction.UP)
                    turnRight();
                stepForward();
            }

            while (getY() > toY ){
                while (getDirection() != Direction.DOWN)
                    turnLeft();
                stepForward();
            }

            isConnect = random.nextBoolean();

            if (!isConnect) {
                new RobotConnectionException("Failed move robot");
            }
        }
    }

    public void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        int numberOfAttempts = 3;
        boolean isThatOK = false;
        int i = 1;

        while (!isThatOK & (i<=numberOfAttempts)) {
            try (RobotConnection robotConnection = robotConnectionManager.getConnection()) {
                robotConnection.moveRobotTo(toX, toY);
                isThatOK = true;
                System.out.println("Connection to robot is successful");
            }
            catch (RobotConnectionException RCE) {
                System.out.println(RCE.getMessage());
            }

            if (!isThatOK) i++;

        }

        if (!isThatOK) {
            throw new RobotConnectionException("Three attempts is failed");
        }

    }

    public void test() {
        try{
            RobotConnectionManagerImplementation rcm = new RobotConnectionManagerImplementation();
            moveRobot(rcm, 2, 2);
        } catch (RobotConnectionException RCE){
            System.out.println(RCE.getMessage());
        }
    }
}
