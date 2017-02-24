package listener;

import java.util.TimerTask;
import org.apache.log4j.Logger;
import utility.GitminingHelper;

public class DataTimeTask extends TimerTask{
	private static Logger log = Logger.getLogger(DataTimeTask.class);
	
	public void run() {
		try {
			GitminingHelper.getInstance().routineTask();
		} catch (Exception e) {
			log.info("---------parse exception information---------");
		}
	}
}