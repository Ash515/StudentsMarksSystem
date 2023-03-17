import com.adventnet.authentication.AAAUSER;
import com.adventnet.authentication.AAALOGIN;
import com.adventnet.authentication.AAAACCOUNT;
import com.adventnet.authentication.AAAPASSWORD;
import com.adventnet.authentication.AAAACCPASSWORD;
import com.adventnet.authentication.AAAACCOWNERPROFILE;
import com.adventnet.authentication.AAAAUTHORIZEDROLE;
import com.adventnet.authentication.PasswordException;
import com.adventnet.authentication.util.AuthUtil;
import com.adventnet.persistence.DataAccessException;
import com.adventnet.persistence.DataObject;
import com.adventnet.persistence.Row;
import com.adventnet.mfw.bean.BeanUtil;
import com.adventnet.persistence.Persistence;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class StaffAuth extends HttpServlet{
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String loginName = "staff";
		String firstName = "Rahul";
		String middleName = "K";
		String lastName = "Sharma";
		String serviceName = "System";
		String accAdminProfile = "Profile 1";
		String password = "Yejjb9%Sg";
		String passwordProfile = "Profile 1";
		
		PrintWriter out = res.getWriter();
		
		try
		{
			Persistence persistence = (Persistence) BeanUtil.lookup("Persistence"); 
			DataObject dobj = persistence.constructDataObject();
			Row userRow = new Row("AaaUser");
			userRow.set("FIRST_NAME", firstName);
			userRow.set("MIDDLE_NAME", middleName); // optional
			userRow.set("LAST_NAME", lastName); // optional
			dobj.addRow(userRow);

			Row loginRow = new Row("AaaLogin");
			loginRow.set("NAME", loginName);
			dobj.addRow(loginRow);

			Row accRow = new Row("AaaAccount");
			accRow.set(AAAACCOUNT.SERVICE_ID, AuthUtil.getServiceId(serviceName));
			accRow.set(AAAACCOUNT.ACCOUNTPROFILE_ID, AuthUtil.getAccountProfileId(accAdminProfile));
			dobj.addRow(accRow);

			Row passwordRow = new Row("AaaPassword");
			passwordRow.set("PASSWORD", password);
			passwordRow.set("PASSWDPROFILE_ID", AuthUtil.getPasswordProfileId(passwordProfile));
			dobj.addRow(passwordRow);

			Row accPassRow = new Row("AaaAccPassword");
			accPassRow.set("ACCOUNT_ID", accRow.get("ACCOUNT_ID"));
			accPassRow.set("PASSWORD_ID", passwordRow.get("PASSWORD_ID"));
			dobj.addRow(accPassRow);
			
			Row authRoleRow1 = new Row("AaaAuthorizedRole");
			authRoleRow1.set("ACCOUNT_ID", accRow.get("ACCOUNT_ID"));
			authRoleRow1.set("ROLE_ID", AuthUtil.getRoleId("Staff"));
			dobj.addRow(authRoleRow1);

			AuthUtil.createUserAccount(dobj);
			
			out.println("User Created Sucessfully!!!");
		}
		catch(PasswordException pe)
		{	
			out.println("User creation failed!!!");
			pe.printStackTrace();
		}
		catch(DataAccessException dae)
		{	
			out.println("User creation failed!!!");
			dae.printStackTrace();
		}
		catch(Exception e)
		{	
			out.println("User creation failed!!!");
			e.printStackTrace();
		}
	}
}
