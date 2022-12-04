package reversi.view;

import reversi.model.Color;
import reversi.model.Disk;

import java.util.Arrays;

public class GameBoard {

    /**
     * Размер игрового поле
     */
    private final int SIZE = 8;

    /**
     * Игровое поле
     */
    private final Disk[][] gameBoard = new Disk[SIZE][SIZE];

    /**
     * Констрокутор, инициализрующий игровое поле
     */
    public GameBoard() {
        initGameBoard();
    }

    /**
     * Метод, инициализирующий игровое поле и добаление на него 4-х стартовых фишек
     */
    private void initGameBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gameBoard[i][j] = new Disk(Color.DEFAULT);
            }
        }
        addNewDisk(3, 4, Color.BLACK);
        addNewDisk(4, 3, Color.BLACK);
        addNewDisk(3, 3, Color.WHITE);
        addNewDisk(4, 4, Color.WHITE);
    }

    /**
     * Возвращает кол-во черных дисков на поле
     * @return кол-во черных дисков на поле
     */
    public int getNumberOfBlackDisk() {
        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            sum += Arrays.stream(gameBoard[i]).filter(disk -> disk.getColor() == Color.BLACK).count();
        }
        return sum;
    }

    /**
     * Возвращает количество белых дисков на поле
     * @return кол-во белых дисков на поле
     */
    public int getNumberOfWhiteDisk() {
        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            sum += Arrays.stream(gameBoard[i]).filter(disk -> disk.getColor() == Color.WHITE).count();
        }
        return sum;
    }

    /**
     * Возвращает размер игрового поля
     * @return размер поля
     */
    public int getSIZE() {
        return SIZE;
    }

    /**
     * Метод, возвращающий цвет диска по координатам X и Y,
     * если такого диска не существует, то возвращает Color.DEFAULT
     *
     * @param x Координата X
     * @param y Координата Y
     * @return Цвет диска с координатами X, Y или Color.DEFAULT, если такого диска не существует
     */
    public Color getDiskColor(int x, int y) {
        if (x < 0 || y < 0 || x > 7 || y > 7) {
            return Color.DEFAULT;
        }
        return gameBoard[x][y].getColor();
    }

    /**
     * Возвращает фишку с игрового поля по заданным координатам
     * @param x координата x
     * @param y координата y
     * @return фишка
     */
    public Disk getDisk(int x, int y) {
        return gameBoard[x][y];
    }

    /**
     * Метод, добавляющий диск на игрое поле (устанавливающий ему цвет)
     * @param x координата x
     * @param y координата y
     * @param color цвет диска
     */
    public void addNewDisk(int x, int y, Color color) {
        if (x < 0 || y < 0 || x > 7 || y > 7) {
            return;
        }
        gameBoard[x][y].setColor(color);
    }

    /**
     * Проверяет игровое на заполненность
     * @return true, если игровое поле заполнено, иначе false
     */
    public boolean isFull() {
        return getNumberOfBlackDisk() + getNumberOfWhiteDisk() == SIZE * SIZE;
    }

    /**
     * Возвращает копию игрового поля
     * @return игровое поле
     */
    public GameBoard copy() {
        GameBoard gameBoardCopy = new GameBoard();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gameBoardCopy.addNewDisk(i, j, getDiskColor(i, j));
            }
        }
        return gameBoardCopy;
    }
}
