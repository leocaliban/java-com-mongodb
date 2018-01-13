<html>
	<head>
	  <title>Escoha A Cor</title>
	</head>
	<body>
	  <form action="/cor-favorita" method="POST">
	  	<p>Qual é a sua cor favorita?</p>
	  	<#list cores as cor>
	  		<p>
	  			<input type="radio" name="cor" value="${cor}">${cor}</input>
	  		</p>
	  	</#list>
	  	<input type="submit" value="Submit"/>
	  </form>
	</body>
</html>