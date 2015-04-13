package com.yf.test.impl.user;

import java.util.HashMap;
import java.util.Map;

public class SingleOnline {
	/**
	 * 简单（同一用户挤掉线功能---后面登录的挤掉前面的用户）
	 * 2.  在每次登录时将帐号和SessionId放入HashMap
	 *  SingleOnline.addUser(user.getUserCode(), request.getSession().getId());
	 *  
	 *  3.在检测登录的Filter中验证当前SessionId是否有效，若无效，则将当前用户踢下线
	 *  if (!SingleOnline.isValidUser(user.getUserCode(), session.getId())) {
     *	session.invalidate();
		}	
	 * 
	 */
	private static Map<String, String> mapOnline = new HashMap<String, String>();
    /**
     * 将用户添加到在线列表
     * @param userCode
     * @param sessionId
     */
    public static synchronized void addUser(String userCode, String sessionId) {
        if (mapOnline.containsKey(userCode))
            mapOnline.remove(userCode);
        
        mapOnline.put(userCode, sessionId);
    }

    /**
     * 是否为合法用户
     * @param userCode
     * @param sessionId
     * @return
     */
    public static boolean isValidUser(String userCode, String sessionId) {
        if (!mapOnline.containsKey(userCode))
            return false;
        
        if (!mapOnline.get(userCode).equals(sessionId))
            return false;

        return true;
    }
    
}