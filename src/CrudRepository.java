
import java.sql.SQLException;
import java.util.Optional;

import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll() throws SQLException;

    Optional<T> findById(Long id) throws SQLException;
    void save(T entity) throws SQLException;
    void remove(T entity) throws SQLException;
    void removeById(Long id) throws SQLException;
    void update(T entity1, T entity2) throws SQLException;

    void save(int n, List<T> users) throws SQLException;

//
}
