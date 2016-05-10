package com.baidu.ee.msg.demo;

import com.baidu.ee.msg.IMsg;
import com.baidu.ee.msg.center.MsgCenter;
import com.baidu.ee.msg.factory.MsgFactory;
import com.baidu.ee.msg.vo.MsgVo;

/**
 * 测试客户端实例demo<br>
 * 随机生成消息类型,调用消息中心模块指派给相应的服务器<br>
 * <p>例子说明(参数设置：200,20/min)<p>
 * 1.随机生成200条不同类型的消息;(根据需要设置)<br>
 * 2.调用消息中心模块,指派给相应的服务器处理(默认限流为：20条/min,根据需要设置);
 * @author Jord
 *
 */
public class Client {
	
	public static void main(String[] args) throws InterruptedException {
		int msgSum = 200;
		int sendNumPerMin = 20;
		MsgVo vo = null;
		String[] msgType = {"EmailMsg","HiMsg","SmsMsg"};
		for(int i=1;i<=msgSum;i++){
			vo = new MsgVo("Jord_"+i, "Nice to meet u. I have met u "+i+" times.How U doing...", "Susan_"+i);
			int index = (int) (Math.random()*10000)%msgType.length;
			IMsg msg = MsgFactory.getInstance(msgType[index], vo);
			MsgCenter msgCenter = MsgCenter.getInstance();
			msgCenter.setSendNumPerMin(sendNumPerMin);//默认各个服务器的处理能力为: 20/min;根据需要设置
			msgCenter.send(msg);
		}
	}
}
