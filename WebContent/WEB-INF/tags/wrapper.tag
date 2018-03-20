<%@tag description="Generic page template" pageEncoding="UTF-8"%>
<%@attribute name="pagetitle" fragment="true"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<!DOCTYPE html>
<html>
<head>
<jsp:invoke fragment="pagetitle" />

<%@include file="../html/head.html"%>
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

	<%@include file="../html/foot.html"%>
</body>
</html>