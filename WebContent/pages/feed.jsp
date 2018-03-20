<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:wrapper>
  <jsp:attribute name="pagetitle">
    <title>Mumagram - User feed</title>
  </jsp:attribute>

  <jsp:body>
    <div class="main-content">
      <c:forEach begin="0" end="3" varStatus="loop">
        <t:post/>
      </c:forEach>

      <div class="uk-clearfix"></div>
    </div>

    <t:feedsidebar user="${user}"></t:feedsidebar>
  </jsp:body>
</t:wrapper>