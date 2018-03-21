package mumagram.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mumagram.model.User;
import mumagram.util.DbUtil;

public class UserRepository {
	private Connection connection;

	public UserRepository() {
		connection = DbUtil.getConnection();
	}

	public User findOneById(int id) {
		User user = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT id, firstname, lastname, email, username, password, salt, bio, profile_picture, is_private FROM user WHERE id = ?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				user = new User();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User findOneBy(String[] fields) {
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT id, firstname, lastname, email, username, password, salt, bio, profile_picture, is_private FROM user WHERE id = ?");
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User findOneByUsername(String username) {
		User user = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT id, firstname, lastname, email, username, password, salt, bio, profile_picture, is_private FROM user WHERE username = ?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				user = new User();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User findOneByEmail(String email) {
		User user = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT id, firstname, lastname, email, username, password, salt, bio, profile_picture, is_private FROM user WHERE email = ?");
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				user = new User();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User isUserExists(String email, String username) {
		User user = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT id, firstname, lastname, email, username, password, salt, bio, profile_picture, is_private FROM user WHERE email = ? OR username = ?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, username);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				user = new User();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean save(User user) {
		boolean result = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO user(firstname, lastname, email, username, password, salt, bio, profile_picture, is_private, created_date)"
							+ "VALUES" + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, user.getFirstname());
			preparedStatement.setString(2, user.getLastname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getUsername());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getSalt());
			preparedStatement.setString(7, user.getBio());
			preparedStatement.setString(8, user.getProfilePicture());
			preparedStatement.setBoolean(9, user.isPrivate());
			preparedStatement.setDate(10, Date.valueOf(user.getCreatedDate()));
			result = preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean update(User user) {
		boolean result = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE user SET firstname = ?, lastname = ?, email = ?, username = ?, password = ?, salt = ?, bio = ?, profile_picture = ?, is_private = ?, updated_date = ?)"
							+ "WHERE id = ?");
			preparedStatement.setString(1, user.getFirstname());
			preparedStatement.setString(2, user.getLastname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getUsername());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getSalt());
			preparedStatement.setString(7, user.getBio());
			preparedStatement.setString(8, user.getProfilePicture());
			preparedStatement.setBoolean(9, user.isPrivate());
			preparedStatement.setDate(10, Date.valueOf(user.getUpdatedDate()));
			preparedStatement.setInt(11, user.getId());
			result = preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<User> search(String query) {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT id, firstname, lastname, email, username, profile_picture FROM user WHERE username LIKE ? OR firstname LIKE ? OR lastname LIKE ?");
			preparedStatement.setString(1, "%" + query + "%");
			preparedStatement.setString(2, "%" + query + "%");
			preparedStatement.setString(3, "%" + query + "%");
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(null);
				user.setSalt(null);
				user.setBio(null);
				user.setProfilePicture(rs.getString("profile_picture"));
				user.setPrivate(false);

				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public int countPost(int userid) {
		int count = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT count(1) as count FROM post WHERE user_id = ?");
			preparedStatement.setInt(1, userid);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int countFollower(int userid) {
		int count = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT count(1) as count FROM user_followers WHERE user_id = ?");
			preparedStatement.setInt(1, userid);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int countFollowing(int userid) {
		int count = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT count(1) as count FROM user_followers WHERE follower_id = ?");
			preparedStatement.setInt(1, userid);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}


}
