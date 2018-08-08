/*
* На игровом поле находится робот. Позиция робота на поле описывается двумя 
* целочисленным координатами: X и Y. Ось X смотрит слева направо, 
* ось Y — снизу вверх. (Помните, как рисовали графики функций в школе?)

* В начальный момент робот находится в некоторой позиции на поле. 
* Также известно, куда робот смотрит: вверх, вниз, направо или налево. 
* Ваша задача — привести робота в заданную точку игрового поля.

* Пример

* В метод передано: toX == 3, toY == 0; начальное состояние робота такое: 
* robot.getX() == 0, robot.getY() == 0, robot.getDirection() == Direction.UP

* Чтобы привести робота в указанную точку (3, 0), метод должен вызвать у робота 
* следующие методы:

* robot.turnRight();
* robot.stepForward();
* robot.stepForward();
* robot.stepForward();
 */

/**
 *
 * @author Maria Zorkaltseva
 */

public class Task_11 {
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    
    public class Robot {  
    Direction direction;
    int x;
    int y;
    
    Robot(int x, int y, Direction direction) {
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
} //end class Robot
  
    public static void moveRobot(Robot robot, int toX, int toY) { 
        toX = toX - robot.getX();
        toY = toY - robot.getY();  
        
        while (robot.getX() < toX) {
            while (robot.getDirection() != Direction.RIGHT)
                robot.turnRight();
            robot.stepForward();
        }
        
        while (robot.getX() > toX ){
            while (robot.getDirection() != Direction.LEFT)
                robot.turnLeft();
            robot.stepForward();
        }
        
        while (robot.getY() < toY) {
            while (robot.getDirection() != Direction.UP)
                robot.turnRight();
            robot.stepForward();
        }
        
        while (robot.getY() > toY ){
            while (robot.getDirection() != Direction.DOWN)
                robot.turnLeft();
            robot.stepForward();
        }
    }
    
    public static void test() {
        Task_11 task = new Task_11();
        Task_11.Robot robot = task.new Robot(0, 0, Direction.UP);
        
        Task_11.moveRobot(robot, 3, 0);  
        System.out.println("x = " + robot.getX() + ", " + "y = " + robot.getY());
        System.out.println("Current direction = " + robot.getDirection());
    }
}


