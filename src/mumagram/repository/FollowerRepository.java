package mumagram.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mumagram.model.Follower;
import mumagram.model.User;
import mumagram.util.DbUtil;

public class FollowerRepository {
	private Connection connection;
	private UserRepository userRepository;

	public FollowerRepository() {
		connection = DbUtil.getConnection();
		userRepository = new UserRepository();
	}

	public Follower findOneById(int id) {
		Follower follower = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT id, user_id, follower_id, status, created_date, updated_date FROM user_followers WHERE id = ?"
			);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				User user = userRepository.findOneById(rs.getInt("user_id"));
				User followerUser = userRepository.findOneById(rs.getInt("follower_id"));
				follower = new Follower();
				follower.setId(rs.getInt("id"));
				follower.setUser(user);
				follower.setFollower(followerUser);
				follower.setStatus(rs.getString("status"));
				follower.setCreatedDate(rs.getDate("created_date").toLocalDate());
				follower.setUpdatedDate(rs.getDate("updated_date").toLocalDate());
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return follower;
	}
	
	public Follower isFollower(User user, User follower) {
		Follower result = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT id, user_id, follower_id, status, created_date, updated_date FROM user_followers WHERE user_id = ? AND follower_id = ?"
			);
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setInt(2, follower.getId());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				User followingUser = userRepository.findOneById(rs.getInt("user_id"));
				User followerUser = userRepository.findOneById(rs.getInt("follower_id"));

				result = new Follower();
				result.setId(rs.getInt("id"));
				result.setUser(followingUser);
				result.setFollower(followerUser);
				result.setStatus(rs.getString("status"));
				result.setCreatedDate(rs.getDate("created_date").toLocalDate());
				result.setUpdatedDate(rs.getDate("updated_date").toLocalDate());
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean save(Follower follower) {
		boolean result = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
				"INSERT INTO user_followers(user_id, follower_id, status, created_date)"
				+ "VALUES"
				+ "(?, ?, ?, ?)"
			);
			preparedStatement.setInt(1, follower.getUser().getId());
			preparedStatement.setInt(2, follower.getFollower().getId());
			preparedStatement.setString(3, follower.getStatus());
			preparedStatement.setDate(4, Date.valueOf(follower.getCreatedDate()));
			result = preparedStatement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean update(Follower follower) {
		boolean result = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
				"UPDATE user_followers SET user_id = ?, follower_id = ?, status = ?, updated_date = ?"
				+ "WHERE id = ?"
			);
			preparedStatement.setInt(1, follower.getUser().getId());
			preparedStatement.setInt(2, follower.getFollower().getId());
			preparedStatement.setString(3, follower.getStatus());
			preparedStatement.setDate(4, Date.valueOf(follower.getUpdatedDate()));
			preparedStatement.setInt(5, follower.getId());
			result = preparedStatement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean delete(Follower follower) {
		boolean result = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
				"DELETE FROM user_followers WHERE id = ?"
			);
			preparedStatement.setInt(1, follower.getId());
			result = preparedStatement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
