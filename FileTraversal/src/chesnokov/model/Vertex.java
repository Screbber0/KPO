package chesnokov.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Вершиной графа является каждый файл
 */
public class Vertex {

    /**
     * Путь до файла
     */
    private final Path filePath;

    /**
     * Была ли вершина уже посешена на данном рекурсивном обходе
     */
    private boolean isBeingVisited;

    /**
     * Была ли вершина посещена в принципе
     */
    private boolean isVisited;

    /**
     * Лист смежных вершин
     */
    private final List<Vertex> adjacencyList;

    /**
     * Кол-во вершин, которые ссылаются на данную вершину
     */
    private int countParents = 0;

    /**
     * Конструктор, в котром вершине устанавливают ее путь
     * @param path Путь до вершины
     */
    public Vertex(Path path) {
        this.filePath = path;
        this.adjacencyList = new ArrayList<>();
    }

    /**
     * Добавялет вершину в список сосодей данной вершины
     * @param adjacent соседняя вершина
     */
    public void addNeighbor(Vertex adjacent) {
        this.adjacencyList.add(adjacent);
    }

    /**
     * Получения пути к файлу
     * @return путь к файлу
     */
    public Path getFilePath() {
        return filePath;
    }

    /**
     * Получения списка соседей
     * @return Список соседей
     */
    public List<Vertex> getAdjacencyList() {
        return adjacencyList;
    }

    /**
     * Устанавливаем были ли эта вершина была посещена в данном рекурсивном обходк
     */
    public void setIsBeingVisited(boolean bool) {
        isBeingVisited = bool;
    }

    /**
     * Возвращает был ли файл посещен в данном рекурсивном проходе
     * @return true - если да, иначе false
     */
    public boolean isBeingVisited() {
        return isBeingVisited;
    }

    /**
     * Возвращает был ли файл посещен в принципе
     * @return true - если был, иначе false
     */
    public boolean isVisited() {
        return isVisited;
    }

    /**
     * Устанавливает был ли файл посещен в принципе
     */
    public void setVisited(boolean bool) {
        isVisited = bool;
    }

    /**
     * Увеличиваем количество родителей этой вершины на 1
     */
    public void addOneParent() {
        ++countParents;
    }

    /**
     * Получаем количество родителей данной вершины
     * @return Кол-во родителей данной вершины
     */
    public int getCountParents() {
        return countParents;
    }
}