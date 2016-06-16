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
	    <link href="<spring:url value="/resources/css/clean-blog.min.css"/>" rel="stylesheet">
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
		<!-- Navigation -->
	    <nav class="navbar navbar-default navbar-custom navbar-fixed-top">
	        <div class="container-fluid">
	            <!-- Brand and toggle get grouped for better mobile display -->
	            <div class="navbar-header page-scroll">
	                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	                    <span class="sr-only">Toggle navigation</span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                </button>
	                <a class="navbar-brand" href="http://localhost:8080/cms">MBz CMS</a>
	            </div>
	
	            <!-- Collect the nav links, forms, and other content for toggling -->
	            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	                <ul class="nav navbar-nav navbar-right">
	                    <li>
	                        <a href="http://localhost:8080/cms">Home</a>
	                    </li>
	                    <li>
	                        <a href="about.html">About</a>
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
	
	    <header class="intro-header" style="background-image: url('<spring:url value="http://localhost:8080/cms/resources/img/post-bg.jpg"></spring:url>')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="post-heading">
                        <h1 style="color:white;">${ post.title }</h1>
                        <span class="meta">Posted by <a href="#">${ post.user.username }</a></span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Post Content -->
    <article>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <p>${ post.description }</p>
                </div>
            </div>
        </div>
    </article>
	
	    <hr>
		<div class="container">
			<div class="row">
				<p>Comments</p>
				<ul>
					<c:forEach items="${comments}" var="comment">
						<li>${ comment.description }</li>	
					</c:forEach>
				</ul>
			</div>
			<h4>Add Comment</h4>
			<form class="form-horizontal" role="form" method="post" action='<spring:url value="/post/comments/add"></spring:url>'>
				<input type="hidden" name="postId" value="${ post.id }">
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-3 control-label">Comment</label>
                    <div class="col-sm-9">
                        <input class="form-control" id="inputEmail3" placeholder="Comment Text" name="commentText" required="" type="text">
                    </div>
                </div>
                <div class="form-group last">
                    <div class="col-sm-offset-3 col-sm-9">
                        <input type="submit" class="btn btn-primary" value="Add">
                    </div>
                </div>
            </form>
		</div>
	    <!-- Footer -->
	    <footer>
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
	                    <ul class="list-inline text-center">
	                        <li>
	                            <a href="#">
	                                <span class="fa-stack fa-lg">
	                                    <i class="fa fa-circle fa-stack-2x"></i>
	                                    <i class="fa fa-twitter fa-stack-1x fa-inverse"></i>
	                                </span>
	                            </a>
	                        </li>
	                        <li>
	                            <a href="#">
	                                <span class="fa-stack fa-lg">
	                                    <i class="fa fa-circle fa-stack-2x"></i>
	                                    <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
	                                </span>
	                            </a>
	                        </li>
	                        <li>
	                            <a href="#">
	                                <span class="fa-stack fa-lg">
	                                    <i class="fa fa-circle fa-stack-2x"></i>
	                                    <i class="fa fa-github fa-stack-1x fa-inverse"></i>
	                                </span>
	                            </a>
	                        </li>
	                    </ul>
	                </div>
	            </div>
	        </div>
	    </footer>
	
	    <!-- jQuery -->
	    <script src="<spring:url value="/resources/js/jquery.js" />"></script>
	
	    <!-- Bootstrap Core JavaScript -->
	    <script src="<spring:url value="/resources/js/bootstrap.min.js" />"></script>
	
	    <!-- Custom Theme JavaScript -->
	    <script src="<spring:url value="/resources/js/clean-blog.min.js" />"></script>
	</jsp:body>
</t:layout>
