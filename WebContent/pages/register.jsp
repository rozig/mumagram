<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/mumagram/register" enctype="multipart/form-data">
		<input type="text" name="firstname" placeholder="Firstname">
		<input type="text" name="lastname" placeholder="Lastname">
		<input type="text" name="username" placeholder="Username">
		<input type="text" name="email" placeholder="Email address">
		<input type="password" name="password" placeholder="Password">
		<input type="password" name="password-repeat" placeholder="Password repeat">
		<input type="file" name="profile-picture" placeholder="Profile picture">
		<button type="submit">Register</button>
	</form>
</body>
</html>