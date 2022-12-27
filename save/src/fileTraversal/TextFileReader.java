//package fileTraversal;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Stream;
//
//public class TextFileReader {
//    private final String rootDirectory;
//
//    public TextFileReader(String rootDirectory) {
//        this.rootDirectory = rootDirectory;
//    }
//
//    public List<Path> getPathList() throws IOException {
//        List<Path> pathList = new ArrayList<>();
//        try (Stream<Path> paths = Files.walk(Paths.get(rootDirectory))) {
//            paths
//                    .filter(Files::isRegularFile)
//                    .forEach(pathList::add);
//        }
//        return pathList;
//    }
//
//    private boolean tryFindVertexInGraph(Vertex startVertex, Path findPath, List<Vertex> vertexList) {
//        for (Vertex finishVertex : vertexList) {
//            if (findPath.equals(finishVertex.getFilePathWithoutRoot())) {
//                startVertex.addNeighbor(finishVertex);
//                finishVertex.addOneParent();
//                return true;
//            }
//        }
//        return false;
//    }
//
//    // TODO: подумать про пустой line
//    private Path parsePath(String line) {
//        String result = line.replaceAll("[‘’]", "").split(" ", 2)[1];
//        return Path.of(result);
//    }
//
//    public void setEdgesFromFiles(List<Vertex> vertexList) throws IOException {
//        for (Vertex startVertex : vertexList) {
//            try (BufferedReader bufferedReader =
//                         new BufferedReader(new FileReader(startVertex.getFilePath().toFile()))) {
//                String requirePath;
//                while ((requirePath = bufferedReader.readLine()) != null) {
//                    if (requirePath.trim().startsWith("require")) {
//                        if (!tryFindVertexInGraph(startVertex, parsePath(requirePath), vertexList)) {
//                            throw new FileNotFoundException("File not found");
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
