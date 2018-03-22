<%@tag description="Post template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<article class="post">
  <header class="post-header">
    <a href="${ baseUrl }/profile/@${ post.user.username }" class="post-profile uk-flex link">
      <span class="profile-img uk-border-circle"><img src="${post.user.profilePicture }" alt=""/></span>
      <span class="profile-name">${ post.user.username }</span>
    </a>
  </header>

  <div class="post-body-wrapper">
    <div class="post-media-wrapper">
      <div class="post-media">
        <a href="${ baseUrl }/post/${ post.id }" class="post-media-link">
          <img src="${post.picture }" alt="">
        </a>
      </div>
    </div>
  </div>

  <footer class="post-footer">
    <div class="post-buttons">
      <a href="#" class="uk-icon-link uk-margin-small-right post-button">
        <span uk-icon="heart"></span>
      </a>
      <a href="#" class="uk-icon-link uk-margin-small-right post-button">
        <span uk-icon="comment"></span>
      </a>
    </div>
    <div class="post-counter margin-small-bottom">
      <div class="counter">
        <a href="#" class="counter-link link">
          <span>${post.likeCount}</span> likes
        </a>
      </div>
    </div>

    <div class="post-description">
      <div class="post-desc">
        <a href="#" class="link">${ post.user.username }</a>
        <span>
          ${post.description }
        </span>
      </div>
    </div>

    <div class="post-more-comments margin-small-bottom">
      <a class="more-comments" href="#" role="button">View all <span>9</span> comments</a>
    </div>

    <div class="post-comments">
      <ul id="post-comments-">
        <li class="text-li">
          <a href="#" class="link">sayadeni_</a>
          <span>
            Bennerannn terbukkktii ka peleanggsiing dari@#@DOKTER.TUBUHIDEAL  ampuuhh bangeett proddukk
          </span>
        </li>
        <li class="text-li">
          <a href="#" class="link">sayadeni_</a>
          <span>
            Bennerannn terbukkktii ka peleanggsiing dari@#@DOKTER.TUBUHIDEAL  ampuuhh bangeett proddukk
          </span>
        </li>
        <li class="text-li">
          <a href="#" class="link">sayadeni_</a>
          <span>
            Bennerannn terbukkktii ka peleanggsiing dari@#@DOKTER.TUBUHIDEAL  ampuuhh bangeett proddukk
          </span>
        </li>
      </ul>
    </div>

    <div class="post-date">
      <a class="date" href="/p/BgiIKxRH73g/">
        <time class="time datetime" datetime="2018-03-20T05:27:04.000Z" title="Mar 20, 2018">${ post.createdDate }</time> 
      </a>
    </div>

    <div class="post-comment-form">
      <form class="post-comment">
        <textarea name="comment" id="post-id" class="comment" placeholder="Add a comment" autocomplete="off" autocorrect="off"></textarea>
      </form>
    </div>
  </footer>
</article>