package reversi;

import reversi.model.Color;
import reversi.model.GameSetting;
import reversi.utility.ConsoleInput;
import reversi.utility.ConsoleOutput;
import reversi.utility.GameBoardSaver;
import reversi.utility.GameBoardUpdater;
import reversi.view.GameBoard;

import java.awt.*;
import java.util.List;

/**
 * Класс, обрабатывающий команды пользователя
 */
public class CommandHandler {

    /**
     * Запуск игры
     */
    public static void runGame() {
        int maxCountDisks = 0;
        ConsoleInput consoleInput = new ConsoleInput();
        do {
            GameBoard gameBoard = new GameBoard();
            ConsoleOutput consoleOutput = new ConsoleOutput(gameBoard);
            GameBoardUpdater gameBoardUpdater = new GameBoardUpdater(gameBoard);
            GameBoardSaver gameBoardSaver = new GameBoardSaver(gameBoard);
            Color colorPlayer = Color.BLACK;
            GameSetting gameSetting = consoleInput.chooseGameSetting();
            String blackPlayerName = consoleInput.enterName(Color.BLACK);
            String whitePlayerName;
            if (gameSetting == GameSetting.PLAYER) {
                whitePlayerName = consoleInput.enterName(Color.WHITE);
            } else {
                whitePlayerName = "Компьютер";
            }
            while (!gameBoard.isFull()
                    && gameBoard.getNumberOfBlackDisk() != 0
                    && gameBoard.getNumberOfWhiteDisk() != 0) {
                if (isPat(gameBoardUpdater)) {
                    break;
                }
                List<Point> pointList = gameBoardUpdater.getUpdatePointList(colorPlayer);
                if (pointList.size() == 0) {
                    System.out.println("Нет ходов, переход хода к следющему игроку");
                    colorPlayer = GameBoardUpdater.repaintColor(colorPlayer);
                    continue;
                }
                if (gameSetting == GameSetting.EASY && colorPlayer == Color.WHITE) {
                    consoleOutput.printComputerTurn();
                    gameBoardUpdater.easyComputerPlaceDisk();
                } else if (gameSetting == GameSetting.HARD && colorPlayer == Color.WHITE) {
                    consoleOutput.printComputerTurn();
                    gameBoardUpdater.hardComputerPlaceDisk(gameBoardSaver);
                } else {
                    consoleOutput.printGameBoard(pointList);
                    int updateNumber = consoleInput.chooseCommand(colorPlayer, pointList);
                    if (updateNumber == -1) {
                        if (gameSetting == GameSetting.PLAYER) {
                            if (gameBoardSaver.isOnlyInitItem()) {
                                colorPlayer = GameBoardUpdater.repaintColor(colorPlayer);
                            }
                            gameBoardSaver.removeLastSave();
                            gameBoardUpdater.copyFromOtherGameBoard(gameBoardSaver.loadSaveAndRemove());
                        }
                        if (gameSetting == GameSetting.EASY || gameSetting == GameSetting.HARD) {
                            gameBoardSaver.removeLastSave();
                            gameBoardSaver.removeLastSave();
                            gameBoardUpdater.copyFromOtherGameBoard(gameBoardSaver.loadSaveAndRemove());
                            colorPlayer = GameBoardUpdater.repaintColor(colorPlayer);
                        }
                    } else {
                        gameBoardUpdater.placeDisk(pointList.get(updateNumber - 1), colorPlayer);
                    }
                }
                colorPlayer = GameBoardUpdater.repaintColor(colorPlayer);
                gameBoardSaver.makeSave(gameBoard);
            }
            consoleOutput.printGameBoard();
            maxCountDisks =
                    consoleOutput.findWinner(gameSetting, maxCountDisks, blackPlayerName, whitePlayerName);
        } while (!consoleInput.isGameFinishInput());
    }

    /**
     * Проверка на пат
     *
     * @param gameBoardUpdater объект, который считает возможные ходы для каждого игрока
     * @return true, если пат. Иначе false
     */
    public static boolean isPat(GameBoardUpdater gameBoardUpdater) {
        return gameBoardUpdater.getUpdatePointList(Color.BLACK).isEmpty()
                && gameBoardUpdater.getUpdatePointList(Color.WHITE).isEmpty();
    }
}
