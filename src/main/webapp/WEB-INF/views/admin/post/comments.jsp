<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:layout>
	<jsp:attribute name="title">
		<title>Welcome to my CMS</title>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="author" content="Mahesh MBz">
	</jsp:attribute>
	<jsp:attribute name="header_css">
		<link href="<spring:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href='http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
	    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
	
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	</jsp:attribute>
	<jsp:body>
		<nav class="navbar navbar-default navbar-custom ">
	        <div class="container-fluid">
	            <!-- Brand and toggle get grouped for better mobile display -->
	            <div class="navbar-header page-scroll">
	                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	                    <span class="sr-only">Toggle navigation</span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                </button>
	                <a class="navbar-brand" href="http://localhost:8080/cms/admin/home">MBz CMS</a>
	            </div>
	
	            <!-- Collect the nav links, forms, and other content for toggling -->
	            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	                <ul class="nav navbar-nav navbar-right">
	                    <li>
	                        <a href='<spring:url value="/admin/all"></spring:url>'>Users</a>
	                    </li>
	                    <li>
	                        <a href='<spring:url value="/post/all"></spring:url>'>Post</a>
	                    </li>
	                    <li>
	                        <a href='<spring:url value="/category/all"></spring:url>'>Category</a>
	                    </li>
	                    <c:choose>
		                    <c:when test="${isLoggedIn eq true }">
		                    	<li>
		                    		<a href='<spring:url value="/logout"></spring:url>'>Logout</a>
		                    	</li>
		                    </c:when>
		                    <c:when test="${isLoggedIn eq false }">
		                    	<li>
		                    		<a href='<spring:url value="/login"></spring:url>'>Login</a>
		                    	</li>
		                    </c:when>
	                    </c:choose>
	                </ul>
	            </div>
	            <!-- /.navbar-collapse -->	
	        </div>
	        <!-- /.container -->
	    </nav>
	    <div class="container">
	    	<div class="row">
	    		<table class="table table-striped table-hover ">
					  <thead>
					    <tr>
					      <th>#</th>
					      <th>Comment</th>
					      <th>Delete <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></th>
					    </tr>
					  </thead>
					  <tbody>
					  	<c:forEach items="${comments}" var="comment" varStatus="loop">
						    <tr>
						      <td><c:out value="${ loop.index + 1 }"/></td>
						      <td><c:out value="${comment.description}" /></td>
						      <td>
							      <form action='<spring:url value="/post/comments/delete"></spring:url>' method="post">
								      <input type="hidden" name="commentId" value="${ comment.commentId }">
								      <input type="hidden" name="postId" value="${ postId }">
								      <input type="submit" class="btn btn-danger" value="Delete">
							      </form>
						      </td>
						    </tr>	  		
					  	</c:forEach>
					  </tbody>
					</table>
	    	</div>
	    </div>
	    <script src="<spring:url value="/resources/js/jquery.js"/>"></script>
	    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
	    <script src="<spring:url value="/resources/js/clean-blog.min.js"/>"></script>
	</jsp:body>
</t:layout>