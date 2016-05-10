package com.baidu.ee.msg.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.baidu.ee.msg.server.EmailMsgServer;
import com.baidu.ee.msg.server.IMsgServer;

/**
 * 消息服务器<br>
 * 简单工厂,登记实现单例
 * @author Jord
 *
 */
public class ServerFactory {
	private static String DEFAULT_PACKAGE = "com.baidu.ee.msg.server.";
	private static Map<String,IMsgServer> serverMap = new ConcurrentHashMap<>();
	/**
	 * 获取消息服务器的单例
	 * @param name
	 * @return
	 */
	public static IMsgServer getInstance(String name){
		IMsgServer server = serverMap.get(name);
		if(server==null){
			try {
				server = (IMsgServer) Class.forName(DEFAULT_PACKAGE+name+"Server").newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				server = new EmailMsgServer();
			}finally{
				serverMap.put(name, server);
			}
		}
		
		return server;
	}
}
