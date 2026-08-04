package jdbc.dao;

import models.Book;
import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    //Driver: Seokyoung, Navigator: Thien
    @Override
    public void save(Book book) {

        String insertQuery = "INSERT INTO books (title, author) VALUES (?, ?)";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();) {

            PreparedStatement ps = connection.prepareStatement(insertQuery);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Driver: Seokyoung, Navigator: Thien
    @Override
    public Book getById(int id) {

        String selectQuery = "SELECT * FROM books WHERE id = ?";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();) {

            PreparedStatement ps = connection.prepareStatement(selectQuery);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                Book book = new Book(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"));

                return book;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Driver: Thien, Navigator: Seokyoung
    @Override
    public List<Book> getAll() {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();){
            // code live
            String selectQuery = "select * from books";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            //when use select return resultSet
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Book>books = new ArrayList<>();
            while(resultSet.next()){
                // we make a book out of the first row and if there another row we make a book and add to the list until no row left
                Book book = new Book(resultSet.getInt("id"),resultSet.getString("title"),resultSet.getString("author"));
                books.add(book);

            }
            return books;


        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    //Driver: Thien, Navigator: Seokyoung
    @Override
    public void update(Book book) {

        try(Connection connection = ConnectionFactory.getInstance().getConnection();){
            String updateQurey = "UPDATE books SET title = ?, author = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(updateQurey);
            ps.setString(1, book.getTitle());
            ps.setString(2,book.getAuthor());
            ps.setInt(3,book.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Driver: Seokyoung, Navigator: Thien
    @Override
    public void delete(int id) {

        try(Connection connection = ConnectionFactory.getInstance().getConnection();){
            String deleteQuery = "DELETE FROM books WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(deleteQuery);
            ps.setInt(1,id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
