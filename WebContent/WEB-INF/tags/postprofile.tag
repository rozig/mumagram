<%@tag description="Feed sidebar template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@tag import="mumagram.model.Post" %>
<%@attribute name="post" required="true" type="mumagram.model.Post"%>

<div class="posts-profile">
  <a href="${ baseUrl }/post/${ post.id }">
    <div class="posts-profile-container">
      <div class="posts-profile-content ${ post.filter }" style="background-image: url(${post.picture})">
      </div>
      <div class="posts-profile-overlay uk-flex uk-flex-middle">
        <ul class="posts-profile-overlay-icons">
          <li><span uk-icon="heart"></span> <span>${ post.likeCount }</span></li>
          <li><span uk-icon="comment"></span> <span>${ post.commentCount }</span></li>
        </ul>
      </div>
    </div>
  </a>
</div>