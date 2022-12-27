package fileTraversal;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private final Path filePath;

    /**
     * Была ли вершина уже посешена на данном рекурсивном обходе
     */
    private boolean isBeingVisited;

    /**
     * Была ли вершина посещена в принципе
     */
    private boolean isVisited;
    private final List<Vertex> adjacencyList;
    private int countParents = 0;

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
     * @return
     */
    public List<Vertex> getAdjacencyList() {
        return adjacencyList;
    }

    /**
     * Устанавливаем были ли эта вершина была посещена
     */
    public void setIsBeingVisited(boolean bool) {
        isBeingVisited = bool;
    }

    public boolean isBeingVisited() {
        return isBeingVisited;
    }

    public boolean isVisited() {
        return isVisited;
    }

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
     * @return
     */
    public int getCountParents() {
        return countParents;
    }

}
