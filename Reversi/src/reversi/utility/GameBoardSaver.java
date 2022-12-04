package reversi.utility;

import reversi.view.GameBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, отвечающий за сохранения состоний доски, если игрок захочет отменять свои ходы
 */
public class GameBoardSaver {

    /**
     * Список состояний игровой доски
     */
    private final List<GameBoard> gameBoardList = new ArrayList<>();

    /**
     * Конструктор с начальной позицией игровой доски
     * @param gameBoard игровое поле
     */
    public GameBoardSaver(GameBoard gameBoard) {
        gameBoardList.add(gameBoard.copy());
    }

    /**
     * Проверка на равенство начальной игровой доски
     * @param gameBoard игровая доска, которую необходимо сравнить
     * @return true, если состояние совпадает, иначе false
     */
    private boolean isEqualsInitGameBoard(GameBoard gameBoard) {
        if (!isOnlyInitItem()) {
            return false;
        }
        GameBoard gameBoardInit = gameBoardList.get(0);
        for (int i = 0; i < gameBoardInit.getSIZE(); i++) {
            for (int j = 0; j < gameBoardInit.getSIZE(); j++) {
                if (gameBoardInit.getDiskColor(i, j) != gameBoard.getDiskColor(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Делает сохранение состояния
     * @param gameBoard сотояние игровой доски
     */
    public void makeSave(GameBoard gameBoard) {
        if (isEqualsInitGameBoard(gameBoard)) {
            return;
        }
        gameBoardList.add(gameBoard.copy());
    }

    /**
     * Удаляет последнее состояние
     */
    public void removeLastSave() {
        if (gameBoardList.size() > 1) {
            gameBoardList.remove(gameBoardList.size() - 1);
        }
    }

    /**
     * Возвращает последнее состояние игровой доски, а затем удаляет его
     * @return последнее сосотояние игровой доски
     */
    public GameBoard loadSaveAndRemove() {
        GameBoard gameBoardSave = gameBoardList.get(gameBoardList.size() - 1);
        removeLastSave();
        return gameBoardSave;
    }

    /**
     * Проверка на наличие только начального состояния доски
     * @return true, если всего 1 сотояние, иначе false
     */
    public boolean isOnlyInitItem() {
        return gameBoardList.size() == 1;
    }
}
