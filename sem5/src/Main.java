import model.User;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        TaskRunner taskRunner = new TaskRunner();
        FileUserReader fileReader = new FileUserReader("input.txt");
        List<User> userList = fileReader.GetAllUsers();
        taskRunner.runAllTasks(userList);
    }
}