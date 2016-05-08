package com.baidu.ee.msg.factory;

import com.baidu.ee.msg.server.EmailMsgServer;
import com.baidu.ee.msg.server.IMsgServer;

/**
 * 消息服务器简单工厂
 * @author Jord
 *
 */
public class ServerFactory {
	private static String DEFAULT_PACKAGE = "com.baidu.ee.msg.server.";

	public static IMsgServer getInstance(String name){
		IMsgServer server = null;
		try {
			server = (IMsgServer) Class.forName(DEFAULT_PACKAGE+name+"Server").newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			server = new EmailMsgServer();
		}
		return server;
	}
}
