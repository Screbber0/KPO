package fileTraversal;

/**
 * Класс для исключений вызываемых из-за зацикленности графа
 */
public class CycleException extends Exception {
    public CycleException(String message) {
        super(message);
    }
}
