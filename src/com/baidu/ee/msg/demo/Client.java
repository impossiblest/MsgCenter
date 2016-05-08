package com.baidu.ee.msg.demo;

import com.baidu.ee.msg.IMsg;
import com.baidu.ee.msg.center.MsgCenter;
import com.baidu.ee.msg.factory.MsgFactory;
import com.baidu.ee.msg.vo.MsgVo;

/**
 * ���Կͻ���ʵ��demo<br>
 * ���������Ϣ����,������Ϣ����ģ��ָ�ɸ���Ӧ�ķ�����
 * @author Jord
 *
 */
public class Client {
	
	public static void main(String[] args) throws InterruptedException {
		int msgSum = 20;
		MsgVo vo = null;
		String[] msgType = {"EmailMsg","HiMsg","SmsMsg"};
		for(int i=1;i<=msgSum;i++){
			vo = new MsgVo("Jord_"+i, "Nice to meet u. I have met u "+i+" times.How U doing...", "Susan_"+i);
			int index = (int) (Math.random()*10000)%msgType.length;
			IMsg msg = MsgFactory.getInstance(msgType[index], vo);
			MsgCenter.getInstance().send(msg);
		}
	}
}
