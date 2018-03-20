<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<<<<<<< Updated upstream
<t:empty>
	<jsp:attribute name="pagetitle">
	<title>Mumagram - Login</title>
	</jsp:attribute>
	
	<jsp:body>
		<div class="empty-body">
		  <div class="empty-content">
		    <h1 class="logo-text">Mumagram</h1>
		
		    <div class="empty-form">
		      <form>
		        <div class="uk-margin empty-field">
		          <div class="uk-inline">
		            <span class="uk-form-icon" uk-icon="icon: user"></span>
		            <input class="uk-input" placeholder="Username" type="text">
		          </div>
		        </div>
		
		        <div class="uk-margin empty-field">
		          <div class="uk-inline">
		            <span class="uk-form-icon" uk-icon="icon: lock"></span>
		            <input class="uk-input" placeholder="Password" type="password">
		          </div>
		        </div>
		
		        <div class="uk-margin empty-field">
		          <div class="uk-inline">
		            <button class="uk-button uk-button-primary uk-width-1-1 uk-margin-small-bottom">Login</button>
		          </div>
		        </div>
		      </form>
		    </div>
		  </div>
		</div>
		
		<div class="empty-content">
		  <p class="empty-info">Don't you have an account? <a href="#">Sign up</a></p>
		</div>
	</jsp:body>
</t:empty>
=======
<html>
<head>
<title>Login</title>
</head>
<body>
	<form action="/mumagram/login" method="post">
		<input type="text" name="username" required="required" /> <input
			type="password" name="password" required="required" />
		<button>Login</button>
	</form>
</body>
</html>
>>>>>>> Stashed changes
