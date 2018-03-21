<%@tag description="Header template" pageEncoding="UTF-8"%>

<header class="main-header">
  <div class="main-header-holder"></div>
  <div class="main-header-container">

    <nav class="main-nav uk-navbar uk-navbar-container uk-margin">
      <div class="uk-navbar-left">
        <a class="uk-navbar-item uk-logo main-logo" href="#">Mumagram</a>

        <div class="uk-navbar-toggle">
          <form class="uk-search uk-search-default">
            <span uk-search-icon></span>
            <input class="uk-search-input" type="search" placeholder="Search...">
          </form>
        </div>
      </div>

      <div class="uk-navbar-right">
        <ul class="uk-subnav">
          <li>
            <a href="#">
              <span uk-icon="camera"></span>
            </a>
          </li>
          <li>
            <a href="#"><span uk-icon="user"></span></a>
            <div uk-dropdown="mode: click; pos: bottom-right">
              <ul class="uk-nav uk-dropdown-nav">
                <li><a href="/mumagram/profile">View profile</a></li>
                <li><a href="/mumagram/profile/edit">Edit profile</a></li>
                <li><a href="/mumagram/logout?logout=true">Log out</a></li>
              </ul>
            </div>
          </li>
        </ul>

      </div>
    </nav>
  </div>
</header>