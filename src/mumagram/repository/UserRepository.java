package mumagram.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mumagram.model.User;
import mumagram.util.DbUtil;

public class UserRepository {
	private Connection connection;

	public UserRepository() {
		connection = DbUtil.getConnection();
	}

	public User findOneById(int id) {
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT id, firstname, lastname, email, username, password, hash, bio, profile_picture, is_private FROM user WHERE id = ?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setHash(rs.getString("hash"));
				user.setBio(rs.getString("bio"));
				user.setProfilePicture(rs.getString("profile_picture"));
				user.setPrivate(rs.getBoolean("is_private"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
