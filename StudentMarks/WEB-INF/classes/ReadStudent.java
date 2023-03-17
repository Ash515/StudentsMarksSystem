import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.persistence.*;
import java.util.Iterator;
import java.io.*;
import com.adventnet.ds.query.Criteria;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReadStudent extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		
		PrintWriter out = res.getWriter();
		
		try{
			HttpSession session = req.getSession();
			String role = (String)session.getAttribute("currentRole");
			DataObject dataObject = DataAccess.get("Students", (Criteria)null);
			Iterator it = dataObject.getRows("Students");
			JSONArray arr = new JSONArray();
			JSONObject result = new JSONObject();
			while(it.hasNext()){
				Row r = (Row)it.next();
				JSONObject jObj = r.getAsJSON();
				if(role.equals("Principal")) jObj.put("delete", "<a href=\"deletestud?rollno="+r.get(1)+"\" target=\"_blank\">Delete</a>");
				else jObj.put("delete", "Delete not allowed");
				if(!role.equals("Staff")) jObj.put("edit", "<a href=\"Edit student.html?rollno="+r.get(1)+"\" target=\"_blank\">Edit</a>");
				else jObj.put("edit", "Update not allowed");
				arr.put(jObj);
			}
			
			result.put("data", arr);
			out.println(result);
			out.close();
		}
		catch(DataAccessException e){
			System.err.println("Error in ReadStudent");
			e.printStackTrace();
		}
	}
}