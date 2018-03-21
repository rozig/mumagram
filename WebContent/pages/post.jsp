<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:wrapper>
  <jsp:attribute name="pagetitle">
    <title>Mumagram - Post</title>
  </jsp:attribute>

  <jsp:body>
    
    <div class="post-hidden-wrapper">
            <div class="dashboard-wrap" id="dashboard" >
              <div class="dashboard-filesContainer">
                <ul class="dashboard-files dashboard-files--noFiles">
                  <div class="dashboard-bgIcon">
                    <svg aria-hidden="true" class="dicon" width="48" height="69" viewBox="0 0 48 69"><path d="M.5 1.5h5zM10.5 1.5h5zM20.5 1.5h5zM30.504 1.5h5zM45.5 11.5v5zM45.5 21.5v5zM45.5 31.5v5zM45.5 41.502v5zM45.5 51.502v5zM45.5 61.5v5zM45.5 66.502h-4.998zM35.503 66.502h-5zM25.5 66.502h-5zM15.5 66.502h-5zM5.5 66.502h-5zM.5 66.502v-5zM.5 56.502v-5zM.5 46.503V41.5zM.5 36.5v-5zM.5 26.5v-5zM.5 16.5v-5zM.5 6.5V1.498zM44.807 11H36V2.195z"></path>
                    </svg>
                    <h3 class="dashboard-dropFilesTitle">
                      <form method="post" action="${ baseUrl }/post" id="post-form" enctype="multipart/form-data">
	                      <span>Drop files here, paste or <label for="main-input" class="dashboard-browse">browse</label>
	                        <input class="dashboard-input" id="main-input" hidden="" aria-hidden="true" tabindex="-1" type="file" name="file">
	                        <input class="dashboard-input" hidden="" id="input-filter" aria-hidden="true" tabindex="-1" type="hidden" name="filter">
	                      </span>
                      </form>
                    </h3>
                  </div>
                </ul>
              </div>
            </div><!-- Dashboard end -->

            <div class="instagram-wrap" id="instagram">
              <div class="insta-pic-top-wrapper">
                <div class="uk-modal-footer">
                  <a href="${ baseUrl }" class="uk-button uk-button-default uk-modal-close" type="button">Cancel</a>
                  <a href="#" id="input-save" class="uk-button uk-button-primary uk-float-right">Save</a>
                </div>
              </div>
              <div class="insta-pic-wrapper">
                <div class="insta-pic-container">
                  <div id="insta-pic"></div>
                </div>
              </div>

              <div class="insta-pic-options-wrapper">
                <div class="insta-pic-options-container">

                  <div class="insta-pic-options">
                    <div class="insta-pic-option filter-charmes">
                      <a href="#" class="insta-pic-href" data-effect="filter-normal">
                        <img class="insta-pic" src="" alt="">
                      </a>
                    </div>
                  </div>

                  <div class="insta-pic-options">
                    <div class="insta-pic-option filter-charmes">
                      <a href="#" class="insta-pic-href" data-effect="filter-charmes">
                        <img class="insta-pic" src="" alt="">
                      </a>
                    </div>
                  </div>

                  <div class="insta-pic-options">
                    <div class="insta-pic-option filter-lofi">
                      <a href="#" class="insta-pic-href" data-effect="filter-lofi">
                        <img class="insta-pic" src="" alt="">
                      </a>
                    </div>
                  </div>

                  <div class="insta-pic-options">
                    <div class="insta-pic-option filter-aqua">
                      <a href="#" class="insta-pic-href" data-effect="filter-aqua">
                        <img class="insta-pic" src="" alt="">
                      </a>
                    </div>
                  </div>

                  <div class="insta-pic-options">
                    <div class="insta-pic-option filter-inkwell">
                      <a href="#" class="insta-pic-href" data-effect="filter-inkwell">
                        <img class="insta-pic" src="" alt="">
                      </a>
                    </div>
                  </div>

                  <div class="insta-pic-options">
                    <div class="insta-pic-option filter-reyes">
                      <a href="#" class="insta-pic-href" data-effect="filter-reyes">
                        <img class="insta-pic" src="" alt="">
                      </a>
                    </div>
                  </div>

                </div>
              </div>
            </div>
            <div class="uk-clearfix"></div>
          </div>
	
  </jsp:body>
</t:wrapper>