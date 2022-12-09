package homework;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        FileIterator fileIterator = new FileIterator("input.txt");
        System.out.println(fileIterator.next());
        System.out.println(fileIterator.next());
        System.out.println(fileIterator.next());
        // Scanner scanner = new Scanner(file, StandardCharsets.UTF_8);
    }
}