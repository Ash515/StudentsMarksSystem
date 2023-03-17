import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class TrialServlet1 extends HttpServlet{
	
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException{
		PrintWriter out = rs.getWriter();
		try{
			System.err.println("TrialServlet1 called!!!");
			new CrudSchedule().initializeSchedule();
			out.println("Schedule created sucessfully!!!");
		}
		catch(Exception e){
			out.println("Unable to create schedule!!!");
			System.err.println("Error in TrialServlet1");
			e.printStackTrace();
		}
		
		out.close();
	}
}
