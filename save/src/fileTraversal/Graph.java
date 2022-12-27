package fileTraversal;

import java.nio.file.Path;
import java.util.*;

public class Graph {
    /**
     * Отвечает за то, был ли изменен граф, если нет, orderVertices менять не надо
     */
    private boolean hasBeenChanged;

    /**
     * Все вершины в графе
     */
    private final List<Vertex> vertices;

    /**
     * Список, где все вершины храняться в упорядоченном виде (но с повторениями)
     */
    private List<Vertex> orderVertices;
    public Graph() {
        vertices = new ArrayList<>();
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
        hasBeenChanged = true;
    }

    /**
     * Рекурсивный метод, проверяющий наличие циклических зависимостей отностельно указанной вершины
     * @param sourceVertex вершина
     * @return true - если существует циклическая зависимость, иначе false
     */
    private boolean hasCycle(Vertex sourceVertex) {
        sourceVertex.setIsBeingVisited(true);

        for (Vertex neighbor : sourceVertex.getAdjacencyList()) {
            if (neighbor.isBeingVisited()) {
                return true;
            } else if (!neighbor.isVisited() && hasCycle(neighbor)) {
                return true;
            }
        }

        sourceVertex.setIsBeingVisited(false);
        sourceVertex.setVisited(true);
        return false;
    }

    /**
     * Метод, который проверяет весь граф на наличие циклических зависимостей
     * @return true - если существует циклическая зависимость, иначе false
     */
    public boolean isHasCycle() {
        for (Vertex sourceVertex : vertices) {
            if (hasCycle(sourceVertex)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ищем все корневые вершины
     * @return Список корневых вершин
     */
    public List<Vertex> findAllRootVertex() {
        List<Vertex> rootList = new ArrayList<>();
        for (Vertex vertex : vertices) {
            if (vertex.getCountParents() == 0) {
                rootList.add(vertex);
            }
        }
        return rootList;
    }

    /**
     * Рекурсивно обходим граф относительно указанной вершины
     * @param vertex вершина с которой начинается обход
     */
    private void recFillOrderVertical(Vertex vertex) {
        orderVertices.add(vertex);
        for (Vertex neighbor : vertex.getAdjacencyList()) {
            recFillOrderVertical(neighbor);
        }
    }

    /**
     * Получаем упорядоченный список вершин, гды выполняется условие:
     * если файл А, зависит от файла В, то файл
     * А находится ниже файла В в списке
     * @return упорядоченный список вершин
     */
    public List<Vertex> getOrderVertices() {
        if (hasBeenChanged) {
            orderVertices = new ArrayList<>();
            List<Vertex> rootVertexList = findAllRootVertex();
            for (Vertex rootVertex : rootVertexList) {
                recFillOrderVertical(rootVertex);
            }
        }
        List<Vertex> resultList = new ArrayList<>();
        Set<Vertex> vertexHashSet = new HashSet<>();
        for (int i = orderVertices.size() - 1; i >= 0; i--) {
            Vertex currentVertex = orderVertices.get(i);
            if (!vertexHashSet.contains(currentVertex)) {
                vertexHashSet.add(currentVertex);
                resultList.add(currentVertex);
            }
        }
        hasBeenChanged = false;
        return resultList;
    }

    /**
     * Получает список вершин
     * @return список вершин
     */
    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Метод, который пытается добавить в граф ребро с заданным путем,
     * если у метода это получается (вершина с указанным путем существует в графе),
     * то добавляет ребро от стартовой вершины и возвращает true, иначе false
     * @param startVertex Вершина которая имееет require на искомую вершину
     * @param findPath Полный путь до искомой вершины
     * @return Если ребро было добавлено, то возвращает true, иначе false
     */
    public boolean tryAddEdgeInGraph(Vertex startVertex, Path findPath) {
        for (Vertex finishVertex : vertices) {
            if (finishVertex.getFilePath().equals(findPath)) {
                startVertex.addNeighbor(finishVertex);
                finishVertex.addOneParent();
                return true;
            }
        }
        return false;
    }
}
