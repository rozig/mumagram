<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:empty>
	<jsp:attribute name="pagetitle">
	<title>Mumagram - Sign up</title>
	</jsp:attribute>

	<jsp:body>
      <div class="empty-body">
        <div class="empty-content">
          <h1 class="logo-text">Mumagram</h1>

          <div class="empty-form">
            <form method="post" action="/mumagram/register"
						enctype="multipart/form-data">
              <div class="uk-margin empty-field">
                <div class="uk-inline">
                  <span class="uk-form-icon" uk-icon="icon: user"></span>
                  <input class="uk-input" name="firstname"
									placeholder="Firstname" type="text">
                </div>
              </div>

              <div class="uk-margin empty-field">
                <div class="uk-inline">
                  <span class="uk-form-icon" uk-icon="icon: user"></span>
				<input class="uk-input" type="text" name="lastname"
									placeholder="Lastname">
                </div>
              </div>

              <div class="uk-margin empty-field">
                <div class="uk-inline">
                  <span class="uk-form-icon" uk-icon="icon: user"></span>
                  <input class="uk-input" type="text" name="username"
									placeholder="Username">
                </div>
              </div>

              <div class="uk-margin empty-field">
                <div class="uk-inline">
                  <span class="uk-form-icon" uk-icon="icon: lock"></span>
                  <input class="uk-input" type="text" name="email"
									placeholder="Email address">
                </div>
              </div>
              
              <div class="uk-margin empty-field">
              	<div class="uk-inline">
              	   <span class="uk-form-icon" uk-icon="icon: lock"></span>
              		<input class="uk-input" type="password"
									name="password" placeholder="Password" />
              	</div>
 			  </div>
 			  <div class="uk-margin empty-field">
              	<div class="uk-inline">
              	   <span class="uk-form-icon" uk-icon="icon: lock"></span>
              		<input class="uk-input" type="password"
									name="password-repeat" placeholder="Password repeat" />
              	</div>
 			  </div>
 			  <div class="uk-margin empty-field">
              	<div class="uk-inline">
              	   <span class="uk-form-icon" uk-icon="icon: lock"></span>
              		<input class="uk-input" type="file"
									name="profile-picture" placeholder="Profile picture" />
              	</div>
 			  </div>
              <div class="uk-margin empty-field">
                <div class="uk-inline">
                  <button
									class="uk-button uk-button-primary uk-width-1-1 uk-margin-small-bottom">Sign Up</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
	<div>${ errorMessage }</div>

      <div class="empty-content">
        <p class="empty-info">Have an account? <a href="#">Log in</a>
			</p>
      </div>
	</jsp:body>
</t:empty>
