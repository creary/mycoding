package com.yf.util.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 * 
 * @author
 * 
 */
public class IpUtil {

	/**
	 * @param request
	 *            IP
	 * @return IP Address
	 */
	public static String getIpAddrByRequest(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 
		创建时间：   2014年12月30日
		创建人： liting 
	 * getAddress(返回类型是 InetAddress 调用InetAddress.getHostAddress()返回string 否则返回的格式前会有一个/  如：/192.168.1.10
	 * 如果返回的是String 就可以了 192.168.1.10
	 *  )    
	 * @param   name    
	 * @Exception 异常对象    
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
    public static  InetAddress getAddress() {
        try {
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements();) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                if (addresses.hasMoreElements()) {
                    return addresses.nextElement();
                }
            }
        } catch (SocketException e) {
        }
        return null;
    }
	/**
	 * @return 本机IP 这个貌似有虚拟ip时候（装过vmvare虚拟机等）会获取虚拟机的ip  需要关闭虚拟机的虚拟网卡才行 ：【【【 建议getAddress()方法】】
	 * @throws SocketException
	 */
	public static String getRealIp() throws SocketException {
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP

		Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress ip = null;
		boolean finded = false;// 是否找到外网IP
		while (netInterfaces.hasMoreElements() && !finded) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
					netip = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					localip = ip.getHostAddress();
				}
			}
		}
		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}

}
