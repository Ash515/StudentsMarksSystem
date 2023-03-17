import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.persistence.*;
import java.util.Iterator;
import java.io.*;
import com.adventnet.ds.query.Criteria;
import com.adventnet.ds.query.Column;
import com.adventnet.ds.query.QueryConstants;
import org.json.JSONArray;
import org.json.JSONObject;

public class UpdateRec extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		
		PrintWriter out = res.getWriter();
		
		try{
			String tableName = req.getParameter("semester");
			
			Criteria c = new Criteria(new Column(tableName, "ROLL_NO"), new Integer(req.getParameter("rollno")), QueryConstants.EQUAL);
			DataObject dataObject = DataAccess.get(tableName, c);
			Iterator it = dataObject.getRows(tableName);
			while(it.hasNext()){
				Row r = (Row)it.next();
				r.set(2, Integer.parseInt(req.getParameter("sub1")));
				r.set(3, Integer.parseInt(req.getParameter("sub2")));
				r.set(4, Integer.parseInt(req.getParameter("sub3")));
				dataObject.updateRow(r);
			}
			DataAccess.update(dataObject);
			out.println("Completed updating");
		}
		catch(DataAccessException e){
			out.println("Error in updating student marks...");
			System.err.println("Error in UpdateRec");
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		Long rollNo = Long.parseLong(req.getParameter("rollno"));
		PrintWriter out = res.getWriter();
		
		try{
			String tableName = req.getParameter("semester");
			Criteria c = new Criteria(new Column(tableName, "ROLL_NO"), rollNo, QueryConstants.EQUAL);
			DataObject dataObject = DataAccess.get(tableName, c);
			Iterator it = dataObject.getRows(tableName);
			
			JSONObject obj = new JSONObject();
			
			while(it.hasNext()){
				Row r = (Row)it.next();
				obj = r.getAsJSON();
			}

			out.println(obj);
		}
		catch(Exception e){
			out.println("Unable to get record!!");
			System.err.println("Error in UpdateRec");
			e.printStackTrace();
		}
	}
}
