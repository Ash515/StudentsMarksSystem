import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.persistence.*;
import java.util.Iterator;
import java.io.*;
import com.adventnet.ds.query.Criteria;
import com.adventnet.ds.query.Column;
import com.adventnet.ds.query.QueryConstants;

public class DeleteRec extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		PrintWriter out = res.getWriter();
		
		try{
			String tableName = req.getParameter("semester");
			DataObject dataObject = DataAccess.get(tableName, (Criteria)null);
			Criteria c = new Criteria(new Column(tableName, "ROLL_NO"), new Long(req.getParameter("rollno")), QueryConstants.EQUAL);
			dataObject.deleteRows(tableName, c);
			DataAccess.update(dataObject);
			
			out.println("Completed deletion");
		}
		catch(DataAccessException e){
			out.println("Error in deleting student record!!!");
			System.err.println("Error in DeleteRec");
			e.printStackTrace();
		}
	}
}