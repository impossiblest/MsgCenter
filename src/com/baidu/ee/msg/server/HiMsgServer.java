package com.baidu.ee.msg.server;

import com.baidu.ee.msg.IMsg;

/**
 * Hi消息服务器
 * @author Jord
 *
 */
public class HiMsgServer extends BaseMsgServer implements IMsgServer {
	
	@Override
	public boolean sendMsg(IMsg msg) throws InterruptedException {
		//1.入列
		queue.put(msg);
		//2.check
		//3.出列--发消息
		startThread(msg);
		return true;
	}

}
