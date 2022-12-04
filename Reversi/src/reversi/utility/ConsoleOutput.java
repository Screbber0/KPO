package reversi.utility;

import reversi.model.GameSetting;
import reversi.view.GameBoard;
import reversi.model.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, печатающий информацию об игровом поле в консоль
 */
public class ConsoleOutput {

    private final GameBoard gameBoard;

    public ConsoleOutput(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Метод печатаюший игровую доску на экран
     */
    public void printGameBoard() {
        displayOptionsToPlay(new ArrayList<>());
    }

    /**
     * Метод, печатающий игровую доску и возможные ходы игрока на ней на экран
     * @param pointList возможные варианты хода
     */
    public void printGameBoard(List<Point> pointList) {
        displayOptionsToPlay(pointList);
    }

    /**
     * Проверка на возможность походить на заданную клетку
     * @param pointList лист клеток
     * @param x координата по x
     * @param y координата по y
     * @return true, если сюда похожить можно, иначе false
     */
    private boolean isInPointList(List<Point> pointList, int x, int y) {
        for (int i = 0; i < pointList.size(); i++) {
            if (pointList.get(i).getX() == x && pointList.get(i).getY() == y) {
                if (i + 1 >= 10) {
                    System.out.print(" ");
                    System.out.print(i + 1 + " |");
                } else {
                    System.out.print(" ");
                    System.out.print(i + 1 + " |");
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Метод печатающий игрвую доску на экран со всеми возможеными вариантами хода
     * @param pointList список возможных ходов
     */
    private void displayOptionsToPlay(List<Point> pointList) {
        for (int i = 0; i < gameBoard.getSIZE(); i++) {
            System.out.println("‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒");
            System.out.print("|");
            for (int j = 0; j < gameBoard.getSIZE(); j++) {
                if (isInPointList(pointList, i, j)) {
                    continue;
                }
                if (gameBoard.getDiskColor(i, j) == Color.BLACK) {
                    System.out.print(" ⚫ |");
                }
                if (gameBoard.getDiskColor(i, j) == Color.WHITE) {
                    System.out.print(" ⚪ |");
                }
                if (gameBoard.getDiskColor(i, j) == Color.DEFAULT) {
                    System.out.print(" ‒ |");
                }
            }
            System.out.println();
        }
        System.out.println("‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒‒");
    }

    /**
     * Печатает в консоль игровое поле и сообщение о том что компьютер сделал ход
     */
    public void printComputerTurn() {
        printGameBoard();
        System.out.println("==========================");
        System.out.println("Ход компьютера");
        System.out.println("==========================");
    }

    /**
     * Метод, подводящий итоги при завершение игры. Печатает результаты за игру
     * и возвращает максимальное кол-во фишек игрока
     * @param gameSetting настройки игры
     * @param maxCountDisks текущий максимум фишек
     * @param blackPlayerName имя игрока за черных
     * @param whitePlayerName имя игрока за белых
     * @return новый максисимум, если рекорд удалось побить, иначе старый максимум
     */
    public int findWinner(GameSetting gameSetting,
                                 int maxCountDisks, String blackPlayerName, String whitePlayerName) {
        System.out.println("Игра окончена!");
        if (gameBoard.getNumberOfBlackDisk() >= gameBoard.getNumberOfWhiteDisk()) {
            if (gameBoard.getNumberOfBlackDisk() == gameBoard.getNumberOfWhiteDisk()) {
                System.out.println("Ничья");
            } else {
                System.out.println("Победил " + blackPlayerName + ".");
            }
            System.out.println(blackPlayerName + ": " + gameBoard.getNumberOfBlackDisk());
            System.out.println(whitePlayerName + ": " + gameBoard.getNumberOfWhiteDisk());
            if (gameBoard.getNumberOfBlackDisk() > maxCountDisks) {
                System.out.println("Установлен рекорд среди игроков: " + gameBoard.getNumberOfBlackDisk());
                maxCountDisks = gameBoard.getNumberOfBlackDisk();
            } else {
                System.out.println("Текущий рекорд: " + maxCountDisks);
            }
        }
        if (gameBoard.getNumberOfBlackDisk() < gameBoard.getNumberOfWhiteDisk()) {
            System.out.println("Победил " + whitePlayerName);
            System.out.println(blackPlayerName + ": " + gameBoard.getNumberOfBlackDisk());
            System.out.println(whitePlayerName + ": " + gameBoard.getNumberOfWhiteDisk());
            if (gameSetting == GameSetting.PLAYER && gameBoard.getNumberOfWhiteDisk() > maxCountDisks) {
                System.out.println("Установлен рекорд среди игроков: " + gameBoard.getNumberOfWhiteDisk());
                maxCountDisks = gameBoard.getNumberOfWhiteDisk();
            } else if (gameSetting != GameSetting.PLAYER && gameBoard.getNumberOfBlackDisk() > maxCountDisks) {
                System.out.println("Установлен рекорд среди игроков: " + gameBoard.getNumberOfBlackDisk());
                maxCountDisks = gameBoard.getNumberOfBlackDisk();
            } else {
                System.out.println("Текущий рекорд: " + maxCountDisks);
            }
        }
        return maxCountDisks;
    }
}
