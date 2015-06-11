package com.yf.util.quartz;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 定时任务管理器
 * 
 * @author biezhi
 * @since 1.0
 */
public class QuartzManager {

	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
	private static String DEFAULT_JOB_GROUP_NAME = "UNIQUE_WEB_JOB_GROUP";
	private static String DEFAULT_TRIGGER_GROUP_NAME = "UNIQUE_WEB_TRIGGER_GROUP";

	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * 
	 * @param jobClass 			任务Class
	 * @param jobName 			任务名称
	 * @param cronExpression 	时间设置，参考cron表达式
	 * @return 					任务对象
	 */
	public static JobDetail addJob(final Class<? extends Job> jobClass,
			final String jobName, final String cronExpression) {
		try {
			if(null != jobClass && null != jobName && null != cronExpression){
				Scheduler scheduler = gSchedulerFactory.getScheduler();
				// 任务
				JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, DEFAULT_JOB_GROUP_NAME).build();

				// 触发器
				Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, DEFAULT_TRIGGER_GROUP_NAME).withSchedule(
						CronScheduleBuilder.cronSchedule(cronExpression))
						.forJob(jobName, DEFAULT_JOB_GROUP_NAME).build();
				scheduler.scheduleJob(jobDetail, trigger);
				// 启动
				if (!scheduler.isShutdown()) {
					scheduler.start();
				}
				return jobDetail;
			} else{
				throw new QuartzException("添加任务时有NULL值");
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new QuartzException("添加任务失败");
		}
	}

	/**
	 * 添加一个定时任务
	 * 
	 * @param jobClass 			任务class
	 * @param jobName 			任务名称
	 * @param jobGroupName 		任务所属组
	 * @param triggerName 		触发器名称
	 * @param triggerGroupName 	触发器所属组
	 * @param cronExpression 	时间表达式
	 * @return 					任务对象
	 */
	public static JobDetail addJob(final Class<? extends Job> jobClass,
			final String jobName, final String jobGroupName,
			final String triggerName, final String triggerGroupName,
			final String cronExpression) {
		try {
			Scheduler scheduler = gSchedulerFactory.getScheduler();
			// 任务
			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();

			// 触发器
			Trigger trigger = TriggerBuilder
					.newTrigger().withIdentity(triggerName, triggerGroupName)
					.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
					.forJob(jobName, jobGroupName).build();

			scheduler.scheduleJob(jobDetail, trigger);
			// 启动
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
			return jobDetail;
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new QuartzException(e);
		}
	}

	/**
	 * 更新一个任务
	 * @param jobName 任务名称
	 * @param cronExpression 新的调度表达式
	 */
	public static void updateJobTime(final String jobName, final String cronExpression) {
		try {
			Scheduler scheduler = gSchedulerFactory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, DEFAULT_TRIGGER_GROUP_NAME);

			// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new QuartzException(e);
		}
	}
	
	/**
	 * 更新一个任务
	 * @param triggerName 触发器名称
	 * @param triggerGroupName 触发器所属组
	 * @param cronExpression 调度表达式
	 */
	public static void updateJobTime(final String triggerName, final String triggerGroupName, final String cronExpression) {
		try {
			Scheduler scheduler = gSchedulerFactory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

			// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(cronExpression);

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new QuartzException(e);
		}
	}
	
	/**
	 * 移除一个任务
	 * @param jobName		任务名称
	 */
	public static void removeJob(final String jobName){
		try {  
			Scheduler scheduler = gSchedulerFactory.getScheduler();
			JobKey jobKey = getJobKey(jobName);
			scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {  
            e.printStackTrace();  
            throw new QuartzException(e);  
        }
	}
	
	/**
	 * 移除一个任务
	 * @param jobName		任务名称
	 * @param jobGroupName	任务所属组
	 */
	public static void removeJob(final String jobName, final String jobGroupName){
		try {  
			Scheduler scheduler = gSchedulerFactory.getScheduler();
			JobKey jobKey = getJobKey(jobName, jobGroupName);
			scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {  
            e.printStackTrace();  
            throw new QuartzException(e);  
        }
	}
	
	/**
	 * 移除一组任务
	 * @param jobNames		一组任务的名称
	 */
	public static void removeJobs(final String ... jobNames){
		if(null != jobNames && jobNames.length > 0){
			for(String jobName : jobNames){
				removeJob(jobName);
			}
		}
	}
	
	/**
	 * 移除一个任务组
	 * @param jobKeys		任务集合
	 */
	public static void removeJobs(final List<JobKey> jobKeys){
		try {
			Scheduler scheduler = gSchedulerFactory.getScheduler();
			scheduler.deleteJobs(jobKeys);
        } catch (SchedulerException e) {  
            e.printStackTrace();  
            throw new QuartzException(e);  
        }
	}
	
	/**
	 * 获取一个任务	
	 * @param jobName			任务名称
	 * @return 任务对象
	 */
	public static JobDetail getJobDetail(final String jobName){
		try {
			Scheduler sched = gSchedulerFactory.getScheduler(); 
			JobKey jobKey = JobKey.jobKey(jobName, DEFAULT_JOB_GROUP_NAME);
			return sched.getJobDetail(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new QuartzException(e);  
		}
	}
	
	/**
	 * 获取一个任务
	 * @param jobName			任务名称
	 * @param jobGroupName		任务所属组
	 * @return
	 */
	public static JobDetail getJobDetail(final String jobName, final String jobGroupName){
		try {
			Scheduler sched = gSchedulerFactory.getScheduler(); 
			JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
			return sched.getJobDetail(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new QuartzException(e);  
		}
	}
	
	/**
	 * 获取jobkey
	 * @param jobName		任务名称
	 * @return	
	 */
	public static JobKey getJobKey(final String jobName){
		return JobKey.jobKey(jobName, DEFAULT_JOB_GROUP_NAME);
	}
	
	/**
	 * 获取jobkey
	 * @param joName		任务名称
	 * @param jobGroupName	任务所属组
	 * @return
	 */
	public static JobKey getJobKey(final String jobName, final String jobGroupName){
		return JobKey.jobKey(jobName, jobGroupName);
	}
	
	/**
	 * 暂停一个任务
	 * @param jobName
	 */
	public static void pauseJob(final String jobName){
		try {
			JobKey jobKey = getJobKey(jobName);
			if(null != jobKey){
				Scheduler scheduler = gSchedulerFactory.getScheduler();
				scheduler.pauseJob(jobKey);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new QuartzException(e);  
		}
	}
	
	/**
	 * 恢复一个任务
	 * @param jobName
	 */
	public static void resumeJob(final String jobName){
		try {
			JobKey jobKey = getJobKey(jobName);
			if(null != jobKey){
				Scheduler scheduler = gSchedulerFactory.getScheduler();
				scheduler.resumeJob(jobKey);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new QuartzException(e);  
		}
	}
	
	/**
	 * 运行一个任务
	 * @param jobName		任务名称
	 */
	public static void execute(final String jobName){
		try {
			JobKey jobKey = getJobKey(jobName);
			if(null != jobKey){
				Scheduler scheduler = gSchedulerFactory.getScheduler();
				scheduler.triggerJob(jobKey);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new QuartzException(e);  
		}
	}
	
	/**
	 * 运行一个任务
	 * @param jobDetail		任务
	 */
	public static void execute(final JobDetail jobDetail){
		try {
			JobKey jobKey = getJobKey(jobDetail.getKey().getName());
			if(null != jobKey){
				Scheduler scheduler = gSchedulerFactory.getScheduler();
				scheduler.triggerJob(jobKey);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new QuartzException(e);  
		}
	}
	
	/**
	 * 启动所有任务
	 */
	public static void startAll(){
		try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  
            sched.start();  
        } catch (SchedulerException e) {  
            e.printStackTrace();  
            throw new QuartzException(e);  
        } 
	}
	
	/**
	 * 关闭所有任务
	 */
	public static void shutdownAll(){
		 try {
			Scheduler sched = gSchedulerFactory.getScheduler(); 
			 if(!sched.isShutdown()) {  
			     sched.shutdown();  
			 }
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new QuartzException(e);  
		} 
	}
}