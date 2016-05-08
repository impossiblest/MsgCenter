package com.baidu.ee.msg.server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.baidu.ee.msg.IMsg;

/**
 * Hi��Ϣ������
 * @author Jord
 *
 */
public class HiMsgServer extends BaseMsgServer implements IMsgServer {
	
	/**
	 * ������Ϣ���͵��߳�
	 */
	private static Thread th ;
	/**
	 * ������Ϣ����
	 */	
	private static BlockingQueue<IMsg> queue = new LinkedBlockingQueue<IMsg>();
	
	static{
		//2.check
		//3.����--����Ϣ
		th = new Thread(new QueueThread(queue));
		th.setPriority(Thread.MIN_PRIORITY);
		th.start();
	}
	
	@Override
	public boolean sendMsg(IMsg msg) throws InterruptedException {
		//1.����
		queue.put(msg);
		return true;
	}

}
