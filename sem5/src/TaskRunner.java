import model.User;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskRunner {

    /**
     * Сортировка по возрасту
     * @param userList список пользователей
     * @return отсортированный по возрасту список пользователей
     */
    public List<User> orderByAge(List<User> userList) {
        return userList.stream()
                .sorted(Comparator.comparingInt(User::getAge))
                .toList();
    }

    /**
     * Группировка по id
     * @param userList список пользователей
     * @return Словарь из id - ключ, и список по этому ключу - значение
     */
    public Map<Long, List<User>> groupById(List<User> userList) {
        return userList.stream()
                .collect(Collectors.groupingBy(User::getId));
    }

    /**
     * Находит кол-во национальностей в списке
     * @param userList список пользователей
     * @return кол-во национальностей
     */
    public Integer countNationality(List<User> userList) {
        return userList.stream()
                .collect(Collectors.groupingBy(User::getNationality))
                .size();
    }

    /**
     * Список User-ов старше 10 лет, у которых первый символ firstName не равен M
     * @param userList список пользователей
     */
    public List<User> getSpecialQuery(List<User> userList) {
        return userList.stream()
                .filter(el -> el.getAge() > 10
                        && !el.getFirstName().toLowerCase().startsWith("m"))
                .toList();
    }

    /**
     * Запускает все задачи на выполнение
     * @param userList список пользователей
     */
    public void runAllTasks(List<User> userList) {
        System.out.println("Task1 (Сортировка по возрасту)");
        orderByAge(userList).forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------------");

        System.out.println("Task2 (Группировка по id)");
        var result = groupById(userList);
        for (Map.Entry<Long, List<User>> userById : result.entrySet()) {
            System.out.println("Id: " + userById.getKey());
            for (User user : userById.getValue()) {
                System.out.println(user);
            }
        }
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Task3");
        System.out.println("Кол-во различных национальностей: " + countNationality(userList));
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Task4 (Распечатать User-ов старше 10 лет, у которых первый символ firstName не равен M)");
        getSpecialQuery(userList).forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------------");
    }
}
