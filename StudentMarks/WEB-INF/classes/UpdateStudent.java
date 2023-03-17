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

public class UpdateStudent extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		
		PrintWriter out = res.getWriter();
		
		try{
			Criteria c = new Criteria(new Column("Students", "ROLL_NO"), new Long(req.getParameter("rollno")), QueryConstants.EQUAL);
			DataObject dataObject = DataAccess.get("Students", c);
			Iterator it = dataObject.getRows("Students");
			while(it.hasNext()){
				Row r = (Row)it.next();
				r.set(2, req.getParameter("name"));
				r.set(4, req.getParameter("dept"));
				r.set(5, req.getParameter("year"));
				r.set(6, req.getParameter("email"));
				dataObject.updateRow(r);
			}
			DataAccess.update(dataObject);
			out.println("Completed updating");
		}
		catch(DataAccessException e){
			out.println("Error in updating student...");
			System.err.println("Error in UpdateStudent");
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		Long rollNo = Long.parseLong(req.getParameter("rollno"));
		PrintWriter out = res.getWriter();
		
		try{
			Criteria c = new Criteria(new Column("Students", "ROLL_NO"), rollNo, QueryConstants.EQUAL);
			DataObject dataObject = DataAccess.get("Students", c);
			Iterator it = dataObject.getRows("Students");
			
			JSONObject obj = new JSONObject();
			
			while(it.hasNext()){
				Row r = (Row)it.next();
				obj = r.getAsJSON();
			}

			out.println(obj);
		}
		catch(Exception e){
			out.println("Unable to get record!!");
			System.err.println("Error in UpdateStudent");
			e.printStackTrace();
		}
	}
}
