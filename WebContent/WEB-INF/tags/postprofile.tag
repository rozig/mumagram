<%@tag description="Feed sidebar template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@tag import="mumagram.model.Post" %>
<%@attribute name="post" required="true" type="mumagram.model.Post"%>

<div class="posts-profile">
  <a href="#">
    <div class="posts-profile-container">
      <div class="posts-profile-content">
        <img src="${post.picture}" alt="">
      </div>
      <div class="posts-profile-overlay uk-flex uk-flex-middle">
        <ul class="posts-profile-overlay-icons">
          <li><span uk-icon="heart"></span> <span>2</span></li>
          <li><span uk-icon="comment"></span> <span>3</span></li>
        </ul>
      </div>
    </div>
  </a>
</div>