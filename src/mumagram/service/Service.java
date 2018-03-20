package mumagram.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Part;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;


public class Service {
	private static final Random RANDOM = new SecureRandom();

	public String getNextSalt() {
		byte[] salt = new byte[16];
	    RANDOM.nextBytes(salt);
	    StringBuilder sb = new StringBuilder();
        for(int i = 0; i < salt.length; i++) {
            sb.append(Integer.toString((salt[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
	
	public String encodePassword(String password, String salt) {
		final String SERVER_KEY = "8WD2c48373XJ";
		String encodedPassword = null;
		String passwordToEncode = password + salt + SERVER_KEY;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(passwordToEncode.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			encodedPassword = sb.toString();
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encodedPassword;
	}
	
	public boolean checkPassword(String preEncodedPassword, String salt, String plainPassword) {
		return preEncodedPassword.equals(encodePassword(plainPassword, salt));
	}
	
	public String getFileName(Part part) {
	    for(String content: part.getHeader("content-disposition").split(";")) {
	        if(content.trim().startsWith("filename")) {
	            return content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	public String imageUploader(String name, Part profilePicturePart) throws IOException {
		String resultStr = null;
		try {
			String[] filExt = getFileName(profilePicturePart).split("\\.");
			String filename = name + "." + filExt[1];
			String contentType = profilePicturePart.getContentType();
			AWSCredentials credentials = new BasicAWSCredentials(
			  "AKIAIJ5FTHNX4GMTKJJQ", 
			  "cbfkvOKxULn3HBIgCFnSEbv4CUBWyzn5IJnM6N/t"
			);

			AmazonS3 s3client = AmazonS3ClientBuilder.standard()
			  .withCredentials(new AWSStaticCredentialsProvider(credentials))
			  .withRegion(Regions.US_EAST_2).build();
			
			List<Bucket> buckets = s3client.listBuckets();
			
			ObjectMetadata meta = new ObjectMetadata();
			
			byte[] bytes = IOUtils.toByteArray(profilePicturePart.getInputStream());
			meta.setContentLength(bytes.length);
			meta.setContentType(contentType);
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
			
			s3client.putObject(new PutObjectRequest(buckets.get(0).getName(), filename, byteArrayInputStream, meta));
			
			resultStr = String.valueOf(s3client.getUrl(
				buckets.get(0).getName(),
				filename
            ));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return resultStr;
    }
	
	public void sendEmail(String recipient, String subject, String content) {
		String username = "60593ce370128e";
		String password = "02074b42b9b08f";
		
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.host", "smtp.mailtrap.io");
		props.setProperty("mail.smtp.port", "2525");
		props.setProperty("mail.smtp.user", username);
		props.setProperty("mail.smtp.password", password);
		
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("no-reply@mumagram.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(subject);
			message.setContent(content, "text/html");
			Transport.send(message);
		} catch(MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
