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
	 * 在此自定义消息进入消息队列Queue操作<br>
	 * 以及启动线程,处理消息验证限流要求、发送操作
	 * @param msg
	 * @return
	 * @throws InterruptedException
	 */
	public boolean sendMsg(IMsg msg) throws InterruptedException;
	
	/**
	 * 设置每分钟处理消息数量,默认为20/min
	 * @param sendNumPerMin
	 */
	public void setSendNumPerMin(int sendNumPerMin);
}
