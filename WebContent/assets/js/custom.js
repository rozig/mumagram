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
  $inputfilter = $('#input-filter');

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