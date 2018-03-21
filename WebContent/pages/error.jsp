<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:wrapper>
  <jsp:attribute name="pagetitle">
    <title>Mumagram - Error page</title>
  </jsp:attribute>

  <jsp:body>
    
	<div class="mum-error" uk-height-viewport>
		<div class="mum-error-container">
			<h2>Sorry, this page isn't available.</h2>
			<p>
			The link you followed may be broken, or the page may have been removed.
			<a href="/">Go back to Mumagram.</a>
			</p>
		</div>
	</div>

  </jsp:body>
</t:wrapper>