import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.adventnet.persistence.*;
import com.adventnet.ds.query.Criteria;
import java.util.Iterator;
import com.adventnet.ds.query.Column;
import com.adventnet.ds.query.QueryConstants;


public class GetResult extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		Long rollNo = Long.parseLong(req.getParameter("rollno"));
		String sem = req.getParameter("semester");

		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		try{
			Criteria c = new Criteria(new Column(sem, "ROLL_NO"), rollNo, QueryConstants.EQUAL);
			DataObject dataObject = DataAccess.get(sem, c);
			Iterator it = dataObject.getRows(sem);
			while(it.hasNext()){
				Row r = (Row)it.next();
				double m1 = r.getInt(2), m2 = r.getInt(3), m3 = r.getInt(4);
				double gpa = ((m1+m2+m3)/300.0) * 10.0;
				out.println("Showing exam results for "+sem+"<br><br>");
				out.println("<table>");
				out.println("<tr><td>Subject</td><td>Result</td></tr>");
				out.println("<tr><td>"+m1+"</td><td>"+((m1>40)?"PASS":"RA")+"</td></tr>");
				out.println("<tr><td>"+m2+"</td><td>"+((m2>40)?"PASS":"RA")+"</td></tr>");
				out.println("<tr><td>"+m3+"</td><td>"+((m3>40)?"PASS":"RA")+"</td></tr>");
				out.println("</table>");
				out.println("<br><br>GPA : "+gpa);
			}
			
			out.close();
		}
		
		catch(Exception e){
			out.println("Error in Displaying Results....Please try after some time!!!");
			System.err.println("Error in GetResult");
			e.printStackTrace();
		}
	}
}