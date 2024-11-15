import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbsImpl implements UserRepository {
    private Connection connection;
    private static final String SQL_SELECT_FROM_DRIVER = "SELECT * FROM driver";
    public UserRepositoryJdbsImpl(Connection connection) {this.connection = connection;}


    @Override
    public List<User> findAllByAge(Integer age) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_FROM_DRIVER + (" WHERE age = " + age));
        List<User> result = new ArrayList<>();

        while (resultSet.next()) { //итерируемый обьект
            User user = new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age"),

                    resultSet.getString("car_make"),
                    resultSet.getString("phone"),
                    resultSet.getString("car_number"));
            result.add(user);
        }
        return result;
    }

    @Override
    public List<User> findAllByCarNumber(Integer number) throws SQLException {
        return List.of();
    }

    @Override
    public List<User> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_FROM_DRIVER);

        List<User> result = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age"),

                    resultSet.getString("car_make"),
                    resultSet.getString("phone"),
                    resultSet.getString("car_number"));
            result.add(user);
        }
        return result;
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_FROM_DRIVER + (" WHERE id = " + id));

        if (resultSet.next()) { //итерируемый обьект
            User user = new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age"),

                    resultSet.getString("car_make"),
                    resultSet.getString("phone"),
                    resultSet.getString("car_number"));
            return Optional.of(user);
        }
        return Optional.empty(); //в случае если таких id не найдется
    }

    @Override
    public void save(User entity) throws SQLException {
        String sqlInsertUser = "insert into driver(name, last_name, age, car_make, phone, car_number) " +
                "values (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
        try{
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getAge());
            preparedStatement.setString(4, entity.getCar_make());
            preparedStatement.setString(5, entity.getPhone());
            preparedStatement.setString(6, entity.getCar_number());

            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println("такой пользователь уже создан");
        }

    }

    @Override
    public void remove(User entity) throws SQLException {
        String sqldeleteUser = "delete from driver " +
                "WHERE name = ? AND last_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqldeleteUser);
        try{
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());

            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println("такой пользователь уже удален");
        }
    }

    @Override
    public void removeById(Long id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from driver " +
                "WHERE id = "+ id);
    }

    public Optional<Long> findId(User entity) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("select * from driver where name = '%s' and last_name = '%s'",
                entity.getFirstName(), entity.getLastName()));
        if (resultSet.next()) { //итерируемый обьект
            return Optional.of(resultSet.getLong("id"));
        }
        return Optional.empty(); //в случае если таких id не найдется
    }
    @Override
    public void update(User entity1, User entity2) throws SQLException {
        String sqlupdateUser = "UPDATE driver " +
                "SET name = ?, last_name = ?, age = ?, car_make = ?, phone = ?, car_number = ?  WHERE id = "+ findId(entity1).get();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlupdateUser);
        try{
            preparedStatement.setString(1, entity2.getFirstName());
            preparedStatement.setString(2, entity2.getLastName());
            preparedStatement.setInt(3, entity2.getAge());

            preparedStatement.setString(4, entity2.getCar_make());
            preparedStatement.setString(5, entity2.getPhone());
            preparedStatement.setString(6, entity2.getCar_number());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("данные не изменились");
        }
    }

    @Override
    public void save(int n, List<User> users) throws SQLException {
        String sqlInsertUser = "insert into driver(name, last_name, age, car_make, phone, car_number) " +
                "values (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
        for (User user : users) {
            try{
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setInt(3, user.getAge());

                preparedStatement.setString(4, user.getCar_make());
                preparedStatement.setString(5, user.getPhone());
                preparedStatement.setString(6, user.getCar_number());

                preparedStatement.executeUpdate();
            }catch (Exception e){
                System.out.println("такой пользователь уже создан");
            }
        }

    }
//
    public Optional<User> findByCarNumber(String number) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("select * from driver where car_number = '%s'",
                number));
        List<User> result = new ArrayList<>();

        if (resultSet.next()) {
            User user = new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age"),

                    resultSet.getString("car_make"),
                    resultSet.getString("phone"),
                    resultSet.getString("car_number"));
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public void removeByCarMake(String name) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate((String.format("delete from driver where car_make = '%s'", name)));
    }

    public void updatePhoneNumber(User user, String phone) throws SQLException {
        String sqlupdateUser = "UPDATE driver " +
                "SET name = ?, last_name = ?, age = ?, car_make = ?, phone = ?, car_number = ?  WHERE id = "+ findId(user).get();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlupdateUser);
        try{
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getAge());

            preparedStatement.setString(4, user.getCar_make());
            preparedStatement.setString(5, phone);
            preparedStatement.setString(6, user.getCar_number());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("данные не изменились");
        }
    }



}
