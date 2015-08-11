package com.yf.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJob implements Job {

   private Logger log = LoggerFactory.getLogger(TestJob.class);

   public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
      log.debug("TestJob run successfully...");
   }

}