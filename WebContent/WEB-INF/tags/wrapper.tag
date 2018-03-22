<%@tag description="Generic page template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@attribute name="pagetitle" fragment="true" %>
<!DOCTYPE html>
<html>

  <head>
    <jsp:invoke fragment="pagetitle"/>
    <%@include file="../html/head.html" %>
  </head>
  
  <body data-url="${ baseUrl }">
	<div class="main-wrapper">
	
    <t:header></t:header>

    <div class="main-main">
      <div class="main-container">

        <div class="main-body">
        	<jsp:doBody/>
        	
        	<div class="uk-clearfix"></div>
        </div>
      </div>
    </div>
  </div>
    
	<%@include file="../html/foot.html" %>
  </body>
</html>