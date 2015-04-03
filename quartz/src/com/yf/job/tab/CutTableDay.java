package com.yf.job.tab;

import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yf.db.CutTable;
import com.yf.util.DBUtil;
import com.yf.util.DateUtil;
import com.yf.util.ResourcesUtil;

public class CutTableDay implements Job{
/**
 * quartz定时器中需要执行的任务   
 */
	 Logger log = LoggerFactory.getLogger(CutTableDay.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		log.info("执行调度任务");
		log.info("分表日期：day :"+DateUtil.getCurrentTabDay().trim());
		log.info("表名："+(ResourcesUtil.getVbyKey("tabname")+DateUtil.getCurrentTabDay()).trim());
		if(!CutTable.isTabHere(ResourcesUtil.getVbyKey("dbname"), (ResourcesUtil.getVbyKey("tabname")+DateUtil.getCurrentTabDay()).trim())){
			log.info("条件满足正在切分表");
			
		}else{
			log.info(ResourcesUtil.getVbyKey("dbname")+"数据库已经存在了"+ResourcesUtil.getVbyKey("tabname")+DateUtil.getCurrentTabDay()+"表");
		}
		
		
	}
}

