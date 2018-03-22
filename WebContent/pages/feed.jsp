<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:wrapper>
  <jsp:attribute name="pagetitle">
    <title>Mumagram - User feed</title>
  </jsp:attribute>

  <jsp:body>
    <div class="main-content">
      <div id="feed-post-container"></div>
	  
	  <div id="ajax-loader"></div>
	  
      <div class="uk-clearfix"></div>
    </div>

    <t:feedsidebar user="${user}"></t:feedsidebar>
  </jsp:body>
</t:wrapper>