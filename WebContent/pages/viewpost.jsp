<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:wrapper>
  <jsp:attribute name="pagetitle">
    <title>Mumagram - Post</title>
  </jsp:attribute>

  <jsp:body>
    
    <div class="viewpost-wrapper">
            <div class="viewpost-container">
              <div class="viewpost-inner">
                <header class="viewpost-header">
                  <a href="${ baseUrl }/profile/@${ user.username }" class="post-profile uk-flex link">
                    <span class="profile-img uk-border-circle profile-img-medium"><img src="${ user.profilePicture }" alt=""></span>
                    <span class="profile-name">${ user.username }</span>
                  </a>
                </header>

                <div class="viewpost-image-wrapper">
                  <div class="viewpost-image-container">
                    <img src="${ post.picture }" class="viewpost-image" alt="">
                  </div>
                </div>

                <div class="viewpost-comment-wrapper">
                  <div class="viewpost-comment-container">

                    <c:if test="${ post.description != null }">
                    	<div class="post-description">
	                      <div class="post-desc">
	                        <a href="${ baseUrl }/profile/@${ user.username }" class="link">${ user.username }</a>
	                        <span>${ post.description }</span>
	                      </div>
	                    </div>
                    </c:if>

                    <div class="post-comments">
                      <ul id="post-comments-">
                      	<c:forEach var="comment" items="${ comments }">
                      		<li class="text-li">
	                          <a href="${ baseUrl }/profile/@${ comment.user.username }" class="link">${ comment.user.username }</a>
	                          <span>${ comment.comment }</span>
	                        </li>
                      	</c:forEach>
                      </ul>
                    </div>
                  </div>
                </div>

                <footer class="viewpost-footer-wrapper">
                  <div class="post-buttons">
                    <a href="#" class="uk-icon-link uk-margin-small-right post-button">
                      <span uk-icon="heart" class="uk-icon"><svg width="20" height="20" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg" ratio="1"> <path fill="none" stroke="#000" stroke-width="1.03" d="M10,4 C10,4 8.1,2 5.74,2 C3.38,2 1,3.55 1,6.73 C1,8.84 2.67,10.44 2.67,10.44 L10,18 L17.33,10.44 C17.33,10.44 19,8.84 19,6.73 C19,3.55 16.62,2 14.26,2 C11.9,2 10,4 10,4 L10,4 Z"></path></svg></span>
                    </a>
                    <a href="#" class="uk-icon-link uk-margin-small-right post-button">
                      <span uk-icon="comment" class="uk-icon"><svg width="20" height="20" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg" ratio="1"> <path d="M6,18.71 L6,14 L1,14 L1,1 L19,1 L19,14 L10.71,14 L6,18.71 L6,18.71 Z M2,13 L7,13 L7,16.29 L10.29,13 L18,13 L18,2 L2,2 L2,13 L2,13 Z"></path></svg></span>
                    </a>
                  </div>
                  <c:if test="${ post.likeCount > 0 }">
	                  <div class="post-counter margin-small-bottom">
	                    <div class="counter">
	                      <a href="#" class="counter-link link">
	                        <span>${ post.likeCount }</span> ${ post.likeCount > 1 ? 'likes' : 'like' }
	                      </a>
	                    </div>
	                  </div>
                  </c:if>

                  <div class="post-date">
                    <a class="date" href="${ baseUrl }/profile/@${ user.username }">
                      <time class="time datetime" datetime="${ post.createdDate }" title="${ post.createdDate }">${ post.createdDate }</time> 
                    </a>
                  </div>

                  <div class="post-comment-form">
                     <textarea name="comment" id="add-comment-on-view-post" class="comment" placeholder="Add a comment" autocomplete="off" autocorrect="off"></textarea>
                     <input type="hidden" id="post-id" value="${ post.id }"/>
                     <input type="hidden" id="user-id" value="${ user.id }"/>
                  </div>
                </footer>
              </div>
            </div>
            <div class="uk-clearfix"></div>
          </div>
	
  </jsp:body>
</t:wrapper>