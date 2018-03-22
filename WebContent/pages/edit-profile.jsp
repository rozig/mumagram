<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:wrapper>
	<jsp:attribute name="pagetitle">
    <title>Mumagram - User profile</title>
  </jsp:attribute>

	<jsp:body>
    
            <div class="edit-profile">
            <div class="empty-content">
            		<div class="uk-grid">
            		<div class="uk-width-1-4">
					        <ul class="uk-tab-right"
							uk-tab="connect: #component-tab-left; animation: uk-animation-fade">
					            <li class="uk-active"><a href="#">Edit Profile</a></li>
					            <li id="cp"><a href="#">Change Password</a></li>
<!-- 					            <li><a href="#">Authorized Applications</a></li>
					            <li><a href="#">Comments</a></li>
					            <li><a href="#">Email and SMS</a></li>
					            <li><a href="#">Manage Contacts</a></li> -->
					        </ul>
            		</div>
   				 <div class="uk-width-3-4 edit-profile-form-divider">
					<ul id="component-tab-left" class="uk-switcher">
		                    <li>
							 <div class="empty-form edit-profile-data">
					            <form method="post" action="${ baseUrl}/profile/edit"
										enctype="multipart/form-data">
								 <div class="uk-margin empty-field">
								 	<div uk-grid>
								 		<div class="uk-width-1-4">
								 			<div class="profile-small-img-wrapper">
									              <div class="profile-small-img-content">									        
									                <span
																class="profile-img-small uk-border-circle">
															   <label for="profile-picture">
															      <input type="file" name="profile-picture"
																	id="profile-picture" style="visibility: hidden;position:absolute;top:0;left:0;" value="${ user.profilePicture }" />
															     <img
																	src="${user.profilePicture != null ? user.profilePicture : baseUrl.concat('/assets/images/profile-new.jpg') }"
																	alt="" />
															   </label>
	
									                </span>

									               
									            </div>
									        </div>
								 		</div>
								 		<div class="uk-width-3-4">
								 		 <h3 class="profile-post-name">
														<strong>${user.username }</strong>
													</h3>
								 		 ${user.firstname } ${user.lastname }
								 		</div>
									  </div>	    
								 </div>
								 <div class="uk-clearfix"></div>		

					              <div class="uk-margin empty-field">
									<div uk-grid>
										<div class="uk-width-1-4">
												<label class="uk-form-label" for="firstname-text">Firstname</label>
										</div>
										<div class="uk-width-3-4">
											<div class="uk-form-controls edit-profile-form-control">					           
								                  <span class="uk-form-icon"
															uk-icon="icon: user"></span>
								                  <input class="uk-input" name="firstname"
															value="${user.firstname }" id="firstname-text"
															type="text">
												<input type="hidden" name="id" value="${user.id}" />
											</div>
										</div>
									</div>

					              </div>

					
					              <div class="uk-margin empty-field">
										<div uk-grid>
            										<div class="uk-width-1-4">
            												<label class="uk-form-label" for="lastname-text">Lastname</label>
            										</div>
            										<div class="uk-width-3-4">
            												 <div
														class="uk-form-controls edit-profile-form-control">	
												                <span class="uk-form-icon"
															uk-icon="icon: user"></span>
																<input class="uk-input" name="lastname"
															value="${user.lastname }" id="lastname-text" type="text">
														</div>
            										</div>
            								</div>
					                

					              </div>
					
					              <div class="uk-margin empty-field">
						              <div uk-grid>
	            										<div class="uk-width-1-4">
	            											<label class="uk-form-label" for="username-text">Username</label>
	            										</div>
	            										<div class="uk-width-3-4">
	            											<div
														class="uk-form-controls edit-profile-form-control">	
	            												<span class="uk-form-icon" uk-icon="icon: user"></span>
						                  					<input class="uk-input" type="text"
															name="username" value="${user.username }">
	            											</div>
	            										</div>
	            						  </div>
					              </div>
					              <div class="uk-margin empty-field">
						              <div uk-grid>
	            										<div class="uk-width-1-4">
	            											<label class="uk-form-label" for="email-text">Email&ensp;&ensp;&ensp;&ensp;</label>
	            										</div>
	            										<div class="uk-width-3-4">
	            											<div
														class="uk-form-controls edit-profile-form-control">	
	            												<span class="uk-form-icon" uk-icon="icon: mail"></span>
						                  					<input class="uk-input" type="text"
															name="email" value="${user.email }">
	            											</div>
	            										</div>
	            						  </div>
					              </div>
					              <div class="uk-margin empty-field">
						              <div uk-grid>
	            										 <div class="uk-width-1-4">
	            											<label class="uk-form-label" for="email-text">Private&ensp;&ensp;</label>
	            										</div>
	            										<div class="uk-width-3-4">
	            											<div
														class="uk-form-controls edit-profile-form-control">	
						                  					<label><input class="uk-checkbox"
															type="checkbox" id="is-private" name="is-private"
															value="checked"
															${user['private'] ? 'checked' : 'unchecked'}>  When your account is private, only people you approve can see your photos  on Mumagram. Your existing followers won't be affected </label>
	            											</div>
	            										</div>
	            						  </div>
					              </div>
					              <div class="uk-margin empty-field">
						              <div uk-grid>
	            										 <div class="uk-width-1-4">
	            											<label class="uk-form-label" for="email-text">Bio &ensp;&ensp;&ensp;&ensp;</label>
	            										</div>
	            										<div class="uk-width-3-4">
	            											<div
														class="uk-form-controls edit-profile-form-control">	
	            												<textarea class="uk-textarea" rows="5"
															name="bio">${user.bio}</textarea>
	            											</div>
	            										</div>
	            						  </div>
					              </div>
					
					          					              
					              <div class="uk-margin empty-field">
					                <div class="uk-inline">
					                <div class="uk-width-1-4">
					                </div>
					               <div class="uk-width-3-4">
					                		<button
														class="uk-button uk-button-primary uk-width-1-1 uk-margin-small-bottom">Submit</button>
					                </div>
					                  
					                </div>
					              </div>
					            		<c:choose>
									    <c:when test="${errorMessage != null }">
						
										 <div class="uk-margin empty-field">			 				
											<div class="uk-alert-danger" uk-alert>
									
									    		<p> ${errorMessage }</p>
										</div>
										</div>
									    </c:when>
									    <c:otherwise>
									    </c:otherwise>
									</c:choose>
									    
									<c:choose>
									    <c:when test="${successMessage != null }">
						
										 <div class="uk-margin empty-field">			 				
										<div class="uk-alert-success" uk-alert>
									
									    <p> ${successMessage }</p>
										</div>
										</div>
									    </c:when>
									    <c:otherwise>
									    </c:otherwise>
								    </c:choose>
								</form>				          
						</div>
		              </li>
		              <li>
		                    <div class="empty-form edit-profile-data">
					       
								 <div class="uk-margin empty-field">
								 	<div uk-grid>
								 		<div class="uk-width-1-4">
								 			<div class="profile-small-img-wrapper">
									              <div class="profile-small-img-content">
									              
									                <span
															class="profile-img-small uk-border-circle"><img
															src="${user.profilePicture != null  ?  user.profilePicture : baseUrl.concat('/assets/images/profile-new.jpg') }" alt="" />
															
									                </span>

									               
									            </div>
									        </div>
								 		</div>
								 		<div class="uk-width-3-4">
								 		 <h3 class="profile-post-name">
														<strong>${user.username }</strong>
													</h3>
								 		${user.firstname } ${user.lastname }
								 		</div>
									  </div>
								 </div>
								 <div class="uk-clearfix"></div>		

				
					              <div class="uk-margin empty-field">
						              <div uk-grid>
	            										<div class="uk-width-1-4">
	            											<label class="uk-form-label" for="oldpassword">Old password</label>
	            										</div>
	            										<div class="uk-width-3-4">
	            											<div
													class="uk-form-controls edit-profile-form-control">	
	            												<span class="uk-form-icon" uk-icon="icon: lock"></span>
						                  					<input class="uk-input" type="password"
														id="oldpassword" name="oldpassword" required>
															<input type="hidden" name="id" id="pass-user-id"
														value="${user.id}" />
	            											</div>
	            										</div>
	            						  </div>
					              </div>
					              <div class="uk-margin empty-field">
						              <div uk-grid>
	            										<div class="uk-width-1-4">
	            											<label class="uk-form-label" for="newpassword">New password</label>
	            										</div>
	            										<div class="uk-width-3-4">
	            											<div
													class="uk-form-controls edit-profile-form-control">	
	            												<span class="uk-form-icon" uk-icon="icon: lock"></span>
						                  					<input class="uk-input" type="password"
														id="newpassword" name="newpassword" required>
	            											</div>
	            										</div>
	            						  </div>
					              </div>
					              <div class="uk-margin empty-field">
						              <div uk-grid>
	            										<div class="uk-width-1-4">
	            											<label class="uk-form-label"
													for="confirm-newpassword">Confirm new password</label>
	            										</div>
	            										<div class="uk-width-3-4">
	            											<div
													class="uk-form-controls edit-profile-form-control">	
	            												<span class="uk-form-icon" uk-icon="icon: lock"></span>
						                  					<input class="uk-input" type="password"
														id="confirmnewpassword" name="confirmnewpassword" required>
	            											</div>
	            										</div>
	            						  </div>
					              </div>

					              
					              <div class="uk-margin empty-field">
					                <div class="uk-inline">
					                <div class="uk-width-1-4">
					                &ensp;
					                </div>
					                <div class="uk-width-3-4">
					                		<button id="button-pass"
													class="uk-input uk-button uk-button-primary uk-width-1-1 uk-margin-small-bottom">Change password</button>
					                </div>
					                  
					                </div>
					              </div>
					            		<div id="response-pass"></div>			          
						</div>
		              </li>
		              
<!-- 		              <li>
		                   <div class="empty-form edit-profile-data">
		                   	<p>You have not authorized any applications to access your Instagram account.</p>
		                   </div>
		              </li>
		              <li>
		                    2
		              </li>
		              <li>
		                    3
		              </li>
		              <li>
		                    4
		              </li> -->
		              
                		</ul>
				</div>
				</div>
				</div>
			</div>	
			
			

  </jsp:body>
</t:wrapper>