package com.baidu.ee.msg.server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.baidu.ee.msg.IMsg;

/**
 * Hi消息服务器
 * @author Jord
 *
 */
public class HiMsgServer extends BaseMsgServer implements IMsgServer {
	
	/**
	 * 处理消息发送的线程
	 */
	private static Thread th ;
	/**
	 * 接收消息队列
	 */	
	private static BlockingQueue<IMsg> queue = new LinkedBlockingQueue<IMsg>();
	
	static{
		//2.check
		//3.出列--发消息
		th = new Thread(new QueueThread(queue));
		th.setPriority(Thread.MIN_PRIORITY);
		th.start();
	}
	
	@Override
	public boolean sendMsg(IMsg msg) throws InterruptedException {
		//1.入列
		queue.put(msg);
		return true;
	}

}
