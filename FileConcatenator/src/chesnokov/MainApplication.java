package chesnokov;

import chesnokov.exeption.CycleException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainApplication {
    /**
     * Main, который запускает конкатенацию файлов и реагирует на различные ошибки в ходе объединения файлов
     */
    public static void main(String[] args) {
        FileConcatenation fileConcatenation = new FileConcatenation();
        try {
            fileConcatenation.makeConcatenation();
        } catch (FileNotFoundException fn) {
            System.out.println("Некоректно заданный require");
        } catch (CycleException c) {
            System.out.println("Граф построить невозможно, так как существует циклическая зависимость");
        } catch (IOException e) {
            System.out.println("Ошибка при считывание файла");
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный путь до корневой директории");
        }
    }
}