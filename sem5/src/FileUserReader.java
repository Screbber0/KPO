import model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUserReader {

    private final String path;

    public FileUserReader(String path) {
        this.path = path;
    }

    public List<User> GetAllUsers() throws IOException {
        List<User> userList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                userList.add(parseUser(line));
            }
        }
        return userList;
    }

    private User parseUser(String line) {
        var data = line.split(", ");
        long id = Long.parseLong(data[0]);
        String firstName = data[1];
        String secondName = data[2];
        int age = Integer.parseInt(data[3]);
        String nationality = data[4];
        return new User(id, firstName, secondName, age, nationality);
    }
}
