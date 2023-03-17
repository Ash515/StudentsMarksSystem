import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.adventnet.persistence.*;
import com.adventnet.ds.query.Criteria;

public class CreateRec extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		PrintWriter out = res.getWriter();
		
		try{
			String tableName = req.getParameter("semester");
			DataObject dataObject = new WritableDataObject();
			int m1 = Integer.parseInt(req.getParameter("sub1"));
			int m2 = Integer.parseInt(req.getParameter("sub2"));
			int m3 = Integer.parseInt(req.getParameter("sub3"));
			
			Row r = new Row(tableName);
			
			r.set(1, Long.parseLong(req.getParameter("rollno")));
			r.set(2, m1);
			r.set(3, m2);
			r.set(4, m3);
			
			dataObject.addRow(r);
			DataAccess.add(dataObject);
			out.println("Records created sucessfully!!!");
		}
		catch(DataAccessException e){
			out.println("Error in creating records....Please try again");
			System.err.println("Error in Creating Records....Please try again");
			e.printStackTrace();
		}
	}
}