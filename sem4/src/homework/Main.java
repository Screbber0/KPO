package homework;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileIterator fileIterator = new FileIterator("input.txt");
        while (fileIterator.hasNext()) {
            System.out.println(fileIterator.next());
        }
    }
}