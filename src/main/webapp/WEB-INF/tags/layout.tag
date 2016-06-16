<%@ tag description="Layout Page Tag" %>
<%@ attribute name="title" fragment="true" %>
<%@ attribute name="header_css" fragment="true" %>
<%@ attribute name="header_js" fragment="true" %>
<!doctype html>
<html>
	<head>
		<jsp:invoke fragment="title"/>
		<jsp:invoke fragment="header_css"/>
		<jsp:invoke fragment="header_js"/>
	</head>
	<body>
		<jsp:doBody/>
	</body>
</html>