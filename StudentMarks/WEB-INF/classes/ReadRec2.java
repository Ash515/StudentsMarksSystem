import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.persistence.*;
import java.util.Iterator;
import java.io.*;
import com.adventnet.ds.query.Criteria;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReadRec2 extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		
		PrintWriter out = res.getWriter();
		
		try{
			HttpSession session = req.getSession();
			String user = (String)session.getAttribute("currentRole");
			DataObject dataObject = DataAccess.get("Sem2", (Criteria)null);
			Iterator it = dataObject.getRows("Sem2");
			JSONArray arr = new JSONArray();
			JSONObject result = new JSONObject();
			while(it.hasNext()){
				Row r = (Row)it.next();
				double gpa = (((r.getInt(2)+r.getInt(3)+r.getInt(4))*10.0)/300);
				JSONObject jObj = r.getAsJSON();
				jObj.put("gpa", String.valueOf(gpa));
				if(!user.equals("Staff")) jObj.put("delete", "<a href=\"deleterec?rollno="+r.get(1)+"&semester=Sem2\" target=\"_blank\">Delete</a>");
				else jObj.put("delete", "Delete not allowed");
				jObj.put("edit", "<a href=\"Edit marks.html?rollno="+r.get(1)+"&sem=Sem2\" target=\"_blank\">Edit</a>");
				arr.put(jObj);
			}
			
			result.put("data", arr);
			out.println(result);
			out.close();
		}
		catch(DataAccessException e){
			System.err.println("Error in ReadRec");
			e.printStackTrace();
		}
	}
}