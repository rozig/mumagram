$(function(){
  'use strict'
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
  $searchinput = $('#search-input');
  
  if($follow.length){
	  var profile_id = $follow.attr('data-profile-id');
	  var user_id = $follow.attr('data-user-id');
	  
	  var get_status = function(){
		  $.ajax({
			  url: 'follow',
			  method: 'GET',
			  data: {
				  'profile_id': profile_id
			  }
		  }).done(function(data){
			  console.log(data);
			  
			  if(data.code === 1000){
	  			if(Object.keys(data.data).length>0){
	  				
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
		  });
	  };
	  
	  get_status();
  }
  
  if($searchinput.length){
	  $searchinput.on('input', function(){
		  var a = setTimeout(work, 10);
		  var self = $(this);
		  
		  function work(){
			  $.ajax({
				  url: 'search',
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

  // is draggable or not. from: (https://css-tricks.com/drag-and-drop-file-uploading/)
  var isAdvancedUpload = function()
  {
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
  $input.on( 'change', function( e )
  {
    showFiles( e.target.files );
  });

  if( isAdvancedUpload )
  {
    $drop
        .addClass( 'has-advanced-upload' ) // letting the CSS part to know drag&drop is supported by the browser
        .on( 'drag dragstart dragend dragover dragenter dragleave drop', function( e )
        {
          // preventing the unwanted behaviours
          e.preventDefault();
          e.stopPropagation();
        })
        .on( 'dragover dragenter', function() //
        {
          $drop.addClass( 'is-dragover' );
        })
        .on( 'dragleave dragend drop', function()
        {
          $drop.removeClass( 'is-dragover' );
        })
        .on( 'drop', function( e )
        {
          droppedFiles = e.originalEvent.dataTransfer.files; // the files that were dropped
          //console.log(droppedFiles);
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
}
});