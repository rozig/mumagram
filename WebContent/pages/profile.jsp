<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:wrapper>
  <jsp:attribute name="pagetitle">
    <title>Mumagram - User profile</title>
  </jsp:attribute>

  <jsp:body>
    
	<header class="profile-header">
            <div class="profile-big-img-wrapper">
              <div class="profile-big-img-content">
                <span class="profile-img-big uk-border-circle"><img src="/mumagram/assets/images/profile.jpg" alt=""/>
                <img src="/mumagram/assets/images/profile.jpg" alt="" class="profile-big-img">
                </span>
              </div>
            </div>

            <section class="profile-header-body">
              <div class="profile-header-top uk-flex uk-flex-middle">
                <h1 class="profile-post-name">swganzo</h1>
                <a href="#" class="profile-button">
                  <button class="uk-button uk-button-default uk-button-small">Edit profile</button>
                </a>
              </div>
              <div class="profile-header-middle">
                <ul class="profile-middle-ul">
                  <li class="profile-posts">
                    <span><strong>17</strong> posts</span>
                  </li>
                  <li class="profile-followers">
                    <span><strong>27</strong> followers</span>
                  </li>
                  <li class="profile-followings">
                    <span><strong>37</strong> followings</span>
                  </li>
                </ul>
              </div>

              <div class="profile-header-bottom">
                <h2 class="profile-bottom-name">Kenzo Footman</h2>
              </div>
            </section>
            <div class="uk-clearfix"></div>
          </header>

          <div class="posts-profile-wrapper">
            <c:forEach begin="0" end="3" varStatus="loop">
			  <t:postprofile post="${post}"/>
			</c:forEach>
          </div>

  </jsp:body>
</t:wrapper>