<%@tag description="Generic page template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@attribute name="pagetitle" fragment="true" %>
<!DOCTYPE html>
<html>

  <head>
    <jsp:invoke fragment="pagetitle"/>
    <t:head/>
  </head>
  
  <body data-url="${ baseUrl }">
	<div class="main-wrapper">
	
    <t:header/>

    <div class="main-main">
      <div class="main-container">

        <div class="main-body">
        	<jsp:doBody/>
        	
        	<div class="uk-clearfix"></div>
        </div>
      </div>
    </div>
  </div>
    
  <t:foot/>
  </body>
</html>