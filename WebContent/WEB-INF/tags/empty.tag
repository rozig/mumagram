<%@tag description="Login page template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@attribute name="pagetitle" fragment="true" %>
<!DOCTYPE html>
<html>

  <head>
    <jsp:invoke fragment="pagetitle"/>
    <t:head/>
  </head>
  
  <body>
	<section class="empty-wrapper">
	  <div class="empty-main">
	    <div class="empty-container">
	
	      <div class="empty-big">
		    <jsp:doBody/>
		  </div>
	
	      <div class="empty-little">
	        <div class="empty-img"></div>
	      </div>
	    </div>
	  </div>
	</section>
    
	<t:foot/>
  </body>
</html>