package com.baidu.ee.msg.server;

import com.baidu.ee.msg.IMsg;

public class SmsMsgServer extends BaseMsgServer implements IMsgServer {
	

	@Override
	public boolean sendMsg(IMsg msg) throws InterruptedException {
		//1.����
		queue.put(msg);
		//2.check
		//3.����--����Ϣ
		startThread(msg);
		return true;
	}

}
