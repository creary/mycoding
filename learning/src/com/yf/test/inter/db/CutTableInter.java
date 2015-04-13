package com.yf.test.inter.db;

public interface CutTableInter {
	/**
	 * 判断表是否存在数据库中：存在就是true 否则返回false
	 * @return
	 */
	public boolean isHere();
	/**
	 * 分表
	 */
	public void cutTab();
	
	public void cutTab(String sql);
	/**
	 * 重命名表：
	 */
	public void renameTab();
	/**
	 * 重命名表根据传入的SQL语句
	 * @param sql
	 */
	public void renameTab(String sql);
	
	/**
	 * 分表规则
	 * @param s
	 * @return
	 */
	public String replaTab(String s);
	

}
