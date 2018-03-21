package mumagram.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import mumagram.model.Post;
import mumagram.model.User;
import mumagram.util.DbUtil;

public class PostRepository {
	private UserRepository userRepository;

	public PostRepository() {
		userRepository = new UserRepository();
	}

	public Post findOneById(int id) {
		Post post = new Post();
		try(Connection connection = DbUtil.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT id, picture, description, filter, user_id, created_date, updated_date FROM post WHERE id = ?"
			);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				User user = userRepository.findOneById(rs.getInt("user_id"));
				post.setId(rs.getInt("id"));
				post.setPicture(rs.getString("picture"));
				post.setDescription(rs.getString("description"));
				post.setFilter(rs.getString("filter"));
				post.setCreatedDate(LocalDate.parse(rs.getString("created_date")));
				post.setUser(user);
			}

			rs.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return post;
	}
	
	public List<Post> getPostsByUser(User user) {
		List<Post> posts = new ArrayList<Post>();

		try(Connection connection = DbUtil.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT id,picture,description,user_id,created_date, updated_date FROM post WHERE user_id = ? ORDER BY created_date DESC ,id DESC LIMIT 9");
			preparedStatement.setInt(1, user.getId());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Post post = new Post();
				post.setId(rs.getInt("id"));
				post.setPicture(rs.getString("picture"));
				post.setDescription(rs.getString("description"));
				post.setUser(user);
				post.setCreatedDate(rs.getDate("created_date").toLocalDate());
				if(rs.getDate("updated_date")!= null) {
					post.setUpdatedDate(rs.getDate("updated_date").toLocalDate());
				}
				posts.add(post);
			}

			rs.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return posts;
	}
	
	public int getLikeCount(Post post) {
		int result = 0;
		
		try(Connection connection = DbUtil.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT count(1) AS `count` FROM post p INNER JOIN like l ON p.id = l.post_id WHERE p.id = ?");
			preparedStatement.setInt(1, post.getId());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
			}

			rs.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int getCommentCount(Post post) {
		int result = 0;
		
		try(Connection connection = DbUtil.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT count(1) AS `count` FROM post p INNER JOIN comment c ON p.id = c.post_id WHERE p.id = ?");
			preparedStatement.setInt(1, post.getId());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
			}

			rs.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean save(Post post) {
		boolean result = false;
		try(Connection connection = DbUtil.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(
				"INSERT INTO `post` (`picture`,`description`,`filter`,`user_id`,`created_date`)"
				+ "VALUES"
				+ "(?, ?, ?, ?, ?)"
			);
			preparedStatement.setString(1, post.getPicture());
			preparedStatement.setString(2, post.getDescription());
			preparedStatement.setString(3, post.getFilter());
			preparedStatement.setInt(4, post.getUser().getId());
			preparedStatement.setDate(5, Date.valueOf(post.getCreatedDate()));
			result = preparedStatement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
