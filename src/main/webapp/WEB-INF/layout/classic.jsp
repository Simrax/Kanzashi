<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="de.ks.kanzashi.servlet.ImageServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/css/multizoom.css" type="text/css" />
<link rel="stylesheet"
	href="//cdn.datatables.net/1.10.4/css/jquery.dataTables.min.css"
	type="text/css" />

<script type="text/javascript" src="/js/multizoom.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"></script>

<style>
/* required to avoid jumping */
#commentWrapper {
  /* left: 450px; */
  position: absolute;
  margin-left: 35px;
  width: 280px;
}

#comment {
  position: absolute;
  top: 0;
  /* just used to show how to include the margin in the effect */
  margin-top: 20px;
  border-top: 1px solid purple;
  padding-top: 19px;
}

#comment.fixed {
  position: fixed;
  top: 0;
}

#cssmenu,
#cssmenu ul,
#cssmenu li,
#cssmenu a {
  margin: 0;
  padding: 0;
  border: 0;
  list-style: none;
  font-weight: normal;
  text-decoration: none;
  line-height: 1;
  font-family: 'Open Sans', sans-serif;
  font-size: 14px;
  position: relative;
}
#cssmenu a {
  line-height: 1.3;
}

#cssmenu {
  width: 250px;
}
#cssmenu > ul > li > a {
  padding-right: 40px;
  font-size: 25px;
  font-weight: bold;
  display: block;
  background: #bd0e36;
  color: #ffffff;
  border-bottom: 1px solid #5e071b;
  text-transform: uppercase;
  position: relative;
}
#cssmenu > ul > li > a > span {
  background: #ed1144;
  padding: 10px;
  display: block;
  font-size: 13px;
  font-weight: 300;
}
#cssmenu > ul > li > a:hover {
  text-decoration: none;
}
#cssmenu > ul > li.active {
  border-bottom: none;
}
#cssmenu > ul > li.active > a {
  color: #fff;
}
#cssmenu > ul > li.active > a span {
  background: #bd0e36;
}
#cssmenu span.cnt {
  position: absolute;
  top: 8px;
  right: 15px;
  padding: 0;
  margin: 0;
  background: none;
}

#cssmenu ul ul {
  display: none;
}
#cssmenu ul ul li {
  border: 1px solid #e0e0e0;
  border-top: 0;
}
#cssmenu ul ul a {
  padding: 10px;
  display: block;
  color: #ed1144;
  font-size: 13px;
}
#cssmenu ul ul a:hover {
  color: #bd0e36;
}
#cssmenu ul ul li.odd {
  background: #f4f4f4;
}
#cssmenu ul ul li.even {
  background: #fff;
}

</style>

<script type="text/javascript">
jQuery(document).ready(function($) {
	
	
	$('#cssmenu > ul > li ul').each(function(index, element){
		  var count = $(element).find('li').length;
		  var content = '<span class="cnt">' + count + '</span>';
		  $(element).closest('li').children('a').append(content);
		});
	
	$('#cssmenu ul ul li:odd').addClass('odd');
	$('#cssmenu ul ul li:even').addClass('even');
	
	$('#cssmenu > ul > li > a').click(function() {

		  var checkElement = $(this).next();

		  $('#cssmenu li').removeClass('active');
		  $(this).closest('li').addClass('active'); 

		  if((checkElement.is('ul')) && (checkElement.is(':visible'))) {
		    $(this).closest('li').removeClass('active');
		    checkElement.slideUp('normal');
		  }
		  if((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
		    $('#cssmenu ul ul:visible').slideUp('normal');
		    checkElement.slideDown('normal');
		  }

		  if($(this).closest('li').find('ul').children().length == 0) {
		    return true;
		  } else {
		    return false; 
		  }

		});
	
	
	var top = $('#comment').offset().top - parseFloat($('#comment').css('marginTop').replace(/auto/, 0));
	  $(window).scroll(function (event) {
	    // what the y position of the scroll is
	    var y = $(this).scrollTop();

	    // whether that's below the form
	    if (y >= top) {
	      // if so, ad the fixed class
	      $('#comment').addClass('fixed');
	    } else {
	      // otherwise remove it
	      $('#comment').removeClass('fixed');
	    }
	  });
	
	$('#ItemTable').DataTable(
		{
			"language" : {
				"url" : "http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/German.json"
			},
			"bLengthChange" : false,
			"ordering" : false,
			"bProcessing" : true,
			"sPaginationType" : "full_numbers"
		});

	$('#image1').addimagezoom({ // single image zoom
		zoomrange : [ 3, 10 ],
		magnifiersize : [ 300, 300 ],
		magnifierpos : 'right',
		cursorshade : true,
		largeimage : 'hayden.jpg' //<-- No comma after last option!
	})

	$('#image2').addimagezoom() // single image zoom with default options

	$('#multizoom1').addimagezoom({ // multi-zoom: options same as for previous Featured Image Zoomer's addimagezoom unless noted as '- new'
		/* descArea: '#description', // description selector (optional - but required if descriptions are used) - new
		speed: 1500, // duration of fade in for new zoomable images (in milliseconds, optional) - new
		descpos: true, // if set to true - description position follows image position at a set distance, defaults to false (optional) - new
		imagevertcenter: true, // zoomable image centers vertically in its container (optional) - new
		magvertcenter: true, // magnified area centers vertically in relation to the zoomable image (optional) - new */
		zoomrange : [ 3, 10 ],
		magnifiersize : [ 600, 600 ],
		magnifierpos : 'right',
		/* cursorshadecolor: '#fdffd5', */
		cursorshade : true
	//<-- No comma after last option!
	});

	$('#multizoom2').addimagezoom({ // multi-zoom: options same as for previous Featured Image Zoomer's addimagezoom unless noted as '- new'
		descArea : '#description2', // description selector (optional - but required if descriptions are used) - new
		disablewheel : true
	// even without variable zoom, mousewheel will not shift image position while mouse is over image (optional) - new
	//^-- No comma after last option!	
	});

})
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
</head>
<body>

	<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
		prefix="tilesx"%>

	<tilesx:useAttribute name="current" />

	<div class="container">

		<!-- Static navbar -->
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<spring:url value="/"/>">Kanzashi</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="${current == 'item' ? 'active' : ''}"><a
							href='<spring:url value="/"/>'>Artikel</a></li>
						<security:authorize access="hasRole('ROLE_ADMIN')">
							<li class="${current == 'users' ? 'active' : ''}"><a
								href='<spring:url value="/users.html"/>'>Alle Benutzer</a></li>
						</security:authorize>
						<%-- 						<security:authorize access="isAuthenticated()">
							<li class="${current == 'users' ? 'active' : ''}"><a href='<spring:url value="/account.html"/>'>My account</a></li>
						</security:authorize> --%>
						<li class="${current == 'herkunft' ? 'active' : ''}"><a
							href="<spring:url value="/herkunft.html"/>">Herkunft</a></li>
						<li><a href="#">Kontakt</a></li>
						<!-- <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="#">Action</a></li>
                  <li><a href="#">Another action</a></li>
                  <li><a href="#">Something else here</a></li>
                  <li class="divider"></li>
                  <li class="dropdown-header">Nav header</li>
                  <li><a href="#">Separated link</a></li>
                  <li><a href="#">One more separated link</a></li>
                </ul>
              </li> -->
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="${current == 'register' ? 'active' : ''}"><a
							href='<spring:url value="/register.html"/>'>Registrieren</a></li>
						<security:authorize access="!isAuthenticated()">
							<li class="${current == 'login' ? 'active' : ''}"><a
								href='<spring:url value="/login.html"/>'>Login</a></li>
						</security:authorize>
						<security:authorize access="isAuthenticated()">
							<li><a
								href='<spring:url value="/j_spring_security_logout"/>'>Logout</a></li>
						</security:authorize>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>

		<table>
			<tr>

				<td style="width: 100%;">				
						<tiles:insertAttribute name="body" />
				</td>
				
				<td style="vertical-align: top; padding-top: 70px;">
					<div id="commentWrapper">
						<div id="comment">
							<tiles:insertAttribute name="menu" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><tiles:insertAttribute
						name="footer" /></td>
			</tr>
		</table>


		<br> <br>
</div>
</body>
</html>