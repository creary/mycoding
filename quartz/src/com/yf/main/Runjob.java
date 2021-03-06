package com.yf.main;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yf.job.tab.CutTableDay;
import com.yf.util.DateUtil;

public class Runjob {
	Logger log= LoggerFactory.getLogger(Runjob.class);
	public void run() throws Exception {
		// 调度工厂
		SchedulerFactory sf = new StdSchedulerFactory();
		
		// 从工厂中，获取一个任务调度实体
		Scheduler sched = sf.getScheduler();

		// 定义任务运行时间，这里的话，你需要改成你想要任务在什么时候执行
		Date runTime = DateUtil.buildDate(10, 19, 01);
		log.info("任务将在：" + DateUtil.fromDate2String(runTime) + "执行");
		// 初始化任务实体
		JobDetail job = JobBuilder
							.newJob(CutTableDay.class)
							.withIdentity("job1", "group1")
							.build();
		// 初始化触发器
		Trigger trigger = TriggerBuilder
							.newTrigger()
							.withIdentity("job1", "group1")
							.startAt(runTime)
							.build();
		// 设置定时任务
		sched.scheduleJob(job, trigger);
		// 启动定时任务
		
		sched.start();
		// 停止
//		sched.shutdown(true);
	}
	public static void main(String[] args) throws Exception {
		Runjob example = new Runjob();
		example.run();
	}
	
}
