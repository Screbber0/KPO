package reversi.utility;

import reversi.model.Color;
import reversi.view.GameBoard;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, добавляющий на поле новые фишки
 */
public class GameBoardUpdater {

    /**
     * Игровое поле
     */
    private final GameBoard gameBoard;

    /**
     * Конструктор, устагавливающий игровое поле
     *
     * @param gameBoard Игровое поле
     */
    public GameBoardUpdater(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Метод, выставляющий фишку на игровое поле
     *
     * @param point Координаты фишки
     * @param color Цвет фишки
     */
    public void placeDisk(Point point, Color color) {
        int x = point.x;
        int y = point.y;
        gameBoard.getDisk(x, y).setColor(color);
        if (upLineCheck(x, y, color)) {
            for (int i = 1; i < gameBoard.getSIZE(); i++) {
                if (gameBoard.getDiskColor(x - i, y) == color) {
                    break;
                }
                gameBoard.getDisk(x - i, y).setColor(color);
            }
        }
        if (rightUpperDiagonalCheck(x, y, color)) {
            for (int i = 1; i < gameBoard.getSIZE(); i++) {
                if (gameBoard.getDiskColor(x - i, y + i) == color) {
                    break;
                }
                gameBoard.getDisk(x - i, y + i).setColor(color);
            }
        }
        if (rightLineCheck(x, y, color)) {
            for (int i = 1; i < gameBoard.getSIZE(); i++) {
                if (gameBoard.getDiskColor(x, y + i) == color) {
                    break;
                }
                gameBoard.getDisk(x, y + i).setColor(color);
            }
        }
        if (rightLowerDiagonalCheck(x, y, color)) {
            for (int i = 1; i < gameBoard.getSIZE(); i++) {
                if (gameBoard.getDiskColor(x + i, y + i) == color) {
                    break;
                }
                gameBoard.getDisk(x + i, y + i).setColor(color);
            }
        }
        if (lowerLineCheck(x, y, color)) {
            for (int i = 1; i < gameBoard.getSIZE(); i++) {
                if (gameBoard.getDiskColor(x + i, y) == color) {
                    break;
                }
                gameBoard.getDisk(x + i, y).setColor(color);
            }
        }
        if (leftLowerDiagonalCheck(x, y, color)) {
            for (int i = 1; i < gameBoard.getSIZE(); i++) {
                if (gameBoard.getDiskColor(x + i, y - i) == color) {
                    break;
                }
                gameBoard.getDisk(x + i, y - i).setColor(color);
            }
        }
        if (leftLineCheck(x, y, color)) {
            for (int i = 1; i < gameBoard.getSIZE(); i++) {
                if (gameBoard.getDiskColor(x, y - i) == color) {
                    break;
                }
                gameBoard.getDisk(x, y - i).setColor(color);
            }
        }
        if (leftUpperDiagonalCheck(x, y, color)) {
            for (int i = 1; i < gameBoard.getSIZE(); i++) {
                if (gameBoard.getDiskColor(x - i, y - i) == color) {
                    break;
                }
                gameBoard.getDisk(x - i, y - i).setColor(color);
            }
        }
    }

    /**
     * Перекрашивает BLACK в WHITE и наоборот, DEFAULT оствляет так же
     *
     * @param color Цвет
     * @return Перекрашенный цвет
     */
    public static Color repaintColor(Color color) {
        if (color == reversi.model.Color.WHITE) {
            return reversi.model.Color.BLACK;
        }
        if (color == reversi.model.Color.BLACK) {
            return reversi.model.Color.WHITE;
        }
        return reversi.model.Color.DEFAULT;
    }

    /**
     * Проверка возможности замкнуть линию фишек сверху
     *
     * @param x           Координата по x
     * @param y           Координата по y
     * @param colorPlayer Цвет текущего игрока
     * @return true, если замкнуть можно, иначе false
     */
    private boolean upLineCheck(int x, int y, Color colorPlayer) {
        if (gameBoard.getDiskColor(x - 1, y) == repaintColor(colorPlayer)) {
            for (int i = 2; i < gameBoard.getSIZE(); i++) {
                reversi.model.Color currentDiskColor = gameBoard.getDiskColor(x - i, y);
                if (currentDiskColor == colorPlayer) {
                    return true;
                }
                if (currentDiskColor != repaintColor(colorPlayer)) {
                    break;
                }
            }
        }
        return false;
    }

    /**
     * Проверка возможности замкнуть правую верхнюю диагональ фишек
     *
     * @param x           Координата по x
     * @param y           Координата по y
     * @param colorPlayer Цвет текущего игрока
     * @return true, если замкнуть можно, иначе false
     */
    private boolean rightUpperDiagonalCheck(int x, int y, Color colorPlayer) {
        if (gameBoard.getDiskColor(x - 1, y + 1) == repaintColor(colorPlayer)) {
            for (int i = 2; i < gameBoard.getSIZE(); i++) {
                reversi.model.Color currentDiskColor = gameBoard.getDiskColor(x - i, y + i);
                if (currentDiskColor == colorPlayer) {
                    return true;
                }
                if (currentDiskColor != repaintColor(colorPlayer)) {
                    break;
                }
            }
        }
        return false;
    }

    /**
     * Проверка возможности замкнуть линию фишек справа
     *
     * @param x           Координата по x
     * @param y           Координата по y
     * @param colorPlayer Цвет текущего игрока
     * @return true, если замкнуть можно, иначе false
     */
    private boolean rightLineCheck(int x, int y, Color colorPlayer) {
        if (gameBoard.getDiskColor(x, y + 1) == repaintColor(colorPlayer)) {
            for (int i = 2; i < gameBoard.getSIZE(); i++) {
                reversi.model.Color currentDiskColor = gameBoard.getDiskColor(x, y + i);
                if (currentDiskColor == colorPlayer) {
                    return true;
                }
                if (currentDiskColor != repaintColor(colorPlayer)) {
                    break;
                }
            }
        }
        return false;
    }

    /**
     * Проверка возможности замкнуть правую нижнюю диагональ фишек
     *
     * @param x           Координата по x
     * @param y           Координата по y
     * @param colorPlayer Цвет текущего игрока
     * @return true, если замкнуть можно, иначе false
     */
    private boolean rightLowerDiagonalCheck(int x, int y, Color colorPlayer) {
        if (gameBoard.getDiskColor(x + 1, y + 1) == repaintColor(colorPlayer)) {
            for (int i = 2; i < gameBoard.getSIZE(); i++) {
                reversi.model.Color currentDiskColor = gameBoard.getDiskColor(x + i, y + i);
                if (currentDiskColor == colorPlayer) {
                    return true;
                }
                if (currentDiskColor != repaintColor(colorPlayer)) {
                    break;
                }
            }
        }
        return false;
    }

    /**
     * Проверка возможности замкнуть линию фишек снизу
     *
     * @param x           Координата по x
     * @param y           Координата по y
     * @param colorPlayer Цвет текущего игрока
     * @return true, если замкнуть можно, иначе false
     */
    private boolean lowerLineCheck(int x, int y, Color colorPlayer) {
        if (gameBoard.getDiskColor(x + 1, y) == repaintColor(colorPlayer)) {
            for (int i = 2; i < gameBoard.getSIZE(); i++) {
                reversi.model.Color currentDiskColor = gameBoard.getDiskColor(x + i, y);
                if (currentDiskColor == colorPlayer) {
                    return true;
                }
                if (currentDiskColor != repaintColor(colorPlayer)) {
                    break;
                }
            }
        }
        return false;
    }

    /**
     * Проверка возможности замкнуть левую нижнюю диагональ фишек
     *
     * @param x           Координата по x
     * @param y           Координата по y
     * @param colorPlayer Цвет текущего игрока
     * @return true, если замкнуть можно, иначе false
     */
    private boolean leftLowerDiagonalCheck(int x, int y, Color colorPlayer) {
        if (gameBoard.getDiskColor(x + 1, y - 1) == repaintColor(colorPlayer)) {
            for (int i = 2; i < gameBoard.getSIZE(); i++) {
                reversi.model.Color currentDiskColor = gameBoard.getDiskColor(x + i, y - i);
                if (currentDiskColor == colorPlayer) {
                    return true;
                }
                if (currentDiskColor != repaintColor(colorPlayer)) {
                    break;
                }
            }
        }
        return false;
    }


    /**
     * Проверка возможности замкнуть линию фишек слева
     *
     * @param x           Координата по x
     * @param y           Координата по y
     * @param colorPlayer Цвет текущего игрока
     * @return true, если замкнуть можно, иначе false
     */
    private boolean leftLineCheck(int x, int y, Color colorPlayer) {
        if (gameBoard.getDiskColor(x, y - 1) == repaintColor(colorPlayer)) {
            for (int i = y - 2; i >= 0; i--) {
                reversi.model.Color currentDiskColor = gameBoard.getDiskColor(x, i);
                if (currentDiskColor == colorPlayer) {
                    return true;
                }
                if (currentDiskColor != repaintColor(colorPlayer)) {
                    break;
                }
            }
        }
        return false;
    }

    /**
     * Проверка возможности замкнуть левую верхнюю диагональ фишек
     *
     * @param x           Координата по x
     * @param y           Координата по y
     * @param colorPlayer Цвет текущего игрока
     * @return true, если замкнуть можно, иначе false
     */
    private boolean leftUpperDiagonalCheck(int x, int y, Color colorPlayer) {
        if (gameBoard.getDiskColor(x - 1, y - 1) == repaintColor(colorPlayer)) {
            for (int i = 2; i < gameBoard.getSIZE(); i++) {
                Color currentDiskColor = gameBoard.getDiskColor(x - i, y - i);
                if (currentDiskColor == colorPlayer) {
                    return true;
                }
                if (currentDiskColor != repaintColor(colorPlayer)) {
                    break;
                }
            }
        }
        return false;
    }

    /**
     * Проверка возможности поставить фишку по указанным координатам
     *
     * @param x           Координата по x
     * @param y           Координата по y
     * @param colorPlayer Цвет текущего игрока
     * @return true, если можно, ианче false
     */
    private boolean isCanPlaceDisk(int x, int y, Color colorPlayer) {
        if (gameBoard.getDiskColor(x, y) != Color.DEFAULT) {
            return false;
        }
        return upLineCheck(x, y, colorPlayer)
                || rightUpperDiagonalCheck(x, y, colorPlayer)
                || rightLineCheck(x, y, colorPlayer)
                || rightLowerDiagonalCheck(x, y, colorPlayer)
                || lowerLineCheck(x, y, colorPlayer)
                || leftLowerDiagonalCheck(x, y, colorPlayer)
                || leftLineCheck(x, y, colorPlayer)
                || leftUpperDiagonalCheck(x, y, colorPlayer);
    }

    /**
     * Получить список координат, куда можно поставить фишку
     *
     * @param colorPlayer цвет игрока
     * @return список координат
     */
    public List<Point> getUpdatePointList(Color colorPlayer) {
        List<Point> pointList = new ArrayList<>();
        for (int i = 0; i < gameBoard.getSIZE(); i++) {
            for (int j = 0; j < gameBoard.getSIZE(); j++) {
                if (isCanPlaceDisk(i, j, colorPlayer)) {
                    pointList.add(new Point(i, j));
                }
            }
        }
        return pointList;
    }

    /**
     * Находит ценность поле, на которое ставится фишка
     *
     * @param x координата по x
     * @param y координата по y
     * @return ценность поля
     */
    private double findInitFieldValue(int x, int y) {
        if ((x == 0 || x == 7) && (y == 0 || y == 7)) {
            return 0.8;
        }
        if ((x == 0 || x == 7) || (y == 0 || y == 7)) {
            return 0.4;
        }
        return 0;
    }

    /**
     * Находит стоимость замыкаемого диска
     *
     * @param x координата то x
     * @param y координата по y
     * @return ценность замыкаемых фишек
     */
    private int findDiskValue(int x, int y) {
        return (x == 0 || x == 7 || y == 0 || y == 7) ? 2 : 1;
    }


    /**
     * Находит ценность хода на клетку
     *
     * @param x           координата x
     * @param y           координата y
     * @param colorPlayer цвет игрока
     * @return ценность хода
     */
    private double findMoveValue(int x, int y, Color colorPlayer) {
        double currentValueDisk = findInitFieldValue(x, y);
        if (upLineCheck(x, y, colorPlayer)) {
            for (int i = 1; i < gameBoard.getSIZE(); i++) {
                if (gameBoard.getDiskColor(x - i, y) == colorPlayer) {
                    break;
                }
                currentValueDisk += findDiskValue(x - i, y);
            }
        }
        if (rightUpperDiagonalCheck(x, y, colorPlayer)) {
            for (int i = 1; i < gameBoard.getSIZE(); i++) {
                if (gameBoard.getDiskColor(x - i, y + i) == colorPlayer) {
                    break;
                }
                currentValueDisk += findDiskValue(x - i, y + i);
            }
        }
        if (rightLineCheck(x, y, colorPlayer)) {
            for (int i = 1; i < gameBoard.getSIZE(); i++) {
                if (gameBoard.getDiskColor(x, y + i) == colorPlayer) {
                    break;
                }
                currentValueDisk += findDiskValue(x, y + i);
            }
        }
        if (rightLowerDiagonalCheck(x, y, colorPlayer)) {
            for (int i = 1; i < gameBoard.getSIZE(); i++) {
                if (gameBoard.getDiskColor(x + i, y + i) == colorPlayer) {
                    break;
                }
                currentValueDisk += findDiskValue(x + i, y + i);
            }
        }
        if (lowerLineCheck(x, y, colorPlayer)) {
            for (int i = 1; i < gameBoard.getSIZE(); i++) {
                if (gameBoard.getDiskColor(x + i, y) == colorPlayer) {
                    break;
                }
                currentValueDisk += findDiskValue(x + i, y);
            }
        }
        if (leftLowerDiagonalCheck(x, y, colorPlayer)) {
            for (int i = 1; i < gameBoard.getSIZE(); i++) {
                if (gameBoard.getDiskColor(x + i, y - i) == colorPlayer) {
                    break;
                }
                currentValueDisk += findDiskValue(x + i, y - i);
            }
        }
        if (leftLineCheck(x, y, colorPlayer)) {
            for (int i = 1; i < gameBoard.getSIZE(); i++) {
                if (gameBoard.getDiskColor(x, y - i) == colorPlayer) {
                    break;
                }
                currentValueDisk += findDiskValue(x, y - i);
            }
        }
        if (leftUpperDiagonalCheck(x, y, colorPlayer)) {
            for (int i = 1; i < gameBoard.getSIZE(); i++) {
                if (gameBoard.getDiskColor(x - i, y - i) == colorPlayer) {
                    break;
                }
                currentValueDisk += findDiskValue(x - i, y - i);
            }
        }
        return currentValueDisk;
    }

    /**
     * Компьютер легкого уровня сложности размещает фишку
     */
    public void easyComputerPlaceDisk() {
        Color color = Color.WHITE;
        List<Point> pointList = getUpdatePointList(color);
        Point maxValuePoint = pointList.get(0);
        double maxValueDisk = 0;
        for (Point point : pointList) {
            double currentDiskValue = findMoveValue(point.x, point.y, color);
            if (currentDiskValue > maxValueDisk) {
                maxValueDisk = currentDiskValue;
                maxValuePoint = point;
            }
        }
        placeDisk(maxValuePoint, Color.WHITE);
    }

    /**
     * Компьютер сложного уровня сложности размещает фишку
     */
    public void hardComputerPlaceDisk(GameBoardSaver saver) {
        List<Point> pointList = getUpdatePointList(Color.WHITE);
        Point maxValuePoint = pointList.get(0);
        double maxValueDisk = -100;
        for (Point point : pointList) {
            double currentDiskValue = findMoveValue(point.x, point.y, Color.WHITE);
            saver.makeSave(gameBoard);
            placeDisk(point, Color.WHITE);
            // Найти самый худший вариант для нас 
            List<Point> conditionalPointList = getUpdatePointList(Color.BLACK);
            double conditionalMaxValueDisk = 0;
            for (Point conditionalPoint : conditionalPointList) {
                double conditionalCurrentDiskValue
                        = findMoveValue(conditionalPoint.x, conditionalPoint.y, Color.BLACK);
                if (conditionalCurrentDiskValue > conditionalMaxValueDisk) {
                    conditionalMaxValueDisk = conditionalCurrentDiskValue;
                }
            }
            currentDiskValue -= conditionalMaxValueDisk;
            copyFromOtherGameBoard(saver.loadSaveAndRemove());
            if (currentDiskValue > maxValueDisk) {
                maxValueDisk = currentDiskValue;
                maxValuePoint = point;
            }
        }
        placeDisk(maxValuePoint, Color.WHITE);
    }

    /**
     * Копирует состояние игровой доски
     *
     * @param otherGameBoard игровая доска, состояние которой надо скопировать
     */
    public void copyFromOtherGameBoard(GameBoard otherGameBoard) {
        for (int i = 0; i < otherGameBoard.getSIZE(); i++) {
            for (int j = 0; j < otherGameBoard.getSIZE(); j++) {
                gameBoard.addNewDisk(i, j, otherGameBoard.getDiskColor(i, j));
            }
        }
    }
}
