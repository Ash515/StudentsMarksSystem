import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.persistence.*;
import java.util.Iterator;
import java.io.*;
import com.adventnet.ds.query.Criteria;
import com.adventnet.ds.query.Column;
import com.adventnet.ds.query.QueryConstants;

public class DeleteStudent extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		PrintWriter out = res.getWriter();
		
		try{
			DataObject dataObject = DataAccess.get("Students", (Criteria)null);
			Criteria c = new Criteria(new Column("Students", "ROLL_NO"), new Long(req.getParameter("rollno")), QueryConstants.EQUAL);
			dataObject.deleteRows("Students", c);
			DataAccess.update(dataObject);
			out.println("Completed deletion");
		}
		catch(DataAccessException e){
			out.println("Error in deleting student record!!!");
			System.err.println("Error in DeleteStudents");
			e.printStackTrace();
		}
	}
}