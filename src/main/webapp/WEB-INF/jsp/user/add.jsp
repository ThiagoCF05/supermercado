<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html lang="en">
 	<jsp:include page="../menu/header.jsp" />

  <body>
	<div class="navbar navbar-fixed-top">
	  <div class="navbar-inner">
	    <div class="container">
	      <a class="brand span2" href="#">Q &amp; A</a>

	      <form class="navbar-search">
		    <input type="text" class="search-query span6" placeholder="Search">
		  </form>
		  <a class="btn btn-primary pull-right" style="color: #fff; font-weight: bold; font-size: 14px;" href="#">Sign In</a>
	    </div>
	  </div>
	</div>

	<div class="container">
		<form accept-charset="UTF-8" action="" class="form-horizontal"
			id="addUser" method="post">
			<legend>User registration</legend>
			
			<div class="control-group string required">
				<label class="string required control-label" for="userName"><abbr title="required">*</abbr> User Name</label>
				<div class="controls">
					<input class="string required span6" id="userName" name="userName" size="50" type="text">
				</div>
			</div>
			<div class="control-group string required">
				<label class="string required control-label" for="password"><abbr title="required">*</abbr> Password</label>
				<div class="controls">
					<input class="string required span6" id="password" name="password" size="50" type="password">
				</div>
			</div>
			<div class="control-group string required">
				<label class="string required control-label" for="email"><abbr title="required">*</abbr> Email</label>
				<div class="controls">
					<input class="string required span6" id="email" name="email" size="50" type="text">
				</div>
			</div>
			<div class="form-actions">
				<input class="btn btn-primary" name="commit" type="submit" value="Create User">
				<a class="btn btn-danger" href="<c:url value="/"/>">Cancel</a> 
			</div>
		</form>
	</div>
	<hr>
	<footer>
		<p>&copy; Satish Ab 2012</p>
	</footer>
	<jsp:include page="../menu/includeScripts.jsp" />
  </body>
</html>