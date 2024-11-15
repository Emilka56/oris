import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//
public class MainRepository {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "5432";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        UserRepository userRepository = new UserRepositoryJdbsImpl(connection);


        User user1 = new User("rezeda", "shayahmetova", 19, "Honda", "89228190467", "A012FF");
        User user2 = new User("roman", "loshkarev", 21, "Honda", "89226780467", "B089FF");
        User user3 = new User("lenya", "loshkarev", 21, "Hyundai", "89226678467", "H066FF");
        User user4 = new User("emilka", "ivanova", 22, "Nissan", "89222860469", "A079UF");
        User user5 = new User("ludmila", "loshova", 25, "Nissan", "89229762967", "Y789KF");
//
        List<User> list = new ArrayList<>();
        list.add(user4);
        list.add(user5);

        userRepository.save(2, list);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        System.out.println(userRepository.findByCarNumber("Y789KF").get().getFirstName());
//        userRepository.removeByCarMake("Nissan");
        userRepository.updatePhoneNumber(user1, "89228190678");

        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);

//        userRepository.remove(user1);

//        userRepository.removeById(4l);

//        userRepository.update(user2, user3);

//        List<User> users = userRepository.findAll();
//        users.forEach(user -> System.out.println(user.getFirstName()));


    }
}
