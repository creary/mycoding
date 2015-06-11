package com.yf.util.quartz;

import org.unique.support.Support;

/**
 * 任务调度增强
 * @author biezhi
 * @version 1.0
 */
public class QuartzSupport extends Support {

	@Override
	public void startup() {
		this.status = 1;
	}

	@Override
	public void shutdown() {
		this.status = 0;
	}
}
