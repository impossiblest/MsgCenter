package com.baidu.ee.msg.server;

import com.baidu.ee.msg.IMsg;
/**
 * Sms消息服务器
 * @author Jord
 *
 */
public class SmsMsgServer extends BaseMsgServer implements IMsgServer {
	

	@Override
	public boolean sendMsg(IMsg msg) throws InterruptedException {
		//1.入列
		queue.put(msg);
		//2.启动线程,处理消息验证限流要求、发送操作
		startThreadByMsgType(msg);
		return true;
	}

}
