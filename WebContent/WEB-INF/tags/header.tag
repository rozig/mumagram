<%@tag description="Header template" pageEncoding="UTF-8"%>

<header class="main-header">
  <div class="main-header-holder"></div>
  <div class="main-header-container">

    <nav class="main-nav uk-navbar uk-navbar-container uk-margin">
      <div class="uk-navbar-left">
        <a class="uk-navbar-item uk-logo main-logo" href="${baseUrl}">Mumagram</a>

        <div class="uk-navbar-toggle">
        	<div class="uk-inline search-result">
	          <form class="uk-search uk-search-default">
	            <span uk-search-icon></span>
	            <input class="uk-search-input" id="search-input" type="search" placeholder="Search...">
	          </form>
	          
			    <div id="search-result" class="search-dropdown">
			    </div>
		    </div>
        </div>
      </div>

      <div class="uk-navbar-right">
        <ul class="uk-subnav">
          <li>
            <a href="${baseUrl}/post">
              <span uk-icon="camera"></span>
            </a>
          </li>
          <li>
            <a href="#"><span uk-icon="user"></span></a>
            <div uk-dropdown="mode: click; pos: bottom-right">
              <ul class="uk-nav uk-dropdown-nav">
                <li><a href="${baseUrl}/profile/@${user.username}">View profile</a></li>
                <li><a href="${baseUrl}/profile/edit">Edit profile</a></li>
                <li><a href="${baseUrl}/logout?logout=true">Log out</a></li>
              </ul>
            </div>
          </li>
        </ul>

      </div>
    </nav>
  </div>
</header>