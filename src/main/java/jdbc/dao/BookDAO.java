package jdbc.dao;
import java.util.List;

public interface BookDAO {
    void save(Book book);
    Book getById(int id);
    List<Book> getAll();
    void update(Book book);
    void delete(int id);
}
