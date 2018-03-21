package mumagram.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import mumagram.model.Post;
import mumagram.model.User;
import mumagram.util.DbUtil;

public class PostRepository {
	private Connection connection;
	
	public PostRepository() {
		connection = DbUtil.getConnection();
	}
	
	public Post findOneById(int id) {
		Post post = new Post();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT id, picture, description, user_id, created_date, updated_date FROM post WHERE id = ?"
			);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				User user = getUser(rs.getInt("user_id"));
				post.setId(rs.getInt("id"));
				post.setPicture(rs.getString("picture"));
				post.setDescription(rs.getString("description"));
				post.setUser(user);
				post.setCreatedDate(LocalDate.now());
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return post;
	}
	
	private User getUser(int id) {
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT id, firstname, lastname, email, username, password, salt, bio, profile_picture, is_private FROM user WHERE id = ?"
			);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setSalt(rs.getString("salt"));
				user.setBio(rs.getString("bio"));
				user.setProfilePicture(rs.getString("profile_picture"));
				user.setPrivate(rs.getBoolean("is_private"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
