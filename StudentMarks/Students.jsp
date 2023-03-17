<!DOCTYPE HTML>
<html>
	<head>
		<title>Students</title>
		<meta content="initial-scale=1, maximum-scale=1,
        user-scalable=0" name="viewport" />
    <meta name="viewport" content="width=device-width" />
  
    <link rel="stylesheet" href=
"https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css" />
 
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  
     <script type="text/javascript" src=
"https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js">
     </script>
	 
	</head>
	
	<body>
		<h1 align="center">Showing Student Details</h1>
		<table id="students" class="display" style="width:100%">
        <thead>
            <tr>
                <th>RollNo</th>
                <th>Name</th>
                <th>DOB</th>
                <th>Dept</th>
				<th>Year</th>
                <th>Email</th>
				<th>Delete</th>
				<th>Edit</th>
            </tr>
        </thead>
    </table>
	
	<br><br>
	<%	
		String user = (String)session.getAttribute("currentRole");
		if(user.equals("Principal")){
	%>	
	<div align="right">
		<a href="Add student.html" align="center">Add new Student</a>
	</div>
	<%
		}
	%>
	<br><br><br>
	<a href="logout.jsp">Click to logout</a>
	<script>
	$(document).ready(function () {
		$('#students').DataTable({
			ajax: {
				url: 'readstud',
				dataSource: 'data'
			},
			columns:[
				{data: "roll_no"},
				{data: "student_name"},
				{data: "dob"},
				{data: "dept"},
				{data: "year"},
				{data: "email_id"},
				{data: "delete"},
				{data: "edit"}
			]
		});
	});
	</script>
	</body>
</html>