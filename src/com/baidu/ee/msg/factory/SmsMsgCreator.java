package com.baidu.ee.msg.factory;

import com.baidu.ee.msg.IMsg;
import com.baidu.ee.msg.SmsMsg;
import com.baidu.ee.msg.vo.MsgVo;

/**
 * 创建Sms的工厂
 * @author Jord
 *
 */
public class SmsMsgCreator implements IMsgFactory {
	private static SmsMsgCreator singleton;
	private SmsMsgCreator(){}
	public static SmsMsgCreator getInstance(){
		if(singleton==null){
			synchronized(SmsMsgCreator.class){
				if(singleton==null){
					singleton = new SmsMsgCreator();
				}
			}
		}
		return singleton;
	}
	@Override
	public IMsg create(MsgVo vo) {
		return new SmsMsg(vo);
	}



}
