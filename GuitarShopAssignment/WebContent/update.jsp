<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Customer Display Application</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="index.html">Home</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
<br />
<br />
<br />
   <div class="container">
	    ${message}
	    <div class="jumbotron">
		  <h1>Customer Names</h1>
		 <p class="lead">Edit Customer Details</p>
		</div>	
	  
	    <form action="PostUpdate" method="post">
	     <div class="form-group">
	    	Customer ID:<br>
	    	<input type="text" name="customerid" value ="${customerid}"></input>
			<br>
			 First name:<br>
			<input type="text" name="firstname" value ="${customerfirstname}"></input>
			<br>
			 Last name:<br>
			<input type="text" name="lastname" value ="${customerlastname}"></input>
			<br>
			 Email ID:<br>
			 <input type="text" name="emailid" value ="${customeremailid}"></input>
			 <br>
			 Password:<br>
			 <input type="password" name="password" value="${customerpassword}" ></input>
			 <br> 
			 Billing Address ID:<br>
			 <input type="text" name="billingaddressid" value ="${customerbillingid}"></input>
			 <br>
			 Shipping Address ID:<br>
			 <input type="text" name="shippingaddressid" value="${customershippingid}"></input>
			 <br>
		</div>
		 <button type="submit" class="btn btn-primary">Edit</button>
	</form>
    </div><!-- /.container -->
</body>
</html>