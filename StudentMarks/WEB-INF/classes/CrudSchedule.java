import com.adventnet.persistence.*;
import com.adventnet.taskengine.*;
import com.adventnet.mfw.bean.BeanUtil;

public class CrudSchedule {
	public void initializeSchedule(){
		try{
			Persistence pers = (Persistence) BeanUtil.lookup("Persistence");
			DataObject data = pers.constructDataObject();

			Row taskengineRow = new Row("TaskEngine_Task");
			taskengineRow.set("TASK_NAME","CrudTask");
			taskengineRow.set("CLASS_NAME","CrudTask");
			

			Row scheduleRow = new Row("Schedule");
			scheduleRow.set("SCHEDULE_NAME","CrudSchedule");

			Row scheduledTaskRow = new Row("Scheduled_Task");
			scheduledTaskRow.set("SCHEDULE_ID",scheduleRow.get("SCHEDULE_ID"));
			scheduledTaskRow.set("TASK_ID",taskengineRow.get("TASK_ID"));
			
			Row periodicRow = new Row("Periodic");
			periodicRow.set("SCHEDULE_ID", scheduleRow.get("SCHEDULE_ID"));
			periodicRow.set("START_DATE","2022-08-04 14:55:00.0");
			periodicRow.set("END_DATE","2022-08-10 14:42:00.0");
			periodicRow.set("TIME_PERIOD",30);
			periodicRow.set("UNIT_OF_TIME","seconds");


			data.addRow(taskengineRow);
			data.addRow(scheduleRow);
			data.addRow(scheduledTaskRow);
			data.addRow(periodicRow);
			pers.add(data);


			Scheduler s = (Scheduler) BeanUtil.lookup("Scheduler");
			DataObject taskInputDO = pers.constructDataObject();
			Row taskInputRow = new Row("Task_Input");
			taskInputDO.addRow(taskInputRow);
			s.scheduleTask("CrudSchedule","CrudTask",taskInputDO);

			
		}
		catch(Exception e){
			System.err.println("Error in initializing schedule");
			e.printStackTrace();
		}
	}
}