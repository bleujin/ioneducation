package net.ion.bleujin.idioms.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleJob {

	
	private ScheduledExecutorService ses = Executors.newScheduledThreadPool(4) ;
	
	public void addSchedule(final Job job){
		
		ses.scheduleAtFixedRate(new Runnable(){
			@Override
			public void run() {
				job.handle() ;
			}
		}, 10, 60, TimeUnit.SECONDS) ;
		
		
	}
}




class Job {
	
	public void handle(){
		//
	}
}