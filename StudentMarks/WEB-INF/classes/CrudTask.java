import com.adventnet.taskengine.*;
import com.adventnet.persistence.*;
import com.adventnet.ds.query.*;
import java.io.*;
import java.util.*;
import java.sql.Date;

public class CrudTask implements Task{
	public void executeTask(TaskContext taskContext) throws TaskExecutionException{
		
		try{
			System.err.println("CrudTask Called");
			String student = null;
			BufferedReader br = new BufferedReader(new FileReader("D:\\AdventNet\\MickeyLite\\lib\\main.txt"));
			
			while((student=br.readLine())!=null){
				String[] stud = student.split(",");
				if(stud[0].equals("C")){
					DataObject dataObject = new WritableDataObject();
					String name = stud[1], dob = stud[2], dept = stud[3];
					int year = Integer.parseInt(stud[4]);
					String email = stud[5];
					
					Row r = new Row("Students");
					
					r.set(2, name);
					r.set(3, Date.valueOf(dob));
					r.set(4, dept);
					r.set(5, year);
					r.set(6, email);
					
					dataObject.addRow(r);
					DataAccess.add(dataObject);
					System.out.println("Records created sucessfully!!!");
				}
				
				else if(stud[0].equals("U")){
					Criteria c = new Criteria(new Column("Students", "ROLL_NO"), new Integer(stud[1]), QueryConstants.EQUAL);
					DataObject dataObject = DataAccess.get("Students", c);
					Iterator it = dataObject.getRows("Students");
					while(it.hasNext()){
						Row r = (Row)it.next();
						r.set(2, stud[1]);
						r.set(3, Date.valueOf(stud[2]));
						r.set(4, stud[3]);
						r.set(5, Integer.parseInt(stud[4]));
						r.set(6, stud[5]);
						dataObject.updateRow(r);
					}
					DataAccess.update(dataObject);
					System.out.println("Completed updation");
				}
				
				else if(stud[0].equals("D")){
					DataObject dataObject = DataAccess.get("Students", (Criteria)null);
					Criteria c = new Criteria(new Column("Students", "ROLL_NO"), new Integer(stud[1]), QueryConstants.EQUAL);
					dataObject.deleteRows("Students", c);
					DataAccess.update(dataObject);
					System.out.println("Completed deletetion");
				}
			}
			
			br.close();
		}
		
		catch(Exception e){
			System.err.println("Error in CrudTask...");
			e.printStackTrace();
		}
	}
	
	public void stopTask() throws TaskExecutionException{
		System.err.println("Stopping Task!!!");
	}
}