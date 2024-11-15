import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User> {
    List<User> findAllByAge(Integer age) throws SQLException;
    List<User> findAllByCarNumber(Integer number) throws SQLException;
    void removeByCarMake(String name) throws SQLException;
    void updatePhoneNumber(User user, String phone) throws SQLException;
    Optional<User> findByCarNumber(String number) throws SQLException;
    //
}
