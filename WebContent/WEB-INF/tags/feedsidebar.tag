<%@tag description="Feed sidebar template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@tag import="mumagram.model.User" %>
<%@attribute name="user" required="true" type="mumagram.model.User"%>

<div class="main-sidebar">
  <div class="main-sidebar-container">
    <div class="sidebar-profile">
      <div class="sidebar-profile-wrapper">
        <div class="sidebar-profile-pic-wrapper">
          <a href="${ baseUrl }/profile/@${user.username}" class="sidebar-pic uk-border-circle">
            <img src="${user.profilePicture}" alt=""/>
          </a>
        </div>
        <div class="sidebar-profile-text">
          <div class="sidebar-profile-username">
            <a href="${ baseUrl }/profile/@${user.username}" class="sidebar-username link">
              ${user.username}
            </a>
          </div>
          <div class="sidebar-profile-name">
            ${user.fullname}
          </div>
        </div>
      </div>
    </div>
    
    <div class="sidebar-body">
    	<jsp:doBody/>
    </div>

    <div class="sidebar-footer">
      <nav class="sidebar-footer-nav">
        <ul>
          <li><a href="#">About us</a></li>
          <li><a href="#">Terms</a></li>
          <li><a href="">Contact</a></li>
        </ul>
      </nav>

      <p class="footer-text">©2018 Mumagram</p>
    </div>
  </div>
</div>
<!-- sidebar end -->