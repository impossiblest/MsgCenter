package com.baidu.ee.msg.server;

import com.baidu.ee.msg.IMsg;

/**
 * Hi��Ϣ������
 * @author Jord
 *
 */
public class HiMsgServer extends BaseMsgServer implements IMsgServer {
	
	@Override
	public boolean sendMsg(IMsg msg) throws InterruptedException {
		//1.����
		queue.put(msg);
		//2.�����߳�,������Ϣ��֤����Ҫ�󡢷��Ͳ���
		startThreadByMsgType(msg);
		return true;
	}

}
