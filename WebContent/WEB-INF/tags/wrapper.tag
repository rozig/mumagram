<%@tag description="Generic page template" pageEncoding="UTF-8"%>
<%@attribute name="pagetitle" fragment="true"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<!DOCTYPE html>
<html>
<<<<<<< Updated upstream
  
  <head>
  	<jsp:invoke fragment="pagetitle"/>
  	
    <%@include file="../html/head.html" %>
  </head>
  
  <body>
    <div id="pageheader">
      <jsp:invoke fragment="header"/>
    </div>
    
    <div id="body">
      <jsp:doBody/>
    </div>
    
    <div id="pagefooter">
      <jsp:invoke fragment="footer"/>
    </div>
    
    <%@include file="../html/foot.html" %>
  </body>
=======
<head>
<meta charset="utf-8">

<jsp:invoke fragment="pagetitle" />

<link rel="apple-touch-icon" sizes="180x180"
	href="/mumagram/images/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="/mumagram/images/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="/mumagram/images/favicon-16x16.png">
<link rel="mask-icon" href="/mumagram/images/safari-pinned-tab.svg"
	color="#5bbad5">
<meta name="msapplication-TileColor" content="#da532c">
<meta name="theme-color" content="#ffffff">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- UIkit CSS -->
<link rel="stylesheet" href="/mumagram/plugins/uikit/css/uikit.min.css" />
</head>
<body>
	<div id="pageheader">
		<jsp:invoke fragment="header" />
	</div>

	<div id="body">
		<jsp:doBody />
	</div>

	<div id="pagefooter">
		<jsp:invoke fragment="footer" />
	</div>

	<!-- UIkit JS -->
	<script src="/mumagram/plugins/jquery/jquery-3.3.1.min.js"></script>
	<script src="/mumagram/plugins/uikit/js/uikit.min.js"></script>
	<script src="/mumagram/plugins/uikit/js/uikit-icons.min.js"></script>
</body>
>>>>>>> Stashed changes
</html>