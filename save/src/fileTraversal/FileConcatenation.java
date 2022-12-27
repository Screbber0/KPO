package fileTraversal;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileConcatenation {

    /**
     * Метод, который запускает конкатенацию всех файлов в один
     * @throws IOException Ошибки при чтении и записи
     * @throws CycleException Ошибка при наоличии циклической зависимости
     */
    public void doConcatenation() throws IOException, CycleException {
        Scanner scanner = new Scanner(System.in);
        Path rootPath = Paths.get(scanner.nextLine().trim());
        File file = new File(rootPath.toUri());
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Некорректный путь до директории");
        }
        List<Path> pathList = getPathList(rootPath);
        Graph graph = new Graph();
        for (Path path : pathList) {
            graph.addVertex(new Vertex(path));
        }
        initAllEdgesFromFiles(graph, rootPath);
        if (graph.isHasCycle()) {
            throw new CycleException("Существует циклическая зависимость");
        }
        List<Vertex> orderGraph = graph.getOrderVertices();
        for (Vertex orderVertex : orderGraph) {
            System.out.println(orderVertex.getFilePath());
        }
        writeUnionTextFile(graph, "output.txt");
    }

    /**
     * Пишет объединенный текст в указанный файл
     * @param graph Граф
     * @param fileName имя файла
     * @throws IOException Исключение при чтении и записи в файлы
     */
    private void writeUnionTextFile(Graph graph, String fileName) throws IOException {
        List<Vertex> orderGraph = graph.getOrderVertices();
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            raf.setLength(0);
        }
        for (Vertex vertex : orderGraph) {
            try (BufferedReader bufferedReader =
                         new BufferedReader(new FileReader(vertex.getFilePath().toFile()));
                 BufferedWriter bufferedWriter =
                         new BufferedWriter(new FileWriter(fileName, true))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    bufferedWriter.append(line);
                    bufferedWriter.newLine();
                }
            }
        }
    }

    /**
     * Получает все возможные директории по указанному пути
     * @param rootDirectory корневая директория
     * @return список путей к директориям
     */
    private List<Path> getPathList(Path rootDirectory) throws IOException {
        List<Path> pathList = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(rootDirectory)) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(pathList::add);
        }
        return pathList;
    }

    /**
     * Метод, который парсит Path
     * @param path Путь, который необходимо распарсить
     * @return Путь после парсинга
     * @throws FileNotFoundException Если длинна меньше или равно 1 (Только require, следовательно
     * найти файл по такому путь невозможно)
     */
    private Path parsePath(String path) throws FileNotFoundException {
        if (path.split(" ").length <= 1) {
            throw new FileNotFoundException("Такой файл найти невозможно");
        }
        String result = path.replaceAll("[‘’]", "").split(" ", 2)[1];
        return Path.of(result);
    }

    /**
     * Инициализирует все ребра в графе исходя из require в файлах
     * @param graph Граф
     * @param absolutePath абсолютный путь до грфаф
     * @throws IOException если require не удалось найти файл по указанному пути
     */
    private void initAllEdgesFromFiles(Graph graph, Path absolutePath) throws IOException {
        List<Vertex> vertexList = graph.getVertices();
        for (Vertex startVertex : vertexList) {
            try (BufferedReader bufferedReader =
                         new BufferedReader(new FileReader(startVertex.getFilePath().toFile()))) {
                String requirePath;
                while ((requirePath = bufferedReader.readLine()) != null) {
                    if (requirePath.trim().toLowerCase().startsWith("require")) {
                        Path findPath = absolutePath.resolve(parsePath(requirePath));
                        if (!graph.tryAddEdgeInGraph(startVertex, findPath)) {
                            throw new FileNotFoundException("File not found");
                        }
                    }
                }
            }
        }
    }
}
