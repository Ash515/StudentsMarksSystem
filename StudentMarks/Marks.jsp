<!DOCTYPE HTML>
<html>
	<head>
		<title>Marks</title>
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
	
		<div class="marks">
			Choose an Option to display marks!!!
		</div>
		<select class="semester" name="semester">
			<option value="Sem1">Semester 1</option>
			<option value="Sem2">Semester 2</option>
		</select>
		
		<h1 align="center">Showing Mark Details</h1>
		<table id="marks" class="display" style="width:100%">
        <thead>
            <tr>
                <th>RollNo</th>
                <th>Sub1</th>
                <th>Sub2</th>
                <th>Sub3</th>
				<th>Result</th>
                <th>Edit</th>
				<th>Delete</th>
            </tr>
        </thead>
    </table>
	
	<br><br>
	<%
		String userRole = (String)session.getAttribute("currentRole");
		if(!userRole.equals("Student")){
	%>
	<div align="right">
		<a href="Add marks.html" align="center">Add new Record</a>
	</div>
	<%
		}
	%>
	
	<br><br><br>
	<a href="logout.jsp">Click to logout</a>
	<script>
		$('#marks').DataTable({
				ajax: {
					url: 'readrec1',
					type: "GET",
					dataSource: 'data',
				},
				columns:[
					{data: "roll_no"},
					{data: "marks_1"},
					{data: "marks_2"},
					{data: "marks_3"},
					{data: "gpa"},
					{data: "delete"},
					{data: "edit"}
				]
		});		
		$(document).ready(function() {
			$("select").on('change',function(){
			if(this.value==="Sem1"){
						$('#marks').DataTable({
						destroy: true,
					ajax: {
						url: 'readrec1',
						type: "GET",
						dataSource: 'data',
					},
					columns:[
						{data: "roll_no"},
						{data: "marks_1"},
						{data: "marks_2"},
						{data: "marks_3"},
						{data: "gpa"},
						{data: "delete"},
						{data: "edit"}
					]
				});
			}
			else{
				$('#marks').DataTable({
						destroy: true,
					ajax: {
						url: 'readrec2',
						type: "GET",
						dataSource: 'data',
					},
					columns:[
						{data: "roll_no"},
						{data: "marks_1"},
						{data: "marks_2"},
						{data: "marks_3"},
						{data: "gpa"},
						{data: "delete"},
						{data: "edit"}
					]
				});
			}
			});
		});	
	</script>
	</body>
</html>

