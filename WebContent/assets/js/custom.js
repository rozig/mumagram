$(function(){
  'use strict'
  var _base_url = $('body').data('url');
  var $instapic = $('#insta-pic'),
  $instaoption = $('.insta-pic-href'),
  $insta = $('#instagram'),
  $drop = $('#dashboard'),
  $instaimg = $('#insta-pic'),
  $instapics = $('.insta-pic'),
  $input = $('#main-input'),
  $inputbutton = $('#input-save'),
  $form = $('#post-form'),
  $inputfilter = $('#input-filter'),
  $searchresult = $('#search-result'),
  $follow = $('#follow'),
  $searchinput = $('#search-input'),
  $postwrapper = $('#feed-post-container'),
  $ajaxloader = $('#ajax-loader'),
  $document = $('document'),
  $window = $('window');

  // change password request start
  var $oldPassword = $('#oldpassword');
  var $newPassword = $('#newpassword');
  var $confirmNewPassword = $('#confirmnewpassword');
  var $userIdPass = $('#pass-user-id');
  var $buttonPass = $('#button-pass');
  // change password request end
  
  // add comment on view post
  var $addCommentOnViewPost = $('#add-comment-on-view-post');
  
  // Like button
  $(document).on('click', '.like-button', function(e){
	  e.preventDefault(e);
	  var self = $(this);
  	$.ajax({
  		url: _base_url+'/like',
  		method: 'POST',
  		data: {
  			user_id: $(this).attr('data-user-id'),
  			post_id: $(this).attr('data-id')
  		}
  	}).done(function(data){
  		change_like(self, data.status);
  	});
  	
  });

	var change_like = function(button, status){
      var $counter = button.parent().parent().find('.counter-link span');
	    console.log($counter);
	  var likes = parseInt( $counter.text() );
	  if(status === 'liked'){
	    button.addClass('nice-liked');
	    likes++;
	  }else{
		button.removeClass('nice-liked');
		likes--;
	  }
	  $counter.text(likes);
	}
	// Like button end
	
	
  // user feed section
  if($postwrapper.length){
    var page_counter = 0;
    var limit = false;
    
    var load_posts = function(){
      // show ajax loader
      $ajaxloader.show();
      
      $.ajax({
        url: _base_url+'/get-posts',
        method: 'POST',
        data: {
          'page': page_counter,
          'type': 'feed'
        }
      }).done(function(data){
        if(data.code === 1000){
          if(Object.keys(data.data).length>0){
            // append posts
            append_posts(data.data);
          }else{
            //doesn't exist
            $follow.hide();
          }
        }else{
          //error exists
          console.log(data.data);
        }
        
        // adding page number
        page_counter++;
      }).fail(function(e){
        console.log(e);
      }).always(function() {
        $ajaxloader.hide();
      });
    }
    
    // when page load, load posts
    load_posts();
    
    // infinite scroll
    $(window).on("scroll", function() {
    	var scrollHeight = $(document).height();
    	var scrollPosition = $(window).height() + $(window).scrollTop();
    	if ((scrollHeight - scrollPosition) / scrollHeight === 0) {
    		setTimeout(function() {
    			load_posts();
    			
    		}, 300 );
    	}
    });
    
    var append_posts = function(data){    	
    	if(data.length==0){

    		var limit = true;
    		
    		if(page_counter<1 && limit){
	    		$postwrapper.append(`<div class="mum-error" uk-height-viewport>
					<div class="mum-error-container">
						<h2>Please follow people.</h2>
						<p>
							Search people by username or first name or last name.
						</p>
					</div>
				</div>`);
	    		}
    		return;
    	}
    	
    	data.forEach(function(post){
            var template = `<article class="post">
  <header class="post-header">
    <a href="${ _base_url }/profile/@${ post.user.username }" class="post-profile uk-flex link">
      <span class="profile-img uk-border-circle"><img src="${post.user.profilePicture }" alt=""/></span>
      <span class="profile-name">${ post.user.username }</span>
    </a>
  </header>

  <div class="post-body-wrapper">
    <div class="post-media-wrapper">
      <div class="post-media ${ post.filter }">
        <a href="${ _base_url }/post/${ post.id }" class="post-media-link">
          <img src="${post.picture }" alt="">
        </a>
      </div>
    </div>
  </div>

  <footer class="post-footer">
    <div class="post-buttons">
      <a href="#" data-id="${ post.id }" class="uk-icon-link uk-margin-small-right post-button like-button">
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
        <a href="${ _base_url }/profile/@${ post.user.username }" class="link">${ post.user.username }</a>
        <span>
          ${post.description ? post.description : ''}
        </span>
      </div>
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
      <a class="date" href="${ _base_url }/post/${ post.id }">
        <time class="time datetime" title="${ post.createdDate.dayOfMonth }.${ post.createdDate.monthValue }.${ post.createdDate.year }">${ post.createdDate.dayOfMonth }.${ post.createdDate.monthValue }.${ post.createdDate.year }</time> 
      </a>
    </div>

    <div class="post-comment-form">
      <form class="post-comment">
        <textarea name="comment" id="post-id" class="comment" placeholder="Add a comment" autocomplete="off" autocorrect="off"></textarea>
      </form>
    </div>
  </footer>
</article>`;
            // setting timeout
            setTimeout(function () {
            	$postwrapper.append(template);
            }, 50);
          });
      
    }
  }
  
  // follow button section
  if($follow.length){
    var profile_id = $follow.attr('data-profile-id');
    var username = $follow.attr('data-username');
    
    $follow.on('click',function(){
     get_status(); 
   });
    
    var get_status = function(){
      $follow.attr('disabled', 'disabled');
      $.ajax({
        url: _base_url+'/follow',
        method: 'POST',
        data: {
          'username': username,
          'following_user_id': profile_id
        }
      }).done(function(data){

        if(data.code === 1000){
          if(Object.keys(data.data).length>0){
            change_follow_button(data.status);
          }else{
            //doesn't exist
            $follow.hide();
          }
        }else{
          //error exists
          console.log(data.data);
        }
      }).fail(function(e){
        console.log(e);
      }).always(function() {
        $follow.removeAttr('disabled');
      });
    };
    
    var change_follow_button = function(data){      
      if(data==="Follow"){
        $follow.addClass('uk-button-primary');
      }else{
        $follow.removeClass('uk-button-primary');
      }

      $follow.html(data);
    }
  }
//follow button section
  
  if($searchinput.length){
    $searchinput.on('input', function(){
      var a = setTimeout(work, 10);
      var self = $(this);
      
      function work(){
        $.ajax({
          url: _base_url+'/search',
          method: 'GET',
          data: {
            'query': self.val()
          }
        }).done(function(data){
          $searchresult.html('');
          if(data.code === 1000){

            if(Object.keys(data.data).length>0){
              data.data.forEach(function(t){
                var template = `<div class="sidebar-profile-wrapper">
                <div class="sidebar-profile-pic-wrapper">
                <a href="/mumagram/profile/@${t.username}" class="sidebar-pic uk-border-circle">
                <img src="${t.profilePicture}" alt="">
                </a>
                </div>
                <div class="sidebar-profile-text">
                <div class="sidebar-profile-username">
                <a href="/mumagram/profile/@${t.username}" class="sidebar-username link">
                ${t.username}
                </a>
                </div>
                <div class="sidebar-profile-name">
                ${t.fullname}
                </div>
                </div>
                </div>`;

                $searchresult.append(template);
              });
              
            }else{
              $searchresult.append( $('<p class="result-error"></p>').text('Not found') );
            }
            
          }else{
            $searchresult.append( $('<p class="result-error"></p>').text(data.data) );
          }
        }).fail(function(e){
          console.log(e);
        });
        $searchresult.show();  
      }
    });
    
    $(document).on("click", function(event){
      var $trigger1 = $searchresult;
      var $trigger2 = $searchinput;
      if($trigger1 !== event.target && !$trigger1.has(event.target).length &&
        $trigger2 !== event.target && !$trigger2.has(event.target).length
        ){
        $searchresult.hide();
    }
  });
  }

  var image = false;
  
  if($drop){
    var droppedFiles = false;

  // is draggable or not. from:
  // (https://css-tricks.com/drag-and-drop-file-uploading/)
  var isAdvancedUpload = function(){
    var div = document.createElement( 'div' );
    return ( ( 'draggable' in div ) || ( 'ondragstart' in div && 'ondrop' in div ) ) && 'FormData' in window && 'FileReader' in window;
  }();
  
  $inputbutton.on('click', function(){
    $form.submit();
  });

  // show insta options
  var showFiles  = function( files ){
    if(files.length < 1) return false;

    insta_show(true);

    var file = files[0];
    var reader = new FileReader();

    reader.onload = function(e) {
      $instapics.each(function(){
        var self = $(this);

        self.attr('src', e.target.result);
      });
    }

    reader.readAsDataURL(file);

    loadImage(
      files[0],
      function (img) {
        var file = files[0];
        var reader = new FileReader();

        reader.onload = function(e) {
          $instapics.each(function(){
            var self = $(this);

            self.attr('src', e.target.result);
          });
        }
        image = img;
        reader.readAsDataURL(file);

        $instaimg.html('');
        $instaimg.append(img);
      },
      {maxWidth: 670} // Options
      );
  };

  // when file select
  $input.on( 'change', function( e ){
    showFiles( e.target.files );
  });

  if( isAdvancedUpload ){
    $drop.addClass( 'has-advanced-upload' ).on( 'drag dragstart dragend dragover dragenter dragleave drop', function( e ){
        // preventing the unwanted behaviours
        e.preventDefault();
        e.stopPropagation();
      }).on( 'dragover dragenter', function(){
        $drop.addClass( 'is-dragover' );
      }).on( 'dragleave dragend drop', function(){
        $drop.removeClass( 'is-dragover' );
      }).on( 'drop', function( e ){
        droppedFiles = e.originalEvent.dataTransfer.files;
        showFiles( droppedFiles );
      });
    }

  // show insta options
  function insta_show(b){
    var hidden = 'uk-display-hidden';
    var block = 'uk-display-block';

    if(!b){
      hidden = 'uk-display-block';
      block = 'uk-display-hidden';
    }

    $insta.removeClass(hidden);
    $insta.addClass(block);
    $drop.removeClass(block);
    $drop.addClass(hidden);
  }

  // insta option select
  $instaoption.on('click', function(e){
    e.preventDefault(e);

    if(!$instaimg){
      return;
    }

    var self = $(this);
    var attr = self.attr('data-effect');

    $instaimg.removeClass (function (index, className) {
      return (className.match (/(^|\s)filter-\S+/g) || []).join(' ');
    });

    $inputfilter.val(attr);

    $instaimg.addClass(attr);
  });
  
  // change pass ajax request
  
  $buttonPass.on('click',function(e){

    $.ajax({ 
      url: 'http://localhost:8080'+'/mumagram/change-password', 
      type:'POST', 
      data:{
        id:$userIdPass.val(),
        oldpassword:$oldPassword.val(),
        newpassword:$newPassword.val(),
        confirmnewpassword:$confirmNewPassword.val()
      },
      success:function(resultData) {
        $('#response-pass').empty();
        if(resultData.code===1000 && resultData.status==="success"){
          var div2 = $('<div>').addClass('uk-alert-success').attr("uk-alert","").append($('<p>').text(resultData.data));
          var div1 = $('<div>').addClass('uk-margin empty-field').append(div2);
          $('#response-pass').append(div1);
        }
        else if(resultData.code===2000 && resultData.status==="error"){
          var div2 = $('<div>').addClass('uk-alert-danger').attr("uk-alert","").append($('<p>').text(resultData.data));
          var div1 = $('<div>').addClass('uk-margin empty-field').append(div2);
          $('#response-pass').append(div1);
        }
        else
        {
          var div2 = $('<div>').addClass('uk-alert-danger').attr("uk-alert","").append($('<p>').text("Please check your internet!"));
          var div1 = $('<div>').addClass('uk-margin empty-field').append(div2);
          $('#response-pass').append(div1);
        }
      }
    }).fail(errorAjax);
    
    function errorAjax(error){
      var div2 = $('<div>').addClass('uk-alert-danger').attr("uk-alert","").append($('<p>').text("error"));
      var div1 = $('<div>').addClass('uk-margin empty-field').append(div2);
      $('#response-pass').append(div1);
    }

  });
  if($addCommentOnViewPost.length){
  $addCommentOnViewPost.keyup(function(evt) {

	  var sellf = $(this);
	  
	  if(evt.keyCode === 13) {
		  $.ajax({
			  url: _base_url + "/comment/add",
			  type: "POST",
			  data: {
				  comment: $(this).val(),
				  post_id: $("#post-id").val(),
				  user_id: $("#user-id").val()
			  },
			  success: function(response) {
				  if(response.code === 1000) {
					  var li = $("<li>").addClass("text-li");
					  $("<a>").attr("href", _base_url + "/profile/@" + response.data.user.username)
					  		.addClass("link").text(response.data.user.username).appendTo(li);
					  $("<span>").text(' '+response.data.comment).appendTo(li);
					  li.appendTo("#post-comments-");
					  
					  sellf.val('');
					  var $commentScroll = sellf.parent().parent().parent().find('.viewpost-comment-container');
					  $commentScroll.animate({ scrollTop: $commentScroll[0].scrollHeight}, 1000);
					  
				  } else {
					  
				  }
			  },
			  error: function(err) {
				  console.log(err);
			  }
		  });
		  
	  }
  });
  }
  }
});