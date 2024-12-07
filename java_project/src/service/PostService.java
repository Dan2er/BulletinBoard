package service;

import database.DatabaseConnection;
import model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostService {

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        String query = "SELECT * FROM Post";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getInt("post_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("created_at"),
                        resultSet.getTimestamp("updated_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public boolean addPost(int userId, String title, String content) {
        String query = "INSERT INTO Post (user_id, title, content) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, content);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePost(int postId, String title, String content) {
        String query = "UPDATE Post SET title = ?, content = ? WHERE post_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);
            preparedStatement.setInt(3, postId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePost(int postId) {
        String query = "DELETE FROM Post WHERE post_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, postId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
