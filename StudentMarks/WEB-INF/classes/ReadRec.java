import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.persistence.*;
import java.util.Iterator;
import java.io.*;
import com.adventnet.ds.query.Criteria;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReadRec extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		try{
			BufferedReader br = new BufferedReader(new FileReader("D:\\AdventNet\\MickeyLite\\lib\\students.txt"));
			String str = null;
			while((str=br.readLine())!=null){
				out.println(str+"<br>");
			}
			out.close();
		}
		catch(Exception e){
			System.err.println("Error in ReadRec");
			e.printStackTrace();
		}
	}
}