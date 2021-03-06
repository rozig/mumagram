package mumagram.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mumagram.model.Comment;
import mumagram.model.Post;
import mumagram.model.User;
import mumagram.util.DbUtil;

public class CommentRepository {
	private PostRepository postRepository;
	private UserRepository userRepository;

	public CommentRepository() {
		postRepository = new PostRepository();
		userRepository = new UserRepository();
	}
	
	/*
	 * Receives an int value as parameter and read comment data from database. Then converting the data to the model object
	 */
	public Comment findOneById(int id) {
		Comment comment = null;
		try(Connection connection = DbUtil.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT id, comment, user_id, post_id, created_date, updated_date FROM comment WHERE id = ?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				Post post = postRepository.findOneById(rs.getInt("post_id"));
				User user = userRepository.findOneById(rs.getInt("user_id"));
				
				comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setComment(rs.getString("comment"));
				comment.setPost(post);
				comment.setUser(user);
				comment.setCreatedDate(rs.getDate("created_date").toLocalDate());
				comment.setUpdatedDate(rs.getDate("updated_date").toLocalDate());
			}

			rs.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comment;
	}

	/*
	 * Receives an Post object as parameter and read comment list from database. Then converting the list to the model object list
	 */
	public List<Comment> getCommentsByPost(Post post) {
		List<Comment> comments = new ArrayList<Comment>();

		try(Connection connection = DbUtil.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT id,comment,post_id,user_id,created_date,updated_date FROM comment WHERE post_id = ?");
			preparedStatement.setInt(1, post.getId());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Post postObject = postRepository.findOneById(rs.getInt("post_id"));
				User userObject = userRepository.findOneById(rs.getInt("user_id"));

				Comment comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setPost(postObject);
				comment.setUser(userObject);
				comment.setComment(rs.getString("comment"));
				comment.setCreatedDate(rs.getDate("created_date").toLocalDate());
				if(rs.getDate("updated_date")!= null) {
					comment.setUpdatedDate(rs.getDate("updated_date").toLocalDate());
				}
				comments.add(comment);
			}

			rs.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comments;
	}

	/*
	 * Receives comment object as parameter and insert comment data to the database table.
	 */
	public boolean save(Comment comment) {
		boolean result = false;
		try(Connection connection = DbUtil.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO comment(comment, post_id, user_id, created_date)"
							+ "VALUES" + "(?, ?, ?, ?)");
			preparedStatement.setString(1, comment.getComment());
			preparedStatement.setInt(2, comment.getPost().getId());
			preparedStatement.setInt(3, comment.getUser().getId());
			preparedStatement.setDate(4, Date.valueOf(comment.getCreatedDate()));
			result = preparedStatement.execute();

			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * Receives comment object as parameter and update comment data in the database table.
	 */
	public boolean update(Comment comment) {
		boolean result = false;
		try(Connection connection = DbUtil.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(
				"UPDATE comment SET comment = ?, post_id = ?, user_id = ?, updated_date = ?"
				+ "WHERE id = ?"
			);
			preparedStatement.setString(1, comment.getComment());
			preparedStatement.setInt(2, comment.getPost().getId());
			preparedStatement.setInt(3, comment.getUser().getId());
			preparedStatement.setDate(4, Date.valueOf(comment.getUpdatedDate()));
			preparedStatement.setInt(5, comment.getId());
			result = preparedStatement.execute();

			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * Receives comment object as parameter and delete comment data from the database table.
	 */
	public boolean delete(Comment comment) {
		boolean result = false;
		try(Connection connection = DbUtil.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(
				"DELETE FROM comment WHERE id = ?"
			);
			preparedStatement.setInt(1, comment.getId());
			result = preparedStatement.execute();

			preparedStatement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
