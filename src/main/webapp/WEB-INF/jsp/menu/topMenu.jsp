<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>

<c:url value="/" var="homeUrl" />
<c:url value="user" var="userUrl" />
<c:url value="admin" var="adminUrl" />
<c:url value="logout" var="logoutUrl" />
<c:url value="/campaigns/list" var="listCampaign" />
<c:url value="/produtos/list" var="produto" />
<c:url value="/supermercados/list" var="supermercado" />

<c:if test="${not empty msg }">
	<div id="alertMessage" class="alert">
	  <button type="button" id="closeMessage" class="close" data-dismiss="alert">&times;</button>
	  <strong>Warning!</strong> <span id="msg">${msg }</span>
	</div>	
</c:if>
<c:if test="${empty msg }">
	<div id="alertMessage" class="alert" style="display:none">
	  <button type="button" id="closeMessage" class="close" data-dismiss="alert">&times;</button>
	  <strong>Warning!</strong> <span id="msg"></span>
	</div>
</c:if>


<div class="navbar">
  <div class="navbar-inner">
    <div class="container">
 
      <!-- Be sure to leave the brand out there if you want it shown -->
      <a class="brand" href="#">Supermercados</a>
	  <ul class="nav">
	      <li class="divider-vertical"><a href="${ supermercado}">Supermercado</a></li>
	      <li class="divider-vertical"><a href="${ produto}">Produto</a></li>
	    </ul>
 
      <!-- Everything you want hidden at 940px or less, place within here -->
      <div class="nav-collapse collapse">
        <!-- .nav, .navbar-search, .navbar-form, etc -->
      </div>
 
    </div>
  </div>
</div>