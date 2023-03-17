<html>
	<head>
		<title>Actions</title>
	</head>
	<body>
	<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  width: 200px;
  background-color: #f1f1f1;
}

li a {
  display: block;
  color: #000;
  padding: 8px 16px;
  text-decoration: none;
}

li a.active {
  background-color: #04AA6D;
  color: white;
}

li a:hover:not(.active) {
  background-color: #555;
  color: white;
}

	</style>
	<%
		String user = null;
		boolean isStudent = false;
		
		if(request.isUserInRole("Student")){
			user = "Student";
			isStudent = true;
			session.setAttribute("currentRole", "Student");
		}
		
		else if(request.isUserInRole("Staff")){
			user = "Staff";
			session.setAttribute("currentRole", "Staff");
		}
		
		else if(request.isUserInRole("HOD")){
			user = "Head Of the Department";
			session.setAttribute("currentRole", "HOD");
		}
		
		else if(request.isUserInRole("Principal")){
			user = "Principal";
			session.setAttribute("currentRole", "Principal");
		}
	%>
	
	<h1>Welcome <%=user%></h1>
	
	<div class="actions">
		<h3>What would you like to do? </h3>
		<ul>
		<%
			if(!isStudent){
		%>
		<li><a href="Students.jsp">View Students List</a></li> <br>
		<li><a href="Marks.jsp">View Marks List</a></li> <br>
		<%
			}
		%>
		<li><a href="View results.html">View Exam Results</a></li>
		</ul>
	</div>
	
	<br><br><br>
	<a href="logout.jsp">Click to logout</a>
	</body>
</html>