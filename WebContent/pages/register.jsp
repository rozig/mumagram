<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:empty>
	<jsp:attribute name="pagetitle">
	<title>Mumagram - Sign up</title>
	</jsp:attribute>
	
	<jsp:body>
      <div class="empty-body">
        <div class="empty-content">
          <h1 class="logo-text">Mumagram</h1>

          <div class="empty-form">
            <form>
              <div class="uk-margin empty-field">
                <div class="uk-inline">
                  <span class="uk-form-icon" uk-icon="icon: mail"></span>
                  <input class="uk-input" name="email" placeholder="Email" type="email">
                </div>
              </div>

              <div class="uk-margin empty-field">
                <div class="uk-inline">
                  <span class="uk-form-icon" uk-icon="icon: lock"></span>
                  <input class="uk-input" name="fullname" placeholder="Full Name" type="text">
                </div>
              </div>

              <div class="uk-margin empty-field">
                <div class="uk-inline">
                  <span class="uk-form-icon" uk-icon="icon: user"></span>
                  <input class="uk-input" name="username" placeholder="Username" type="text">
                </div>
              </div>

              <div class="uk-margin empty-field">
                <div class="uk-inline">
                  <span class="uk-form-icon" uk-icon="icon: lock"></span>
                  <input class="uk-input" name="password" placeholder="Password" type="password">
                </div>
              </div>

              <div class="uk-margin empty-field">
                <div class="uk-inline">
                  <button class="uk-button uk-button-primary uk-width-1-1 uk-margin-small-bottom">Sign Up</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>

      <div class="empty-content">
        <p class="empty-info">Have an account? <a href="#">Log in</a></p>
      </div>
	</jsp:body>
</t:empty>