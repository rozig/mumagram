<%@tag description="Generic page template" pageEncoding="UTF-8"%>
<%@attribute name="pagetitle" fragment="true" %>
<!DOCTYPE html>
<html>

  <head>
    <jsp:invoke fragment="pagetitle"/>
    <%@include file="../html/head.html" %>
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
    
	<%@include file="../html/foot.html" %>
  </body>
</html>