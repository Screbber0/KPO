package reversi.utility;

import reversi.model.Color;
import reversi.model.GameSetting;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

/**
 * Класс, отвечающий за консольный ввод в программе
 */
public class ConsoleInput {

    /**
     * Сканер для ввода данных из консоли
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Выбирает настройки игры
     * @return игровые настройки
     */
    public GameSetting chooseGameSetting() {
        System.out.println("""
                Выберите режим игры, для этого введите команду:
                /p - режим против игрока
                /e - режим против компьютера в легком режиме
                /h - режим игры против компьютера в сложном режиме
                """);
        String commandLine;
        GameSetting gameSetting = null;
        do {
            commandLine = scanner.nextLine().toLowerCase().trim();
            switch (commandLine) {
                case ("/p") -> gameSetting = GameSetting.PLAYER;
                case ("/e") -> gameSetting = GameSetting.EASY;
                case ("/h") -> gameSetting = GameSetting.HARD;
                default -> System.out.println("Выберите корректный режим игры");
            }
        } while (!commandLine.equals("/p")
                && !commandLine.equals("/e")
                && !commandLine.equals("/h"));
        return gameSetting;
    }

    /**
     * Ввод имени игрока
     * @param colorPlayer цвет игрока
     * @return имя игрока
     */
    public String enterName(Color colorPlayer) {
        if (colorPlayer == Color.BLACK) {
            System.out.println("Введите имя игрока");
        }
        if (colorPlayer == Color.WHITE) {
            System.out.println("Введите имя второго игрока");
        }
        return scanner.nextLine();
    }

    /**
     * Считывает команду, введенную пользлвателем
     * @param colorPlayer цвет игрока
     * @param pointList список координат, куда можно сделать ход
     * @return -1, если игрок решил отменить свой ход, иначе 1..pointList.size
     */
    public int chooseCommand(Color colorPlayer, List<Point> pointList) {
        if (colorPlayer == Color.BLACK) {
            System.out.println("Ход черного игрока ⚫");
        }
        if (colorPlayer == Color.WHITE) {
            System.out.println("Ход белого игрока ⚪");
        }
        System.out.println("Введите номер хода из диапазона 1 - " + pointList.size());
        System.out.println("Или наберите команду /z для отмены последнего хода");
        String line;
        int inputOption;
        do {
            try {
                line = scanner.nextLine();
                if (line.toLowerCase().trim().equals("/z")) {
                    return -1;
                }
                inputOption = Integer.parseInt(line);
                if (inputOption > 0 && inputOption <= pointList.size()) {
                    break;
                } else {
                    System.out.println("Число должно быть в диапазоне 1 - " + pointList.size() + " повторите попытку");
                }
            } catch (Exception e) {
                System.out.println("Введите корректную команду");
            }

        } while (true);
        return inputOption;
    }

    /**
     * Хочет ли пользователь завершить игру
     * @return true, если игра завершается, иначе false
     */
    public boolean isGameFinishInput() {
        System.out.println();
        System.out.println("""
                Хотите сыграть еще раз? Наберите:
                /y - для еще одной игры
                /n - для выхода
                """);
        String commandline = scanner.nextLine().toLowerCase().trim();
        while (!commandline.equals("/n") && !commandline.equals("/y")) {
            System.out.println("Выберите корректную команду");
            commandline = scanner.nextLine().toLowerCase().trim();
        }
        return commandline.equals("/n");
    }
}
