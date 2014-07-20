<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>

<div role="navigation" class="navbar navbar-blank navbar-static-top">
	<div class="container">
		<ul class="nav navbar-nav navbar-right language-change">
        	<li><a href="#" class="lang-selected" >PL</a></li>
        	<li><a href="#">EN</a></li>
      	</ul>
	
	</div>
</div>

<div class="container" >

	<div class="top-navigation" >
		<div class="horizontal-right" >
			<!-- 
			<div class='language-change' >
				<img title="" src="${contextPath}/static/images/icons/pl_flag.png" />
				<img title="" src="${contextPath}/static/images/icons/en_flag.png" />
			</div>
			 -->
			 <security:authorize access="isAuthenticated()">
			 	<!-- 
			      <span class="badge" style="margin-right: 5px;"><span><spring:message code="header.loggedAs"/>:</span> <security:authentication property="name"/></span><a href="${contextPath}/logout"><button class="btn btn-mini btn-secondary" ><spring:message code="button.logout"/></button></a>
			       -->
			</security:authorize>
			<security:authorize access="!isAuthenticated()">
			 	<!-- 
	              <a href="${contextPath}/login"><button class="btn btn-mini btn-secondary" ><spring:message code="button.login"/></button></a>
	              -->
	        </security:authorize>
		</div>
	</div>
	
	<h1><spring:message code="saos.fullname"/></h1>

</div>

<div role="navigation" class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <button data-target=".navbar-collapse" data-toggle="collapse" class="navbar-toggle" type="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
      	<li class="active"><a href="${contextPath}/">Co robimy</a></li>
        <li ><a href="${contextPath}/search">Wyszukiwarka</a></li>
        <li><a href="${contextPath}/results">Rezultaty</a></li>
        <li><a href="#">Wsparcie</a></li>
        <li class="dropdown">
          <a data-toggle="dropdown" class="dropdown-toggle" href="#">E-uslugi <span class="caret"></span></a>
          <ul role="menu" class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li class="dropdown-header">Nav header</li>
            <li><a href="#">Separated link</a></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
        <li><a href="#">Kontakt</a></li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Logowanie</a></li>
      </ul>
      
    </div><!--/.nav-collapse -->
  </div><!--/.container-fluid -->
</div>


