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
	private Connection connection;
	private PostRepository postRepository;
	private UserRepository userRepository;

	public CommentRepository() {
		connection = DbUtil.getConnection();
		postRepository = new PostRepository();
		userRepository = new UserRepository();
	}
	public Comment findOneById(int id) {
		Comment comment = null;
		try {
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
		} finally {
			DbUtil.closeConnection();
		}
		return comment;
	}

	public List<Comment> getCommentsByPost(Post post) {
		List<Comment> comments = new ArrayList<Comment>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT id,comment,post_id,user_id,created_date,updated_date FROM comment WHERE post_id = ? ORDER BY created_date DESC,id DESC");
			preparedStatement.setInt(1, post.getId());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Post postObject = postRepository.findOneById(rs.getInt("post_id"));
				User userObject = userRepository.findOneById(rs.getInt("user_id"));

				Comment comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setPost(postObject);
				comment.setUser(userObject);
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
		} finally {
			DbUtil.closeConnection();
		}

		return comments;
	}

	public boolean save(Comment comment) {
		boolean result = false;
		try {
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
		} finally {
			DbUtil.closeConnection();
		}
		return result;
	}

	public boolean update(Comment comment) {
		boolean result = false;
		try {
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
		} finally {
			DbUtil.closeConnection();
		}
		return result;
	}
	
	public boolean delete(Comment comment) {
		boolean result = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
				"DELETE FROM comment WHERE id = ?"
			);
			preparedStatement.setInt(1, comment.getId());
			result = preparedStatement.execute();

			preparedStatement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeConnection();
		}
		return result;
	}
}
