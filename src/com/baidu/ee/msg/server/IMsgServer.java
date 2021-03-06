package com.baidu.ee.msg.server;

import com.baidu.ee.msg.IMsg;
/**
 * 消息服务器接口
 * @author Jord
 *
 */
public interface IMsgServer {
	/**
	 * 服务器发送消息接口<br>
	 * 在此自定义消息进入消息队列Queue操作
	 * @param msg
	 * @return
	 * @throws InterruptedException
	 */
	public boolean sendMsg(IMsg msg) throws InterruptedException;
	
	/**
	 * 设置每分钟处理消息数量,默认为30/min
	 * @param m
	 */
	public void setM(int m);
}
