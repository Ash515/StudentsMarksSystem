<!DOCTYPE HTML>
<html>
	<head>
		<title>Auth</title>
	</head>
	
	<body>
		<style>
			input[type=text], input[type=password] {
			  width: 30%;
			  padding: 12px 20px;
			  margin: 8px 0;
			  display: inline-block;
			  border: 1px solid #ccc;
			  border-radius: 4px;
			  box-sizing: border-box;
			}

			input[type=submit] {
			  width: 65%;
			  background-color: #4CAF50;
			  color: white;
			  padding: 14px 20px;
			  margin: 8px 0;
			  border: none;
			  border-radius: 4px;
			  cursor: pointer;
			}

			input[type=submit]:hover {
			  background-color: #45a049;
			}

			div {
			  border-radius: 5px;
			  background-color: #f2f2f2;
			  padding: 20px;
			  margin-left: 25%;
			  margin-right: 25%;
			}
		</style>
		<h1 align="center">User Login</h1>
		<div class="login">
			<form method='get' action='<%=response.encodeURL("j_security_check")%>'>
				User name : <input class='textBox' type="text" name="j_username"><br><br>
				Password : <input class='textBox' type="password" name="j_password"><br><br>
				<input type="submit" value="Login">
			</form>
		</div>
	</body>
</html>
